package gestorCampamentos;

import java.util.ArrayList;
import java.util.Collections;

import actividad.Actividad;
import asistente.Asistente;
import campamento.Campamento;
import monitor.Monitor;

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
			this.listaActividades_ = new ArrayList<Actividad>();
			this.listaCampamentos_ = new ArrayList<Campamento>();
			this.listaMonitores_ = new ArrayList<Monitor>();
			/*this.listaCampamentos_.clear();
			this.listaMonitores_.clear();
			this.listaActividades_.clear();*/
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
			return listaCampamentos_;
		}
		/**
		 * Método que devuelve la lista de monitores del gestor.
		 * @return ArrayList<Monitor>.
		 */
		public ArrayList<Monitor> getListaMonitores() {
			return listaMonitores_;
		}
		/**
		 * Método que devuelve la lista de actividades del gestor.
		 * @return ArrayList<Actividad>.
		 */
		public ArrayList<Actividad> getListaActividades() {
			return listaActividades_;
		}
		/**
		 * Método que establece la lista de actividades del gestor.
		 * @param listaActividades
		 * @return void.
		 */
		public void setListaActividades(ArrayList<Actividad> listaActividades){
			this.listaActividades_=listaActividades;
		}
		/**
		 * Método que establece la lista de monitores del gestor.
		 * @param listaMonitores
		 * @return void.
		 */
		public void setListaMonitores(ArrayList<Monitor> listaMonitores){
			this.listaMonitores_=listaMonitores;
		}
		/**
		 * Método que establece la lista de campamentos del gestor.
		 * @param listaCampamento
		 * @return void.
		 */
		public void setListaCampamentos(ArrayList<Campamento> listaCampamentos){
			this.listaCampamentos_=listaCampamentos;
		}
		/**
		 * Metodo que añade a la lista de monitores un nuevo monitor pasado como argumento.
		 * @param monitor Monitor que se va a añadir a la lista de monitores.
		 * @return void.
		 */
		public void crearMonitor(Monitor monitor) {	
			if(this.listaMonitores_.size() > 0)
				monitor.setId(this.listaMonitores_.get(this.listaMonitores_.size() - 1).getId() + 1);
			else
				monitor.setId(1);
			this.listaMonitores_.add(monitor);
			Collections.sort(this.listaMonitores_);
		}
		/**
		 * Metodo que añade a la lista de actividades una nueva actividad pasada como argumento.
		 * @param actividad Actividad que se va a añadir a la lista de actividades.
		 * @return void.
		 */
		public void crearActividad(Actividad actividad) {
			if(this.listaActividades_.size() > 0)
				actividad.setId(this.listaActividades_.get(this.listaActividades_.size() - 1).getId() + 1);
			else
				actividad.setId(1);
			this.listaActividades_.add(actividad);	
			Collections.sort(this.listaActividades_);
		}
		/**
		 * Metodo que crea Campamnetos mediante valores introducidos por el usuario.
		 * @return void
		 */
		public void crearCampamento(Campamento campamento) {
			if(this.listaCampamentos_.size() > 0)
				campamento.setId(this.listaCampamentos_.get(this.listaCampamentos_.size() - 1).getId() + 1);
			else
				campamento.setId(1);
	        this.listaCampamentos_.add(campamento);
	        Collections.sort(this.listaCampamentos_);
		}
		/**
		 * Metodo para asociar una actividad a un monitor.
		 * @param Monitor Monitor que se va a asociar a una actividad.
		 * @param Actividad Actividad a la cual se va a asociar un monitor.
		 * @return Void.
		 */
		public void asociarMonitorActividad( Actividad actividad, Monitor monitor) {
			for(Actividad act : this.listaActividades_) {				
				if(act.getId() == actividad.getId()) {
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
				if(cam.getId() == idCampamento) {
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
				
				if(cam.getId() == idCampamento) {
					
					for(Actividad act : cam.getListaActividad()) {
						
						for(Monitor mon : act.getListaMonitores()) {
							
							if(mon.getId() == monitor.getId()) {
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
				
				if(cam.getId() == idCampamento) {
					
					for(Actividad act : cam.getListaActividad()) {
						
						for(Monitor mon : act.getListaMonitores()) {
							
							if(mon.getId() == monitor.getId()) {
								return false;
							}
							
						}
						
					}
					
					if(monitor.getEspecial()) {
						aux = cam.getListaMonitor().add(monitor);
					}
					
				}
				
			}
			
			return aux;
		}
		
	
		
		
}
