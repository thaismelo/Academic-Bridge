/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorios.implementacoes.banco;

import dao.DAO_SQLite;
import irepositorios.interfaces.RepositorioGenerico;
import exceptions.banco.ExceptionErroNoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entidades.Tarefa;
import entidades.TarefaParaMonitor;

/**
 *
 * @author thais
 */
public class RepositorioTarefaParaMonitor implements RepositorioGenerico<TarefaParaMonitor>{
    @Override
    public void inserir(TarefaParaMonitor t) throws ExceptionErroNoBanco {
        try {
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "INSERT INTO TarefaDoMonitor (codtarefa,data,codProf,codMonit,validade) VALUES(?,?,?,?,0)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, t.getTarefaParaMonitor().getId());
            pstmt.setString(2, t.getData());
            pstmt.setInt(3, t.getCodProf());
            pstmt.setInt(4, t.getCodMonit());
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
    public void excluir(TarefaParaMonitor t) throws ExceptionErroNoBanco {
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
    public void alterar(TarefaParaMonitor t) throws ExceptionErroNoBanco {
        try {
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "UPDATE TarefaDoMonitor SET codTarefa = ?, data = ? WHERE idTarefaMonitor = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, t.getTarefaParaMonitor().getId());
            pstmt.setString(2, t.getData());
            pstmt.setInt(3, t.getId());
            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
    }

    @Override
    public TarefaParaMonitor recuperar(int codigo) throws ExceptionErroNoBanco {
        try {
            ResultSet resultSet = null;
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "SELECT * FROM TarefaDoMonitor WHERE idTarefaMonitor = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, codigo);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                TarefaParaMonitor t = new TarefaParaMonitor(resultSet.getInt("idTarefaMonitor"),resultSet.getInt("codMonit"), resultSet.getString("data"));
                t.setTarefaParaMonitor(new RepositorioTarefa().recuperar(resultSet.getInt("codTarefa")));
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
    public List<TarefaParaMonitor> recuperarTodos() throws ExceptionErroNoBanco {
        try {
            ResultSet resultSet = null;
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "SELECT * FROM TarefaDoMonitor WHERE validade = 0";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            resultSet = pstmt.executeQuery();
            List<TarefaParaMonitor> listaAtividade= new ArrayList<>();
            while (resultSet.next()) {
                TarefaParaMonitor t = new TarefaParaMonitor(resultSet.getInt("idTarefaMonitor"),resultSet.getInt("codMonit"), resultSet.getString("data"));
                t.setTarefaParaMonitor(new RepositorioTarefa().recuperar(resultSet.getInt("codTarefa")));
                t.setCodProf(resultSet.getInt("codProf"));
                listaAtividade.add(t);
            }
            resultSet.close();
            pstmt.close();
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
    
    public List<TarefaParaMonitor> recuperarTodosPorCodProf(int cod) throws ExceptionErroNoBanco {
        try {
            ResultSet resultSet = null;
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "SELECT * FROM TarefaDoMonitor WHERE codProf = ? AND validade = 0";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, cod);
            resultSet = pstmt.executeQuery();
            List<TarefaParaMonitor> listaAtividade= new ArrayList<>();
            while (resultSet.next()) {
                TarefaParaMonitor t = new TarefaParaMonitor(resultSet.getInt("idTarefaMonitor"),resultSet.getInt("codProf"), resultSet.getString("data"));
                t.setCodMonit(resultSet.getInt("codMonit"));
                t.setTarefaParaMonitor(new RepositorioTarefa().recuperar(resultSet.getInt("codTarefa")));
                listaAtividade.add(t);
            }
            resultSet.close();
            pstmt.close();
            return listaAtividade;
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
    }
    
    public List<Tarefa> recuperarTodosPorCodMonit(int cod) throws ExceptionErroNoBanco {
        try {
            ResultSet resultSet = null;
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "SELECT * FROM TarefaDoMonitor WHERE codMonit = ? AND validade = 0";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, cod);
            resultSet = pstmt.executeQuery();
            List<Tarefa> listaAtividade= new ArrayList<>();
            while (resultSet.next()) {
                listaAtividade.add(new RepositorioTarefa().recuperar(resultSet.getInt("codTarefa")));
            }
            resultSet.close();
            pstmt.close();
            return listaAtividade;
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
    }
}
