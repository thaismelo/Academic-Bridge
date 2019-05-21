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
import negocio.modelo.Planejamento;
import negocio.modelo.Prioridades;
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
            String sql = "INSERT INTO Professor (idLogin,idDisc,nome,email,validade) VALUES(?,?,?,?,0)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1,professor.getIdLogin());
            pstmt.setInt(2,professor.getIdDisc());
            pstmt.setString(3, professor.getNome());
            pstmt.setString(4, professor.getEmail());
            pstmt.executeUpdate();
            
            ResultSet resultSet = null;
            PreparedStatement preparedStatement = null;
            sql = "SELECT * FROM Professor WHERE id = (select MAX(ID) from Professor);";
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                professor.setId(resultSet.getInt("id"));
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
            String sql = "UPDATE Professor SET validade = 1 WHERE id = ?";
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
            String sql = "SELECT * FROM Login WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            Login log = new Login(rs.getInt("id"),rs.getInt("tipo"),rs.getString("login"),rs.getString("senha"));
            new CRUDLogin().removerLogin(log);
            //Planejamento
            sql = "SELECT * FROM Planejamento WHERE idProf = ? AND validade = 0";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            while(rs.next()){
                Planejamento pl = new Planejamento(rs.getInt("id"),rs.getInt("idProf"),rs.getInt("idMonitor"),rs.getInt("idTarefa"),rs.getString("data"));
                new CRUDPlanejamento().removerPlanejamento(pl);
            }
            //Monitor
            sql = "SELECT * FROM Monitor WHERE idProf = ? AND validade = 0";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            while(rs.next()){
                Monitor moni = new Monitor(rs.getInt("id"),rs.getInt("idLogin"),rs.getInt("idProf"),rs.getString("nome"),rs.getString("email"));
                new CRUDMonitor().removerMonitor(moni);
            }
            //Prioridades
            sql = "SELECT * FROM Prioridades WHERE idProf = ? AND validade = 0";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            while(rs.next()){
                Prioridades pri = new Prioridades(rs.getInt("id"),rs.getInt("idProf"),rs.getInt("idMonitor"),rs.getString("nomeMonitor"),rs.getString("prioridade"));
                new CRUDPrioridades().removerPrioridades(pri);
            }
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
            String sql = "UPDATE Professor SET nome = ?, email = ? WHERE id = ?";
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
            String sql = "SELECT * FROM Professor WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, codigo);
            resultSet = pstmt.executeQuery();
            Professor prof = null;
            while (resultSet.next()) {
                prof = new Professor();
                prof.setId(resultSet.getInt("id"));
                prof.setEmail(resultSet.getString("email"));
                prof.setNome(resultSet.getString("nome"));
                prof.setIdLogin(resultSet.getInt("idLogin"));
                prof.setIdDisc(resultSet.getInt("idDisc"));
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
            String sql = "SELECT * FROM Professor;";
            Statement stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sql);
            List<Professor> listaProf= new ArrayList<>();
            Professor prof = null;
            while (resultSet.next()) {
                prof = new Professor();
                prof.setId(resultSet.getInt("id"));
                prof.setEmail(resultSet.getString("email"));
                prof.setNome(resultSet.getString("nome"));
                prof.setIdLogin(resultSet.getInt("idLogin"));
                prof.setIdDisc(resultSet.getInt("idDisc"));
                listaProf.add(prof);
            }
            resultSet.close();
            stmt.close();
            return listaProf;
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
    }
    
    
    
}
