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
 * Class that manage all related with camps
 * @implSpec Singleton Design Pattern
 * @author Enrique de los Reyes Montilla
 */
public class GestorCampamentos {
		/**
		 * Singleton private instance.
		 */
		private static GestorCampamentos instance_ = null;
		/**
		 * Private constructor
		 */
		private GestorCampamentos() {}
		/**
		 * Instance access method.
		 * @return GestorCampamentos Instance
		 */
		public static GestorCampamentos getInstance() {
			if(instance_ == null ) {
				instance_ = new GestorCampamentos();
			}
			return instance_;
		}
		/**
		 * Method that gets all camps
		 * @return ArrayList<CampDTO> List of camps.
		 */
		public ArrayList<CampDTO> getListaCampamentos() {
			CampamentoDAO db = CampamentoDAO.getInstance();
			return db.readAll();
		}
		/**
		 * Method that reads all camps available with at least x number of places
		 * @param seats Minimum number of places
		 * @return ArrayList<CampDTO> List of camps.
		 */
		public ArrayList<CampDTO> getListaCampamentos(int seats) {
			CampamentoDAO db = CampamentoDAO.getInstance();
			return db.readAllAvailableSeats(seats);
		}
		/**
		 * Method that get all camps with an specific age level
		 * @param love Age level.
		 * @return ArrayList<CampDTO> List of camps.
		 */
		public ArrayList<CampDTO> getListaCampamentos(Nivel love) {
			CampamentoDAO db = CampamentoDAO.getInstance();
			return db.readAllAvailableLevel(love);
		}
		/**
		 * Method that returns all monitors
		 * @return ArrayList<MonitorDTO> List of monitors
		 */
		public ArrayList<MonitorDTO> getListaMonitores() {
			MonitorDAO db = MonitorDAO.getInstance();
			return db.readAll();
		}
		/**
		 * Method that returns all activities
		 * @return ArrayList<ActivityDTO> List of activities.
		 */
		public ArrayList<ActivityDTO> getListaActividades() {
			ActividadDAO db = ActividadDAO.getInstance();
			return db.readAll();
		}
		/**
		 * Method that returns all activities in a camp
		 * @param idC Camp identifier
		 * @return ArrayList<ActivityDTO> List of activities.
		 */
		public ArrayList<ActivityDTO> getListaActividades(int idC) {
			CampamentoDAO db = CampamentoDAO.getInstance();
			return db.readActivitiesCamp(idC);
		}
		/**
		 * Method that returns all monitors associated with an activity
		 * @param idA Activity identifier
		 * @return ArrayList<MonitorDTO> List of monitors.
		 */
		public ArrayList<MonitorDTO> getListaMonitores(int idA) {
			ActividadDAO db = ActividadDAO.getInstance();
			return db.readMonitorsActivity(idA);
		}
		/**
		 * Method that adds a monitor
		 * @param monitor Monitor to add
		 * @return boolean True if the monitor has been added, false otherwise
		 */
		public Boolean crearMonitor(MonitorDTO monitor) {	
			MonitorDAO db = MonitorDAO.getInstance();
			return db.create(monitor);
		}
		/**
		 * Method that add an activity
		 * @param actividad Activity to add
		 * @return boolean True if the activity has been added correctly, false otherwise
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
		 * @return Boolean True if the activity has been added correctly, false otherwise
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
		 * Method that add a new camp
		 * @param start Camp start date
		 * @param end Camp end date
		 * @param level Camp level
		 * @param maxP Max level of assistants registered in the camp
		 * @return boolean True if the camp has been added correctly, false otherwise
		 */
		public Boolean crearCampamento(LocalDate start, LocalDate end, Nivel level, int maxP) {
			CampDTO campamento = new CampDTO(-1, start, end, level, maxP);
			CampamentoDAO db = CampamentoDAO.getInstance();
			return db.create(campamento);
		}
		/**
		 * Method that add a new camp
		 * @param start Camp start date
		 * @param end Camp end date
		 * @param level Camp level
		 * @param maxP Max level of assistants registered in the camp
		 * @return boolean True if the camp has been added correctly, false otherwise
		 */
		public Boolean crearCampamento(String start, String end, String level, int maxP) {
			LocalDate s = LocalDate.parse(start);
			LocalDate e = LocalDate.parse(end);
			Nivel l;
			if(level.equalsIgnoreCase("infantil"))
				l = Nivel.Infantil;
			else if(level.equalsIgnoreCase("juvenil"))
				l = Nivel.Juvenil;
			else if(level.equalsIgnoreCase("adolescente"))
				l = Nivel.Adolescente;
			else
				return false;
			CampDTO campamento = new CampDTO(-1, s, e, l, maxP);
			CampamentoDAO db = CampamentoDAO.getInstance();
			return db.create(campamento);
		}
		/**
		 * Method that add a monitor to an activity
		 * @param idA Activity identifier
		 * @param idM Monitor identifier
		 * @return boolean True if the the monitor has been added to the activity, false otherwise
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
		 * method that add an activity to a camp
		 * @param idCampamento Camp id
		 * @param idActividad Activity id
		 * @return Boolean True if the activity has added to the camp, false otherwise
		 */
		public boolean asociarActividadCampamento(int idCampamento, int idActividad) {
			CampamentoDAO dbC = CampamentoDAO.getInstance();
			ActividadDAO dbA = ActividadDAO.getInstance();
			CampDTO cdto = dbC.read(idCampamento);
			ActivityDTO adto = dbA.read(idActividad);
			
			if(cdto == null || adto == null || !cdto.getNivel().toString().equalsIgnoreCase(adto.getNivel().toString())
			)
				return false;
			else {
				if(cdto.getNivel() == adto.getNivel()) 
					return dbC.addActivity(idCampamento, idActividad);
				else
					return dbC.addActivity(idCampamento, idActividad);
			}
			
		}
		/**
		 * Method that add a monitor responsible to a camp
		 * @param idCampamento Camp id
		 * @param idMonitor Monitor id
		 * @return Boolean True if the monitor has been added, else otherwise
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
		 * Method that add a special monitor responsible to a camp
		 * @param idCampamento Camp id
		 * @param idMonitor Monitor id
		 * @return Boolean True if the monitor has been added, else otherwise
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
		/**
		 * Method that check if a String is a date
		 * @param date Date
		 * @return boolean True if it is a date, false otherwise
		 */
		private boolean isDate(String date) {
			return isDateFormat(date) && dateValid(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(5, 7)), Integer.parseInt(date.substring(8, 10)));
		}
		/**
		 * Method that check if a string has a date format
		 * @param date Date
		 * @return boolean True if the string has a date format, false otherwise
		 */
		private boolean isDateFormat(String date) {
			return date.length() == 10 && isInteger(date.substring(0, 4)) && date.charAt(4) == '/' && isInteger(date.substring(5, 7)) && date.charAt(7) == '/' && isInteger(date.substring(8, 10));
		}
		/**
		 * Method that check a date
		 * @param year Year
		 * @param month Month
		 * @param day Day
		 * @return boolean True if the date exists, false otherwise
		 */
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
		/**
		 * Method that check if a string is a number
		 * @param number Number
		 * @return boolean True if is an integer, false otherwise
		 */
		private boolean isInteger(String number) {
			return number != null && number.matches("[0-9]+");
		}
}
