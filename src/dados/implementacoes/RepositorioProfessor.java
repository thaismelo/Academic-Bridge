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
import negocio.modelo.Login;
import negocio.modelo.Monitor;
import negocio.modelo.Professor;


/**
 *
 * @author thais
 */
public class RepositorioProfessor implements RepositorioGenerico<Professor>{

    @Override
    public void inserir(Professor professor) throws ExceptionErroNoBanco {
        try {
            
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "INSERT INTO Professor (codLogin,codDisc,nome,email,validade) VALUES(?,?,?,?,0)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1,professor.getLogin().getId());
            pstmt.setInt(2,professor.getIdDisc());
            pstmt.setString(3, professor.getNome());
            pstmt.setString(4, professor.getEmail());
            pstmt.executeUpdate();
            
            ResultSet resultSet = null;
            PreparedStatement preparedStatement = null;
            sql = "SELECT * FROM Professor WHERE idProf = (select MAX(idProf) from Professor);";
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                professor.setId(resultSet.getInt("idProf"));
            }
            
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
    }

    @Override
    public void excluir(Professor t) throws ExceptionErroNoBanco {
        try {
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            this.excluirDependentes(t.getId());
            String sql = "UPDATE Professor SET validade = 1 WHERE idProf = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, t.getId());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        } catch (SenhaInvalidaException | DadoInexistenteException ex) {
            Logger.getLogger(RepositorioProfessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void excluirDependentes(int id)throws ExceptionErroNoBanco, SenhaInvalidaException, DadoInexistenteException{
        try{
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            ResultSet rs = null;
            //Login 
            String sql = "SELECT * FROM Login WHERE idLogin = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            Login log = new Login(rs.getInt("id"),rs.getInt("tipo"),rs.getString("login"),rs.getString("senha"));
            new CRUDLogin().removerLogin(log);
            //Monitor
            sql = "SELECT * FROM Monitor WHERE codProf = ? AND validade = 0";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            //while(rs.next()){
                //Monitor moni = new Monitor(rs.getInt("id"),rs.getInt("idLogin"),rs.getInt("idProf"),rs.getString("nome"),rs.getString("email"));
               // new CRUDMonitor().removerMonitor(moni);
            //}
            //Fechando
            rs.close();
            pstmt.close();
        }catch(SQLException ex){
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
    }

    @Override
    public void alterar(Professor t) throws ExceptionErroNoBanco {
        try {
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "UPDATE Professor SET nome = ?, email = ? WHERE idProf= ?";
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
    public Professor recuperar(int codigo) throws ExceptionErroNoBanco {
        try {
            ResultSet resultSet = null;
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "SELECT * FROM Professor p join Login l on p.codLogin=l.idLogin WHERE idProf = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, codigo);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Professor prof = new Professor();
                Login login = new Login();
                login.setId(resultSet.getInt("idLogin"));
                login.setLogin(resultSet.getString("login"));
                login.setSenha(resultSet.getString("senha"));
                login.setTipo(resultSet.getInt("tipo"));
                prof.setId(resultSet.getInt("idProf"));
                prof.setEmail(resultSet.getString("email"));
                prof.setNome(resultSet.getString("nome"));
                prof.setIdDisc(resultSet.getInt("codDisc"));
                prof.setLogin(login);
                return prof;
            }
            resultSet.close();
            pstmt.close();
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
        return null;    }

    @Override
    public List<Professor> recuperarTodos() throws ExceptionErroNoBanco {
        try {
            ResultSet resultSet = null;
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "SELECT * FROM Professor p join Login l on p.codLogin=l.idLogin WHERE p.validade = 0;";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            resultSet = pstmt.executeQuery();
            List<Professor> listaProf= new ArrayList<>();
            while (resultSet.next()) {
                Professor prof = new Professor();
                Login login = new Login();
                login.setId(resultSet.getInt("idLogin"));
                login.setLogin(resultSet.getString("login"));
                login.setSenha(resultSet.getString("senha"));
                prof.setId(resultSet.getInt("idProf"));
                prof.setEmail(resultSet.getString("email"));
                prof.setNome(resultSet.getString("nome"));
                prof.setIdDisc(resultSet.getInt("codDisc"));
                prof.setLogin(login);
                listaProf.add(prof);
            }
            resultSet.close();
            pstmt.close();
            return listaProf;
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
            String recuperarUltimoIdSql = "SELECT * FROM Professor WHERE idProf= (SELECT MAX(idProf) FROM Professor);";
            PreparedStatement pstmt = conn.prepareStatement(recuperarUltimoIdSql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                id = (rs.getInt("idProf"));
            }
            rs.close();
            pstmt.close();
            return id;
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
    
    }
    
    
    
}
