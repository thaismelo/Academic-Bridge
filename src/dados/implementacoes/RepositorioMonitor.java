/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.implementacoes;

import dados.DAO_SQLite;
import exceptions.banco.ExceptionErroNoBanco;
import dados.RepositorioGenerico;
import exceptions.banco.DadoInexistenteException;
import exceptions.banco.DadoNuloException;
import exceptions.entidades.Login.SenhaInvalidaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.modelo.Aluno;
import negocio.modelo.Frequencia;
import negocio.modelo.Login;
import negocio.modelo.Monitor;
import negocio.modelo.Professor;
import negocio.modelo.Tarefa;

/**
 *
 * @author thais
 */
public class RepositorioMonitor implements RepositorioGenerico<Monitor> {

    @Override
    public void inserir(Monitor t) throws ExceptionErroNoBanco {
        try {

            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "INSERT INTO Monitor (codLogin,codProf,nome,email,validade) VALUES(?,?,?,?,0)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, t.getLogin().getId());
            pstmt.setInt(2, t.getProf().getId());
            pstmt.setString(3, t.getNome());
            pstmt.setString(4, t.getEmail());
            pstmt.executeUpdate();

            ResultSet resultSet = null;
            PreparedStatement preparedStatement = null;
            sql = "SELECT * FROM Monitor WHERE idMonitor = (select MAX(idMonitor) from Monitor);";
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                t.setId(resultSet.getInt("idMonitor"));
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }

    }

    @Override
    public void excluir(Monitor t) throws ExceptionErroNoBanco {
        try {
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            this.excluirDependentes(t);
            String sql = "UPDATE Monitor SET validade = 1 WHERE idMonitor = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, t.getId());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        } catch (SenhaInvalidaException | DadoInexistenteException ex) {
            Logger.getLogger(RepositorioMonitor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void excluirDependentes(Monitor m)throws ExceptionErroNoBanco, SenhaInvalidaException, DadoInexistenteException{
        try{
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            ResultSet rs = null;
            //Login 
            String sql = "SELECT * FROM Login WHERE idLogin = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, m.getLogin().getId());
            rs = pstmt.executeQuery();
            while(rs.next()){
                Login log = new CRUDLogin().recuperarLogin(rs.getInt("idLogin"));
                new CRUDLogin().removerLogin(log);
            }
            //Turma
            sql = "SELECT * FROM Turma WHERE codMonitor = ? AND validade = 0";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, m.getId());
            rs = pstmt.executeQuery();
            while(rs.next()){
                Aluno al = new CRUDAluno().recuperarAluno(rs.getInt("idTurma"));
                new CRUDAluno().removerAluno(al);
            }
            //Frequencia
            sql = "SELECT * FROM Frequencia WHERE codMonitor = ? AND validade = 0";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, m.getId());
            rs = pstmt.executeQuery();
            while(rs.next()){
                Frequencia fr = new CRUDFrequencia().recuperarFrequencia(rs.getInt("idFrequencia"));
                new CRUDFrequencia().removerFrequencia(fr);
            }
            //Tarefa
            sql = "SELECT * FROM Tarefa WHERE idCriador = ? AND tipoCriador = 2 AND validade = 0";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, m.getId());
            rs = pstmt.executeQuery();
            while(rs.next()){
                Tarefa t = new CRUDTarefa().recuperarTarefa(rs.getInt("idTarefa"));
                new CRUDTarefa().removerTarefa(t);
            }
            rs.close();
            pstmt.close();
        }catch(SQLException ex){
            throw new ExceptionErroNoBanco(ex.getMessage());
        } catch (DadoNuloException ex) {
            Logger.getLogger(RepositorioMonitor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
        public void alterar(Monitor t) throws ExceptionErroNoBanco {
        try {
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "UPDATE Monitor SET nome = ?, email = ? WHERE idMonitor = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, t.getNome());
            pstmt.setString(2, t.getEmail());
            pstmt.setInt(3, t.getId());
            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }    
}

    @Override
        public Monitor recuperar(int codigo) throws ExceptionErroNoBanco {
        try {
            ResultSet resultSet = null;
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "SELECT * FROM Monitor m join Login l on (m.codLogin=l.idLogin) join Professor p on (m.codProf=p.idProf) WHERE idMonitor = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, codigo);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Login login = new Login();
                Professor prof = new Professor();
                Monitor monitor = new Monitor();
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
                return monitor;
            }
            resultSet.close();
            pstmt.close();

        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
        return null;    
    
    }

    @Override
        public List<Monitor> recuperarTodos() throws ExceptionErroNoBanco {
        try {
            ResultSet resultSet = null;
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "SELECT * FROM Monitor m join Login l on (m.codLogin=l.idLogin) join Professor p on (m.codProf=p.idProf) WHERE m.validade = 0";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            resultSet = pstmt.executeQuery();
            List<Monitor> listaMonitor= new ArrayList<>();
            while (resultSet.next()) {
                Login login = new Login();
                Professor prof = new Professor();
                Monitor monitor = new Monitor();
                login.setId(resultSet.getInt("idLogin"));
                login.setLogin(resultSet.getString("login"));
                login.setSenha(resultSet.getString("senha"));
                prof.setId(resultSet.getInt("idProf"));
                prof.setEmail(resultSet.getString("email"));
                prof.setNome(resultSet.getString("nome"));
                prof.setIdDisc(resultSet.getInt("codDisc"));
                monitor.setId(resultSet.getInt("idMonitor"));
                monitor.setEmail(resultSet.getString("email"));
                monitor.setNome(resultSet.getString("nome"));
                monitor.setLogin(login);
                monitor.setProf(prof);
                listaMonitor.add(monitor);
            }
            resultSet.close();
            pstmt.close();
            return listaMonitor;
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }    }

    @Override
    public int recuperaUltimoID() throws ExceptionErroNoBanco {
        int id = 0;
        try {
            ResultSet rs = null;
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String recuperarUltimoIdSql = "SELECT * FROM Monitor WHERE idMonitor= (SELECT MAX(idMonitor) FROM Monitor);";
            PreparedStatement pstmt = conn.prepareStatement(recuperarUltimoIdSql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                id = (rs.getInt("idMonitor"));
            }
            rs.close();
            pstmt.close();
            return id;
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
    }
    
}
