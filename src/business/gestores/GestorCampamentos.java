package business.gestores;

import java.time.LocalDate;
import java.util.ArrayList;

import data.dao.ActividadDAO;
import data.dao.ActividadMonitorDAO;
import data.dao.CampamentoActividadDAO;
import data.dao.CampamentoDAO;
import data.dao.MonitorDAO;
import business.campamento.*;
import business.actividad.*;
import business.monitor.*;
import business.dto.*;


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
		 * Constructor privado que crea una lista de campamentos, monitores y actividades vacia.
		 */
		private GestorCampamentos() {}
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
			CampamentoDAO db = CampamentoDAO.getInstance();
			return db.readAll();
		}
		/**
		 * MÃ©todo que devuelve la lista de monitores del gestor.
		 * @return ArrayList<Monitor>.
		 */
		public ArrayList<Monitor> getListaMonitores() {
			MonitorDAO db = MonitorDAO.getInstance();
			return db.readAll();
		}
		/**
		 * MÃ©todo que devuelve la lista de actividades del gestor.
		 * @return ArrayList<Actividad>.
		 */
		public ArrayList<Actividad> getListaActividades() {
			ActividadDAO db = ActividadDAO.getInstance();
			return db.readAll();
		}
		/**
		 * Metodo que aÃ±ade a la lista de monitores un nuevo monitor pasado como argumento.
		 * @param monitor Monitor que se va a aÃ±adir a la lista de monitores.
		 * @return void.
		 */
		public Boolean crearMonitor(Monitor monitor) {	
			MonitorDAO db = MonitorDAO.getInstance();
			return db.create(monitor);
		}
		/**
		 * Metodo que aÃ±ade a la lista de actividades una nueva actividad pasada como argumento.
		 * @param actividad Actividad que se va a aÃ±adir a la lista de actividades.
		 * @return void.
		 */
		public Boolean crearActividad(Actividad actividad) {
			ActividadDAO db = ActividadDAO.getInstance();
			return db.create(actividad);
		}
		/**
		 * Metodo que crea Campamentos mediante valores introducidos por el usuario.
		 * @return void
		 */
		public Boolean crearCampamento(LocalDate start, LocalDate end, Nivel level, int maxP, int idM) {
			MonitorDAO dbM = MonitorDAO.getInstance();
			Monitor m = dbM.read(idM);
			if(m == null)
				return false;
			Campamento campamento = new Campamento(-1, start, end, level, maxP);
			campamento.setResponsable(m);
			CampamentoDAO db = CampamentoDAO.getInstance();
			return db.create(campamento);
		}
		/**
		 * Metodo para asociar una actividad a un monitor.
		 * @param Monitor Monitor que se va a asociar a una actividad.
		 * @param Actividad Actividad a la cual se va a asociar un monitor.
		 * @return Void.
		 */
		public Boolean asociarMonitorActividad(int idA, int idM) {
			ActividadMonitorDAO dbAM = ActividadMonitorDAO.getInstance();
			ActividadDAO dbA = ActividadDAO.getInstance();
			MonitorDAO dbM = MonitorDAO.getInstance();
			ActividadMonitorDTO am = new ActividadMonitorDTO(idA, idM);
			if(dbA.read(idA) == null || dbM.read(idM) == null)
				return false;
			else
				return dbAM.create(am);
		}
		/**
		 * Metodo para asociar una actividad a un campamento.
		 * @param idCampamento Id del campamento al que se va a asociar una actividad.
		 * @param actividad Actividad que se va a asociar al campamento.
		 * @return Boolean.
		 */
		public boolean asociarActividadCampamento(int idCampamento, int idActividad) {
			CampamentoActividadDAO db = CampamentoActividadDAO.getInstance();
			CampamentoActividadDTO ca = new CampamentoActividadDTO(idActividad, idCampamento);
			return db.create(ca);
		}
		/**
		 * Metodo para asociar un monitor a un campamento.
		 * @param idCampamento Id del campamento al que se va a asociar un monitor.
		 * @param monitor Monitor que se va a asociar al campamento.
		 * @return Boolean.
		 */
		public boolean asociarMonitorResponsable(int idCampamento, int idMonitor) {
			CampamentoDAO dbC = CampamentoDAO.getInstance();
			MonitorDAO dbM = MonitorDAO.getInstance();
			if(dbC.read(idCampamento) == null || dbM.read(idMonitor) == null)
				return false;
			else
				return dbC.updateResponsable(idCampamento, idMonitor);
		}
		/**
		 * Metodo para asociar un monitor especial a un campamento.
		 * @param idCampamento Id del campamento al que se va a asociar un monitor.
		 * @param monitor Monitor que se va a asociar al campamento.
		 * @return Boolean.
		 */
		public boolean asociarMonitorEspecial(int idCampamento, int idMonitor) {
			CampamentoDAO dbC = CampamentoDAO.getInstance();
			MonitorDAO dbM = MonitorDAO.getInstance();
			Monitor monitor;
			if(dbC.read(idCampamento) == null || (monitor = dbM.read(idMonitor)) == null || !monitor.getEspecial())
				return false;
			else
				return dbC.updateEspecial(idCampamento, idMonitor);
		}
}
