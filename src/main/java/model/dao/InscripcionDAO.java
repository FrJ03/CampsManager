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
 * Clase InscripcionDAO que realiza las consultas relacionadas con las inscripciones.
 * @author Manuel García Obrero
 */

public class InscripcionDAO implements InterfaceDAO<RegistrationDTO>{ 
	/**
	 * Variable privada Singleton.
	 */
	private static InscripcionDAO instance_= null;
	/*
	 * *Representa la dirección al fichero properties.
	 */
	private static String dir_ = "sql.properties";
	/**
	 * Metodo que sirve de acceso a la instancia.
	 * @return Instancia de la clase InscripcionDAO.
	 */
	public static InscripcionDAO getInstance() {
		if(instance_ == null) {
			instance_ = new InscripcionDAO();
		}
		return instance_;
	}
	/**
	 * Constructor vacío de la clase MonitorDAO.
	 */
	private InscripcionDAO() {}
	
	/**
	 * Añade una nueva inscripcion a la base de datos.
	 * @param object Inscripcion el cual va a ser añadido a la base de datos.
	 * @return boolean
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
	 * Lee una Inscripcion de la base de datos, dado el id del campamento y el id del participante.
	 * @param idCampamento y idParticipante que son los que se van a leer de la base de datos.
	 * @return Incripcion
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
	 * Lee una Inscripcion de la base de datos.
	 * @param Inscripcion Incripcion con el IdParticipante y IdCampamento que se va a leer de la base de datos.
	 * @return IncripcionCompleta o InscripcionParcial, depende del tipo que sea la Inscripcion
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
	 * Elimina un monitor de la base de datos.
	 * @param object Monitor el cual se va a eliminar de la base de datos.
	 * @return boolean
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
