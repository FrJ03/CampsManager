package model.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import controller.dto.customer.CustomerDTO;
import controller.dto.customer.Rol;
import model.common.Connector;

/**
 * Class that manage database access related to customer implemented using Singleton design pattern
 * @author Enrique de los Reyes Montilla
 */
public class CustomerDAO implements InterfaceDAO<CustomerDTO> {
	/**
	 * VSingleton private attribute.
	 */
	private static CustomerDAO instance_= null;
	/**
	 * Properties file path.
	 */
	private static String dir_ = "sql.properties";
	/**
	 * Instance access method.
	 * @return Instance class.
	 */
	public static CustomerDAO getInstance() {
		if(instance_ == null) {
			return new CustomerDAO();
		}
		return instance_;
	}
	/**
	 * Empty constructor.
	 */
	private CustomerDAO() {}
	/**
	 * Add a customer to the database
	 * @param object Customer to be added.
	 * @return boolean true if the customer has been added correctly, false otherwise
	 */
	@Override
	public boolean create(CustomerDTO object) {
		BufferedReader reader = null;
		int status = 0;
		boolean res = false;
		Connector con = new Connector();
		try{
			
			Properties p = new Properties();	
			InputStream queries = getClass().getClassLoader().getResourceAsStream("sql.properties");
			reader = new BufferedReader(new InputStreamReader(queries));
			p.load(reader);
			String create = p.getProperty("createCustomer");
			
			Connection c=con.getConnection();
			PreparedStatement ps=c.prepareStatement(create);
			
			ps.setString(1,object.getEmail());
			ps.setString(2,object.getPassword());
			ps.setString(3,object.getRol().toString());
			
			
			status = ps.executeUpdate();	
			if (status == 1) {
				res = true;
			}
			con.deleteConnection(c);
			
		} catch(Exception e) { System.out.println(e); }
		
		return res;
		
	}
	/**
	 * Read a customer.
	 * @param object Customer that contains the customer id to read.
	 * @return CustomerDTO Customer red if it exists, null otherwise
	 */
	@Override
	public CustomerDTO read(CustomerDTO object) {
		
		BufferedReader reader = null;
		Connector con = new Connector();
		CustomerDTO as = null;
		
		try{
			
			Properties p = new Properties();	
			InputStream queries = getClass().getClassLoader().getResourceAsStream("sql.properties");
			reader = new BufferedReader(new InputStreamReader(queries));
			p.load(reader);
			String query = p.getProperty("readCustomer");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
			ps.setString(1, object.getEmail());
	
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
				object = new CustomerDTO(object.getEmail(), rs.getString(1), Rol.valueOf(rs.getString(2)));
                as = object;
            } 
			
			con.deleteConnection(c);
		} catch(Exception e) { System.out.println(e); }
		
		return as;

	}
	/**
	 * Delete a customer.
	 * @param object Customer that has the customer id to erase.
	 * @return boolean true if the customer has been deleted, false otherwise
	 */
	@Override
	public boolean delete(CustomerDTO object) {
		
		int rs =0;
		boolean status = false;
		BufferedReader reader = null;
		Connector con = new Connector();
		try{
			
			Properties p = new Properties();	
			InputStream queries = getClass().getClassLoader().getResourceAsStream("sql.properties");
			reader = new BufferedReader(new InputStreamReader(queries));
			p.load(reader);
			String query = p.getProperty("deleteCustomer");
			
			Connection c=con.getConnection();
			PreparedStatement preparedStatement = c.prepareStatement(query);
	        preparedStatement.setString(1, object.getEmail());
	
			rs = preparedStatement.executeUpdate(); 
			
			if(rs == 1) {
				status = true;
			}
			
			con.deleteConnection(c);
			
		} catch(Exception e) { System.out.println(e); }
		
		return status;

	}
	
	/**
	 * Update a customer.
	 * @param object Customer to update.
	 * @return boolean true if the customer has been updated, false otherwise.
	 */
	public boolean update(CustomerDTO object){

		BufferedReader reader = null;
		Connector con = new Connector();
		boolean status = false;
		
		try{
			
			Properties p = new Properties();	
			InputStream queries = getClass().getClassLoader().getResourceAsStream("sql.properties");
			reader = new BufferedReader(new InputStreamReader(queries));
			p.load(reader);
			String query = p.getProperty("updateCustomer");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
	
			ps.setString(1, object.getPassword());
            ps.setString(2, object.getRolName());
            ps.setString(5, object.getEmail());
            
			int rs = ps.executeUpdate();
			
			if(rs == 1) {
				status = true;
			}
			
			con.deleteConnection(c);
			
		} catch(Exception e) { System.out.println(e); }
		
		return status;
	}
	
	/**
	 * Read a customer.
	 * @param email Customer email.
	 * @return CustomerDTO Customer red if the customer exists, null otherwise
	 */
	public CustomerDTO read(String email) {
		
		BufferedReader reader = null;
		Connector con = new Connector();
		CustomerDTO as = null;
		
		try{
			
			Properties p = new Properties();	
			InputStream queries = getClass().getClassLoader().getResourceAsStream("sql.properties");
			reader = new BufferedReader(new InputStreamReader(queries));
			p.load(reader);
			String query = p.getProperty("readCustomer");
			
			Connection c = con.getConnection();
			
			PreparedStatement ps=c.prepareStatement(query);
			ps.setString(1, email);
	
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
				as = new CustomerDTO(email, rs.getString(1), Rol.valueOf(rs.getString(2)));
                
            } 
			
			con.deleteConnection(c);
		} catch(Exception e) { System.out.println(e); }
		
		return as;

	}
}
