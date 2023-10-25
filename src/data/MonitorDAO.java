package data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import business.Monitor; 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Clase MonitorDAO que realiza las consultas relacionadas con los monitores.
 * @author Enrique de los Reyes Montilla
 */
public class MonitorDAO implements InterfaceDAO<Monitor> {
	/**
	 * Variable privada Singleton.
	 */
	private static MonitorDAO instance_= null;
	/*
	 * *Representa la dirección al fichero properties.
	 */
	private static String dir_ = "rutas.txt";
	/**
	 * Metodo que sirve de acceso a la instancia.
	 * @return Instancia de la clase MonitorDAO.
	 */
	public static MonitorDAO getInstance() {
		if(instance_ == null) {
			return new MonitorDAO();
		}
		return instance_;
	}
	/**
	 * COnstructor vacío de la clase MonitorDAO.
	 */
	private MonitorDAO() {}
	/**
	 * Añade un nuevo monitor a la base de datos.
	 * @param object Monitor el cual va a ser añadido a la base de datos.
	 * @return int
	 */
	@Override
	public int create(Monitor object) {
		
		BufferedReader reader = null;
		int status = 0;
		
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String create = p.getProperty("createMonitor");
			
			Connection con=getConnection();
			PreparedStatement ps=con.prepareStatement(create);
			
			ps.setInt(1,object.getId());
			ps.setString(2,object.getNombre());
			ps.setString(3,object.getApellidos());
			ps.setBoolean(4,object.getEspecial());
			
			status = ps.executeUpdate();	
			deleteConnection(con);
			
		} catch(Exception e) { System.out.println(e); }
		
		return status;
	}
	/**
	 * Lee un monitor de la base de datos.
	 * @param id Id del monitor que se va a leer de la base de datos.
	 * @return Monitor
	 */
	@Override
	public Monitor read(int id) {
		
		Monitor monitor = null;
		BufferedReader reader = null;
		
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("readMonitor");
			
			Connection con=getConnection();
			PreparedStatement ps=con.prepareStatement(query);
			ps.setInt(2,id);
	
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
                monitor = new Monitor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4));
                
            } 
			
			deleteConnection(con);
		} catch(Exception e) { System.out.println(e); }
		
		return monitor;
		
	}
	/**
	 * Elimina un monitor de la base de datos.
	 * @param object Monitor el cual se va a eliminar de la base de datos.
	 * @return int
	 */
	@Override
	public int delete(Monitor object) {
		
		int rs =0;
		BufferedReader reader = null;
		
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("deleteMonitor");
			
			Connection con=getConnection();
			PreparedStatement preparedStatement = con.prepareStatement(query);
	        preparedStatement.setInt(1, object.getId());
	
			rs = preparedStatement.executeUpdate(); 
			
			deleteConnection(con);
			
		} catch(Exception e) { System.out.println(e); }
		
		return rs;
		
	}
	/**
	 * Metodo para realizar la conexión con la base de datos.
	 * @return Connection
	 */
	@Override
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
			
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(dataBase,user,password);
			
		} catch(Exception e) {
			
		System.out.println(e);
		
		}
		
		 return con;
		
	}
	/**
	 * Método que permite la desconexión de la base de datos.
	 * @param Connection Conector que permite el acceso a la base de datos.
	 * @return void
	 */
	@Override
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
