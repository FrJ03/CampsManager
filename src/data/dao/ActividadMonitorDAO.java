package data.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


/**
 * Clase ActividadMonitorDAO que realiza las consultas relacionada con la tabla Actividad-Monitor.
 * @author Manuel García Obrero
 */

public class ActividadMonitorDAO implements InterfaceDAO<ActividadMonitorDTO>{
	/**
	 * Variable privada Singleton.
	 */
	private static ActividadMonitorDAO instance_= null;
	/*
	 * *Representa la dirección al fichero properties.
	 */
	private static String dir_ = "rutas.txt";
	/**
	 * Metodo que sirve de acceso a la instancia.
	 * @return Instancia de la clase ActividadMonitorDTO.
	 */
	public static ActividadMonitorDAO getInstance() {
		if(instance_ == null) {
			instance_= new ActividadMonitorDAO();
		}
		return instance_;
	}
	/**
	 * Constructor vacío de la clase ActividadMonitorDAO.
	 */
	private ActividadMonitorDAO() {}
	
	/**
	 * Añade una nuevo ActividadMonitor a la base de datos.
	 * @param object ActividadMonitorDTO el cual va a ser añadido a la base de datos.
	 * @return boolean
	 */
	@Override
	public boolean create(ActividadMonitorDTO object) {
		BufferedReader reader = null;
		int status = 0;
		boolean res = false;
		Connector con = new Connector();
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String create = p.getProperty("createActividadMonitor");
			
			System.out.println(create);
			Connection c=con.getConnection();
			PreparedStatement ps=c.prepareStatement(create);
			
			ps.setInt(1,object.getActId());
			ps.setInt(2,object.getMonId());
			
			status = ps.executeUpdate();	
			if (status == 1) {
				res = true;
			}
			con.deleteConnection(c);
			
		} catch(Exception e) { System.out.println(e); }
		
		return res;
	}
	
	/**
	 * Lee un ActividadMonitor de la base de datos.
	 * @param ActividadMonitorDTO CampamentoActividadDTO con el idActividad y idMonitor que se va a leer de la base de datos.
	 * @return ActividadMonitorDTO
	 */
	@Override
	public ActividadMonitorDTO read(ActividadMonitorDTO object) {
		
		BufferedReader reader = null;
		Connector con = new Connector();
		
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("readActividadMonitor");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
			ps.setInt(1, object.getActId());
			ps.setInt(2, object.getMonId());
	
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
			object	= new ActividadMonitorDTO(rs.getInt(1), rs.getInt(2));
            } 
			
			con.deleteConnection(c);
		} catch(Exception e) { System.out.println(e); }
		
		return object;
	}
	
	/**
	 * Elimina un ActividadMonitor de la base de datos.
	 * @param object ActividadMonitorDTO el cual se va a eliminar de la base de datos.
	 * @return boolean
	 */
	@Override
	public boolean delete(ActividadMonitorDTO object) {
		int rs =0;
		boolean status = false;
		BufferedReader reader = null;
		Connector con = new Connector();
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("deleteActividadMonitor");
			
			Connection c=con.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement(query);
			preparedStatement.setInt(1, object.getActId());
			preparedStatement.setInt(2, object.getMonId());
	
			rs = preparedStatement.executeUpdate(); 
			
			if(rs == 1) {
				status = true;
			}
			
			con.deleteConnection(c);
			
		} catch(Exception e) { System.out.println(e); }
		
		return status;
	}
}
