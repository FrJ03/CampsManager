package data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import business.inscripciones.InscripcionCompleta;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import data.common.Connector;

/**
 * Clase InscripcionCompletaDAO que realiza las consultas relacionadas con las inscripciones.
 * @author Manuel García Obrero
 */

public class InscripcionCompletaDAO implements InterfaceDAO<InscripcionCompleta>{ 
	/**
	 * Variable privada Singleton.
	 */
	private static InscripcionCompletaDAO instance_= null;
	/*
	 * *Representa la dirección al fichero properties.
	 */
	private static String dir_ = "sql.properties";
	/**
	 * Metodo que sirve de acceso a la instancia.
	 * @return Instancia de la clase InscripcionCompletaDAO.
	 */
	public static InscripcionCompletaDAO getInstance() {
		if(instance_ == null) {
			instance_ = new InscripcionCompletaDAO();
		}
		return instance_;
	}
	/**
	 * Constructor vacío de la clase InscripcionCompletaDAO.
	 */
	private InscripcionCompletaDAO() {}
	
	/**
	 * Añade una nueva inscripcionCompleta a la base de datos.
	 * @param object InscripcionCompleta el cual va a ser añadido a la base de datos.
	 * @return boolean
	 */
	@Override
	public boolean create(InscripcionCompleta object) {
		BufferedReader reader = null;
		int status = 0;
		boolean res = false;
		Connector con = new Connector();
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String create = p.getProperty("createInscripcion");
			
			System.out.println(create);
			Connection c=con.getConnection();
			PreparedStatement ps=c.prepareStatement(create);
			
			ps.setInt(1,object.getIdParticipante());
			ps.setInt(2,object.getIdCampamento());
			ps.setDate(3,java.sql.Date.valueOf(object.getFechaInscripcion()));
			ps.setFloat(4,object.getPrecio());
			ps.setString(5,"Completa");
			ps.setString(6,object.getTemporalidad());
			
			status = ps.executeUpdate();	
			if (status == 1) {
				res = true;
			}
			con.deleteConnection(c);
			
		} catch(Exception e) { System.out.println(e); }
		
		return res;
	}
	/**
	 * Añade una nueva inscripcionCompleta a la base de datos donde la temporalidad es Temprano.
	 * @param object InscripcionCompleta el cual va a ser añadido a la base de datos.
	 * @return boolean
	 */
	public boolean createTemprano(InscripcionCompleta object) {
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
			ps.setString(5,"Completa");
			ps.setString(6,"Temprano");
			
			status = ps.executeUpdate();	
			if (status == 1) {
				res = true;
			}
			con.deleteConnection(c);
			
		} catch(Exception e) { System.out.println(e); }
		
		return res;
	}
	/**
	 * Añade una nueva inscripcionCompleta a la base de datos donde la temporalidad es Tardio.
	 * @param object InscripcionCompleta el cual va a ser añadido a la base de datos.
	 * @return boolean
	 */
	public boolean createTardio(InscripcionCompleta object) {
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
			ps.setString(5,"Completa");
			ps.setString(6,"Tardio");
			
			status = ps.executeUpdate();	
			if (status == 1) {
				res = true;
			}
			con.deleteConnection(c);
			
		} catch(Exception e) { System.out.println(e); }
		
		return res;
	}
	/**
	 * Lee una InscripcionCompleta de la base de datos, dado el id del campamento y el id del participante.
	 * @param idCampamento y idParticipante que son los que se van a leer de la base de datos.
	 * @return IncripcionCompleta 
	 */
		public InscripcionCompleta read(int idCampamento, int idParticipante) {
		
		BufferedReader reader = null;
		Connector con = new Connector();
		InscripcionCompleta object = null;
		
		
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
				if(rs.getString(5)=="Completa") {
					object	= new InscripcionCompleta(rs.getInt(1), rs.getInt(2), rs.getDate(3).toLocalDate(), rs.getFloat(4), rs.getString(5), rs.getString(6));
				}
            } 
			
			con.deleteConnection(c);
		} catch(Exception e) { System.out.println(e); }
		
		return object;
	}	
	/**
	 * Lee una InscripcionCompleta de la base de datos.
	 * @param InscripcionCompleta IncripcionCompleta con el IdParticipante y IdCampamento que se va a leer de la base de datos.
	 * @return IncripcionCompleta 
	 */
	@Override
	public InscripcionCompleta read(InscripcionCompleta object) {
		
		BufferedReader reader = null;
		Connector con = new Connector();
		InscripcionCompleta ins = null;
		
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
				if(rs.getString(5)=="Completa") {
					object	= new InscripcionCompleta(rs.getInt(1), rs.getInt(2), rs.getDate(3).toLocalDate(), rs.getFloat(4), rs.getString(5), rs.getString(6));
					ins=object;
				}
            } 
			
			con.deleteConnection(c);
		} catch(Exception e) { System.out.println(e); }
		
		return ins;
	}
	/**
	 * Elimina una Inscripción de la base de datos.
	 * @param object InscripcionCompleta el cual se va a eliminar de la base de datos.
	 * @return boolean
	 */
	@Override
	public boolean delete(InscripcionCompleta object) {
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
	 * Añade todos las inscripciones de la base de datos a un lista.
	 * @return ArrayList<InscripcionCompleta>
	 */
	public ArrayList<InscripcionCompleta>readAll(){

		BufferedReader reader = null;
		Connector con = new Connector();
		ArrayList<InscripcionCompleta> list = new ArrayList<InscripcionCompleta>();
		
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("readAllInscripcion");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
	
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
                list.add( new InscripcionCompleta(rs.getInt(1), rs.getInt(2), rs.getDate(3).toLocalDate(), rs.getFloat(4), rs.getString(5), rs.getString(6)));
            } 
			
			con.deleteConnection(c);
			
		} catch(Exception e) { System.out.println(e); }
		
		return list;
	}
}
