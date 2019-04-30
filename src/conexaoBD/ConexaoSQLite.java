/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexaoBD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author thais
 */
public class ConexaoSQLite {
    
  public Connection getConnection() {		
		try	{			
			return DriverManager.getConnection("jdbc:sqlite:banco.db");			
		} catch (SQLException e) {			
			throw new RuntimeException(e);
		}
	}

}
