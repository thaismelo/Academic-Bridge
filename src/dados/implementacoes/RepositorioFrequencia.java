/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.implementacoes;

import dados.DAO_SQLite;
import exceptions.banco.ExceptionErroNoBanco;
import dados.RepositorioGenerico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import negocio.modelo.Aluno;
import negocio.modelo.Frequencia;
import negocio.modelo.Login;
import negocio.modelo.Monitor;
import negocio.modelo.Professor;

/**
 *
 * @author thais
 */
public class RepositorioFrequencia implements RepositorioGenerico<Frequencia>{

    @Override
    public void inserir(Frequencia t) throws ExceptionErroNoBanco {
       try {
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "INSERT INTO Frequencia (frequencia,codTurma,codMonitor,validade) VALUES(?,?,?,0)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, t.getFrequencia());
            pstmt.setInt(2, t.getAluno().getId());
            pstmt.setInt(3, t.getMonitor().getId());
            pstmt.executeUpdate();
            ResultSet resultSet = null;
            PreparedStatement preparedStatement = null;
            sql = "SELECT * FROM Frequencia WHERE idFrequencia = (select MAX(idFrequencia) from Frequencia);";
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                t.setId(resultSet.getInt("idFrequencia"));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }   
    
    }
    

    @Override
    public void excluir(Frequencia t) throws ExceptionErroNoBanco {
       try {
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "UPDATE Frequencia SET validade = 1 WHERE idFrequencia = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, t.getId());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }  
    }

    @Override
    public void alterar(Frequencia t) throws ExceptionErroNoBanco {
        try {
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "UPDATE Frequencia SET frequencia = ? WHERE idFrequencia = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, t.getFrequencia());
            pstmt.setInt(2, t.getId());
            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
    }

    @Override
    public Frequencia recuperar(int codigo) throws ExceptionErroNoBanco {
        try {
            ResultSet resultSet = null;
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "SELECT * FROM Frequencia f join Turma t on (f.codTurma=t.idTurma) join Monitor m on (f.codMonitor=m.idMonitor) WHERE idFrequencia = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, codigo);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Frequencia f = new Frequencia();
                Aluno a = new Aluno();
                Monitor monitor = new Monitor();
                Login login = new Login();
                Professor prof = new Professor();
                
                login.setId(resultSet.getInt("idLogin"));
                login.setLogin(resultSet.getString("login"));
                login.setSenha(resultSet.getString("senha"));
                login.setTipo(resultSet.getInt("tipo"));
                prof.setId(resultSet.getInt("idProf"));
                prof.setEmail(resultSet.getString("email"));
                prof.setNome(resultSet.getString("nome"));
                prof.setIdDisc(resultSet.getInt("codDisc"));
                prof.setLogin(login);
                monitor.setId(resultSet.getInt("idMonitor"));
                monitor.setEmail(resultSet.getString("email"));
                monitor.setNome(resultSet.getString("nome"));
                monitor.setLogin(login);
                monitor.setProf(prof);
                a.setId(resultSet.getInt("idTurma"));
                a.setEmail(resultSet.getString("email"));
                a.setNome(resultSet.getString("nome"));
                a.setMonitor(monitor);
                f.setId(resultSet.getInt("idFrequencia"));
                f.setAluno(a);
                f.setMonitor(monitor);
                f.setFrequencia(resultSet.getInt("frequencia"));
                return f;
            }
            resultSet.close();
            pstmt.close();

        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
        return null;    
    }

    @Override
    public List<Frequencia> recuperarTodos() throws ExceptionErroNoBanco {
        try {
            ResultSet resultSet = null;
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "SELECT * FROM Frequencia f join Turma t on (f.codTurma=t.idTurma) join Monitor m on (f.codMonitor=m.idMonitor);";
            Statement stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sql);
            List<Frequencia> listaFrequencia= new ArrayList<>();
            while (resultSet.next()) {
                Frequencia f = new Frequencia();
                Aluno a = new Aluno();
                Monitor monitor = new Monitor();
                Login login = new Login();
                Professor prof = new Professor();
                
                login.setId(resultSet.getInt("idLogin"));
                login.setLogin(resultSet.getString("login"));
                login.setSenha(resultSet.getString("senha"));
                login.setTipo(resultSet.getInt("tipo"));
                prof.setId(resultSet.getInt("idProf"));
                prof.setEmail(resultSet.getString("email"));
                prof.setNome(resultSet.getString("nome"));
                prof.setIdDisc(resultSet.getInt("codDisc"));
                prof.setLogin(login);
                monitor.setId(resultSet.getInt("idMonitor"));
                monitor.setEmail(resultSet.getString("email"));
                monitor.setNome(resultSet.getString("nome"));
                monitor.setLogin(login);
                monitor.setProf(prof);
                a.setId(resultSet.getInt("idTurma"));
                a.setEmail(resultSet.getString("email"));
                a.setNome(resultSet.getString("nome"));
                a.setMonitor(monitor);
                f.setId(resultSet.getInt("idFrequencia"));
                f.setAluno(a);
                f.setMonitor(monitor);
                f.setFrequencia(resultSet.getInt("frequencia"));
                listaFrequencia.add(f);
            }
            resultSet.close();
            stmt.close();
            return listaFrequencia;
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }    }

    @Override
    public int recuperaUltimoID() throws ExceptionErroNoBanco {
        int id = 0;
        try {
            ResultSet rs = null;
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String recuperarUltimoIdSql = "SELECT * FROM Frequencia WHERE idFrequencia= (SELECT MAX(idFrequencia) FROM Frequencia);";
            PreparedStatement pstmt = conn.prepareStatement(recuperarUltimoIdSql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                id = (rs.getInt("idFrequencia"));
            }
            rs.close();
            pstmt.close();
            return id;
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
    }
    
    
    
}
