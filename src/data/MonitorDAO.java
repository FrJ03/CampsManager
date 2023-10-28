package data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import business.Monitor; 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

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
	 * @return boolean
	 */
	@Override
	public boolean create(Monitor object) {
		
		BufferedReader reader = null;
		int status = 0;
		boolean res = false;
		Connector con = new Connector();
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String create = p.getProperty("createMonitor");
			
			System.out.println(create);
			Connection c=con.getConnection();
			PreparedStatement ps=c.prepareStatement(create);
			
			ps.setInt(1,object.getId());
			ps.setString(2,object.getNombre());
			ps.setString(3,object.getApellidos());
			ps.setBoolean(4,object.getEspecial());
			
			status = ps.executeUpdate();	
			if (status == 1) {
				res = true;
			}
			con.deleteConnection(c);
			
		} catch(Exception e) { System.out.println(e); }
		
		return res;
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
		Connector con = new Connector();
		ArrayList<Monitor> list = new ArrayList<Monitor>();
		
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("readAllMonitor");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
	
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				list.add( new Monitor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4)));
                
            } 
			
			con.deleteConnection(c);
		} catch(Exception e) { System.out.println(e); }
		
		return monitor;
		
	}
	/**
	 * Elimina un monitor de la base de datos.
	 * @param object Monitor el cual se va a eliminar de la base de datos.
	 * @return boolean
	 */
	@Override
	public boolean delete(Monitor object) {
		
		int rs =0;
		boolean status = false;
		BufferedReader reader = null;
		Connector con = new Connector();
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("deleteMonitor");
			
			Connection c=con.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement(query);
	        preparedStatement.setInt(1, object.getId());
	
			rs = preparedStatement.executeUpdate(); 
			
			if(rs == 1) {
				status = true;
			}
			
			con.deleteConnection(c);
			
		} catch(Exception e) { System.out.println(e); }
		
		return status;
		
	}
	/**
	 * Añade todos los monitores de la base de datos a un lista.
	 * @return Array<Monitor>
	 */
	public ArrayList<Monitor>readall(){

		BufferedReader reader = null;
		Connector con = new Connector();
		ArrayList<Monitor> list = new ArrayList<Monitor>();
		
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("readAllMonitor");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
	
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
                list.add( new Monitor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4)));
                
            } 
			
			con.deleteConnection(c);
			
		} catch(Exception e) { System.out.println(e); }
		
		return list;
	}

}
