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
import negocio.modelo.Tarefa;

/**
 *
 * @author Guilherme
 */
public class RepositorioTarefa  implements RepositorioGenerico<Tarefa>{

    @Override
    public void inserir(Tarefa tarefa) throws ExceptionErroNoBanco {
        try {
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "INSERT INTO Tarefa (conteudo,idCriador,tipoCriador,estado,validade) VALUES(?,?,?,0,0)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, tarefa.getConteudo());
            pstmt.setInt(2, tarefa.getIdCriador());
            pstmt.setInt(3, tarefa.getTipoCriador());
            pstmt.executeUpdate();
            ResultSet resultSet = null;
            PreparedStatement preparedStatement = null;
            sql = "SELECT * FROM Tarefa WHERE idTarefa = (select MAX(idTarefa) from Tarefa);";
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                tarefa.setId(resultSet.getInt("idTarefa"));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }    
    }

    @Override
    public void excluir(Tarefa tarefa) throws ExceptionErroNoBanco {
        try {
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "UPDATE Tarefa SET validade = 1 WHERE idTarefa = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, tarefa.getId());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
    }

    @Override
    public void alterar(Tarefa tarefa) throws ExceptionErroNoBanco {
        try {
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "UPDATE Tarefa SET conteudo = ?, estado = ? WHERE idTarefa = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, tarefa.getConteudo());
            pstmt.setInt(2, tarefa.getEstado());
            pstmt.setInt(3, tarefa.getId());
            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
    }

    @Override
    public Tarefa recuperar(int codigo) throws ExceptionErroNoBanco {
        try {
            ResultSet resultSet = null;
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "SELECT * FROM Tarefa WHERE idTarefa = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, codigo);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                return new Tarefa(resultSet.getInt("idTarefa"), resultSet.getString("conteudo"), resultSet.getInt("estado"));
            }
            resultSet.close();
            pstmt.close();

        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Tarefa> recuperarTodos() throws ExceptionErroNoBanco {
        try {
            ResultSet resultSet = null;
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "SELECT * FROM Tarefa;";
            Statement stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sql);
            List<Tarefa> listaTarefa= new ArrayList<>();
            while (resultSet.next()) {
                listaTarefa.add(new Tarefa(resultSet.getInt("idTarefa"), resultSet.getString("conteudo"), resultSet.getInt("estado")));
            }
            resultSet.close();
            stmt.close();
            return listaTarefa;
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
    }
    
    public List<Tarefa> recuperarTodosPorCriador(int codCriador,int tipCriador) throws ExceptionErroNoBanco {
        try {
            ResultSet resultSet = null;
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "SELECT * FROM Tarefa WHERE idCriador = ? AND tipoCriador = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, codCriador);
            pstmt.setInt(2, tipCriador);
            resultSet = pstmt.executeQuery();
            List<Tarefa> listaTarefa= new ArrayList<>();
            while (resultSet.next()) {
                listaTarefa.add(new Tarefa(resultSet.getInt("idTarefa"), resultSet.getString("conteudo"), resultSet.getInt("estado")));
            }
            resultSet.close();
            pstmt.close();
            return listaTarefa;
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
            String recuperarUltimoIdSql = "SELECT * FROM Tarefa WHERE idTarefa= (SELECT MAX(idTarefa) FROM Tarefa);";
            PreparedStatement pstmt = conn.prepareStatement(recuperarUltimoIdSql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                id = (rs.getInt("idTarefa"));
            }
            rs.close();
            pstmt.close();
            return id;
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
    }
    
}
