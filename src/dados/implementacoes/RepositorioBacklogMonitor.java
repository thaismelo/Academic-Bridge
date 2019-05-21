/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.implementacoes;

import dados.DAO_SQLite;
import dados.ExceptionErroNoBanco;
import dados.RepositorioGenerico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import negocio.modelo.BacklogMonitor;

/**
 *
 * @author thais
 */
public class RepositorioBacklogMonitor implements RepositorioGenerico<BacklogMonitor>{

    @Override
    public void inserir(BacklogMonitor t) throws ExceptionErroNoBanco {
        try {
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "INSERT INTO BacklogMonitor (idMonitor,idTarefa,validade) VALUES(?,?,0)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, t.getIdMonitor());
            pstmt.setInt(2, t.getIdTarefa());
            pstmt.executeUpdate();
            ResultSet resultSet = null;
            PreparedStatement preparedStatement = null;
            sql = "SELECT * FROM BacklogMonitor WHERE id = (select MAX(ID) from BacklogMonitor);";
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                t.setId(resultSet.getInt("id"));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }       }

    @Override
    public void excluir(BacklogMonitor t) throws ExceptionErroNoBanco {
       try {
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "UPDATE BacklogMonitor SET validade = 1 WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, t.getId());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }       }

    @Override
    public void alterar(BacklogMonitor t) throws ExceptionErroNoBanco {
        try {
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "UPDATE BacklogMonitor SET idTarefa = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, t.getIdTarefa());
            pstmt.setInt(2, t.getId());
            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }   
    }

    @Override
    public BacklogMonitor recuperar(int codigo) throws ExceptionErroNoBanco {
       try {
            ResultSet resultSet = null;
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "SELECT * FROM BacklogMonitor WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, codigo);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                return new BacklogMonitor(resultSet.getInt("id"),resultSet.getInt("idTarefa"), resultSet.getInt("idMonitor"));
            }
            resultSet.close();
            pstmt.close();

        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
        return null;        }

    @Override
    public List<BacklogMonitor> recuperarTodos() throws ExceptionErroNoBanco {
      try {
            ResultSet resultSet = null;
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "SELECT * FROM BacklogMonitor;";
            Statement stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sql);
            List<BacklogMonitor> listaback= new ArrayList<>();
            while (resultSet.next()) {
                listaback.add(new BacklogMonitor(resultSet.getInt("id"),resultSet.getInt("idTarefa"), resultSet.getInt("idMonitor")));
            }
            resultSet.close();
            stmt.close();
            return listaback;
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }        }
    
}
