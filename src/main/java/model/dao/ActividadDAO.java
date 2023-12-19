package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import model.common.Connector;
import controller.dto.activity.*;
import controller.dto.monitor.MonitorDTO;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.ArrayList;

/**
 * Class that performs database queries related to activities implemented using Singleton design pattern
 * @author Manuel García Obrero
 */

public class ActividadDAO implements InterfaceDAO<ActivityDTO> {
	/**
	 * Singleton private attribute.
	 */
	private static ActividadDAO instance_= null;
	/**
	 * Properties path
	 */
	private String dir_;
	/**
	 * Singleton private constructor
	 * @param dir Properties path
	 */
	private ActividadDAO(String dir) {
		dir_=dir;
	};
	/**
	 * Instance access method
	 * @return ActividadDAO Instance
	 */
	public static ActividadDAO getInstance(String dir) {
		if(instance_ == null ) {
			instance_ = new ActividadDAO(dir);
		}
		return instance_;
	}
	
	/**
	 * Add a new activity to the database.
	 * @param object Activity to be added in the database.
	 * @return boolean true if the activity has been added correctly, else otherwise
	 */
	@Override
	public boolean create(ActivityDTO object) {
		BufferedReader reader = null;
		int status = 0;
		boolean res = false;
		Connector con = new Connector();
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String create = p.getProperty("createActividad");
			
			Connection c=con.getConnection();
			PreparedStatement ps=c.prepareStatement(create);
			
			ps.setString(1,object.getName());
			ps.setString(2,object.getNivel().name());
			ps.setString(3,object.getTurno().name());
			ps.setInt(4,object.getParticipantesMax());
			ps.setInt(5,object.getMonitoresMax());
			
			status = ps.executeUpdate();	
			if (status == 1) {
				res = true;
			}
			con.deleteConnection(c);
			
		} catch(Exception e) { System.out.println(e); }
		
		return res;
	}
	
	/**
	 * Read an activity from the database.
	 * @param object Activity with the activity id to read.
	 * @return Actividad Activity red if it exists, null otherwise
	 */
	@Override
		public ActivityDTO read(ActivityDTO object) {
		
		BufferedReader reader = null;
		Connector con = new Connector();
		ActivityDTO ac = null;
		
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("readActividad");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
			ps.setInt(1, object.getId());
	
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
				object	= new ActivityDTO(rs.getInt(1), rs.getString(2), Nivel.valueOf(rs.getString(3)), rs.getInt(5), rs.getInt(6), Turno.valueOf(rs.getString(4)));
				ac = object;
			} 
			
			con.deleteConnection(c);
		} catch(Exception e) { System.out.println(e); }
		
		return ac;
	}
	/**
	 * Read an activity from the database.
	 * @param id Activity id to read.
	 * @return Actividad Activity red if it exists, null otherwise
	 */
		public ActivityDTO read(int id) {
		
		BufferedReader reader = null;
		Connector con = new Connector();
		ActivityDTO object = null;
		
		
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("readActividad");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
			ps.setInt(1, id);
	
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				object	= new ActivityDTO(rs.getInt(1), rs.getString(2), Nivel.valueOf(rs.getString(3)), rs.getInt(5), rs.getInt(6), Turno.valueOf(rs.getString(4)));
            } 
			
			con.deleteConnection(c);
		} catch(Exception e) { System.out.println(e); }
		
		return object;
	}
	/**
	 * Delete an activity from the database.
	 * @param object Activity with the activity id to delete.
	 * @return boolean true if the activity has been deleted correctly, else otherwise
	 */
	@Override
	public boolean delete(ActivityDTO object) {
		int rs =0;
		boolean status = false;
		BufferedReader reader = null;
		Connector con = new Connector();
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("deleteActividad");
			
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
	 * Read all activities of the database.
	 * @return Array<Actividad> List of activities
	 */
	public ArrayList<ActivityDTO>readAll(){
		BufferedReader reader = null;
		Connector con = new Connector();
		ArrayList<ActivityDTO> list = new ArrayList<ActivityDTO>();
		
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("readAllActividad");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
	
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
                list.add( new ActivityDTO(rs.getInt(1), rs.getString(2), Nivel.valueOf(rs.getString(3)), rs.getInt(5), rs.getInt(6), Turno.valueOf(rs.getString(4))));
                
            } 
			
			con.deleteConnection(c);
			
		} catch(Exception e) { System.out.println(e); }
		
		return list;
	}
	/**
	 * Read all monitors of an activity
	 * @param idActivity Activity identifier
	 * @return MonitorDTO List of monitors
	 */
	public ArrayList<MonitorDTO> readMonitorsActivity(int idActivity){
		ArrayList<MonitorDTO> monitors = new ArrayList<MonitorDTO>();
		BufferedReader reader = null;
		Connector con = new Connector();
		
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("readMonitorActividad");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
	
			ps.setInt(1, idActivity);
            
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				monitors.add( new MonitorDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4)));
			}
			
			con.deleteConnection(c);
			
		} catch(Exception e) { System.out.println(e); }
		return monitors;
	}
	/**
	 * Add a monitor to an activity
	 * @param idActivity Activity identifier
	 * @param idMonitor Monitor identifier
	 * @return boolean true if the monitor has been added to the activity, else otherwise
	 */
	public boolean addActivity(int idActivity, int idMonitor) {
		BufferedReader reader = null;
		int status = 0;
		boolean res = false;
		Connector con = new Connector();
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String create = p.getProperty("createActividadMonitor");
			
			System.out.println(create);
			Connection c=con.getConnection();
			PreparedStatement ps=c.prepareStatement(create);
			
			ps.setInt(1, idActivity);
			ps.setInt(2, idMonitor);
			
			status = ps.executeUpdate();	
			if (status == 1) {
				res = true;
			}
			con.deleteConnection(c);
			
		} catch(Exception e) { System.out.println(e); }
		
		return res;
	}
	
}