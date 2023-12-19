package model.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Properties;

import model.common.Connector;
import controller.dto.activity.*;
import controller.dto.camp.*;
import controller.dto.monitor.*;

/**
 * Class that manage database access related to camps implemented using Singleton design pattern
 * @author Enrique de los Reyes Montilla
 */
public class CampamentoDAO implements InterfaceDAO<CampDTO>{
	/**
	 * Sigleton private attribute.
	 */
	private static CampamentoDAO instance_= null;
	/**
	 * Properties path
	 */
	private String dir_;
	/**
	 * Singleton private constructor
	 * @param dir Properties path
	 */
	private CampamentoDAO(String dir) {
		dir_=dir;
	};
	/**
	 * Instance access method
	 * @return CampamentoDAO Instance
	 */
	public static CampamentoDAO getInstance(String dir) {
		if(instance_ == null ) {
			instance_ = new CampamentoDAO(dir);
		}
		return instance_;
	}
	/**
	 * Method that add a new camp to database.
	 * @param object Camp to add.
	 * @return boolean true if the camp has been added correctly, false otherwise
	 */
	@Override
	public boolean create(CampDTO object) {
		
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
			
		} catch(Exception e) { System.out.println(e); }
		
		return res;
	}
	/**
	 * Read a camp from the database.
	 * @param object Camp that contains the camp id to read.
	 * @return CampDTO Camp red if it exists, null otherwise
	 */
	@Override
	public CampDTO read(CampDTO object) {
		
		BufferedReader reader = null;
		Connector con = new Connector();
		CampDTO cam = null;
		
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
				object	= new CampDTO(rs.getInt(1), rs.getDate(4).toLocalDate(), rs.getDate(5).toLocalDate(), Nivel.valueOf(rs.getString(6)), rs.getInt(7) );
				int r = rs.getInt(2);
				int e = rs.getInt(3);
				
				con.deleteConnection(c);
				
				MonitorDAO daoMonitor = MonitorDAO.getInstance(dir_);
				MonitorDTO res = new MonitorDTO();
				res.setId(r);
				object.setResponsable(daoMonitor.read(res));
				res.setId(e);
				object.setResponsableEspecial(daoMonitor.read(res));
				
				cam = object;
            } 
			else {
				con.deleteConnection(c);
			}	
		} catch(Exception e) { System.out.println(e); }
		
		return cam;
	}
	/**
	 * Delete a camp.
	 * @param object Camp that contains the camp id to erase.
	 * @return boolean true if the camp has been deleted, false otherwise
	 */
	@Override
	public boolean delete(CampDTO object) {
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
			
		} catch(Exception e) { System.out.println(e); }
		
		return status;
	}
	/**
	 * Read all camps in database.
	 * @return ArrayList<CampDTO> List of camps
	 */
	public ArrayList<CampDTO> readAll(){
		
		BufferedReader reader = null;
		Connector con = new Connector();
		ArrayList<CampDTO> campamentos = new ArrayList<CampDTO>();
		CampDTO object = new CampDTO();
		
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("readAllCampamentos");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				object = new CampDTO(rs.getInt(1), rs.getDate(4).toLocalDate(), rs.getDate(5).toLocalDate(), Nivel.valueOf(rs.getString(6)), rs.getInt(7) );
				int r = rs.getInt(2);
				int e = rs.getInt(3);
				
				
				MonitorDAO daoMonitor = MonitorDAO.getInstance(dir_);
				MonitorDTO res = new MonitorDTO();
				res.setId(r);
				object.setResponsable(daoMonitor.read(res));
				res.setId(e);
				object.setResponsableEspecial(daoMonitor.read(res));
				
				campamentos.add(object);
            } 
				con.deleteConnection(c);
				
		} catch(Exception e) { System.out.println(e); }
		
		return campamentos;
	}
	/**
	 * Read all camps available.
	 * @return ArrayList<CampDTO> List of available camps
	 */
	public ArrayList<CampDTO> readAllAvailable(){

		BufferedReader reader = null;
		Connector con = new Connector();
		ArrayList<CampDTO> campamentos = new ArrayList<CampDTO>();
		CampDTO object = new CampDTO();
		
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
				object = new CampDTO(rs.getInt(1), rs.getDate(4).toLocalDate(), rs.getDate(5).toLocalDate(), Nivel.valueOf(rs.getString(6)), rs.getInt(7) );
				int r = rs.getInt(2);
				int e = rs.getInt(3);
				
				
				MonitorDAO daoMonitor = MonitorDAO.getInstance(dir_);
				MonitorDTO res = new MonitorDTO();
				res.setId(r);
				object.setResponsable(daoMonitor.read(res));
				res.setId(e);
				object.setResponsableEspecial(daoMonitor.read(res));

				campamentos.add(object);
            } 
				con.deleteConnection(c);
				
		} catch(Exception e) { System.out.println(e); }
		
		return campamentos;
		
	}
	/**
	 * Read all available camps that have more than a number of places
	 * @param seats Minimum number of places
	 * @return ArrayList<CampDTO> List of available camps
	 */
	public ArrayList<CampDTO> readAllAvailableSeats(int seats){

		BufferedReader reader = null;
		Connector con = new Connector();
		ArrayList<CampDTO> campamentos = new ArrayList<CampDTO>();
		CampDTO object = new CampDTO();
		
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("readAllCampamentoAvailableSeats");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
			
			ps.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
			ps.setInt(2, seats);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				object = new CampDTO(rs.getInt(1), rs.getDate(4).toLocalDate(), rs.getDate(5).toLocalDate(), Nivel.valueOf(rs.getString(6)), rs.getInt(7) );
				int r = rs.getInt(2);
				int e = rs.getInt(3);
				
				
				MonitorDAO daoMonitor = MonitorDAO.getInstance(dir_);
				MonitorDTO res = new MonitorDTO();
				res.setId(r);
				object.setResponsable(daoMonitor.read(res));
				res.setId(e);
				object.setResponsableEspecial(daoMonitor.read(res));

				campamentos.add(object);
            } 
				con.deleteConnection(c);
				
		} catch(Exception e) { System.out.println(e); }
		
		return campamentos;
		
	}
	/**
	 * Read all available camps that have an specific level
	 * @param love Camps level
	 * @return ArrayList<CampDTO> List of available camps
	 */
	public ArrayList<CampDTO> readAllAvailableLevel(Nivel love){

		BufferedReader reader = null;
		Connector con = new Connector();
		ArrayList<CampDTO> campamentos = new ArrayList<CampDTO>();
		CampDTO object = new CampDTO();
		
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("readAllCampamentoAvailableLevel");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
			
			ps.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
			ps.setString(2, love.toString());
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				object = new CampDTO(rs.getInt(1), rs.getDate(4).toLocalDate(), rs.getDate(5).toLocalDate(), Nivel.valueOf(rs.getString(6)), rs.getInt(7) );
				int r = rs.getInt(2);
				int e = rs.getInt(3);
				
				
				MonitorDAO daoMonitor = MonitorDAO.getInstance(dir_);
				MonitorDTO res = new MonitorDTO();
				res.setId(r);
				object.setResponsable(daoMonitor.read(res));
				res.setId(e);
				object.setResponsableEspecial(daoMonitor.read(res));

				campamentos.add(object);
            } 
				con.deleteConnection(c);
				
		} catch(Exception e) { System.out.println(e); }
		
		return campamentos;
		
	}
	/**
	 * Method that count all registration of a camp
	 * @param object Camp that contains the camp id
	 * @return int Number of registration
	 */
	public int count(CampDTO object) {
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
	 * Read an available camp from the database.
	 * @param id Camp id to read.
	 * @return CampDTO Camp red if it exists and is available, else otherwise
	 */
	public CampDTO readAvailable(int id) {
		
		BufferedReader reader = null;
		Connector con = new Connector();
		CampDTO object = null ;
		
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
				
				object = new CampDTO() ;
				object	= new CampDTO(rs.getInt(1), rs.getDate(4).toLocalDate(), rs.getDate(5).toLocalDate(), Nivel.valueOf(rs.getString(6)), rs.getInt(7) );
				int r = rs.getInt(2);
				int e = rs.getInt(3);
				
				con.deleteConnection(c);
				
				MonitorDAO daoMonitor = MonitorDAO.getInstance(dir_);
				MonitorDTO res = new MonitorDTO();
				res.setId(r);
				object.setResponsable(daoMonitor.read(res));
				res.setId(e);
				object.setResponsableEspecial(daoMonitor.read(res));
			}
            
			else {
				con.deleteConnection(c);
			}
				
			
		} catch(Exception e) { System.out.println(e); }
		
		return object;
	}
	/**
	 * Read a camp from the database.
	 * @param id Camp id to read.
	 * @return CampDTO Camp red if it exists, null otherwise
	 */
	public CampDTO read(int id) {
		
		BufferedReader reader = null;
		Connector con = new Connector();
		CampDTO object = null ;
		
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
				object	= new CampDTO(rs.getInt(1), rs.getDate(4).toLocalDate(), rs.getDate(5).toLocalDate(), Nivel.valueOf(rs.getString(6)), rs.getInt(7) );
				int r = rs.getInt(2);
				int e = rs.getInt(3);
				
				con.deleteConnection(c);
				
				MonitorDAO daoMonitor = MonitorDAO.getInstance(dir_);
				MonitorDTO res = new MonitorDTO();
				res.setId(r);
				object.setResponsable(daoMonitor.read(res));
				res.setId(e);
				object.setResponsableEspecial(daoMonitor.read(res));
            } 
			else {
				con.deleteConnection(c);
			}	
		} catch(Exception e) { System.out.println(e); }
		
		return object;
	}
	/**
	 * Update the responsible monitor of a camp
	 * @param idCam Camp id to update.
	 * @param idMonitor Monitor id to add.
	 * @return boolean true if the monitor has been added correctly, false otherwise
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
	 * Update the special monitor of a camp
	 * @param idCam Camp id to update.
	 * @param idMonitor Monitor id to add.
	 * @return boolean true if the monitor has been added correctly, false otherwise
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
	/**
	 * Read all activities of a camp
	 * @param idCamp Camp id
	 * @return ArrayList<ActivityDTO> List of activities
	 */
	public ArrayList<ActivityDTO> readActivitiesCamp(int idCamp){
		ArrayList<ActivityDTO> activities = new ArrayList<ActivityDTO>();
		BufferedReader reader = null;
		Connector con = new Connector();
		
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("readActividadCampamento");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
	
			ps.setInt(1, idCamp);
            
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				activities.add( new ActivityDTO(rs.getInt(1), rs.getString(2), Nivel.valueOf(rs.getString(3)), rs.getInt(5), rs.getInt(6), Turno.valueOf(rs.getString(4))));
			}
			
			con.deleteConnection(c);
			
		} catch(Exception e) { System.out.println(e); }
		return activities;
	}
	/**
	 * Method that add an activity to a camp
	 * @param idCamp Camp id
	 * @param idActivity Activity id to add
	 * @return boolean true if the activity has been added correctly, false otherwise
	 */
	public boolean addActivity(int idCamp, int idActivity) {
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
			
			ps.setInt(1,idActivity);
			ps.setInt(2,idCamp);
			
			status = ps.executeUpdate();	
			if (status == 1) {
				res = true;
			}
			con.deleteConnection(c);
			
		} catch(Exception e) { System.out.println(e); }
		
		return res;
	}
}
