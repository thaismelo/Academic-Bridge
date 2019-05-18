/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.sql.*;

/**
 *
 * @author thais
 */
public class DAO_SQLite {
    private final String url = "jdbc:sqlite:banco.db";
	
	private static DAO_SQLite singleton = null; 
	private Connection conn;
	
	private DAO_SQLite () throws SQLException {
		this.connect();
		this.criarTabelaLogin();
                this.criarTabelaProfessor();
                this.criarTabelaDisciplina();
	}
	
	private void criarTabelaLogin () throws SQLException {
		 String sql = "CREATE TABLE IF NOT EXISTS Login ("
	                + "id integer PRIMARY KEY AUTOINCREMENT, "
	                + "tipo INTEGER, login TEXT NOT NULL, senha TEXT NOT NULL"
	                + ");";	     
		 Statement stmt = conn.createStatement();
		 stmt.execute(sql);
	}
        
        
        private void criarTabelaProfessor () throws SQLException {
		 String sql = "CREATE TABLE IF NOT EXISTS Professor ("
	                + "id integer PRIMARY KEY AUTOINCREMENT, "
	                + "idLogin INTEGER, nome TEXT NOT NULL, email TEXT NOT NULL"
	                + ");";	     
		 Statement stmt = conn.createStatement();
		 stmt.execute(sql);
	}
        private void criarTabelaDisciplina () throws SQLException {
		 String sql = "CREATE TABLE IF NOT EXISTS Disciplina ("
	                + "id INTEGER PRIMARY KEY,nome TEXT"
	                + ");";	     
		 Statement stmt = conn.createStatement();
		 stmt.execute(sql);
	}
        
	
	public static DAO_SQLite getSingleton() throws SQLException {
		if (singleton == null) {
			singleton = new DAO_SQLite();
		}
		return singleton;
	}
	
	private Connection connect() throws SQLException {        
            this.conn = DriverManager.getConnection(url);
            return conn;
    }
	
	public Connection getConnection() {
		return this.conn;
	}
        
}
