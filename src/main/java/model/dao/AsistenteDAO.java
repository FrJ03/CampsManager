package model.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import model.common.Connector;
import controller.dto.assistant.*;

/**
 * DAO que permite gestionar los accesos a la base de datos relacionados con asistentes.
 * @author Enrique de los Reyes Montilla
 */
public class AsistenteDAO implements InterfaceDAO<AssistantDTO>{
	/**
	 * Variable privada Singleton.
	 */
	private static AsistenteDAO instance_= null;
	/*
	 * *Representa la dirección al fichero properties.
	 */
	private static String dir_ = "sql.properties";
	/**
	 * Metodo que sirve de acceso a la instancia.
	 * @return Instancia de la clase AsistenteDAO.
	 */
	public static AsistenteDAO getInstance() {
		if(instance_ == null) {
			return new AsistenteDAO();
		}
		return instance_;
	}
	/**
	 * Constructor vacío de la clase AsistenteDAO.
	 */
	private AsistenteDAO() {}
	/**
	 * Añade un nuevo asistente a la base de datos.
	 * @param object Asistente el cual va a ser añadido a la base de datos.
	 * @return boolean
	 */
	@Override
	public boolean create(AssistantDTO object) {
		BufferedReader reader = null;
		int status = 0;
		boolean res = false;
		Connector con = new Connector();
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String create = p.getProperty("createAsistente");
			
			Connection c=con.getConnection();
			PreparedStatement ps=c.prepareStatement(create);
			
			ps.setString(1,object.getNombre());
			ps.setString(2,object.getApellidos());
			ps.setBoolean(3,object.getEspecial());
			ps.setDate(4, java.sql.Date.valueOf(object.get_fechaNacimiento()));
			
			status = ps.executeUpdate();	
			if (status == 1) {
				res = true;
			}
			con.deleteConnection(c);
			
		} catch(Exception e) { System.out.println(e); }
		
		return res;
		
	}
	/**
	 * Lee un asistente de la base de datos.
	 * @param Asistente Asistente con el id se va a leer de la base de datos.
	 * @return Monitor
	 */
	@Override
	public AssistantDTO read(AssistantDTO object) {
		
		BufferedReader reader = null;
		Connector con = new Connector();
		AssistantDTO as = null;
		
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("readAsistente");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
			ps.setInt(1, object.getId());
	
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
				object = new AssistantDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(5).toLocalDate(), rs.getBoolean(4));
                as = object;
            } 
			
			con.deleteConnection(c);
		} catch(Exception e) { System.out.println(e); }
		
		return as;

	}
	/**
	 * Elimina un asistente de la base de datos.
	 * @param object Asistente el cual se va a eliminar de la base de datos.
	 * @return boolean
	 */
	@Override
	public boolean delete(AssistantDTO object) {
		
		int rs =0;
		boolean status = false;
		BufferedReader reader = null;
		Connector con = new Connector();
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("deleteAsistente");
			
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
	 * Añade todos los asistentes de la base de datos a un lista.
	 * @return Array<Monitor>
	 */
	public ArrayList<AssistantDTO> readAll(){

		BufferedReader reader = null;
		Connector con = new Connector();
		ArrayList<AssistantDTO> list = new ArrayList<AssistantDTO>();
		
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("readAllAsistente");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
	
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
                list.add( new AssistantDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(5).toLocalDate(), rs.getBoolean(4)));
                
            } 
			
			con.deleteConnection(c);
			
		} catch(Exception e) { System.out.println(e); }
		
		return list;
	}
	/**
	 * Actualiza un asistente de la base de datos.
	 * @param object Asistente el cual va a ser actualizado.
	 * @return boolean
	 */
	public boolean update(AssistantDTO object){

		BufferedReader reader = null;
		Connector con = new Connector();
		boolean status = false;
		
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("updateAsistente");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
	
			ps.setString(1, object.getNombre());
            ps.setString(2, object.getApellidos());
            ps.setBoolean(3, object.getEspecial());
            ps.setDate(4, java.sql.Date.valueOf(object.get_fechaNacimiento()));
            ps.setInt(5, object.getId());
            
			int rs = ps.executeUpdate();
			
			if(rs == 1) {
				status = true;
			}
			
			con.deleteConnection(c);
			
		} catch(Exception e) { System.out.println(e); }
		
		return status;
	}
	
	/**
	 * Lee un asistente de la base de datos.
	 * @param id Id que se va a leer de la base de datos.
	 * @return Monitor
	 */
	public AssistantDTO read(int id) {
		
		BufferedReader reader = null;
		Connector con = new Connector();
		AssistantDTO as = null;
		
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("readAsistente");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
			ps.setInt(1, id);
	
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
				as = new AssistantDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(5).toLocalDate(), rs.getBoolean(4));
                
            } 
			
			con.deleteConnection(c);
		} catch(Exception e) { System.out.println(e); }
		
		return as;

	}
	
}
