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
import negocio.modelo.Disciplina;

/**
 *
 * @author thais
 */
public class RepositorioDisciplina {

    public void inserirDisciplinas() throws ExceptionErroNoBanco {
        try {
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO Disciplina (idDisc,nome) VALUES(1,'Cálculo I')";
            stmt.executeUpdate(sql);
            String sql1 = "INSERT INTO Disciplina (idDisc,nome) VALUES(2,'Geometria Analítica')";
            stmt.executeUpdate(sql1);
            String sql2 = "INSERT INTO Disciplina (idDisc,nome) VALUES(3,'Lógica')";
            stmt.executeUpdate(sql2);
            String sql3 = "INSERT INTO Disciplina (idDisc,nome) VALUES(4,'Introdução à Programação')";
            stmt.executeUpdate(sql3);
            String sql4 = "INSERT INTO Disciplina (idDisc,nome) VALUES(5,'Cálculo II')";
            stmt.executeUpdate(sql4);
            String sql5 = "INSERT INTO Disciplina (idDisc,nome) VALUES(6,'Álgebra Linear')";
            stmt.executeUpdate(sql5);
            String sql6 = "INSERT INTO Disciplina (idDisc,nome) VALUES(7,'Física p/ Computação')";
            stmt.executeUpdate(sql6);
            String sql7 = "INSERT INTO Disciplina (idDisc,nome) VALUES(8,'AED I')";
            stmt.executeUpdate(sql7);
            String sql8 = "INSERT INTO Disciplina (idDisc,nome) VALUES(9,'POO')";
            stmt.executeUpdate(sql8);
            String sql9 = "INSERT INTO Disciplina (idDisc,nome) VALUES(10,'Sistemas Digitais')";
            stmt.executeUpdate(sql9);
            String sql10 = "INSERT INTO Disciplina (idDisc,nome) VALUES(11,'Discreta')";
            stmt.executeUpdate(sql10);
            String sql11 = "INSERT INTO Disciplina (idDisc,nome) VALUES(12,'AED II')";
            stmt.executeUpdate(sql11);
            String sql12 = "INSERT INTO Disciplina (idDisc,nome) VALUES(13,'PAA')";
            stmt.executeUpdate(sql12);
            String sql13 = "INSERT INTO Disciplina (idDisc,nome) VALUES(14,'PLP')";
            stmt.executeUpdate(sql13);
            String sql14 = "INSERT INTO Disciplina (idDisc,nome) VALUES(15,'Redes de Computadores')";
            stmt.executeUpdate(sql14);
            String sql15 = "INSERT INTO Disciplina (idDisc,nome) VALUES(16,'Inteligência Artificial')";
            stmt.executeUpdate(sql15);
            String sql16 = "INSERT INTO Disciplina (idDisc,nome) VALUES(17,'Sistemas Operacionais')";
            stmt.executeUpdate(sql16);
            String sql17 = "INSERT INTO Disciplina (idDisc,nome) VALUES(18,'Computação Gráfica')";
            stmt.executeUpdate(sql17);

            stmt.close();
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
    }

    public int recuperarUltimoID() throws ExceptionErroNoBanco {
        int id = 0;
        try {
            ResultSet rs = null;
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String recuperarUltimoIdSql = "SELECT * FROM Disciplina WHERE idDisc= (SELECT MAX(idDisc) FROM Disciplina);";
            PreparedStatement pstmt = conn.prepareStatement(recuperarUltimoIdSql);
            rs = pstmt.executeQuery();
            if (rs != null) {
                if (rs.next()) {
                    id = (rs.getInt("idDisc"));
                }
            }
            rs.close();
            pstmt.close();
            return id;
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
    }

    public Disciplina recuperar(int codigo) throws ExceptionErroNoBanco {
        try {
            ResultSet resultSet = null;
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "SELECT * FROM Disciplina WHERE idDisc = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, codigo);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                return new Disciplina(resultSet.getInt("idDisc"), resultSet.getString("nome"));
            }
            resultSet.close();
            pstmt.close();

        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
        return null;
    }

    public List<Disciplina> recuperarTodos() throws ExceptionErroNoBanco {
        try {
            ResultSet resultSet = null;
            Connection conn = DAO_SQLite.getSingleton().getConnection();
            String sql = "SELECT * FROM Disciplina;";
            Statement stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sql);

            List<Disciplina> listaDisc = new ArrayList<>();
            while (resultSet.next()) {
                listaDisc.add(new Disciplina(resultSet.getInt("idDisc"), resultSet.getString("nome")));
            }
            resultSet.close();
            stmt.close();
            return listaDisc;
        } catch (SQLException ex) {
            throw new ExceptionErroNoBanco(ex.getMessage());
        }
    }

}
