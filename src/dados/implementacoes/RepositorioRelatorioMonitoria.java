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
import negocio.modelo.Login;
import negocio.modelo.Monitor;
import negocio.modelo.Professor;
import negocio.modelo.RelatorioMonitoria;
import negocio.modelo.Tarefa;

/**
 *
 * @author thais
 */
public class RepositorioRelatorioMonitoria implements RepositorioGenerico<RelatorioMonitoria>{

    @Override
    public void inserir(RelatorioMonitoria t) throws ExceptionErroNoBanco {
        try {
            
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "INSERT INTO RelatorioMonitoria (codMonitor,codTarefa,data,nivelDeDificuldade,reforcarAssunto,participatividade,validade) VALUES(?,?,?,?,?,?,0)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1,t.getMonitor().getId());
            pstmt.setInt(2,t.getTarefa().getId());
            pstmt.setString(3, t.getData());
            pstmt.setInt(4, t.getNivelDificuldade());
            pstmt.setInt(5, t.getReforcarAssunto());
            pstmt.setInt(6, t.getParticipatividade());
            pstmt.executeUpdate();
            
            ResultSet resultSet = null;
            PreparedStatement preparedStatement = null;
            sql = "SELECT * FROM RelatorioMonitoria WHERE idRelatorio = (select MAX(idRelatorio) from idRelatorio);";
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                t.setId(resultSet.getInt("idRelatorio"));
            }
            
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
    }
    
    

    @Override
    public void excluir(RelatorioMonitoria t) throws ExceptionErroNoBanco {
        try {
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "UPDATE RelatorioMonitoria SET validade = 1 WHERE idRelatorio = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, t.getId());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
}



    @Override
    public void alterar(RelatorioMonitoria t) throws ExceptionErroNoBanco {
        try {
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "UPDATE RelatorioMonitoria SET codTarefa = ?,data = ?,nivelDeDificuldade = ?,reforcarAssunto = ?,participatividade = ?  WHERE idRelatorio= ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, t.getTarefa().getId());
            pstmt.setString(2,t.getData());
            pstmt.setInt(3, t.getNivelDificuldade());
            pstmt.setInt(4, t.getReforcarAssunto());
            pstmt.setInt(5, t.getParticipatividade());
            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }    
    }

    @Override
    public RelatorioMonitoria recuperar(int codigo) throws ExceptionErroNoBanco {
        try {
            ResultSet resultSet = null;
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "SELECT * FROM RelatorioMonitor r join Monitor m on (r.codMonitor=m.idMonitor) join Tarefa t on (r.codTarefa=t.idTarefa) WHERE idFrequencia = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, codigo);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Monitor monitor = new Monitor();
                Login login = new Login();
                Professor prof = new Professor();
                Tarefa tarefa = new Tarefa();
                RelatorioMonitoria r = new RelatorioMonitoria();
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
                tarefa.setId(resultSet.getInt("idTarefa"));
                tarefa.setEstado(resultSet.getInt("estado"));
                tarefa.setConteudo(resultSet.getString("conteudo"));
                r.setId(resultSet.getInt("idRelatorio"));
                r.setMonitor(monitor);
                r.setTarefa(tarefa);
                r.setData(resultSet.getString("data"));
                r.setNivelDificuldade(resultSet.getInt("nivelDeDificuldade"));
                r.setReforcarAssunto(resultSet.getInt("reforcarAssunto"));
                r.setParticipatividade(resultSet.getInt("participatividade"));
                
                return r;
            }
            resultSet.close();
            pstmt.close();
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
        return null;
    
    
    }

    @Override
    public int recuperaUltimoID() throws ExceptionErroNoBanco {
        int id = 0;
        try {
            ResultSet rs = null;
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String recuperarUltimoIdSql = "SELECT * FROM RelatorioMonitoria WHERE idRelatorio= (SELECT MAX(idRelatorio) FROM RelatorioMonitoria);";
            PreparedStatement pstmt = conn.prepareStatement(recuperarUltimoIdSql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                id = (rs.getInt("idRelatorio"));
            }
            rs.close();
            pstmt.close();
            return id;
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
        }

    @Override
    public List<RelatorioMonitoria> recuperarTodos() throws ExceptionErroNoBanco {
         try {
            ResultSet resultSet = null;
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "SELECT * FROM RelatorioMonitoria r join Monitor m on r.codMonitor=m.idMonitor join Tarefa t on r.codTarefa=t.idTarefa;";
            Statement stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sql);
            List<RelatorioMonitoria> listaRelatorios= new ArrayList<>();
            while (resultSet.next()) {
                Monitor monitor = new Monitor();
                Login login = new Login();
                Professor prof = new Professor();
                Tarefa tarefa = new Tarefa();
                RelatorioMonitoria r = new RelatorioMonitoria();
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
                tarefa.setId(resultSet.getInt("idTarefa"));
                tarefa.setEstado(resultSet.getInt("estado"));
                tarefa.setConteudo(resultSet.getString("conteudo"));
                r.setId(resultSet.getInt("idRelatorio"));
                r.setMonitor(monitor);
                r.setTarefa(tarefa);
                r.setData(resultSet.getString("data"));
                r.setNivelDificuldade(resultSet.getInt("nivelDeDificuldade"));
                r.setReforcarAssunto(resultSet.getInt("reforcarAssunto"));
                r.setParticipatividade(resultSet.getInt("participatividade"));
                
                listaRelatorios.add(r);
            }
            resultSet.close();
            stmt.close();
            return listaRelatorios;
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
    
    
    
    }
    
}
