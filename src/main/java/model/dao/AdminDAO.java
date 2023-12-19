package model.dao;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import controller.dto.admin.AdminDTO;
import model.common.Connector;

/**
 * Class that manage database access related to administrators implemented using Singleton design pattern
 * @author Enrique de los Reyes Montilla
 */
public class AdminDAO implements InterfaceDAO<AdminDTO>{
	/**
	 * Singleton private attribute.
	 */
	private static AdminDAO instance_= null;
	/**
	 * Instance access method.
	 * @return AdminDAO instance.
	 */
	public static AdminDAO getInstance() {
		if(instance_ == null) {
			return new AdminDAO();
		}
		return instance_;
	}
	/**
	 * Empty constructor.
	 */
	private AdminDAO() {}
	/**
	 * Add a new admin to the database.
	 * @param object Admin to add.
	 * @return boolean true if the admin has been added correctly, else otherwise
	 */
	@Override
	public boolean create(AdminDTO object) {
		BufferedReader reader = null;
		int status = 0;
		boolean res = false;
		Connector con = new Connector();
		try{
			
			Properties p = new Properties();	
			InputStream queries = getClass().getClassLoader().getResourceAsStream("sql.properties");
			reader = new BufferedReader(new InputStreamReader(queries));
			p.load(reader);
			String create = p.getProperty("createAdmin");
			
			Connection c=con.getConnection();
			PreparedStatement ps=c.prepareStatement(create);
			
			ps.setString(1,object.getEmail());
			ps.setString(2,object.getNombre());
			ps.setString(3,object.getApellidos());
			
			status = ps.executeUpdate();	
			if (status == 1) {
				res = true;
			}
			con.deleteConnection(c);
			
		} catch(Exception e) { System.out.println(e); }
		
		return res;
		
	}
	/**
	 * Read an admin from the database.
	 * @param Admin Admin with contains the admin id to read.
	 * @return AdminDTO Admin red if it exists, null otherwise
	 */
	@Override
	public AdminDTO read(AdminDTO object) {
		
		BufferedReader reader = null;
		Connector con = new Connector();
		AdminDTO as = null;
		
		try{
			
			Properties p = new Properties();	
			InputStream queries = getClass().getClassLoader().getResourceAsStream("sql.properties");
			reader = new BufferedReader(new InputStreamReader(queries));
			p.load(reader);
			String query = p.getProperty("readAdmin");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
			ps.setInt(1, object.getId());
	
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
				object = new AdminDTO(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4));
                as = object;
            } 
			
			con.deleteConnection(c);
		} catch(Exception e) { System.out.println(e); }
		
		return as;

	}
	/**
	 * Delete an admin.
	 * @param object Admin with contains the admin id to delete.
	 * @return boolean true if the admin has been deleted, else otherwise
	 */
	@Override
	public boolean delete(AdminDTO object) {
		
		int rs =0;
		boolean status = false;
		BufferedReader reader = null;
		Connector con = new Connector();
		try{
			
			Properties p = new Properties();	
			InputStream queries = getClass().getClassLoader().getResourceAsStream("sql.properties");
			reader = new BufferedReader(new InputStreamReader(queries));
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
	 * Update an admin.
	 * @param object Admin to update.
	 * @return boolean true if the admin has been updated, else otherwise
	 */
	public boolean update(AdminDTO object){

		BufferedReader reader = null;
		Connector con = new Connector();
		boolean status = false;
		
		try{
			
			Properties p = new Properties();	
			InputStream queries = getClass().getClassLoader().getResourceAsStream("sql.properties");
			reader = new BufferedReader(new InputStreamReader(queries));
			p.load(reader);
			String query = p.getProperty("updateAdmin");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
	
			ps.setString(1, object.getNombre());
            ps.setString(2, object.getApellidos());
            ps.setInt(3, object.getId());
            
			int rs = ps.executeUpdate();
			
			if(rs == 1) {
				status = true;
			}
			
			con.deleteConnection(c);
			
		} catch(Exception e) { System.out.println(e); }
		
		return status;
	}
	
	/**
	 * Read an admin from the database.
	 * @param id Admin id to read.
	 * @return AdminDTO Admin red if it exists, null otherwise
	 */
	public AdminDTO read(int id) {
		
		BufferedReader reader = null;
		Connector con = new Connector();
		AdminDTO as = null;
		
		try{
			
			Properties p = new Properties();	
			InputStream queries = getClass().getClassLoader().getResourceAsStream("sql.properties");
			reader = new BufferedReader(new InputStreamReader(queries));
			p.load(reader);
			String query = p.getProperty("readAdmin");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
			ps.setInt(1, id);
	
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
				as =  new AdminDTO(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4));
                
            } 
			
			con.deleteConnection(c);
		} catch(Exception e) { System.out.println(e); }
		
		return as;

	}
	/**
	 * Read an admin from the database.
	 * @param email Admin email to read.
	 * @return AdminDTO Admin red if it exists, null otherwise
	 */
	public AdminDTO read(String email) {
		
		BufferedReader reader = null;
		Connector con = new Connector();
		AdminDTO as = null;
		
		try{
			
			Properties p = new Properties();	
			InputStream queries = getClass().getClassLoader().getResourceAsStream("sql.properties");
			reader = new BufferedReader(new InputStreamReader(queries));
			p.load(reader);
			String query = p.getProperty("readAdminEmail");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
			ps.setString(1, email);
	
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
				as =  new AdminDTO(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4));
                
            } 
			
			con.deleteConnection(c);
		} catch(Exception e) { System.out.println(e); }
		
		return as;

	}
}


