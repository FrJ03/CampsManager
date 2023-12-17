package model.dao;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import model.common.Connector;
import controller.dto.assistant.*;

/**
 * Class that manage database access related to assistant implemented using Singleton design pattern
 * @author Enrique de los Reyes Montilla
 */
public class AsistenteDAO implements InterfaceDAO<AssistantDTO>{
	/**
	 * Singleton private attribute.
	 */
	private static AsistenteDAO instance_= null;
	/**
	 * Instance access method.
	 * @return AsistenteDAO instance.
	 */
	public static AsistenteDAO getInstance() {
		if(instance_ == null) {
			return new AsistenteDAO();
		}
		return instance_;
	}
	/**
	 * Empty constructor.
	 */
	private AsistenteDAO() {}
	/**
	 * Add a new assistant to the database.
	 * @param object Assistant to be added.
	 * @return boolean true if the assistant has been added correctly, else otherwise
	 */
	@Override
	public boolean create(AssistantDTO object) {
		BufferedReader reader = null;
		int status = 0;
		boolean res = false;
		Connector con = new Connector();
		try{
			
			Properties p = new Properties();	
			InputStream queries = getClass().getClassLoader().getResourceAsStream("sql.properties");
			reader = new BufferedReader(new InputStreamReader(queries));
			p.load(reader);
			String create = p.getProperty("createAsistente");
			
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
	 * Read an assistant from the database.
	 * @param Asistente Assistant that contains the assistant id to read.
	 * @return AssistantDTO Assistant red if it has red correctly, else otherwise
	 */
	@Override
	public AssistantDTO read(AssistantDTO object) {
		
		BufferedReader reader = null;
		Connector con = new Connector();
		AssistantDTO as = null;
		
		try{
			
			Properties p = new Properties();	
			InputStream queries = getClass().getClassLoader().getResourceAsStream("sql.properties");
			reader = new BufferedReader(new InputStreamReader(queries));
			p.load(reader);
			String query = p.getProperty("readAsistente");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
			ps.setInt(1, object.getId());
	
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
				object = new AssistantDTO(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(6).toLocalDate(), rs.getBoolean(5));
                as = object;
            } 
			
			con.deleteConnection(c);
		} catch(Exception e) { System.out.println(e); }
		
		return as;

	}
	/**
	 * Delete an assistant.
	 * @param object Assistant to be deleted.
	 * @return boolean true if the assistant has been deleted, else otherwise
	 */
	@Override
	public boolean delete(AssistantDTO object) {
		
		int rs =0;
		boolean status = false;
		BufferedReader reader = null;
		Connector con = new Connector();
		try{
			
			Properties p = new Properties();	
			InputStream queries = getClass().getClassLoader().getResourceAsStream("sql.properties");
			reader = new BufferedReader(new InputStreamReader(queries));
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
	 * Read all assistant in the database.
	 * @return ArrayList<AssistantDTO> List of assistants
	 */
	public ArrayList<AssistantDTO> readAll(){

		BufferedReader reader = null;
		Connector con = new Connector();
		ArrayList<AssistantDTO> list = new ArrayList<AssistantDTO>();
		
		try{
			
			Properties p = new Properties();	
			InputStream queries = getClass().getClassLoader().getResourceAsStream("sql.properties");
			reader = new BufferedReader(new InputStreamReader(queries));
			p.load(reader);
			String query = p.getProperty("readAllAsistente");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
	
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
                list.add(  new AssistantDTO(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(6).toLocalDate(), rs.getBoolean(5)));
                
            } 
			
			con.deleteConnection(c);
			
		} catch(Exception e) { System.out.println(e); }
		
		return list;
	}
	/**
	 * Update an assistant.
	 * @param object Assistant to be updated.
	 * @return boolean true if the assistant has been updated correctly, else otherwise
	 */
	public boolean update(AssistantDTO object){

		BufferedReader reader = null;
		Connector con = new Connector();
		boolean status = false;
		
		try{
			
			Properties p = new Properties();	
			InputStream queries = getClass().getClassLoader().getResourceAsStream("sql.properties");
			reader = new BufferedReader(new InputStreamReader(queries));
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
	 * Read an assistant from the database.
	 * @param id Assistant id to read.
	 * @return AssistantDTO Assistant red if it has red correctly, else otherwise
	 */
	public AssistantDTO read(int id) {
		
		BufferedReader reader = null;
		Connector con = new Connector();
		AssistantDTO as = null;
		
		try{
			
			Properties p = new Properties();	
			InputStream queries = getClass().getClassLoader().getResourceAsStream("sql.properties");
			reader = new BufferedReader(new InputStreamReader(queries));
			p.load(reader);
			String query = p.getProperty("readAsistente");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
			ps.setInt(1, id);
	
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
				as =  new AssistantDTO(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(6).toLocalDate(), rs.getBoolean(5));
                
            } 
			
			con.deleteConnection(c);
		} catch(Exception e) { System.out.println(e); }
		
		return as;

	}
	/**
	 * Read an assistant from the database.
	 * @param email Assistant id to read.
	 * @return AssistantDTO Assistant red if it has red correctly, else otherwise
	 */
	public AssistantDTO read(String email) {
		
		BufferedReader reader = null;
		Connector con = new Connector();
		AssistantDTO as = null;
		
		try{
			
			Properties p = new Properties();	
			InputStream queries = getClass().getClassLoader().getResourceAsStream("sql.properties");
			reader = new BufferedReader(new InputStreamReader(queries));
			p.load(reader);
			String query = p.getProperty("readAsistenteEmail");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
			ps.setString(1, email);
	
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
				as =  new AssistantDTO(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(6).toLocalDate(), rs.getBoolean(5));
                
            } 
			
			con.deleteConnection(c);
		} catch(Exception e) { System.out.println(e); }
		
		return as;

	}
}
