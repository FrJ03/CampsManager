package data.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


/**
 * Clase CampamentoActividadDAO que realiza las consultas relacionada con la tabla Campamento-Actividad.
 * @author Manuel García Obrero
 * @author Enrique de los Reyes Montilla
 */

public class CampamentoActividadDAO implements InterfaceDAO<CampamentoActividadDTO>{
	/**
	 * Variable privada Singleton.
	 */
	private static CampamentoActividadDAO instance_= null;
	/*
	 * *Representa la dirección al fichero properties.
	 */
	private static String dir_ = "sql.properties";
	/**
	 * Metodo que sirve de acceso a la instancia.
	 * @return Instancia de la clase CampamentoActividadDTO.
	 */
	public static CampamentoActividadDAO getInstance() {
		if(instance_ == null) {
			instance_ = new CampamentoActividadDAO();
		}
		return instance_;
	}
	/**
	 * Constructor vacío de la clase CampamentoActividadDAO.
	 */
	private CampamentoActividadDAO() {}
	
	/**
	 * Añade una nuevo CampamentoActividad a la base de datos.
	 * @param object CampamentoActividadDTO el cual va a ser añadido a la base de datos.
	 * @return boolean
	 */
	@Override
	public boolean create(CampamentoActividadDTO object) {
		BufferedReader reader = null;
		int status = 0;
		boolean res = false;
		Connector con = new Connector();
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String create = p.getProperty("createCampamentoActividad");
			
			System.out.println(create);
			Connection c=con.getConnection();
			PreparedStatement ps=c.prepareStatement(create);
			
			ps.setInt(1,object.getActId());
			ps.setInt(2,object.getCampId());
			
			status = ps.executeUpdate();	
			if (status == 1) {
				res = true;
			}
			con.deleteConnection(c);
			
		} catch(Exception e) { System.out.println(e); }
		
		return res;
	}
	
	/**
	 * Lee un CampamentoActividad de la base de datos.
	 * @param CampamentoActividadDTO CampamentoActividadDTO con el idCampamento y idActividad que se va a leer de la base de datos.
	 * @return CampamentoActividadDTO
	 */
	@Override
	public CampamentoActividadDTO read(CampamentoActividadDTO object) {
		
		BufferedReader reader = null;
		Connector con = new Connector();
		
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("readCampamentoActividad");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
			ps.setInt(1, object.getActId());
			ps.setInt(2, object.getCampId());
	
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
			object	= new CampamentoActividadDTO(rs.getInt(1), rs.getInt(2));
            } 
			
			con.deleteConnection(c);
		} catch(Exception e) { System.out.println(e); }
		
		return object;
	}
	
	/**
	 * Elimina un CampamentoActividad de la base de datos.
	 * @param object CampamentoActividadDTO el cual se va a eliminar de la base de datos.
	 * @return boolean
	 */
	@Override
	public boolean delete(CampamentoActividadDTO object) {
		int rs =0;
		boolean status = false;
		BufferedReader reader = null;
		Connector con = new Connector();
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("deleteCampamentoActividad");
			
			Connection c=con.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement(query);
			preparedStatement.setInt(1, object.getActId());
			preparedStatement.setInt(2, object.getCampId());
	
			rs = preparedStatement.executeUpdate(); 
			
			if(rs == 1) {
				status = true;
			}
			
			con.deleteConnection(c);
			
		} catch(Exception e) { System.out.println(e); }
		
		return status;
	}
	/**
	 * Obtiene la lista de actividades relacionada con un campamento de la base de datos.
	 * @param object CampamentoActividadDTO el cual se va a leer de la base de datos.
	 * @return  ArrayList<ActividadMonitorDTO>
	 */
	public  ArrayList<CampamentoActividadDTO> readAllActividades(CampamentoActividadDTO object) {
		
		BufferedReader reader = null;
		Connector con = new Connector();
		ArrayList <CampamentoActividadDTO> list = new ArrayList<CampamentoActividadDTO>();
		
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("readAllActividades");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
			ps.setInt(1, object.getCampId());
	
			ResultSet rs = ps.executeQuery();
			CampamentoActividadDTO dao = new CampamentoActividadDTO(0, object.getCampId());
			
			while (rs.next()) {
				dao.setActId(rs.getInt(1));
				list.add(dao);
            } 
			
			con.deleteConnection(c);
			
		} catch(Exception e) { System.out.println(e); }
		
		return list;
	}
}

