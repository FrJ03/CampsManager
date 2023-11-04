	package data.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connector {
	/*
	 * Representa la dirección al fichero properties.
	 */
	private static String dir_ = "config.properties";
	/**
	 * Metodo para realizar la conexión con la base de datos.
	 * @return Connection
	 */
	public Connection getConnection() {
		BufferedReader reader = null;
		Connection con = null;
		
		//Obtenermos la URI, password y usuario de la base de datos mediante Properties.
		try{
			
			String password, user, dataBase;
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			
			dataBase = p.getProperty("dataBase");
			user = p.getProperty("user");
			password = p.getProperty("password");
			
			Class.forName(p.getProperty("driver"));

			con= (Connection)DriverManager.getConnection(dataBase,user,password);
			
		}catch (SQLException e) {
				e.printStackTrace();
		} catch (Exception e) {
				e.printStackTrace();
		}
		
		 return con;
		
	}

	/**
	 * Método que permite la desconexión de la base de datos.
	 * @param Connection Conector que permite el acceso a la base de datos.
	 * @return void
	 */
	public void deleteConnection(Connection conn) {
		try {
			
            if (conn != null) {
            	
                conn.close();
                
            }
        } catch (Exception e) {
        	
        	System.out.println(e);
        }
		
	}
}
