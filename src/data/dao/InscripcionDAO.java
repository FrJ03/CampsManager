package data.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import business.InscripcionParcial;
import business.Inscripcion;
import business.InscripcionCompleta;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Clase InscripcionDAO que realiza las consultas relacionadas con las inscripciones.
 * @author Manuel García Obrero
 */

public class InscripcionDAO implements InterfaceDAO<Inscripcion>{ 
	/**
	 * Variable privada Singleton.
	 */
	private static InscripcionDAO instance_= null;
	/*
	 * *Representa la dirección al fichero properties.
	 */
	private static String dir_ = "rutas.txt";
	/**
	 * Metodo que sirve de acceso a la instancia.
	 * @return Instancia de la clase InscripcionDAO.
	 */
	public static InscripcionDAO getInstance() {
		if(instance_ == null) {
			return new InscripcionDAO();
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
	public boolean create(Inscripcion object) {
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
			ps.setString(5,object.getTipo());
			
			status = ps.executeUpdate();	
			if (status == 1) {
				res = true;
			}
			con.deleteConnection(c);
			
		} catch(Exception e) { System.out.println(e); }
		
		return res;
	}
	
	/**
	 * Lee una Inscripcion de la base de datos.
	 * @param Inscripcion Incripcion con el IdParticipante y IdCampamento que se va a leer de la base de datos.
	 * @return IncripcionCompleta o InscripcionParcial, depende del tipo que sea la Inscripcion
	 */
	@Override
	public Inscripcion read(Inscripcion object) {
		
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
				else {
					object	= new InscripcionParcial(rs.getInt(1), rs.getInt(2), rs.getDate(3).toLocalDate(), rs.getFloat(4));
				}
                
            } 
			
			con.deleteConnection(c);
		} catch(Exception e) { System.out.println(e); }
		
		return object;
	}
	
	/**
	 * Elimina un monitor de la base de datos.
	 * @param object Monitor el cual se va a eliminar de la base de datos.
	 * @return boolean
	 */
	@Override
	public boolean delete(Inscripcion object) {
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
}
