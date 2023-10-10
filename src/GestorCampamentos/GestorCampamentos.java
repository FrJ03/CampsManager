package GestorCampamentos;

import java.util.ArrayList;
import Campamento.Campamento;
import java.time.Period;
import Monitor.Monitor;
import Actividad.Actividad;
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
			this.listaCampamentos_.clear();
			this.listaMonitores_.clear();
			this.listaActividades_.clear();
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
		public ArrayList<Campamento> getListaCampamentos_() {
			return listaCampamentos_;
		}
		/**
		 * Método que devuelve la lista de monitores del gestor.
		 * @return ArrayList<Monitor>.
		 */
		public ArrayList<Monitor> getListaMonitores_() {
			return listaMonitores_;
		}
		/**
		 * Método que devuelve la lista de actividades del gestor.
		 * @return ArrayList<Actividad>.
		 */
		public ArrayList<Actividad> getListaActividades_() {
			return listaActividades_;
		}
		/**
		 * Método que establece la lista de actividades del gestor.
		 * @param listaActividades
		 * @return void.
		 */
		public void setListaActividades_(ArrayList<Actividad> listaActividades){
			this.listaActividades_=listaActividades;
		}
		/**
		 * Método que establece la lista de monitores del gestor.
		 * @param listaMonitores
		 * @return void.
		 */
		public void setListaMonitores_(ArrayList<Monitor> listaMonitores){
			this.listaMonitores_=listaMonitores;
		}
		/**
		 * Método que establece la lista de campamentos del gestor.
		 * @param listaCampamento
		 * @return void.
		 */
		public void setListaCampamentos_(ArrayList<Campamento> listaCampamentos){
			this.listaCampamentos_=listaCampamentos;
		}
		/**
		 * Metodo que añade a la lista de monitores un nuevo monitor pasado como argumento.
		 * @param monitor Monitor que se va a añadir a la lista de monitores.
		 * @return void.
		 */
		public void crearMonitor(Monitor monitor) {	
			this.listaMonitores_.add(monitor);
		}
		/**
		 * Metodo que añade a la lista de actividades una nueva actividad pasada como argumento.
		 * @param actividad Actividad que se va a añadir a la lista de actividades.
		 * @return void.
		 */
		public void crearActividad(Actividad actividad) {
			this.listaActividades_.add(actividad);			
		}
		/**
		 * Metodo que crea Campamnetos mediante valores introducidos por el usuario.
		 * @return void
		 */
		public void crearCampamento(Campamento campamento) {
	        this.listaCampamentos_.add(campamento);
		}
		/**
		 * Metodo para asociar una actividad a un monitor.
		 * @param Monitor Monitor que se va a asociar a una actividad.
		 * @param Actividad Actividad a la cual se va a asociar un monitor.
		 * @return Void.
		 */
		public void asociarMonitorActividad( Actividad actividad, Monitor monitor) {
			for(Actividad act : this.listaActividades_) {
				
				if(act.getName_() == actividad.getName_()) {
					act.asociarMonitor(monitor);
				}
				
			}
		}
		/**
		 * Metodo para asociar una actividad a un campmento.
		 * @param idCampamento Id del campamento al que se va a asociar una actividad.
		 * @param actividad Actividad que se va a asociar al campamento.
		 * @return Boolean.
		 */
		public boolean asociarActividadCampamento(int idCampamento, Actividad actividad) {
			boolean aux = false;
			for(Campamento cam : this.listaCampamentos_) {
				
				if(cam.getId_() == idCampamento) {
					aux = cam.asociarActividad(actividad);
				}
			}
			return aux;
		}
		/**
		 * Metodo para asociar un monitor a un campmento.
		 * @param idCampamento Id del campamento al que se va a asociar un monitor.
		 * @param monitor Monitor que se va a asociar al campamento.
		 * @return Boolean.
		 */
		public boolean asociarMonitorCampamento(int idCampamento, Monitor monitor) {
			boolean aux = false;
			
			for(Campamento cam : this.listaCampamentos_) {
				
				if(cam.getId_() == idCampamento) {
					
					for(Actividad act : cam.getListaActividad_()) {
						
						for(Monitor mon : act.getListaMonitores_()) {
							
							if(mon.get_id() == monitor.get_id()) {
								cam.asociarMonitor(monitor);
								aux = true;
							}
							
						}
						
					}
					
				}
				
			}
			
			return aux;
		}
		/**
		 * Metodo para asociar un monitor especial a un campmento.
		 * @param idCampamento Id del campamento al que se va a asociar un monitor.
		 * @param monitor Monitor que se va a asociar al campamento.
		 * @return Boolean.
		 */
		public boolean asociarMonitorEspCampamento(int idCampamento, Monitor monitor) {
			boolean aux = false;
			
			for(Campamento cam : this.listaCampamentos_) {
				
				if(cam.getId_() == idCampamento) {
					
					for(Actividad act : cam.getListaActividad_()) {
						
						for(Monitor mon : act.getListaMonitores_()) {
							
							if(mon.get_id() == monitor.get_id()) {
								return false;
							}
							
						}
						
					}
					
					if(monitor.get_especial()) {
						aux = cam.getListaMonitor_().add(monitor);
					}
					
				}
				
			}
			
			return aux;
		}
		
	
		
		
}
