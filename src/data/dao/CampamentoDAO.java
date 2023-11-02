package data.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.util.Properties;

import business.Campamento;
import business.Actividad;

public class CampamentoDAO implements InterfaceDAO<Campamento>{
	/**
	 * Variable privada Singleton.
	 */
	private static CampamentoDAO instance_= null;
	/*
	 * *Representa la dirección al fichero properties.
	 */
	private static String dir_ = "rutas.txt";
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
	 * Constructor vacío de la clase Campamento.
	 */
	private CampamentoDAO() {}
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
			String create = p.getProperty("createCampamento");
			
			System.out.println(create);
			Connection c=con.getConnection();
			PreparedStatement ps=c.prepareStatement(create);
			
             ps.setDate(4,  java.sql.Date.valueOf(object.getInicioCampamento())); 
             ps.setInt(1, object.getId()); 
             ps.setDate(5, java.sql.Date.valueOf(object.getFinCampamento())); 
             ps.setString(6, object.getNivel().toString()); 
             ps.setInt(7, object.getAsistentesMax()); 
             ps.setInt(2, object.getResponsable().getId());
             ps.setInt(3, object.getResponsableEspecial().getId());
			
			status = ps.executeUpdate();	
			if (status == 1) {
				res = true;
			}
			con.deleteConnection(c);
			CampamentoActividadDAO dao = CampamentoActividadDAO.getInstance();
			
			for ( Actividad i : object.getListaActividad()) {
				
				CampamentoActividadDTO aux = new CampamentoActividadDTO(object.getId(), i.getId());
				res = dao.create(aux);
				
			}
			
			
		} catch(Exception e) { System.out.println(e); }
		
		return res;
	}

	@Override
	public Campamento read(Campamento object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Campamento object) {
		// TODO Auto-generated method stub
		return false;
	}

}
