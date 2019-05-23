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
import negocio.modelo.Frequencia;
import negocio.modelo.Monitor;

/**
 *
 * @author thais
 */
public class RepositorioFrequencia implements RepositorioGenerico<Frequencia>{

    @Override
    public void inserir(Frequencia t) throws ExceptionErroNoBanco {
       try {
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "INSERT INTO Frequencia (frequencia,idTurma,idMonitor,validade) VALUES(?,?,?,0)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, t.getFrequencia());
            pstmt.setInt(2, t.getAluno().getId());
            pstmt.setInt(3, t.getMonitor().getId());
            pstmt.executeUpdate();
            ResultSet resultSet = null;
            PreparedStatement preparedStatement = null;
            sql = "SELECT * FROM Frequencia WHERE id = (select MAX(ID) from Frequencia);";
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
    public void excluir(Frequencia t) throws ExceptionErroNoBanco {
       try {
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "UPDATE Frequencia SET validade = 1 WHERE id = ?";
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
            String sql = "UPDATE Frequencia SET frequencia = ? WHERE id = ?";
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
            String sql = "SELECT * FROM Frequencia f join Turma t on (f.idTurma=t.id) join Monitor m on (f.idMonitor=m.id) WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, codigo);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Frequencia f = new Frequencia();
                Aluno a = new Aluno();
                Monitor m = new Monitor();
            }
            resultSet.close();
            pstmt.close();

        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
        return null;    }

    @Override
    public List<Frequencia> recuperarTodos() throws ExceptionErroNoBanco {
        try {
            ResultSet resultSet = null;
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "SELECT * FROM Frequencia f join Turma t on (f.idTurma=t.id) join Monitor m on (f.idMonitor=m.id);";
            Statement stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sql);
            List<Frequencia> listaFrequencia= new ArrayList<>();
            while (resultSet.next()) {
                listaFrequencia.add(new Frequencia(resultSet.getInt("id"),resultSet.getInt("frequencia"),resultSet.getInt("idTurma"), resultSet.getInt("idMonitor")));
            }
            resultSet.close();
            stmt.close();
            return listaFrequencia;
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }    }

    @Override
    public int recuperaUltimoID() throws ExceptionErroNoBanco {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
