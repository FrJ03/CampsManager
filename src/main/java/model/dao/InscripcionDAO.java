package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import model.common.Connector;
import controller.dto.registration.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Class that manage database access related to registrations implemented using Singleton design pattern
 * @author Manuel García Obrero
 */
public class InscripcionDAO implements InterfaceDAO<RegistrationDTO>{ 
	/**
	 * Singleton private attribute.
	 */
	private static InscripcionDAO instance_= null;
	/**
	 * Properties path
	 */
	private String dir_;
	/**
	 * Singleton private constructor
	 * @param dir Properties path
	 */
	private InscripcionDAO(String dir) {
		dir_=dir;
	};
	/**
	 * Instance access method
	 * @return InscripcionDAO Instance
	 */
	public static InscripcionDAO getInstance(String dir) {
		if(instance_ == null ) {
			instance_ = new InscripcionDAO(dir);
		}
		return instance_;
	}
	
	/**
	 * Add a new registration
	 * @param object Registration to be added.
	 * @return boolean true if the registration has been added correctly, false otherwise
	 */
	@Override
	public boolean create(RegistrationDTO object) {
		BufferedReader reader = null;
		int status = 0;
		boolean res = false;
		Connector con = new Connector();
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String create = p.getProperty("createInscripcion");
			
			Connection c=con.getConnection();
			PreparedStatement ps=c.prepareStatement(create);
			
			ps.setInt(1,object.getIdParticipante());
			ps.setInt(2,object.getIdCampamento());
			ps.setDate(3,java.sql.Date.valueOf(object.getFechaInscripcion()));
			ps.setFloat(4,object.getPrecio());
			ps.setString(5,object.getTipoName());
			ps.setString(6, object.getTemporalidadName());
			
			status = ps.executeUpdate();	
			if (status == 1) {
				res = true;
			}
			con.deleteConnection(c);
			
		} catch(Exception e) { System.out.println(e); }
		
		return res;
	}
	/**
	 * Read a registration
	 * @param idCampamento Camp id
	 * @param idParticipante Assistant id
	 * @return RegistrationDTO Registration if it exists, null otherwise
	 */
	public RegistrationDTO read(int idCampamento, int idParticipante) {
			
			BufferedReader reader = null;
			Connector con = new Connector();
			RegistrationDTO object = null;
			
			
			try{
				
				Properties p = new Properties();	
				reader = new BufferedReader(new FileReader(new File(dir_)));
				p.load(reader);
				String query = p.getProperty("readInscripcion");
				
				Connection c = con.getConnection();
				
				PreparedStatement ps=c.prepareStatement(query);
				ps.setInt(1, idParticipante);
				ps.setInt(2, idCampamento);
		
				ResultSet rs = ps.executeQuery();
				
				if (rs.next()) {
					object	= new RegistrationDTO(rs.getInt(1), rs.getInt(2), rs.getDate(3).toLocalDate(), rs.getFloat(4), rs.getString(5), rs.getString(6));
	            } 
	
				con.deleteConnection(c);
			} catch(Exception e) { System.out.println(e); }
			
			return object;
	}
	/**
	 * Read a registration
	 * @param object Registration that contains the camp id and assistant id
	 * @return RegistrationDTO Registration if it exists, null otherwise
	 */
	@Override
	public RegistrationDTO read(RegistrationDTO object) {
		
		BufferedReader reader = null;
		Connector con = new Connector();
		RegistrationDTO ins = null;
		
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("readInscripcion");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
			ps.setInt(1, object.getIdParticipante());
			ps.setInt(2, object.getIdCampamento());
	
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				object	= new RegistrationDTO(rs.getInt(1), rs.getInt(2), rs.getDate(3).toLocalDate(), rs.getFloat(4), rs.getString(5), rs.getString(6));
				ins = object;
            } 
			
			con.deleteConnection(c);
		} catch(Exception e) { System.out.println(e); }
		
		return ins;
	}
	
	/**
	 * Delete a registration.
	 * @param Registration that contains the camp id and assistant id.
	 * @return boolean true if the registration has been deleted, false otherwise
	 */
	@Override
	public boolean delete(RegistrationDTO object) {
		int rs =0;
		boolean status = false;
		BufferedReader reader = null;
		Connector con = new Connector();
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("deleteInscripcion");
			
			Connection c=con.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement(query);
	        preparedStatement.setInt(1, object.getIdParticipante());
	        preparedStatement.setInt(2, object.getIdCampamento());
	
			rs = preparedStatement.executeUpdate(); 
			
			if(rs == 1) {
				status = true;
			}
			
			con.deleteConnection(c);
			
		} catch(Exception e) { System.out.println(e); }
		
		return status;

	}
	/**
	 * Delete a registration
	 * @param idCamp Camp identifier
	 * @param idAssistant Assistant identifier
	 * @return boolean True if the registration has been deleted, false otherwise
	 */
	public boolean delete(int idCamp, int idAssistant) {
		int rs =0;
		boolean status = false;
		BufferedReader reader = null;
		Connector con = new Connector();
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("deleteInscripcion");
			
			Connection c=con.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement(query);
	        preparedStatement.setInt(1, idAssistant);
	        preparedStatement.setInt(2, idCamp);
	
			rs = preparedStatement.executeUpdate(); 
			
			if(rs == 1) {
				status = true;
			}
			
			con.deleteConnection(c);
			
		} catch(Exception e) { System.out.println(e); }
		
		return status;

	}
	/**
	 * Read all registrations
	 * @return ArrayList<RegistrationDTO> List of registrations
	 */
	public ArrayList<RegistrationDTO>readAll(){

		BufferedReader reader = null;
		Connector con = new Connector();
		ArrayList<RegistrationDTO> list = new ArrayList<RegistrationDTO>();
		
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("readAllInscripcion");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
	
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
                list.add( new RegistrationDTO(rs.getInt(1), rs.getInt(2), rs.getDate(3).toLocalDate(), rs.getFloat(4), rs.getString(5), rs.getString(6)));
            } 
			
			con.deleteConnection(c);
			
		} catch(Exception e) { System.out.println(e); }
		
		return list;
	}
	/**
	 * Read all complete registrations
	 * @return ArrayList<RegistrationDTO> List of registrations
	 */
	public ArrayList<RegistrationDTO>readAllComplete(){

		BufferedReader reader = null;
		Connector con = new Connector();
		ArrayList<RegistrationDTO> list = new ArrayList<RegistrationDTO>();
		
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("readAllInscripcionCompleta");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
	
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
                list.add( new RegistrationDTO(rs.getInt(1), rs.getInt(2), rs.getDate(3).toLocalDate(), rs.getFloat(4), rs.getString(5), rs.getString(6)));
            } 
			
			con.deleteConnection(c);
			
		} catch(Exception e) { System.out.println(e); }
		
		return list;
	}
	/**
	 * Read all partial registrations
	 * @return ArrayList<RegistrationDTO> List of registrations
	 */
	public ArrayList<RegistrationDTO>readAllPartial(){

		BufferedReader reader = null;
		Connector con = new Connector();
		ArrayList<RegistrationDTO> list = new ArrayList<RegistrationDTO>();
		
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("readAllInscripcionParcial");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
	
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
                list.add( new RegistrationDTO(rs.getInt(1), rs.getInt(2), rs.getDate(3).toLocalDate(), rs.getFloat(4), rs.getString(5), rs.getString(6)));
            } 
			
			con.deleteConnection(c);
			
		} catch(Exception e) { System.out.println(e); }
		
		return list;
	}
}
