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
                this.criarTabelaPrioridades();
                this.criarTabelaTarefa();
                this.criarTabelaPlanejamento();
                this.criarTabelaTurma();
                this.criarTabelaFrequencia();
                this.criarTabelaBacklogMonitor();
	}
	
	private void criarTabelaLogin () throws SQLException {
		 String sql = "CREATE TABLE IF NOT EXISTS Login ("
	                + "id integer PRIMARY KEY AUTOINCREMENT, "
	                + "tipo INTEGER, login TEXT NOT NULL, senha TEXT NOT NULL, validade INTEGER"
	                + ");";	     
		 Statement stmt = conn.createStatement();
		 stmt.execute(sql);
	}
        
        
        private void criarTabelaProfessor () throws SQLException {
		 String sql = "CREATE TABLE IF NOT EXISTS Professor ("
	                + "id integer PRIMARY KEY AUTOINCREMENT, "
	                + "idLogin INTEGER, idDisc INTEGER, nome TEXT NOT NULL, email TEXT, validade INTEGER"
	                + ");";	     
		 Statement stmt = conn.createStatement();
		 stmt.execute(sql);
	}
        private void criarTabelaMonitor () throws SQLException {
		 String sql = "CREATE TABLE IF NOT EXISTS Monitor ("
	                + "id integer PRIMARY KEY AUTOINCREMENT, "
	                + "idLogin INTEGER, idProf INTEGER, nome TEXT NOT NULL, email TEXT, validade INTEGER"
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
        private void criarTabelaPrioridades () throws SQLException {
		 String sql = "CREATE TABLE IF NOT EXISTS Prioridades ("
	                + "id integer PRIMARY KEY AUTOINCREMENT,"
                        +"idProf INTEGER,idMonitor INTEGER, nomeMonitor TEXT NOT NULL,prioridade TEXT NOT NULL,validade INTEGER"
	                + ");";	     
		 Statement stmt = conn.createStatement();
		 stmt.execute(sql);
	}
        private void criarTabelaTarefa () throws SQLException {
		 String sql = "CREATE TABLE IF NOT EXISTS Tarefa ("
	                + "id integer PRIMARY KEY AUTOINCREMENT,"
                        +"conteudo TEXT NOT NULL,estado INTEGER, validade INTEGER"
	                + ");";	     
		 Statement stmt = conn.createStatement();
		 stmt.execute(sql);
	}
        private void criarTabelaPlanejamento () throws SQLException {
		 String sql = "CREATE TABLE IF NOT EXISTS Planejamento ("
	                + "id integer PRIMARY KEY AUTOINCREMENT,"
                        +"idProf INTEGER,idMonitor INTEGER,idTarefa INTEGER,data TEXT NOT NULL"
	                + ");";	     
		 Statement stmt = conn.createStatement();
		 stmt.execute(sql);
	}
        
        private void criarTabelaTurma () throws SQLException {
		 String sql = "CREATE TABLE IF NOT EXISTS Turma ("
	                + "id integer PRIMARY KEY AUTOINCREMENT,"
                        +"idMonitor INTEGER, nome TEXT NOT NULL,email TEXT,validade INTEGER"
	                + ");";	     
		 Statement stmt = conn.createStatement();
		 stmt.execute(sql);
	}
        private void criarTabelaFrequencia () throws SQLException {
		 String sql = "CREATE TABLE IF NOT EXISTS Frequencia ("
	                + "id integer PRIMARY KEY AUTOINCREMENT,"
                        +"frequencia INTEGER,idTurma INTEGER, idMonitor INTEGER"
	                + ");";	     
		 Statement stmt = conn.createStatement();
		 stmt.execute(sql);
	}
	private void criarTabelaBacklogMonitor () throws SQLException {
		 String sql = "CREATE TABLE IF NOT EXISTS BacklogMonitor ("
	                + "id integer PRIMARY KEY AUTOINCREMENT,"
                        +"idMonitor INTEGER,idTarefa INTEGER,validade INTEGER"
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
