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
import java.util.List;
import negocio.modelo.Professor;

/**
 *
 * @author thais
 */
public class RepositorioProfessor implements RepositorioGenerico<Professor>{

    @Override
    public void inserir(Professor professor) throws ExceptionErroNoBanco {
        try {
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "INSERT INTO Professor (idLogin,nome,email) VALUES(?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(2, professor.getNome());
            pstmt.setString(3, professor.getEmail());
            pstmt.executeUpdate();
            ResultSet resultSet = null;
            PreparedStatement preparedStatement = null;
            sql = "SELECT * FROM Login WHERE id = (select MAX(ID) from Login);";
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                professor.setId(resultSet.getInt("id"));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
    }

    @Override
    public void excluir(Professor t) throws ExceptionErroNoBanco {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alterar(Professor t) throws ExceptionErroNoBanco {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Professor recuperar(int codigo) throws ExceptionErroNoBanco {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Professor> recuperarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
