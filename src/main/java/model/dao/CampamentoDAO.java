package model.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Properties;

import model.common.Connector;
import view.beans.*;
import view.beans.activity.*;
import view.beans.camp.*;
import view.beans.monitor.*;

/**
 * Clase CampamentoDAO que realiza las consultas relacionada con la tabla Campamento.
 * @author Enrique de los Reyes Montilla
 */
public class CampamentoDAO implements InterfaceDAO<Campamento>{
	/**
	 * Variable privada Singleton.
	 */
	private static CampamentoDAO instance_= null;
	/*
	 * *Representa la dirección al fichero properties.
	 */
	private static String dir_ = "sql.properties";
	/**
	 * Metodo que sirve de acceso a la instancia.
	 * @return Instancia de la clase CampamentoDAO.
	 */
	public static CampamentoDAO getInstance() {
		if(instance_ == null) {
			instance_ = new CampamentoDAO();
		}
		return instance_;
	}
	/**
	 * Constructor vacío de la clase CampamentoDAO.
	 */
	private CampamentoDAO() {}
	/**
	 * Añade un nuevo campamento a la base de datos.
	 * @param object Campamento el cual va a ser añadido a la base de datos.
	 * @return boolean
	 */
	@Override
	public boolean create(Campamento object) {
		
		BufferedReader reader = null;
		int status = 0;
		boolean res = false;
		Connector con = new Connector();
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String create = p.getProperty("createCampameto");
			
			Connection c=con.getConnection();
			PreparedStatement ps=c.prepareStatement(create);
			
             ps.setDate(3,  java.sql.Date.valueOf(object.getInicioCampamento())); 
            
             ps.setDate(4, java.sql.Date.valueOf(object.getFinCampamento()));
             ps.setString(5, object.getNivel().toString()); 
             ps.setInt(6, object.getAsistentesMax()); 
             ps.setInt(1, object.getResponsable().getId());
             if(object.getResponsableEspecial()!=null) {
            	 ps.setInt(2, object.getResponsableEspecial().getId());
             }
             else {
            	 ps.setInt(2, -1);
             }
             
			
			status = ps.executeUpdate();	
			if (status == 1) {
				res = true;
			}
			con.deleteConnection(c);
			CampamentoActividadDAO dao = CampamentoActividadDAO.getInstance();
			
			for ( Actividad i : object.getListaActividad()) {
				
				CampamentoActividadDTO aux = new CampamentoActividadDTO(i.getId(), object.getId());
				res = dao.create(aux);
				
			}
			
		} catch(Exception e) { System.out.println(e); }
		
		return res;
	}
	/**
	 * Lee un Campamento de la base de datos.
	 * @param object Campamaneto con ell Id del campamento que va a ser leido de la base de datos.
	 * @return Campamento
	 */
	@Override
	public Campamento read(Campamento object) {
		
		BufferedReader reader = null;
		Connector con = new Connector();
		Campamento cam = null;
		
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("readCampamento");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
			ps.setInt(1, object.getId());
	
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				object	= new Campamento(rs.getInt(1), rs.getDate(4).toLocalDate(), rs.getDate(5).toLocalDate(), Nivel.valueOf(rs.getString(6)), rs.getInt(7) );
				int r = rs.getInt(2);
				int e = rs.getInt(3);
				
				con.deleteConnection(c);
				
				MonitorDAO daoMonitor = MonitorDAO.getInstance();
				Monitor res = new Monitor();
				res.setId(r);
				object.setResponsable(daoMonitor.read(res));
				res.setId(e);
				object.setResponsableEspecial(daoMonitor.read(res));
				
				CampamentoActividadDAO dao = CampamentoActividadDAO.getInstance();
				
				ArrayList <Actividad> actividades = new ArrayList<Actividad>();
				Actividad aux = new Actividad();
				
				CampamentoActividadDTO dto = new CampamentoActividadDTO(0, object.getId());
				ArrayList <CampamentoActividadDTO> list = dao.readAllActividades(dto);
				
				ActividadDAO actDao = ActividadDAO.getInstance();
				
				for (CampamentoActividadDTO i : list) {
					
					aux.setId(i.getActId());
					actividades.add(actDao.read(aux));
					
				}
				
				object.setListaActividad(actividades);
				cam = object;
            } 
			else {
				con.deleteConnection(c);
			}	
		} catch(Exception e) { System.out.println(e); }
		
		return cam;
	}
	/**
	 * Elimina un Campaemnto de la base de datos.
	 * @param object Campamento el cual se va a eliminar de la base de datos.
	 * @return boolean
	 */
	@Override
	public boolean delete(Campamento object) {
		int rs =0;
		boolean status = false;
		BufferedReader reader = null;
		Connector con = new Connector();
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("deleteCampamento");
			
			Connection c=con.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement(query);
	        preparedStatement.setInt(1, object.getId());
	
			rs = preparedStatement.executeUpdate(); 
			
			if(rs == 1) {
				status = true;
			}
			
			con.deleteConnection(c);
			
			CampamentoActividadDAO dao = CampamentoActividadDAO.getInstance();
			
			for ( Actividad i : object.getListaActividad()) {
				
				CampamentoActividadDTO aux = new CampamentoActividadDTO(i.getId(), object.getId());
				status = dao.delete(aux);
				
			}
			
			con.deleteConnection(c);
			
		} catch(Exception e) { System.out.println(e); }
		
		return status;
	}
	/**
	 * Añade todos los campamnetos de la base de datos a un lista.
	 * @return ArrayList<Campamento>
	 */
	public ArrayList<Campamento> readAll(){
		
		BufferedReader reader = null;
		Connector con = new Connector();
		ArrayList<Campamento> campamentos = new ArrayList<Campamento>();
		Campamento object = new Campamento();
		
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("readAllCampamentos");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Actividad aux = new Actividad();
				
				aux.setId(rs.getInt(2));
				object = new Campamento(rs.getInt(1), rs.getDate(4).toLocalDate(), rs.getDate(5).toLocalDate(), Nivel.valueOf(rs.getString(6)), rs.getInt(7) );
				int r = rs.getInt(2);
				int e = rs.getInt(3);
				
				
				MonitorDAO daoMonitor = MonitorDAO.getInstance();
				Monitor res = new Monitor();
				res.setId(r);
				object.setResponsable(daoMonitor.read(res));
				res.setId(e);
				object.setResponsableEspecial(daoMonitor.read(res));
				
				CampamentoActividadDAO dao = CampamentoActividadDAO.getInstance();
				
				ArrayList <Actividad> actividades = new ArrayList<Actividad>();
				
				CampamentoActividadDTO dto = new CampamentoActividadDTO(0, object.getId());
				ArrayList <CampamentoActividadDTO> list = dao.readAllActividades(dto);
				
				ActividadDAO actDao = ActividadDAO.getInstance();
				
				for (CampamentoActividadDTO i : list) {
					
					aux.setId(i.getActId());
					actividades.add(actDao.read(aux));
					
				}
				
				object.setListaActividad(actividades);
				campamentos.add(object);
            } 
				con.deleteConnection(c);
				
		} catch(Exception e) { System.out.println(e); }
		
		return campamentos;
	}
	/**
	 * Añade todos los campamentos disponibles de la base de datos a un lista.
	 * @return ArrayList<Campamento>
	 */
	public ArrayList<Campamento> readAllAvailable(){

		BufferedReader reader = null;
		Connector con = new Connector();
		ArrayList<Campamento> campamentos = new ArrayList<Campamento>();
		Campamento object = new Campamento();
		
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("readAllCampamentosAvailable");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
			
			ps.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Actividad aux = new Actividad();
				
				aux.setId(rs.getInt(2));
				object = new Campamento(rs.getInt(1), rs.getDate(4).toLocalDate(), rs.getDate(5).toLocalDate(), Nivel.valueOf(rs.getString(6)), rs.getInt(7) );
				int r = rs.getInt(2);
				int e = rs.getInt(3);
				
				
				MonitorDAO daoMonitor = MonitorDAO.getInstance();
				Monitor res = new Monitor();
				res.setId(r);
				object.setResponsable(daoMonitor.read(res));
				res.setId(e);
				object.setResponsableEspecial(daoMonitor.read(res));
				
				long diferenciaDias = ChronoUnit.DAYS.between(LocalDate.now(), object.getInicioCampamento());
					
				CampamentoActividadDAO dao = CampamentoActividadDAO.getInstance();
				
				ArrayList <Actividad> actividades = new ArrayList<Actividad>();
				
				CampamentoActividadDTO dto = new CampamentoActividadDTO(0, object.getId());
				ArrayList <CampamentoActividadDTO> list = dao.readAllActividades(dto);
				
				ActividadDAO actDao = ActividadDAO.getInstance();
				
				for (CampamentoActividadDTO i : list) {
					
					aux.setId(i.getActId());
					actividades.add(actDao.read(aux));
					
				}
				
				object.setListaActividad(actividades);
				campamentos.add(object);
            } 
				con.deleteConnection(c);
				
		} catch(Exception e) { System.out.println(e); }
		
		return campamentos;
		
	}
	/**
	 * Cuenta el numero de Inscripciones de la base de datos que tienen un determinado idCapamento.
	 * @param object Campamento con el id que se utilizará para la consulta.
	 * @return int
	 */
	public int count(Campamento object) {
		ResultSet rs ;
		BufferedReader reader = null;
		Connector con = new Connector();
		int count = 0;
		
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("countParticipantes");
			
			Connection c=con.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement(query);
	        preparedStatement.setInt(1, object.getId());
	
			rs = preparedStatement.executeQuery(); 
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
			con.deleteConnection(c);
			
		} catch(Exception e) { System.out.println(e); }
		
		
		return count;
	}
	/**
	 * Lee un Campamento disponible de la base de datos.
	 * @param id Id del campamento que va a ser leido de la base de datos.
	 * @return Campamento
	 */
	public Campamento readAvailable(int id) {
		
		BufferedReader reader = null;
		Connector con = new Connector();
		Campamento object = null ;
		
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("readCampamentoAvailable");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
			ps.setInt(1, id);
			ps.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
				object = new Campamento() ;
				Actividad aux = new Actividad();
				object	= new Campamento(rs.getInt(1), rs.getDate(4).toLocalDate(), rs.getDate(5).toLocalDate(), Nivel.valueOf(rs.getString(6)), rs.getInt(7) );
				int r = rs.getInt(2);
				int e = rs.getInt(3);
				
				con.deleteConnection(c);
				
				MonitorDAO daoMonitor = MonitorDAO.getInstance();
				Monitor res = new Monitor();
				res.setId(r);
				object.setResponsable(daoMonitor.read(res));
				res.setId(e);
				object.setResponsableEspecial(daoMonitor.read(res));
					
				CampamentoActividadDAO dao = CampamentoActividadDAO.getInstance();
				
				ArrayList <Actividad> actividades = new ArrayList<Actividad>();
				
				CampamentoActividadDTO dto = new CampamentoActividadDTO(0, object.getId());
				ArrayList <CampamentoActividadDTO> list = dao.readAllActividades(dto);
				
				ActividadDAO actDao = ActividadDAO.getInstance();
				
				for (CampamentoActividadDTO i : list) {
					
					aux.setId(i.getActId());
					actividades.add(actDao.read(aux));
					
				}
				object.setListaActividad(actividades);
			}
            
			else {
				con.deleteConnection(c);
			}
				
			
		} catch(Exception e) { System.out.println(e); }
		
		return object;
	}
	/**
	 * Lee un Campamento de la base de datos.
	 * @param id Id del campamento que va a ser leido de la base de datos.
	 * @return Campamento
	 */
	public Campamento read(int id) {
		
		BufferedReader reader = null;
		Connector con = new Connector();
		Campamento object = null ;
		
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("readCampamento");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
			ps.setInt(1, id);
	
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				object	= new Campamento(rs.getInt(1), rs.getDate(4).toLocalDate(), rs.getDate(5).toLocalDate(), Nivel.valueOf(rs.getString(6)), rs.getInt(7) );
				int r = rs.getInt(2);
				int e = rs.getInt(3);
				
				con.deleteConnection(c);
				
				MonitorDAO daoMonitor = MonitorDAO.getInstance();
				Monitor res = new Monitor();
				res.setId(r);
				object.setResponsable(daoMonitor.read(res));
				res.setId(e);
				object.setResponsableEspecial(daoMonitor.read(res));
				
				CampamentoActividadDAO dao = CampamentoActividadDAO.getInstance();
				
				ArrayList <Actividad> actividades = new ArrayList<Actividad>();
				Actividad aux = new Actividad();
				
				CampamentoActividadDTO dto = new CampamentoActividadDTO(0, object.getId());
				ArrayList <CampamentoActividadDTO> list = dao.readAllActividades(dto);
				
				ActividadDAO actDao = ActividadDAO.getInstance();
				
				for (CampamentoActividadDTO i : list) {
					
					aux.setId(i.getActId());
					actividades.add(actDao.read(aux));
					
				}
				
				object.setListaActividad(actividades);
				
            } 
			else {
				con.deleteConnection(c);
			}	
		} catch(Exception e) { System.out.println(e); }
		
		return object;
	}
	/**
	 * Actualiza el monitor responsable de un campamento de la base de datos.
	 * @param idCam Id del campamento que se va a actualizar en la base de datos.
	 * @param idMonitor Id del monitor que se va a declarar como responsable del campamento.
	 * @return boolean
	 */
	public boolean updateResponsable(int idCam, int idMonitor) {
		
		BufferedReader reader = null;
		Connector con = new Connector();
		boolean status = false;
		
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("updateResponsable");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
	
			ps.setInt(1, idMonitor);
            ps.setInt(2, idCam);
            
			int rs = ps.executeUpdate();
			
			if(rs == 1) {
				status = true;
			}
			
			con.deleteConnection(c);
			
		} catch(Exception e) { System.out.println(e); }
		
		return status;

	}
	/**
	 * Actualiza el monitor especial de un campamento de la base de datos.
	 * @param idCam Id del campamento que se va a actualizar en la base de datos.
	 * @param idMonitor Id del monitor que se va a declarar como responsable especial del campamento.
	 * @return boolean
	 */
	public boolean updateEspecial(int idCam, int idMonitor) {
		
		BufferedReader reader = null;
		Connector con = new Connector();
		boolean status = false;
		
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("updateEspecial");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
	
			ps.setInt(1, idMonitor);
            ps.setInt(2, idCam);
            
			int rs = ps.executeUpdate();
			
			if(rs == 1) {
				status = true;
			}
			
			con.deleteConnection(c);
			
		} catch(Exception e) { System.out.println(e); }
		
		return status;

	}
	@Override
	public boolean create(CampBean object) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public CampBean read(CampBean object) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean delete(CampBean object) {
		// TODO Auto-generated method stub
		return false;
	}
}