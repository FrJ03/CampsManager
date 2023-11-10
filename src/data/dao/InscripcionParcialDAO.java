package data.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import business.inscripciones.InscripcionParcial;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import data.common.Connector;

/**
 * Clase InscripcionParcialDAO que realiza las consultas relacionadas con las inscripciones.
 * @author Manuel García Obrero
 */

public class InscripcionParcialDAO implements InterfaceDAO<InscripcionParcial>{ 
	/**
	 * Variable privada Singleton.
	 */
	private static InscripcionParcialDAO instance_= null;
	/*
	 * *Representa la dirección al fichero properties.
	 */
	private static String dir_ = "sql.properties";
	/**
	 * Metodo que sirve de acceso a la instancia.
	 * @return Instancia de la clase InscripcionParcialDAO.
	 */
	public static InscripcionParcialDAO getInstance() {
		if(instance_ == null) {
			instance_ = new InscripcionParcialDAO();
		}
		return instance_;
	}
	/**
	 * Constructor vacío de la clase InscripcionParcialDAO.
	 */
	private InscripcionParcialDAO() {}
	
	/**
	 * Añade una nueva inscripcionParcial a la base de datos.
	 * @param object InscripcionParcial el cual va a ser añadido a la base de datos.
	 * @return boolean
	 */
	@Override
	public boolean create(InscripcionParcial object) {
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
			ps.setString(5,"Parcial");
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
	 * Añade una nueva InscripcionParcial a la base de datos donde la temporalidad es Temprano.
	 * @param object InscripcionParcial el cual va a ser añadido a la base de datos.
	 * @return boolean
	 */
	public boolean createTemprano(InscripcionParcial object) {
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
	 * Añade una nueva InscripcionParcial a la base de datos donde la temporalidad es Tardio.
	 * @param object InscripcionParcial el cual va a ser añadido a la base de datos.
	 * @return boolean
	 */
	public boolean createTardio(InscripcionParcial object) {
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
	 * Lee una InscripcionParcial de la base de datos.
	 * @param InscripcionParcial IncripcionParcial con el IdParticipante y IdCampamento que se va a leer de la base de datos.
	 * @return IncripcionParcial 
	 */
	@Override
	public InscripcionParcial read(InscripcionParcial object) {
		
		BufferedReader reader = null;
		Connector con = new Connector();
		
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
				if(rs.getString(5)=="Parcial") {
					object	= new InscripcionParcial(rs.getInt(1), rs.getInt(2), rs.getDate(3).toLocalDate(), rs.getFloat(4), rs.getString(5), rs.getString(6));
				}
            } 
			else {
				return object=null;
				}
			con.deleteConnection(c);
		} catch(Exception e) { System.out.println(e); }
		
		return object;
	}
	/**
	 * Lee una InscripcionCompleta de la base de datos, dado el id del campamento y el id del participante.
	 * @param idCampamento y idParticipante que son los que se van a leer de la base de datos.
	 * @return IncripcionCompleta 
	 */
	public InscripcionParcial read(int idCampamento, int idParticipante) {
		
		BufferedReader reader = null;
		Connector con = new Connector();
		InscripcionParcial object = new InscripcionParcial();
		
		
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
					object	= new InscripcionParcial(rs.getInt(1), rs.getInt(2), rs.getDate(3).toLocalDate(), rs.getFloat(4), rs.getString(5), rs.getString(6));
				}
            } 
			else {
				return object=null;
				}
			
			con.deleteConnection(c);
		} catch(Exception e) { System.out.println(e); }
		
		return object;
	}	
	
	/**
	 * Elimina una Inscripción de la base de datos.
	 * @param object InscripcionParcial el cual se va a eliminar de la base de datos.
	 * @return boolean
	 */
	@Override
	public boolean delete(InscripcionParcial object) {
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
	 * @return ArrayList<InscripcionParcial>
	 */
	public ArrayList<InscripcionParcial>readAll(){

		BufferedReader reader = null;
		Connector con = new Connector();
		ArrayList<InscripcionParcial> list = new ArrayList<InscripcionParcial>();
		
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("readAllInscripcion");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
	
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
                list.add( new InscripcionParcial(rs.getInt(1), rs.getInt(2), rs.getDate(3).toLocalDate(), rs.getFloat(4), rs.getString(5), rs.getString(6)));
            } 
			
			con.deleteConnection(c);
			
		} catch(Exception e) { System.out.println(e); }
		
		return list;
	}
}
