/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorios.implementacoes.banco;

import dao.DAO_SQLite;
import exceptions.banco.ExceptionErroNoBanco;
import irepositorios.interfaces.RepositorioGenerico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entidades.Login;

/**
 *
 * @author thais
 */
public class RepositorioLogin implements RepositorioGenerico<Login> {

    @Override
    public void inserir(Login login) throws ExceptionErroNoBanco {
        try {
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "INSERT INTO Login (tipo,login,senha,validade) VALUES(?,?,?,0)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, login.getTipo());
            pstmt.setString(2, login.getLogin());
            pstmt.setString(3, login.getSenha());
            pstmt.executeUpdate();
            ResultSet resultSet = null;
            PreparedStatement preparedStatement = null;
            sql = "SELECT * FROM Login WHERE idLogin = (select MAX(idLogin) from Login);";
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                login.setId(resultSet.getInt("idLogin"));
            }
            resultSet.close();
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
    }

    @Override
    public void excluir(Login t) throws ExceptionErroNoBanco {
        try {
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "UPDATE Login SET validade = 1 WHERE idLogin = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, t.getId());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
    }

    @Override
    public void alterar(Login t) throws ExceptionErroNoBanco {
        try {
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "UPDATE Login SET login = ?, senha = ? WHERE idLogin = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, t.getLogin());
            pstmt.setString(2, t.getSenha());
            pstmt.setInt(3, t.getId());
            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
    }

    @Override
    public Login recuperar(int codigo) throws ExceptionErroNoBanco {
        try {
            ResultSet resultSet = null;
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "SELECT * FROM Login WHERE idLogin = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, codigo);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Login l = new Login(resultSet.getInt("idLogin"),resultSet.getInt("tipo"),resultSet.getString("login"), resultSet.getString("senha"));
                l.setLogin(resultSet.getString("login"));
                l.setSenha(resultSet.getString("senha"));
                return l;
            }
            resultSet.close();
            pstmt.close();

        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Login> recuperarTodos() throws ExceptionErroNoBanco {
        try {
            ResultSet resultSet = null;
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "SELECT * FROM Login WHERE validade = 0;";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            resultSet = pstmt.executeQuery();
            List<Login> listaLogin= new ArrayList<>();
            while (resultSet.next()) {
                Login l = new Login(resultSet.getInt("idLogin"),resultSet.getInt("tipo"),resultSet.getString("login"), resultSet.getString("senha"));
                l.setLogin(resultSet.getString("login"));
                l.setSenha(resultSet.getString("senha"));
                listaLogin.add(l);
            }
            resultSet.close();
            pstmt.close();
            return listaLogin;
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
            String recuperarUltimoIdSql = "SELECT * FROM Login WHERE idLogin= (SELECT MAX(idLogin) FROM Login);";
            PreparedStatement pstmt = conn.prepareStatement(recuperarUltimoIdSql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                id = (rs.getInt("idLogin"));
            }
            rs.close();
            pstmt.close();
            return id;
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
    
    }

}
