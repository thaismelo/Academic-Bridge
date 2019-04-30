/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_conexoes;

import conexaoBD.ConexaoSQLite;
import entidades.Professor;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thais
 */
public class ProfessorDAO {
    private Connection conexao;
    private void abrirConexao() {
		if (this.conexao == null) {
			this.conexao = new ConexaoSQLite().getConnection();
		}
	}

	private void fecharConexao() {
		try {
			if (this.conexao != null) {
				this.conexao.close();
				this.conexao = null;
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
        
     public void cadastrarProfessor(Professor professor){
         try{
             this.abrirConexao();
			String sql = "INSERT INTO Professor (id,nome, email) VALUES (?, ?, ?)";
			PreparedStatement stmt = this.conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);		
                        stmt.setString(1, professor.getNome());
			stmt.setString(2, professor.getEmail());				
			int adicionado = stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			int lastId = rs.getInt(1);				
			if (adicionado > 0) {
				System.out.println();
				System.out.println("Professor " + lastId + " adicionado com sucesso!");
				System.out.println("----------------------------------\n");
			}			
			//stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			this.fecharConexao();
		}
         }
     public List<Professor> listarProfessores(){
         List<Professor> professores = new ArrayList<Professor>();
		try {
			this.abrirConexao();
			String sql = "SELECT * FROM Professor";
			PreparedStatement stmt = conexao.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Professor c = new Professor();	
                                c.setId(rs.getInt("id"));
				c.setNome(rs.getString("nome"));
				c.setEmail(rs.getString("email"));				
				professores.add(c);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			this.fecharConexao();
		}
		return professores;
	}
	
         
     
	
}
