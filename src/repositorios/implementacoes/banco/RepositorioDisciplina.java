/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorios.implementacoes.banco;

import dao.DAO_SQLite;
import exceptions.banco.ExceptionErroNoBanco;
import irepositorios.interfaces.RepositorioGenerico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entidades.Disciplina;
import entidades.Login;
import entidades.Monitor;
import entidades.Professor;
import exceptions.banco.DadoInexistenteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import repositorios.implementacoes.CRUDDisciplina;
import repositorios.implementacoes.CRUDMonitor;
import repositorios.implementacoes.CRUDProfessor;

/**
 *
 * @author thais
 */
public class RepositorioDisciplina implements RepositorioGenerico<Disciplina>{

    @Override
    public Disciplina recuperar(int codigo) throws ExceptionErroNoBanco {
        try {
            ResultSet resultSet = null;
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "SELECT * FROM Disciplina d join Professor p on (d.codProf=p.idProf) WHERE idDisc = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, codigo);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Login login = new Login();
                Professor prof = new Professor();
                Disciplina d = new Disciplina();
                login.setId(resultSet.getInt("idLogin"));
                login.setLogin(resultSet.getString("login"));
                login.setSenha(resultSet.getString("senha"));
                login.setTipo(resultSet.getInt("tipo"));
                prof.setId(resultSet.getInt("idProf"));
                prof.setEmail(resultSet.getString("email"));
                prof.setNome(resultSet.getString("nome"));
                prof.setLogin(login);
                d.setId(resultSet.getInt("idDisc"));
                d.setNome(resultSet.getString("nome"));
                d.setCurso(resultSet.getString("curso"));
                d.setProfessor(prof);
                return d;
            }
            resultSet.close();
            pstmt.close();

        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        } 
        return null;
    }

    @Override
    public List<Disciplina> recuperarTodos() throws ExceptionErroNoBanco {
        try {
            ResultSet resultSet = null;
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "SELECT * FROM Disciplina d join Professor p on (d.codProf=p.idProf) WHERE d.validade=0;";
            Statement stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sql);

            List<Disciplina> listaDisc = new ArrayList<>();
            while (resultSet.next()) {
                Login login = new Login();
                Professor prof = new Professor();
                Disciplina d = new Disciplina();
                login.setId(resultSet.getInt("idLogin"));
                login.setLogin(resultSet.getString("login"));
                login.setSenha(resultSet.getString("senha"));
                login.setTipo(resultSet.getInt("tipo"));
                prof.setId(resultSet.getInt("idProf"));
                prof.setEmail(resultSet.getString("email"));
                prof.setNome(resultSet.getString("nome"));
                prof.setLogin(login);
                d.setId(resultSet.getInt("idDisc"));
                d.setNome(resultSet.getString("nome"));
                d.setCurso(resultSet.getString("curso"));
                d.setProfessor(prof);
                listaDisc.add(d);
            
            }
            resultSet.close();
            stmt.close();
            return listaDisc;
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
    }

    @Override
    public void inserir(Disciplina t) throws ExceptionErroNoBanco {
              try {
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "INSERT INTO Disciplina (nome,curso,codProf,validade) VALUES(?,?,?,0)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, t.getNome());
            pstmt.setString(2, t.getCurso());
            pstmt.setInt(3, t.getProfessor().getId());
            pstmt.executeUpdate();
            ResultSet resultSet = null;
            PreparedStatement preparedStatement = null;
            sql = "SELECT * FROM Disciplina WHERE idDisc = (select MAX(idDisc) from Disciplina);";
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                t.setId(resultSet.getInt("idDisc"));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
    
    }
    
    public List<Disciplina> recuperarTodosPorProf(int codProf) throws ExceptionErroNoBanco, DadoInexistenteException {
        try {
            ResultSet resultSet = null;
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "SELECT * FROM Disciplina WHERE codProf = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, codProf);
            resultSet = pstmt.executeQuery();
            List<Disciplina> listaDisc= new ArrayList<>();
            while (resultSet.next()) {
                Disciplina d = new Disciplina(resultSet.getInt("codProf"), resultSet.getString("nome"),
                        resultSet.getString("curso"),new CRUDProfessor().recuperarProfessor(resultSet.getInt("codProf")));
                d.setId(resultSet.getInt("idDisc"));
                listaDisc.add(d);
            }
            resultSet.close();
            pstmt.close();
            return listaDisc;
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
    }

    @Override
    public void excluir(Disciplina t) throws ExceptionErroNoBanco {
        try {
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            this.excluirDependentes(t);
            String sql = "UPDATE Disciplina SET validade = 1 WHERE idDisc = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, t.getId());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        } catch (DadoInexistenteException ex) {
            Logger.getLogger(RepositorioDisciplina.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void excluirDependentes(Disciplina t) throws ExceptionErroNoBanco, DadoInexistenteException {
        try {
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            ResultSet rs = null;
            //Monitor
            String sql = "SELECT * FROM Monitor WHERE codDisciplina = ? AND validade = 0";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, t.getId());
            rs = pstmt.executeQuery();
            while(rs.next()){
                Monitor moni = new CRUDMonitor().recuperarMonitor(rs.getInt("idMonitor"));
                new CRUDMonitor().removerMonitor(moni);
            }
            pstmt.close();
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
    }

    @Override
    public void alterar(Disciplina t) throws ExceptionErroNoBanco {
        try {
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "UPDATE Disciplina SET nome = ?, curso = ? WHERE idDisc = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, t.getNome());
            pstmt.setString(2, t.getCurso());
            pstmt.setInt(3, t.getId());
            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
    }

    @Override
    public int recuperaUltimoID() throws ExceptionErroNoBanco {
        int id = 0;
        try {
            ResultSet rs = null;
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String recuperarUltimoIdSql = "SELECT * FROM Disciplina WHERE idDisc= (SELECT MAX(idDisc) FROM Disciplina);";
            PreparedStatement pstmt = conn.prepareStatement(recuperarUltimoIdSql);
            rs = pstmt.executeQuery();
            if (rs != null) {
                if (rs.next()) {
                    id = (rs.getInt("idDisc"));
                }
            }
            rs.close();
            pstmt.close();
            return id;
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
    }

}
