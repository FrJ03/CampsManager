package model.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import controller.dto.customer.CustomerDTO;
import controller.dto.customer.Rol;
import model.common.Connector;
/**
 * DAO que permite gestionar los accesos a la base de datos relacionados con customers.
 * @author Enrique de los Reyes Montilla
 */
public class CustomerDAO implements InterfaceDAO<CustomerDTO> {
	/**
	 * Variable privada Singleton.
	 */
	private static CustomerDAO instance_= null;
	/*
	 * *Representa la dirección al fichero properties.
	 */
	private static String dir_ = "sql.properties";
	/**
	 * Metodo que sirve de acceso a la instancia.
	 * @return Instancia de la clase CustomerDAO.
	 */
	public static CustomerDAO getInstance() {
		if(instance_ == null) {
			return new CustomerDAO();
		}
		return instance_;
	}
	/**
	 * Constructor vacío de la clase CustomerDAO.
	 */
	private CustomerDAO() {}
	/**
	 * Añade un nuevo Customer a la base de datos.
	 * @param object Customer el cual va a ser añadido a la base de datos.
	 * @return boolean
	 */
	@Override
	public boolean create(CustomerDTO object) {
		BufferedReader reader = null;
		int status = 0;
		boolean res = false;
		Connector con = new Connector();
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
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
	 * Lee un Customer de la base de datos.
	 * @param Customer Customer con el id se va a leer de la base de datos.
	 * @return Monitor
	 */
	@Override
	public CustomerDTO read(CustomerDTO object) {
		
		BufferedReader reader = null;
		Connector con = new Connector();
		CustomerDTO as = null;
		
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
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
	 * Elimina un Customer de la base de datos.
	 * @param object Customer el cual se va a eliminar de la base de datos.
	 * @return boolean
	 */
	@Override
	public boolean delete(CustomerDTO object) {
		
		int rs =0;
		boolean status = false;
		BufferedReader reader = null;
		Connector con = new Connector();
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
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
	 * Actualiza un Customer de la base de datos.
	 * @param object Customer el cual va a ser actualizado.
	 * @return boolean
	 */
	public boolean update(CustomerDTO object){

		BufferedReader reader = null;
		Connector con = new Connector();
		boolean status = false;
		
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
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
	 * Lee un Customer de la base de datos.
	 * @param email email del customer que se va a leer de la base de datos.
	 * @return Monitor
	 */
	public CustomerDTO read(String email) {
		
		BufferedReader reader = null;
		Connector con = new Connector();
		CustomerDTO as = null;
		
		try{
			
			Properties p = new Properties();	
			reader = new BufferedReader(new FileReader(new File(dir_)));
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
