package model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import model.common.Connector;
import controller.dto.monitor.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Clase MonitorDAO que realiza las consultas relacionadas con los monitores.
 * @author Enrique de los Reyes Montilla
 */
public class MonitorDAO implements InterfaceDAO<MonitorDTO> {
	/**
	 * Variable privada Singleton.
	 */
	private static MonitorDAO instance_= null;
	/*
	 * *Representa la dirección al fichero properties.
	 */
	private static String dir_ = "sql.properties";
	/**
	 * Metodo que sirve de acceso a la instancia.
	 * @return Instancia de la clase MonitorDAO.
	 */
	public static MonitorDAO getInstance() {
		if(instance_ == null) {
			instance_ = new MonitorDAO();
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
	public boolean create(MonitorDTO object) {
		
		BufferedReader reader = null;
		int status = 0;
		boolean res = false;
		Connector con = new Connector();
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String create = p.getProperty("createMonitor");
			
			Connection c=con.getConnection();
			PreparedStatement ps=c.prepareStatement(create);
			
			ps.setString(1,object.getNombre());
			ps.setString(2,object.getApellidos());
			ps.setBoolean(3,object.getEspecial());
			
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
	 * @param Monitor Monitor con el id que se va a leer de la base de datos.
	 * @return Monitor
	 */
	@Override
	public MonitorDTO read(MonitorDTO object) {
		
		
		BufferedReader reader = null;
		Connector con = new Connector();
		MonitorDTO mon = null;
		
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("readMonitor");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
			ps.setInt(1, object.getId());
	
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
				object	= new MonitorDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4));
	            mon = object;    
			
            } 
			
			con.deleteConnection(c);
		} catch(Exception e) { System.out.println(e); }
		
		return mon;
		
	}
	/**
	 * Elimina un monitor de la base de datos.
	 * @param object Monitor el cual se va a eliminar de la base de datos.
	 * @return boolean
	 */
	@Override
	public boolean delete(MonitorDTO object) {
		
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
	public ArrayList<MonitorDTO>readAll(){

		BufferedReader reader = null;
		Connector con = new Connector();
		ArrayList<MonitorDTO> list = new ArrayList<MonitorDTO>();
		
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("readAllMonitor");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
	
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
                list.add( new MonitorDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4)));
                
            } 
			
			con.deleteConnection(c);
			
		} catch(Exception e) { System.out.println(e); }
		
		return list;
	}
	/**
	 * Lee un monitor de la base de datos.
	 * @param Id id del monitor que se va a leer de la base de datos.
	 * @return Monitor
	 */
	public MonitorDTO read(int id) {
		
		
		BufferedReader reader = null;
		Connector con = new Connector();
		MonitorDTO mon = null;
		
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("readMonitor");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
			ps.setInt(1, id);
	
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
				mon	= new MonitorDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4));    
			
            } 
			
			con.deleteConnection(c);
		} catch(Exception e) { System.out.println(e); }
		
		return mon;
		
	}
}
