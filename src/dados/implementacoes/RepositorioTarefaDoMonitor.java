/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.implementacoes;

import dados.DAO_SQLite;
import dados.RepositorioGenerico;
import exceptions.banco.ExceptionErroNoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import negocio.modelo.TarefaDoMonitor;

/**
 *
 * @author thais
 */
public class RepositorioTarefaDoMonitor implements RepositorioGenerico<TarefaDoMonitor>{
    @Override
    public void inserir(TarefaDoMonitor t) throws ExceptionErroNoBanco {
        try {
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "INSERT INTO TarefaDoMonitor (codtarefa,data,codProf,validade) VALUES(?,?,?,0)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, t.getTarefaDoMonitor().getId());
            pstmt.setString(2, t.getData());
            pstmt.setInt(3, t.getCodProf());
            pstmt.executeUpdate();
            ResultSet resultSet = null;
            PreparedStatement preparedStatement = null;
            sql = "SELECT * FROM TarefaDoMonitor WHERE idTarefaMonitor = (select MAX(idTarefaMonitor) from TarefaDoMonitor);";
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            t.setId(resultSet.getInt("idTarefaMonitor"));
            
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }    
    }

    @Override
    public void excluir(TarefaDoMonitor t) throws ExceptionErroNoBanco {
        try {
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "UPDATE TarefaDoMonitor SET validade = 1 WHERE idTarefaMonitor = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, t.getId());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
    }

    @Override
    public void alterar(TarefaDoMonitor t) throws ExceptionErroNoBanco {
        try {
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "UPDATE TarefaDoMonitor SET codTarefa = ?, data = ? WHERE idTarefaMonitor = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, t.getTarefaDoMonitor().getId());
            pstmt.setString(2, t.getData());
            pstmt.setInt(3, t.getId());
            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
    }

    @Override
    public TarefaDoMonitor recuperar(int codigo) throws ExceptionErroNoBanco {
        try {
            ResultSet resultSet = null;
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "SELECT * FROM TarefaDoMonitor WHERE idTarefaMonitor = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, codigo);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                TarefaDoMonitor t = new TarefaDoMonitor(resultSet.getInt("idTarefaMonitor"), resultSet.getString("data"));
                t.setTarefaDoMonitor(new RepositorioTarefa().recuperar(resultSet.getInt("codTarefa")));
                return t;
            }
            resultSet.close();
            pstmt.close();

        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
        return null;
    }

   
    @Override
    public List<TarefaDoMonitor> recuperarTodos() throws ExceptionErroNoBanco {
        try {
            ResultSet resultSet = null;
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "SELECT * FROM TarefaDoMonitor;";
            Statement stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sql);
            List<TarefaDoMonitor> listaAtividade= new ArrayList<>();
            while (resultSet.next()) {
                TarefaDoMonitor t = new TarefaDoMonitor(resultSet.getInt("idTarefaMonitor"), resultSet.getString("data"));
                t.setTarefaDoMonitor(new RepositorioTarefa().recuperar(resultSet.getInt("codTarefa")));
                t.setCodProf(resultSet.getInt("codProf"));
                listaAtividade.add(t);
            }
            resultSet.close();
            stmt.close();
            return listaAtividade;
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
            String recuperarUltimoIdSql = "SELECT * FROM TarefaDoMonitor WHERE idTarefaMonitor= (SELECT MAX(idTarefaMonitor) FROM TarefaDoMonitor);";
            PreparedStatement pstmt = conn.prepareStatement(recuperarUltimoIdSql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                id = (rs.getInt("idTarefaMonitor"));
            }
            rs.close();
            pstmt.close();
            return id;
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
    }
    
    public List<TarefaDoMonitor> recuperarTodosPorCodProf(int cod) throws ExceptionErroNoBanco {
        try {
            ResultSet resultSet = null;
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "SELECT * FROM TarefaDoMonitor WHERE codProf = ? AND validade = 0";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, cod);
            resultSet = pstmt.executeQuery();
            List<TarefaDoMonitor> listaAtividade= new ArrayList<>();
            while (resultSet.next()) {
                TarefaDoMonitor t = new TarefaDoMonitor(resultSet.getInt("idTarefaMonitor"),resultSet.getInt("codProf"), resultSet.getString("data"));
                t.setTarefaDoMonitor(new RepositorioTarefa().recuperar(resultSet.getInt("codTarefa")));
                listaAtividade.add(t);
            }
            resultSet.close();
            pstmt.close();
            return listaAtividade;
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
    }
}
