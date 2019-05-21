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
import negocio.modelo.BacklogMonitor;
import negocio.modelo.Login;
import negocio.modelo.Monitor;

/**
 *
 * @author thais
 */
public class RepositorioMonitor implements RepositorioGenerico<Monitor> {

    @Override
    public void inserir(Monitor t) throws ExceptionErroNoBanco {
        try {

            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "INSERT INTO Monitor (idLogin,idProf,nome,email,validade) VALUES(?,?,?,?,0)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, t.getIdLogin());
            pstmt.setInt(2, t.getIdProf());
            pstmt.setString(3, t.getNome());
            pstmt.setString(4, t.getEmail());
            pstmt.executeUpdate();

            ResultSet resultSet = null;
            PreparedStatement preparedStatement = null;
            sql = "SELECT * FROM Monitor WHERE id = (select MAX(ID) from Monitor);";
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                t.setId(resultSet.getInt("id"));
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
            this.excluirDependentes(t.getId());
            String sql = "UPDATE Monitor SET validade = 1 WHERE id = ?";
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

    public void excluirDependentes(int id)throws ExceptionErroNoBanco, SenhaInvalidaException, DadoInexistenteException{
        try{
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            ResultSet rs = null;
            //Login 
            String sql = "SELECT * FROM Login WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            Login log = new Login(rs.getInt("id"),rs.getInt("tipo"),rs.getString("login"),rs.getString("senha"));
            new CRUDLogin().removerLogin(log);
            //Backlog
            sql = "SELECT * FROM BacklogMonitor WHERE idMonitor = ? AND validade = 0";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            while(rs.next()){
                BacklogMonitor b = new BacklogMonitor(rs.getInt("id"),rs.getInt("idTarefa"),rs.getInt("idMonitor"));
                new CRUDBacklogMonitor().removerBacklogMonitor(b);
            }
            //Turma
            sql = "SELECT * FROM Turma WHERE idMonitor = ? AND validade = 0";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            while(rs.next()){
                Aluno al = new Aluno(rs.getInt("id"),rs.getInt("idMonitor"),rs.getString("nome"),rs.getString("email"));
                new CRUDAluno().removerAluno(al);
            }
            //Fechando
            rs.close();
            pstmt.close();
        }catch(SQLException ex){
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
    }


    @Override
        public void alterar(Monitor t) throws ExceptionErroNoBanco {
        try {
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "UPDATE Monitor SET nome = ?, email = ? WHERE id = ?";
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
            String sql = "SELECT * FROM Monitor WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, codigo);
            resultSet = pstmt.executeQuery();
            Monitor monitor = null;
            while (resultSet.next()) {
                monitor = new Monitor();
                monitor.setId(resultSet.getInt("id"));
                monitor.setEmail(resultSet.getString("email"));
                monitor.setNome(resultSet.getString("nome"));
                monitor.setIdLogin(resultSet.getInt("idLogin"));
                monitor.setIdProf(resultSet.getInt("idProf"));
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
            String sql = "SELECT * FROM Monitor;";
            Statement stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sql);
            List<Monitor> listaMonitor= new ArrayList<>();
            Monitor monitor = null;
            while (resultSet.next()) {
                monitor = new Monitor();
                monitor.setId(resultSet.getInt("id"));
                monitor.setEmail(resultSet.getString("email"));
                monitor.setNome(resultSet.getString("nome"));
                monitor.setIdLogin(resultSet.getInt("idLogin"));
                monitor.setIdProf(resultSet.getInt("idProf"));
                listaMonitor.add(monitor);
            }
            resultSet.close();
            stmt.close();
            return listaMonitor;
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }    }
    
}
