package data.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import business.InscripcionCompleta;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

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
			
			status = ps.executeUpdate();	
			if (status == 1) {
				res = true;
			}
			con.deleteConnection(c);
			
		} catch(Exception e) { System.out.println(e); }
		
		return res;
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
					object	= new InscripcionCompleta(rs.getInt(1), rs.getInt(2), rs.getDate(3).toLocalDate(), rs.getFloat(4));
				}
            } 
			
			con.deleteConnection(c);
		} catch(Exception e) { System.out.println(e); }
		
		return object;
	}
	@Override
	public boolean delete(InscripcionCompleta object) {
		// TODO Auto-generated method stub
		return false;
	}
}
