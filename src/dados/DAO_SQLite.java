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
                this.criarTabelaDisciplina();
		this.criarTabelaLogin();
                this.criarTabelaProfessor();
                this.criarTabelaMonitor();
                this.criarTabelaTarefa();
                this.criarTabelaTarefaDoMonitor();
                this.criarTabelaTurma();
                this.criarTabelaFrequencia();
	}
	
	private void criarTabelaLogin () throws SQLException {
		 String sql = "CREATE TABLE IF NOT EXISTS Login ("
	                + "idLogin integer PRIMARY KEY AUTOINCREMENT, "
	                + "tipo INTEGER, login TEXT NOT NULL, senha TEXT NOT NULL, validade INTEGER"
	                + ");";	     
		 Statement stmt = conn.createStatement();
		 stmt.execute(sql);
	}
        
        
        private void criarTabelaProfessor () throws SQLException {
		 String sql = "CREATE TABLE IF NOT EXISTS Professor ("
	                + "idProf integer PRIMARY KEY AUTOINCREMENT, "
	                + "codLogin INTEGER,"
                        + "codDisc INTEGER, "
                        + "nome TEXT NOT NULL, email TEXT, validade INTEGER"
	                + ");";	     
		 Statement stmt = conn.createStatement();
		 stmt.execute(sql);
	}
        private void criarTabelaMonitor () throws SQLException {
		 String sql = "CREATE TABLE IF NOT EXISTS Monitor ("
	                + "idMonitor integer PRIMARY KEY AUTOINCREMENT, "
	                + "codLogin INTEGER, idProf INTEGER, nome TEXT NOT NULL, email TEXT, validade INTEGER"
	                + ");";	     
		 Statement stmt = conn.createStatement();
		 stmt.execute(sql);
	}
        
        
        private void criarTabelaDisciplina () throws SQLException {
		 String sql = "CREATE TABLE IF NOT EXISTS Disciplina ("
	                + "idDisc INTEGER PRIMARY KEY,nome TEXT"
	                + ");";	     
		 Statement stmt = conn.createStatement();
		 stmt.execute(sql);
	}
        
        private void criarTabelaTarefaDoMonitor () throws SQLException {
		 String sql = "CREATE TABLE IF NOT EXISTS TarefaDoMonitor ("
	                + "idTarefaMonitor integer PRIMARY KEY AUTOINCREMENT,"
                        +"codTarefa INTEGER,codProf INTEGER, data TEXT NOT NULL, validade INTEGER"
	                + ");";	     
		 Statement stmt = conn.createStatement();
		 stmt.execute(sql);
	}
        
        private void criarTabelaTarefa () throws SQLException {
		 String sql = "CREATE TABLE IF NOT EXISTS Tarefa ("
	                + "idTarefa integer PRIMARY KEY AUTOINCREMENT,"
                        +"conteudo TEXT NOT NULL,estado INTEGER, validade INTEGER"
	                + ");";	     
		 Statement stmt = conn.createStatement();
		 stmt.execute(sql);
	}

        private void criarTabelaTurma () throws SQLException {
		 String sql = "CREATE TABLE IF NOT EXISTS Turma ("
	                + "idTurma integer PRIMARY KEY AUTOINCREMENT,"
                        +"codMonitor INTEGER, "
                        + "nome TEXT NOT NULL,email TEXT,validade INTEGER"
	                + ");";	     
		 Statement stmt = conn.createStatement();
		 stmt.execute(sql);
	}
        private void criarTabelaFrequencia () throws SQLException {
		 String sql = "CREATE TABLE IF NOT EXISTS Frequencia ("
	                + "idFrequencia integer PRIMARY KEY AUTOINCREMENT,"
                        +"frequencia INTEGER,"
                        + "codTurma INTEGER, "
                        + "codMonitor INTEGER,"
                        + "validade INTEGER"
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
