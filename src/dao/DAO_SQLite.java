/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

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
                this.criarTabelaRelatorioMonitoria();
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
                        + "nome TEXT NOT NULL, email TEXT, validade INTEGER"
	                + ");";	     
		 Statement stmt = conn.createStatement();
		 stmt.execute(sql);
	}
        private void criarTabelaMonitor () throws SQLException {
		 String sql = "CREATE TABLE IF NOT EXISTS Monitor ("
	                + "idMonitor integer PRIMARY KEY AUTOINCREMENT, "
	                + "codLogin INTEGER, codProf INTEGER,codDisciplina INTEGER, nome TEXT NOT NULL, email TEXT, validade INTEGER"
	                + ");";	     
		 Statement stmt = conn.createStatement();
		 stmt.execute(sql);
	}
        
        
        private void criarTabelaDisciplina () throws SQLException {
		 String sql = "CREATE TABLE IF NOT EXISTS Disciplina ("
	                + "idDisc INTEGER PRIMARY KEY AUTOINCREMENT,nome TEXT,curso TEXT, codProf INTEGER,validade INTEGER"
	                + ");";	     
		 Statement stmt = conn.createStatement();
		 stmt.execute(sql);
	}
        
        private void criarTabelaTarefaDoMonitor () throws SQLException {
		 String sql = "CREATE TABLE IF NOT EXISTS TarefaDoMonitor ("
	                + "idTarefaMonitor integer PRIMARY KEY AUTOINCREMENT,"
                        +"codTarefa INTEGER,codProf INTEGER,codMonit INTEGER, data TEXT NOT NULL, validade INTEGER"
	                + ");";	     
		 Statement stmt = conn.createStatement();
		 stmt.execute(sql);
	}
        
        private void criarTabelaTarefa () throws SQLException {
		 String sql = "CREATE TABLE IF NOT EXISTS Tarefa ("
	                + "idTarefa integer PRIMARY KEY AUTOINCREMENT,"
                        +"conteudo TEXT NOT NULL,estado INTEGER, validade INTEGER, idCriador INTEGER, tipoCriador INTEGER"
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
        
        private void criarTabelaRelatorioMonitoria() throws SQLException{
            String sql = "CREATE TABLE IF NOT EXISTS RelatorioMonitoria ("
	                + "idRelatorio integer PRIMARY KEY AUTOINCREMENT,"
                        +"codMonitor INTEGER,"
                        + "codTarefa INTEGER, "
                        +"data TEXT NOT NULL,"
                        + "nivelDeDificuldade INTEGER,"
                        + "reforcarAssunto INTEGER,"
                        +"participatividade INTEGER,"
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
       public static void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println("Error" + e);
            }
        }
    }
	
        public static void closeConnection(Connection conn, Statement stat){
            if(stat!= null){
                try{
                    stat.close();
                    
                }catch(SQLException e){
                    System.err.println("Error" + e);
                }
            }
            
            closeConnection(conn);
            
        }
	private Connection connect() throws SQLException {        
            this.conn = DriverManager.getConnection(url);
            return conn;
    }
	
	public Connection getConnection() {
		return this.conn;
	}
        
}
