package model.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import controller.dto.admin.AdminDTO;
import model.common.Connector;
/**
 * DAO que permite gestionar los accesos a la base de datos relacionados con un admin.
 * @author Enrique de los Reyes Montilla
 */
public class AdminDAO implements InterfaceDAO<AdminDTO>{
	/**
	 * Variable privada Singleton.
	 */
	private static AdminDAO instance_= null;
	/*
	 * *Representa la dirección al fichero properties.
	 */
	private static String dir_ = "sql.properties";
	/**
	 * Metodo que sirve de acceso a la instancia.
	 * @return Instancia de la clase AdminDAO.
	 */
	public static AdminDAO getInstance() {
		if(instance_ == null) {
			return new AdminDAO();
		}
		return instance_;
	}
	/**
	 * Constructor vacío de la clase AdminDAO.
	 */
	private AdminDAO() {}
	/**
	 * Añade un nuevo Admin a la base de datos.
	 * @param object Admin el cual va a ser añadido a la base de datos.
	 * @return boolean
	 */
	@Override
	public boolean create(AdminDTO object) {
		BufferedReader reader = null;
		int status = 0;
		boolean res = false;
		Connector con = new Connector();
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String create = p.getProperty("createAdmin");
			
			Connection c=con.getConnection();
			PreparedStatement ps=c.prepareStatement(create);
			
			ps.setString(1,object.getEmail());
			ps.setString(2,object.getNombre());
			ps.setString(3,object.getApellidos());
			ps.setBoolean(4,object.getEspecial());
			ps.setDate(5, java.sql.Date.valueOf(object.get_fechaNacimiento()));
			
			status = ps.executeUpdate();	
			if (status == 1) {
				res = true;
			}
			con.deleteConnection(c);
			
		} catch(Exception e) { System.out.println(e); }
		
		return res;
		
	}
	/**
	 * Lee un Admin de la base de datos.
	 * @param Admin Admin con el id se va a leer de la base de datos.
	 * @return Monitor
	 */
	@Override
	public AdminDTO read(AdminDTO object) {
		
		BufferedReader reader = null;
		Connector con = new Connector();
		AdminDTO as = null;
		
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("readAdmin");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
			ps.setInt(1, object.getId());
	
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
				object = new AdminDTO(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(6).toLocalDate(), rs.getBoolean(5));
                as = object;
            } 
			
			con.deleteConnection(c);
		} catch(Exception e) { System.out.println(e); }
		
		return as;

	}
	/**
	 * Elimina un Admin de la base de datos.
	 * @param object Admin el cual se va a eliminar de la base de datos.
	 * @return boolean
	 */
	@Override
	public boolean delete(AdminDTO object) {
		
		int rs =0;
		boolean status = false;
		BufferedReader reader = null;
		Connector con = new Connector();
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("deleteAdmin");
			
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
	 * Actualiza un Admin de la base de datos.
	 * @param object Admin el cual va a ser actualizado.
	 * @return boolean
	 */
	public boolean update(AdminDTO object){

		BufferedReader reader = null;
		Connector con = new Connector();
		boolean status = false;
		
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("updateAdmin");
			
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
	 * Lee un Admin de la base de datos.
	 * @param id Id que se va a leer de la base de datos.
	 * @return Monitor
	 */
	public AdminDTO read(int id) {
		
		BufferedReader reader = null;
		Connector con = new Connector();
		AdminDTO as = null;
		
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("readAdmin");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
			ps.setInt(1, id);
	
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
				as =  new AdminDTO(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(6).toLocalDate(), rs.getBoolean(5));
                
            } 
			
			con.deleteConnection(c);
		} catch(Exception e) { System.out.println(e); }
		
		return as;

	}
	/**
	 * Lee un Admin de la base de datos.
	 * @param email Email del Admin que se va a leer de la base de datos.
	 * @return Monitor
	 */
	public AdminDTO read(String email) {
		
		BufferedReader reader = null;
		Connector con = new Connector();
		AdminDTO as = null;
		
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("readAdminEmail");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
			ps.setString(1, email);
	
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
				as =  new AdminDTO(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(6).toLocalDate(), rs.getBoolean(5));
                
            } 
			
			con.deleteConnection(c);
		} catch(Exception e) { System.out.println(e); }
		
		return as;

	}
}

