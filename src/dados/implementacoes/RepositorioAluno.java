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
import negocio.modelo.Aluno;
import negocio.modelo.Login;
import negocio.modelo.Monitor;
import negocio.modelo.Professor;

/**
 *
 * @author thais
 */
public class RepositorioAluno implements RepositorioGenerico<Aluno> {

    @Override
    public void inserir(Aluno t) throws ExceptionErroNoBanco {
        try {
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "INSERT INTO Turma (codMonitor,nome,email,validade) VALUES(?,?,?,0)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, t.getMonitor().getId());
            pstmt.setString(2, t.getNome());
            pstmt.setString(3, t.getEmail());
            pstmt.executeUpdate();
            ResultSet resultSet = null;
            PreparedStatement preparedStatement = null;
            sql = "SELECT * FROM Turma WHERE idTurma = (select MAX(idTurma) from Turma);";
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                t.setId(resultSet.getInt("idTurma"));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
    }

    @Override
    public void excluir(Aluno t) throws ExceptionErroNoBanco {
        try {
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "UPDATE Turma SET validade = 1 WHERE idTurma = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, t.getId());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
    }

    @Override
    public void alterar(Aluno t) throws ExceptionErroNoBanco {
        try {
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "UPDATE Turma SET nome = ?, email = ? WHERE idTurma = ?";
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
    public Aluno recuperar(int codigo) throws ExceptionErroNoBanco {
        try {
            ResultSet resultSet = null;
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "SELECT * FROM Turma t join Monitor m on t.codMonitor=m.idMonitor WHERE idTurma = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, codigo);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Monitor monitor = new Monitor();
                Aluno aluno = new Aluno();
                Login login = new Login();
                Professor prof = new Professor();
                monitor.setNome(resultSet.getString("nome"));
                monitor.setId(resultSet.getInt("idMonitor"));
                monitor.setEmail(resultSet.getString("email"));
                login.setId(resultSet.getInt("idLogin"));
                login.setLogin(resultSet.getString("login"));
                login.setSenha(resultSet.getString("senha"));
                prof.setId(resultSet.getInt("idProf"));
                prof.setEmail(resultSet.getString("email"));
                prof.setNome(resultSet.getString("nome"));
                prof.setIdDisc(resultSet.getInt("codDisc"));
                monitor.setLogin(login);
                monitor.setProf(prof);
                aluno.setEmail(resultSet.getString("nome"));
                aluno.setId(resultSet.getInt("idTurma"));
                aluno.setNome(resultSet.getString("nome"));
                aluno.setMonitor(monitor);

                return aluno;
            }
            resultSet.close();
            pstmt.close();

        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Aluno> recuperarTodos() throws ExceptionErroNoBanco {
        try {
            ResultSet resultSet = null;
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "SELECT * FROM Turma t join Monitor m on t.idMonitor=m.id;";
            Statement stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sql);
            List<Aluno> listaTurma = new ArrayList<>();
            while (resultSet.next()) {
                Monitor monitor = new Monitor();
                Aluno aluno = new Aluno();
                Login login = new Login();
                Professor prof = new Professor();
                monitor.setNome(resultSet.getString("nome"));
                monitor.setId(resultSet.getInt("id"));
                monitor.setEmail(resultSet.getString("email"));
                login.setId(resultSet.getInt("id"));
                login.setLogin(resultSet.getString("login"));
                login.setSenha(resultSet.getString("senha"));
                prof.setId(resultSet.getInt("id"));
                prof.setEmail(resultSet.getString("email"));
                prof.setNome(resultSet.getString("nome"));
                prof.setIdDisc(resultSet.getInt("idDisc"));
                monitor.setLogin(login);
                monitor.setProf(prof);
                aluno.setEmail(resultSet.getString("nome"));
                aluno.setId(resultSet.getInt("id"));
                aluno.setNome(resultSet.getString("nome"));
                aluno.setMonitor(monitor);
                listaTurma.add(aluno);
            }
            resultSet.close();
            stmt.close();
            return listaTurma;
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
            String recuperarUltimoIdSql = "SELECT * FROM Turma WHERE idTurma= (SELECT MAX(idTurma) FROM Turma);";
            PreparedStatement pstmt = conn.prepareStatement(recuperarUltimoIdSql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                id = (rs.getInt("idTurma"));
            }
            rs.close();
            pstmt.close();
            return id;
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
    }

}
