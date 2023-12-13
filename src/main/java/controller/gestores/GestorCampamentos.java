package controller.gestores;

import java.time.LocalDate;
import java.util.ArrayList;

import model.dao.ActividadDAO;
import model.dao.CampamentoDAO;
import model.dao.MonitorDAO;
import controller.dto.activity.*;
import controller.dto.camp.*;
import controller.dto.monitor.*;


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
		 * Método que devuelve la lista de campamentos del gestor.
		 * @return ArrayList<Campamento>.
		 */
		public ArrayList<CampDTO> getListaCampamentos() {
			CampamentoDAO db = CampamentoDAO.getInstance();
			return db.readAll();
		}
		/**
		 * Método que devuelve la lista de campamentos del gestor que tenga un numero de plazas disponible.
		 * @return ArrayList<Campamento>.
		 */
		public ArrayList<CampDTO> getListaCampamentos(int seats) {
			CampamentoDAO db = CampamentoDAO.getInstance();
			return db.readAllAvailableSeats(seats);
		}
		/**
		 * MÃ©todo que devuelve la lista de campamentos del gestor que tenga un numero de plazas disponible.
		 * @return ArrayList<Campamento>.
		 */
		public ArrayList<CampDTO> getListaCampamentos(Nivel love) {
			CampamentoDAO db = CampamentoDAO.getInstance();
			return db.readAllAvailableLevel(love);
		}
		/**
		 * MÃ©todo que devuelve la lista de monitores del gestor.
		 * @return ArrayList<Monitor>.
		 */
		public ArrayList<MonitorDTO> getListaMonitores() {
			MonitorDAO db = MonitorDAO.getInstance();
			return db.readAll();
		}
		/**
		 * MÃ©todo que devuelve la lista de actividades del gestor.
		 * @return ArrayList<Actividad>.
		 */
		public ArrayList<ActivityDTO> getListaActividades() {
			ActividadDAO db = ActividadDAO.getInstance();
			return db.readAll();
		}
		/**
		 * Método que devuelve la lista de actividades de un campamento.
		 * @param idC Identificador del campamento
		 * @return ArrayList<Actividad>.
		 */
		public ArrayList<ActivityDTO> getListaActividades(int idC) {
			CampamentoDAO db = CampamentoDAO.getInstance();
			return db.readActivitiesCamp(idC);
		}
		/**
		 * Método que devuelve la lista de monitores de un actividad
		 * @param idA Identificador de la actividad
		 * @return
		 */
		public ArrayList<MonitorDTO> getListaMonitores(int idA) {
			ActividadDAO db = ActividadDAO.getInstance();
			return db.readMonitorsActivity(idA);
		}
		/**
		 * Metodo que aÃ±ade a la lista de monitores un nuevo monitor pasado como argumento.
		 * @param monitor Monitor que se va a aÃ±adir a la lista de monitores.
		 * @return void.
		 */
		public Boolean crearMonitor(MonitorDTO monitor) {	
			MonitorDAO db = MonitorDAO.getInstance();
			return db.create(monitor);
		}
		/**
		 * Metodo que aÃ±ade a la lista de actividades una nueva actividad pasada como argumento.
		 * @param actividad Actividad que se va a aÃ±adir a la lista de actividades.
		 * @return void.
		 */
		public Boolean crearActividad(ActivityDTO actividad) {
			ActividadDAO db = ActividadDAO.getInstance();
			return db.create(actividad);
		}
		/**
		 * Method that create a new activity
		 * @param name New activity name
		 * @param level New activity level
		 * @param maxAssistants New activity max assistant number
		 * @param maxMonitors New activity max monitor number
		 * @param turn New activity turn
		 * @return Boolean
		 */
		public Boolean crearActividad(String name, String level, int maxAssistants, int maxMonitors, String turn) {
			Nivel l;
			Turno t;
			if(maxAssistants < 1 || maxMonitors < 1)
				return false;
			if(level.equalsIgnoreCase("infantil"))
				l = Nivel.Infantil;
			else if(level.equalsIgnoreCase("juvenil"))
				l = Nivel.Juvenil;
			else if(level.equalsIgnoreCase("adolescente"))
				l = Nivel.Adolescente;
			else
				return false;
			
			if(turn.equalsIgnoreCase("morning"))
				t = Turno.Morning;
			else if(turn.equalsIgnoreCase("afternoon"))
				t = Turno.Afternoon;
			else
				return false;
			ActivityDTO activity = new ActivityDTO(0, name, l, maxAssistants, maxMonitors, t);
			ActividadDAO db = ActividadDAO.getInstance();
			return db.create(activity);
		}
		/**
		 * Metodo que crea Campamentos mediante valores introducidos por el usuario.
		 * @return void
		 */
		public Boolean crearCampamento(LocalDate start, LocalDate end, Nivel level, int maxP, int idM) {
			MonitorDAO dbM = MonitorDAO.getInstance();
			MonitorDTO m = dbM.read(idM);
			if(m == null)
				return false;
			CampDTO campamento = new CampDTO(-1, start, end, level, maxP);
			campamento.setResponsable(m);
			CampamentoDAO db = CampamentoDAO.getInstance();
			return db.create(campamento);
		}
		public Boolean crearCampamento(String start, String end, String level, int maxP, int idM) {
			if(!isDate(start) || !isDate(end))
				return false;
			LocalDate s = LocalDate.of(Integer.parseInt(start.substring(0, 4)), Integer.parseInt(start.substring(5, 7)), Integer.parseInt(start.substring(8, 10)));
			LocalDate e = LocalDate.of(Integer.parseInt(end.substring(0, 4)), Integer.parseInt(end.substring(5, 7)), Integer.parseInt(end.substring(8, 10)));
			Nivel l;
			if(level.equalsIgnoreCase("infantil"))
				l = Nivel.Infantil;
			else if(level.equalsIgnoreCase("juvenil"))
				l = Nivel.Juvenil;
			else if(level.equalsIgnoreCase("adolescente"))
				l = Nivel.Adolescente;
			else
				return false;
			
			MonitorDAO dbM = MonitorDAO.getInstance();
			MonitorDTO m = dbM.read(idM);
			if(m == null)
				return false;
			CampDTO campamento = new CampDTO(-1, s, e, l, maxP);
			campamento.setResponsable(m);
			CampamentoDAO db = CampamentoDAO.getInstance();
			return db.create(campamento);
		}
		/**
		 * Metodo para asociar una actividad a un monitor.
		 * @param MonitorDTO Monitor que se va a asociar a una actividad.
		 * @param ActivityDTO Actividad a la cual se va a asociar un monitor.
		 * @return Void.
		 */
		public Boolean asociarMonitorActividad(int idA, int idM) {
			ActividadDAO dbA = ActividadDAO.getInstance();
			MonitorDAO dbM = MonitorDAO.getInstance();
			
			if(dbA.read(idA) == null || dbM.read(idM) == null)
				return false;
			else
				return dbA.addActivity(idA, idM);
		}
		/**
		 * Metodo para asociar una actividad a un campamento.
		 * @param idCampamento Id del campamento al que se va a asociar una actividad.
		 * @param actividad Actividad que se va a asociar al campamento.
		 * @return Boolean.
		 */
		public boolean asociarActividadCampamento(int idCampamento, int idActividad) {
			CampamentoDAO dbC = CampamentoDAO.getInstance();
			ActividadDAO dbA = ActividadDAO.getInstance();
			CampDTO cdto = dbC.read(idCampamento);
			ActivityDTO adto = dbA.read(idActividad);
			
			if(cdto == null || adto == null)
				return false;
			else {
				if(cdto.getNivel() == adto.getNivel()) 
					return dbC.addActivity(idCampamento, idActividad);
				else
					return dbC.addActivity(idCampamento, idActividad);
			}
			
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
			MonitorDTO monitor;
			if(dbC.read(idCampamento) == null || (monitor = dbM.read(idMonitor)) == null || !monitor.getEspecial())
				return false;
			else
				return dbC.updateEspecial(idCampamento, idMonitor);
		}
		private boolean isDate(String date) {
			return isDateFormat(date) && dateValid(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(5, 7)), Integer.parseInt(date.substring(8, 10)));
		}
		private boolean isDateFormat(String date) {
			return date.length() == 10 && isInteger(date.substring(0, 4)) && date.charAt(4) == '/' && isInteger(date.substring(5, 7)) && date.charAt(7) == '/' && isInteger(date.substring(8, 10));
		}
		private boolean dateValid(int year, int month, int day) {
			if((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) && day <= 31 && day > 0)
				return true;
			else if((month == 4 || month == 6 || month == 9 || month == 11) && day <= 30 && day > 0)
				return true;
			else if(month == 2) {
				if((year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) && day <= 29 && day > 0)
					return true;
				else if(!(year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) && day <= 28 && day > 0)
					return true;
				else 
					return false;
			}
			else 
				return false;
		}
		private boolean isInteger(String number) {
			return number != null && number.matches("[0-9]+");
		}
}
