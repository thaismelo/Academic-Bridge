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
import negocio.modelo.Prioridades;

/**
 *
 * @author thais
 */
public class RepositorioPrioridades implements RepositorioGenerico<Prioridades>{

    @Override
    public void inserir(Prioridades t) throws ExceptionErroNoBanco {
        try{
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "INSERT INTO Prioridades (idProf,idMonitor, nomeMonitor ,prioridade,validade) VALUES(?,?,?,?,0)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, t.getIdProf());
            pstmt.setInt(2, t.getIdMonitor());
            pstmt.setString(3, t.getNomeMonitor());
            pstmt.setString(4, t.getPrioridade());
            pstmt.executeUpdate();
            
            ResultSet resultSet = null;
            PreparedStatement preparedStatement = null;
            sql = "SELECT * FROM Prioridades WHERE id = (select MAX(ID) from Prioridades);";
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
    public void excluir(Prioridades t) throws ExceptionErroNoBanco {

        try {
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "UPDATE Prioridades SET validade = 1 WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, t.getId());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }  
}

    @Override
    public void alterar(Prioridades t) throws ExceptionErroNoBanco {
        try {
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "UPDATE Prioridades SET prioridade = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, t.getPrioridade());
            pstmt.setInt(2, t.getId());
            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
    }

    @Override
    public Prioridades recuperar(int codigo) throws ExceptionErroNoBanco {
           try {
            ResultSet resultSet = null;
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "SELECT * FROM Prioridades WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, codigo);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                return new Prioridades(resultSet.getInt("id"),resultSet.getInt("idProf"),resultSet.getInt("idMonitor"),resultSet.getString("nomeMonitor"),resultSet.getString("prioridade"));
            }
            resultSet.close();
            pstmt.close();

        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Prioridades> recuperarTodos() throws ExceptionErroNoBanco {
        try {
            ResultSet resultSet = null;
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "SELECT * FROM Prioridades;";
            Statement stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sql);
            List<Prioridades> listaP= new ArrayList<>();
            while (resultSet.next()) {
                listaP.add(new Prioridades(resultSet.getInt("id"),resultSet.getInt("idProf"), resultSet.getInt("idMonitor"),resultSet.getString("nomeMonitor"),resultSet.getString("prioridade")));
            }
            resultSet.close();
            stmt.close();
            return listaP;
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
    }
    
}
