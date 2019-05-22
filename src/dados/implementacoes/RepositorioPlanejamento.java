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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.modelo.Planejamento;
import negocio.modelo.Tarefa;

/**
 *
 * @author Guilherme
 */
public class RepositorioPlanejamento implements RepositorioGenerico<Planejamento>{

    @Override
    public void inserir(Planejamento t) throws ExceptionErroNoBanco {
        try {
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "INSERT INTO Planejamento (idProf,idMonitor,idTarefa,data,validade) VALUES(?,?,?,?,0)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, t.getIdProf());
            pstmt.setInt(2, t.getIdMonitor());
            //pstmt.setInt(3, t.getIdTarefa());
            pstmt.setString(4, t.getData());
            pstmt.executeUpdate();
            ResultSet resultSet = null;
            PreparedStatement preparedStatement = null;
            sql = "SELECT * FROM Planejamento WHERE id = (select MAX(ID) from Planejamento);";
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
    public void excluir(Planejamento t) throws ExceptionErroNoBanco {
        try {
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            //this.excluirDependentes(t.getIdTarefa());
            String sql = "UPDATE Planejamento SET validade = 1 WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, t.getId());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
    }
    
    public void excluirDependentes(int id) throws ExceptionErroNoBanco, DadoInexistenteException, DadoNuloException{
        try{
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            ResultSet rs = null;
            //Tarefa 
            String sql = "SELECT * FROM Tarefa WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            Tarefa t = new Tarefa(rs.getInt("id"),rs.getString("conteudo"),rs.getInt("estado"));
            new CRUDTarefa().removerTarefa(t);
        }catch(SQLException ex){
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
    }

    @Override
    public void alterar(Planejamento t) throws ExceptionErroNoBanco {
        try {
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "UPDATE Planejamento SET idMonitor = ?, idTarefa = ?, data = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, t.getIdMonitor());
           // pstmt.setInt(2, t.getIdTarefa());
            pstmt.setString(3, t.getData());
            pstmt.setInt(4, t.getId());
            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
    }

    @Override
    public Planejamento recuperar(int codigo) throws ExceptionErroNoBanco {
        try {
            ResultSet resultSet = null;
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "SELECT * FROM Planejamento WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, codigo);
            resultSet = pstmt.executeQuery();
            //while (resultSet.next()) {
               // return new Planejamento(resultSet.getInt("id"),resultSet.getInt("idProf"),resultSet.getInt("idMonitor"), resultSet.getArray("listaTarefas"),resultSet.getNString("data"));
           // }
            resultSet.close();
            pstmt.close();

        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Planejamento> recuperarTodos() throws ExceptionErroNoBanco {
        try {
            ResultSet resultSet = null;
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "SELECT * FROM Planejamento;";
            Statement stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sql);
            List<Planejamento> listaPlanejamento= new ArrayList<>();
            //while (resultSet.next()) {
                //listaPlanejamento.add(new Planejamento(resultSet.getInt("id"),resultSet.getInt("idProf"),resultSet.getInt("idMonitor"), resultSet.getArray("listaTarefas"),resultSet.getNString("data")));
            //}
            resultSet.close();
            stmt.close();
            return listaPlanejamento;
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
    }
    
}
