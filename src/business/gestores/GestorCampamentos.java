package business.gestores;

import java.util.ArrayList;
import java.util.Collections;

import data.dao.ActividadDAO;
import data.dao.ActividadMonitorDAO;
import data.dao.CampamentoActividadDAO;
import data.dao.CampamentoDAO;
import data.dao.MonitorDAO;


/**
 * Clase que implementa el patrÃ³n Singleton para poder ser utilizada en la creaciÃ³n de Campamentos.
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
		 * MÃ©todo que devuelve la lista de campamentos del gestor.
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
		 * MÃ©todo que devuelve la lista de monitores del gestor.
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
		 * MÃ©todo que devuelve la lista de actividades del gestor.
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
		 * MÃ©todo que establece la lista de actividades del gestor.
		 * @param listaActividades
		 * @return void.
		 */
		public void setListaActividades(ArrayList<Actividad> listaActividades){
			this.listaActividades_ = listaActividades;
		}
		/**
		 * MÃ©todo que establece la lista de monitores del gestor.
		 * @param listaMonitores
		 * @return void.
		 */
		public void setListaMonitores(ArrayList<Monitor> listaMonitores){
			this.listaMonitores_ = listaMonitores;
		}
		/**
		 * MÃ©todo que establece la lista de campamentos del gestor.
		 * @param listaCampamento
		 * @return void.
		 */
		public void setListaCampamentos(ArrayList<Campamento> listaCampamentos){
			this.listaCampamentos_ = listaCampamentos;
		}
		/**
		 * Metodo que aÃ±ade a la lista de monitores un nuevo monitor pasado como argumento.
		 * @param monitor Monitor que se va a aÃ±adir a la lista de monitores.
		 * @return void.
		 */
		public void crearMonitor(Monitor monitor) {	
			listaMonitores_ = null;
			MonitorDAO db = MonitorDAO.getInstance();
			db.create(monitor);
		}
		/**
		 * Metodo que aÃ±ade a la lista de actividades una nueva actividad pasada como argumento.
		 * @param actividad Actividad que se va a aÃ±adir a la lista de actividades.
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
			ActividadMonitorDAO db;
			db = ActividadMonitorDAO.getInstance();
			ActividadMonitorDTO am = new ActividadMonitorDTO(actividad.getId(), monitor.getId());
			db.create(am);
		}
		/**
		 * Metodo para asociar una actividad a un campamento.
		 * @param idCampamento Id del campamento al que se va a asociar una actividad.
		 * @param actividad Actividad que se va a asociar al campamento.
		 * @return Boolean.
		 */
		public boolean asociarActividadCampamento(int idCampamento, Actividad actividad) {
			CampamentoActividadDAO db;
			db = CampamentoActividadDAO.getInstance();
			CampamentoActividadDTO ca = new CampamentoActividadDTO(actividad.getId(), idCampamento);
			return db.create(ca);
		}
		/**
		 * Metodo para asociar un monitor a un campamento.
		 * @param idCampamento Id del campamento al que se va a asociar un monitor.
		 * @param monitor Monitor que se va a asociar al campamento.
		 * @return Boolean.
		 */
		public boolean asociarMonitorCampamento(int idCampamento, Monitor monitor) {
			CampamentoDAO db;
			db = CampamentoDAO.getInstance();
			Campamento aux = new Campamento();
			aux.setId(idCampamento);
			Campamento camp = db.read(aux);
			camp.setResponsable(monitor);
			return db.update(new Campamento(camp.getId(), camp.getResponsable(), camp.getResponsableEspecial(), camp.getInicioCampamento(), camp.getFinCampamento(), camp.getNivel(), camp.getAsistentesMax()));
		}
		/**
		 * Metodo para asociar un monitor especial a un campamento.
		 * @param idCampamento Id del campamento al que se va a asociar un monitor.
		 * @param monitor Monitor que se va a asociar al campamento.
		 * @return Boolean.
		 */
		public boolean asociarMonitorEspCampamento(int idCampamento, Monitor monitor) {
			CampamentoDAO db;
			db = CampamentoDAO.getInstance();
			Campamento aux = new Campamento();
			aux.setId(idCampamento);
			Campamento camp = db.read(aux);
			camp.setResponsableEspecial(monitor);
			return db.update(new Campamento(camp.getId(), camp.getResponsable(), camp.getResponsableEspecial(), camp.getInicioCampamento(), camp.getFinCampamento(), camp.getNivel(), camp.getAsistentesMax()));
		}
}
