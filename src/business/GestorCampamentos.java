package business;

import java.util.ArrayList;
import java.util.Collections;

import data.dao.ActividadDAO;
import data.dao.ActividadMonitorDAO;
import data.dao.CampamentoActividadDAO;
import data.dao.CampamentoDAO;
import data.dao.MonitorDAO;


/**
 * Clase que implementa el patrón Singleton para poder ser utilizada en la creación de Campamentos.
 * @author Enrique de los Reyes Montilla
 */
public class GestorCampamentos {
		/**
		 * Variable privada Singleton.
		 */
		private static GestorCampamentos instance_ = null;
		/**
		 * Lista de campamentos disponibles.
		 */
		private ArrayList<Campamento> listaCampamentos_;
		/**
		 * Lista de monitores disponibles.
		 */
		private ArrayList<Monitor> listaMonitores_;
		/**
		 * Lista de actividades disponibles.
		 */
		private ArrayList<Actividad> listaActividades_;
		/**
		 * Constructor privado que crea una lista de campamentos, monitores y actividades vacia.
		 */
		private GestorCampamentos() {
			this.listaActividades_ = null;
			this.listaCampamentos_ = null;
			this.listaMonitores_ = null;
		};
		/**
		 * Metodo que sirve de acceso a la instancia.
		 * @return Instancia de la clase GestorCampamentos.
		 */
		public static GestorCampamentos getInstance() {
			if(instance_ == null ) {
				instance_ = new GestorCampamentos();
			}
			return instance_;
		}
		/**
		 * Método que devuelve la lista de campamentos del gestor.
		 * @return ArrayList<Campamento>.
		 */
		public ArrayList<Campamento> getListaCampamentos() {
			if(listaCampamentos_ == null){
				CampamentoDAO db;
				db = CampamentoDAO.getInstance();
				listaCampamentos_ = db.readAll();
			}
			return listaCampamentos_;
		}
		/**
		 * Método que devuelve la lista de monitores del gestor.
		 * @return ArrayList<Monitor>.
		 */
		public ArrayList<Monitor> getListaMonitores() {
			if(listaMonitores_ == null){
				MonitorDAO db;
				db = MonitorDAO.getInstance();
				listaMonitores_ = db.readAll();
			}
			return listaMonitores_;
		}
		/**
		 * Método que devuelve la lista de actividades del gestor.
		 * @return ArrayList<Actividad>.
		 */
		public ArrayList<Actividad> getListaActividades() {
			if(listaActividades_ == null){
				ActividadDAO db;
				db = ActividadDAO.getInstance();
				listaActividades_ = db.readAll();
			}
			return listaActividades_;
		}
		/**
		 * Método que establece la lista de actividades del gestor.
		 * @param listaActividades
		 * @return void.
		 */
		public void setListaActividades(ArrayList<Actividad> listaActividades){
			this.listaActividades_ = listaActividades;
		}
		/**
		 * Método que establece la lista de monitores del gestor.
		 * @param listaMonitores
		 * @return void.
		 */
		public void setListaMonitores(ArrayList<Monitor> listaMonitores){
			this.listaMonitores_ = listaMonitores;
		}
		/**
		 * Método que establece la lista de campamentos del gestor.
		 * @param listaCampamento
		 * @return void.
		 */
		public void setListaCampamentos(ArrayList<Campamento> listaCampamentos){
			this.listaCampamentos_ = listaCampamentos;
		}
		/**
		 * Metodo que añade a la lista de monitores un nuevo monitor pasado como argumento.
		 * @param monitor Monitor que se va a añadir a la lista de monitores.
		 * @return void.
		 */
		public void crearMonitor(Monitor monitor) {	
			listaMonitores_ = null;
			MonitorDAO db = MonitorDAO.getInstance();
			db.create(monitor);
		}
		/**
		 * Metodo que añade a la lista de actividades una nueva actividad pasada como argumento.
		 * @param actividad Actividad que se va a añadir a la lista de actividades.
		 * @return void.
		 */
		public void crearActividad(Actividad actividad) {
			listaActividades_ = null;
			ActividadDAO db = ActividadDAO.getInstance();
			db.create(actividad);
		}
		/**
		 * Metodo que crea Campamentos mediante valores introducidos por el usuario.
		 * @return void
		 */
		public void crearCampamento(Campamento campamento) {
			listaCampamentos_ = null;
			CampamentoDAO db = CampamentoDAO.getInstance();
			db.create(campamento);
		}
		/**
		 * Metodo para asociar una actividad a un monitor.
		 * @param Monitor Monitor que se va a asociar a una actividad.
		 * @param Actividad Actividad a la cual se va a asociar un monitor.
		 * @return Void.
		 */
		public void asociarMonitorActividad(Actividad actividad, Monitor monitor) {
			ActividadMonitorDAO db = ActividadMonitorDAO.getInstance();
			ActividadMonitorDTO am = ActividadMonitorDTO(actividad.getId(), monitor.getId());
			db.create(am);
		}
		/**
		 * Metodo para asociar una actividad a un campamento.
		 * @param idCampamento Id del campamento al que se va a asociar una actividad.
		 * @param actividad Actividad que se va a asociar al campamento.
		 * @return Boolean.
		 */
		public boolean asociarActividadCampamento(int idCampamento, Actividad actividad) {
			CampamentoActividadDAO db = CampamentoActividadDAO.getInstance();
			CampamentoActividadDTO ca = CampamentoActividadDTO(actividad.getId(), idCampamento);
			return db.create(ca);
		}
		/**
		 * Metodo para asociar un monitor a un campmento.
		 * @param idCampamento Id del campamento al que se va a asociar un monitor.
		 * @param monitor Monitor que se va a asociar al campamento.
		 * @return Boolean.
		 */
		public boolean asociarMonitorCampamento(int idCampamento, Monitor monitor) {
			CampamentoDAO db = CampamentoDAO.getInstance();
			for(Campamento cam : this.listaCampamentos_){
				if(cam.getId() == idCampamento){
					cam.setResponsable(monitor);
				}
			}
			return db.update(new Campamento(idCampamento);
		}
		/**
		 * Metodo para asociar un monitor especial a un campmento.
		 * @param idCampamento Id del campamento al que se va a asociar un monitor.
		 * @param monitor Monitor que se va a asociar al campamento.
		 * @return Boolean.
		 */
		public boolean asociarMonitorEspCampamento(int idCampamento, Monitor monitor) {
			CampamentoDAO db = CampamentoDAO.getInstance();
			for(Campamento cam : this.listaCampamentos_){
				if(cam.getId() == idCampamento){
					cam.setResponsableEspecial(monitor);
				}
			}
			return db.update(new Campamento(idCampamento);
		}
}
