package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import model.common.Connector;
import controller.dto.monitor.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Class that manage database access related to monitors implemented using Singleton design pattern
 * @author Enrique de los Reyes Montilla
 */
public class MonitorDAO implements InterfaceDAO<MonitorDTO> {
	/**
	 * Singleton private attribute.
	 */
	private static MonitorDAO instance_= null;
	/**
	 * Properties path
	 */
	private String dir_;
	/**
	 * Singleton private constructor
	 * @param dir Properties path
	 */
	private MonitorDAO(String dir) {
		dir_=dir;
	};
	/**
	 * Instance access method
	 * @return MonitorDAO Instance
	 */
	public static MonitorDAO getInstance(String dir) {
		if(instance_ == null ) {
			instance_ = new MonitorDAO(dir);
		}
		return instance_;
	}
	/**
	 * Add a monitor to the database.
	 * @param object Monitor to be added.
	 * @return boolean true if the monitor has been added correctly, false otherwise
	 */
	@Override
	public boolean create(MonitorDTO object) {
		
		BufferedReader reader = null;
		int status = 0;
		boolean res = false;
		Connector con = new Connector();
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String create = p.getProperty("createMonitor");
			
			Connection c=con.getConnection();
			PreparedStatement ps=c.prepareStatement(create);
			
			ps.setString(1,object.getNombre());
			ps.setString(2,object.getApellidos());
			ps.setBoolean(3,object.getEspecial());
			
			status = ps.executeUpdate();	
			if (status == 1) {
				res = true;
			}
			con.deleteConnection(c);
			
		} catch(Exception e) { System.out.println(e); }
		
		return res;
	}
	/**
	 * Read a monitor
	 * @param object Monitor that contains the monitor id to read.
	 * @return MonitorDTO Monitor if the monitor exists, null otherwise
	 */
	@Override
	public MonitorDTO read(MonitorDTO object) {
		
		
		BufferedReader reader = null;
		Connector con = new Connector();
		MonitorDTO mon = null;
		
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("readMonitor");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
			ps.setInt(1, object.getId());
	
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
				object	= new MonitorDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4));
	            mon = object;    
			
            } 
			
			con.deleteConnection(c);
		} catch(Exception e) { System.out.println(e); }
		
		return mon;
		
	}
	/**
	 * Delete a monitor
	 * @param object Monitor that contains the monitor id to erase.
	 * @return boolean true if the monitor has been deleted correctly, false otherwise
	 */
	@Override
	public boolean delete(MonitorDTO object) {
		
		int rs =0;
		boolean status = false;
		BufferedReader reader = null;
		Connector con = new Connector();
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("deleteMonitor");
			
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
	 * Read all monitors.
	 * @return Array<MonitorDTO> List of monitors
	 */
	public ArrayList<MonitorDTO>readAll(){

		BufferedReader reader = null;
		Connector con = new Connector();
		ArrayList<MonitorDTO> list = new ArrayList<MonitorDTO>();
		
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("readAllMonitor");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
	
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
                list.add( new MonitorDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4)));
                
            } 
			
			con.deleteConnection(c);
			
		} catch(Exception e) { System.out.println(e); }
		
		return list;
	}
	/**
	 * Read a monitor.
	 * @param id Monitor id to add
	 * @return MonitorDTO Monitor if the monitor exists, null otherwise
	 */
	public MonitorDTO read(int id) {
		
		
		BufferedReader reader = null;
		Connector con = new Connector();
		MonitorDTO mon = null;
		
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			String query = p.getProperty("readMonitor");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
			ps.setInt(1, id);
	
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
				mon	= new MonitorDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4));    
			
            } 
			
			con.deleteConnection(c);
		} catch(Exception e) { System.out.println(e); }
		
		return mon;
		
	}
}
