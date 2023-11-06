package data.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import business.Actividad;
import business.Nivel;
import business.Turno;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Clase ActividadDAO que realiza las consultas relacionadas con las actividad.
 * @author Manuel García Obrero
 */

public class ActividadDAO implements InterfaceDAO<Actividad> {
	/**
	 * Variable privada Singleton.
	 */
	private static ActividadDAO instance_= null;
	/*
	 * *Representa la dirección al fichero properties.
	 */
	private static String dir_ = "sql.properties";
	/**
	 * Metodo que sirve de acceso a la instancia.
	 * @return Instancia de la clase ActividadDAO.
	 */
	public static ActividadDAO getInstance() {
		if(instance_ == null) {
			instance_ = new ActividadDAO();
		}
		return instance_;
	}
	/**
	 * Constructor vacío de la clase ActividadDAO.
	 */
	private ActividadDAO() {}
	
	/**
	 * Añade una nueva actividad a la base de datos.
	 * @param object Actividad el cual va a ser añadido a la base de datos.
	 * @return boolean
	 */
	@Override
	public boolean create(Actividad object) {
		BufferedReader reader = null;
		int status = 0;
		boolean res = false;
		Connector con = new Connector();
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String create = p.getProperty("createActividad");
			
			System.out.println(create);
			Connection c=con.getConnection();
			PreparedStatement ps=c.prepareStatement(create);
			
			ps.setInt(1,object.getId());
			ps.setString(2,object.getName());
			ps.setString(3,object.getNivel().name());
			ps.setString(4,object.getTurno().name());
			ps.setInt(5,object.getParticipantesMax());
			ps.setInt(6,object.getMonitoresMax());
			
			status = ps.executeUpdate();	
			if (status == 1) {
				res = true;
			}
			con.deleteConnection(c);
			
		} catch(Exception e) { System.out.println(e); }
		
		return res;
	}
	
	/**
	 * Lee una Actividad de la base de datos.
	 * @param Actividad Actividad con el id que se va a leer de la base de datos.
	 * @return Actividad
	 */
	@Override
	public Actividad read(Actividad object) {
		
		BufferedReader reader = null;
		Connector con = new Connector();
		
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("readActividad");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
			ps.setInt(1, object.getId());
	
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
			object	= new Actividad(rs.getInt(1), rs.getString(2), Nivel.valueOf(rs.getString(3)), rs.getInt(5), rs.getInt(6), Turno.valueOf(rs.getString(4)));
            } 
			
			con.deleteConnection(c);
		} catch(Exception e) { System.out.println(e); }
		
		return object;
	}
	
	/**
	 * Elimina una actividad de la base de datos.
	 * @param object Actividad el cual se va a eliminar de la base de datos.
	 * @return boolean
	 */
	@Override
	public boolean delete(Actividad object) {
		int rs =0;
		boolean status = false;
		BufferedReader reader = null;
		Connector con = new Connector();
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("deleteActividad");
			
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
	 * Añade todos las Actividades de la base de datos a un lista.
	 * @return Array<Actividad>
	 */
	public ArrayList<Actividad>readAll(){
		BufferedReader reader = null;
		Connector con = new Connector();
		ArrayList<Actividad> list = new ArrayList<Actividad>();
		
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("readAllActividad");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
	
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
                list.add( new Actividad(rs.getInt(1), rs.getString(2), Nivel.valueOf(rs.getString(3)), rs.getInt(5), rs.getInt(6), Turno.valueOf(rs.getString(4))));
                
            } 
			
			con.deleteConnection(c);
			
		} catch(Exception e) { System.out.println(e); }
		
		return list;
	}
}
