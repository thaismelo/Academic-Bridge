/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorios.implementacoes.banco;

import repositorios.implementacoes.banco.RepositorioProfessor;
import dao.DAO_SQLite;
import exceptions.banco.ExceptionErroNoBanco;
import irepositorios.interfaces.RepositorioGenerico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entidades.Aluno;
import entidades.Frequencia;
import entidades.Login;
import entidades.Monitor;
import entidades.Professor;

/**
 *
 * @author thais
 */
public class RepositorioFrequencia implements RepositorioGenerico<Frequencia>{

    @Override
    public void inserir(Frequencia t) throws ExceptionErroNoBanco {
       try {
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "INSERT INTO Frequencia (frequencia,codTurma,codMonitor,data,validade) VALUES(?,?,?,?,0)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, t.getFrequencia());
            pstmt.setInt(2, t.getAluno().getId());
            pstmt.setInt(3, t.getMonitor().getId());
            pstmt.setString(4, t.getData());
            pstmt.executeUpdate();
            ResultSet resultSet = null;
            PreparedStatement preparedStatement = null;
            sql = "SELECT * FROM Frequencia WHERE idFrequencia = (select MAX(idFrequencia) from Frequencia);";
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                t.setId(resultSet.getInt("idFrequencia"));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }   
    
    }
    

    @Override
    public void excluir(Frequencia t) throws ExceptionErroNoBanco {
       try {
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "UPDATE Frequencia SET validade = 1 WHERE idFrequencia = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, t.getId());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }  
    }

    @Override
    public void alterar(Frequencia t) throws ExceptionErroNoBanco {
        try {
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "UPDATE Frequencia SET frequencia = ? WHERE idFrequencia = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, t.getFrequencia());
            pstmt.setInt(2, t.getId());
            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
    }

    @Override
    public Frequencia recuperar(int codigo) throws ExceptionErroNoBanco {
        try {
            ResultSet resultSet = null;
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "SELECT * FROM Frequencia f join Turma t on (f.codTurma=t.idTurma) join Monitor m on (f.codMonitor=m.idMonitor) join Login l on (m.codLogin=l.idLogin)  WHERE idFrequencia = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, codigo);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Frequencia f = new Frequencia();
                Aluno a = new Aluno();
                Monitor monitor = new Monitor();
                Login login = new Login();
                
                login.setId(resultSet.getInt("idLogin"));
                login.setLogin(resultSet.getString("login"));
                login.setSenha(resultSet.getString("senha"));
                login.setTipo(resultSet.getInt("tipo"));
                
                monitor.setId(resultSet.getInt("idMonitor"));
                monitor.setEmail(resultSet.getString("email"));
                monitor.setNome(resultSet.getString("nome"));
                monitor.setLogin(login);
                monitor.setProf(new RepositorioProfessor().recuperar(resultSet.getInt("codProf")));
                a = new RepositorioAluno().recuperar(resultSet.getInt("idTurma"));
                
                f.setId(resultSet.getInt("idFrequencia"));
                f.setAluno(a);
                f.setMonitor(monitor);
                f.setFrequencia(resultSet.getInt("frequencia"));
                f.setData(resultSet.getString("data"));
                return f;
            }
            resultSet.close();
            pstmt.close();

        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
        return null;    
    }

    @Override
    public List<Frequencia> recuperarTodos() throws ExceptionErroNoBanco {
        try {
            ResultSet resultSet = null;
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "SELECT * FROM Frequencia f join Turma t on (f.codTurma=t.idTurma) join Monitor m on (f.codMonitor=m.idMonitor) join Login l on (m.codLogin=l.idLogin) WHERE f.validade = 0;";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            resultSet = pstmt.executeQuery();
            List<Frequencia> listaFrequencia= new ArrayList<>();
            while (resultSet.next()) {
                Frequencia f = new Frequencia();
                Aluno a = new Aluno();
                Monitor monitor = new Monitor();
                Login login = new Login();
                
                login.setId(resultSet.getInt("idLogin"));
                login.setLogin(resultSet.getString("login"));
                login.setSenha(resultSet.getString("senha"));
                login.setTipo(resultSet.getInt("tipo"));
                
                monitor.setId(resultSet.getInt("idMonitor"));
                monitor.setEmail(resultSet.getString("email"));
                monitor.setNome(resultSet.getString("nome"));
                monitor.setLogin(login);
                monitor.setProf(new RepositorioProfessor().recuperar(resultSet.getInt("codProf")));
                a.setId(resultSet.getInt("idTurma"));
                a.setEmail(resultSet.getString("email"));
                a.setNome(resultSet.getString("nome"));
                a.setMonitor(monitor);
                f.setId(resultSet.getInt("idFrequencia"));
                f.setAluno(a);
                f.setMonitor(monitor);
                f.setFrequencia(resultSet.getInt("frequencia"));
                f.setData(resultSet.getString("data"));
                listaFrequencia.add(f);
            }
            resultSet.close();
            pstmt.close();
            return listaFrequencia;
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }    }

    @Override
    public int recuperaUltimoID() throws ExceptionErroNoBanco {
        int id = 0;
        try {
            ResultSet rs = null;
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String recuperarUltimoIdSql = "SELECT * FROM Frequencia WHERE idFrequencia= (SELECT MAX(idFrequencia) FROM Frequencia);";
            PreparedStatement pstmt = conn.prepareStatement(recuperarUltimoIdSql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                id = (rs.getInt("idFrequencia"));
            }
            rs.close();
            pstmt.close();
            return id;
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
    }
    
    
    public List<Frequencia> recuperarTodosPorMonit(int cod) throws ExceptionErroNoBanco {
        try {
            ResultSet resultSet = null;
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "SELECT * FROM Frequencia f join Turma t on (f.codTurma=t.idTurma) join Monitor m on (f.codMonitor=m.idMonitor) join Login l on (m.codLogin=l.idLogin) WHERE f.validade = 0 AND f.codMonitor=?;";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, cod);
            resultSet = pstmt.executeQuery();
            List<Frequencia> listaFrequencia= new ArrayList<>();
            while (resultSet.next()) {
                Frequencia f = new Frequencia();
                Aluno a = new Aluno();
                Monitor monitor = new Monitor();
                Login login = new Login();
                
                login.setId(resultSet.getInt("idLogin"));
                login.setLogin(resultSet.getString("login"));
                login.setSenha(resultSet.getString("senha"));
                login.setTipo(resultSet.getInt("tipo"));
                
                monitor.setId(resultSet.getInt("idMonitor"));
                monitor.setEmail(resultSet.getString("email"));
                monitor.setNome(resultSet.getString("nome"));
                monitor.setLogin(login);
                monitor.setProf(new RepositorioProfessor().recuperar(resultSet.getInt("codProf")));
                a.setId(resultSet.getInt("idTurma"));
                a.setEmail(resultSet.getString("email"));
                a.setNome(resultSet.getString("nome"));
                a.setMonitor(monitor);
                f.setId(resultSet.getInt("idFrequencia"));
                f.setAluno(a);
                f.setMonitor(monitor);
                f.setFrequencia(resultSet.getInt("frequencia"));
                f.setData(resultSet.getString("data"));
                listaFrequencia.add(f);
            }
            resultSet.close();
            pstmt.close();
            return listaFrequencia;
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }    }
    
    public List<Frequencia> recuperarTodosPorData(String data) throws ExceptionErroNoBanco {
        try {
            ResultSet resultSet = null;
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "SELECT * FROM Frequencia f join Turma t on (f.codTurma=t.idTurma) join Monitor m on (f.codMonitor=m.idMonitor) join Login l on (m.codLogin=l.idLogin) WHERE f.validade = 0 AND f.data=?;";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, data);
            resultSet = pstmt.executeQuery();
            List<Frequencia> listaFrequencia= new ArrayList<>();
            while (resultSet.next()) {
                Frequencia f = new Frequencia();
                Aluno a = new Aluno();
                Monitor monitor = new Monitor();
                Login login = new Login();
                
                login.setId(resultSet.getInt("idLogin"));
                login.setLogin(resultSet.getString("login"));
                login.setSenha(resultSet.getString("senha"));
                login.setTipo(resultSet.getInt("tipo"));
                
                monitor.setId(resultSet.getInt("idMonitor"));
                monitor.setEmail(resultSet.getString("email"));
                monitor.setNome(resultSet.getString("nome"));
                monitor.setLogin(login);
                monitor.setProf(new RepositorioProfessor().recuperar(resultSet.getInt("codProf")));
                a = new RepositorioAluno().recuperar(resultSet.getInt("idTurma"));
                f.setId(resultSet.getInt("idFrequencia"));
                f.setAluno(a);
                f.setMonitor(monitor);
                f.setFrequencia(resultSet.getInt("frequencia"));
                f.setData(resultSet.getString("data"));
                listaFrequencia.add(f);
            }
            resultSet.close();
            pstmt.close();
            return listaFrequencia;
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }    }
    
    
    
}
