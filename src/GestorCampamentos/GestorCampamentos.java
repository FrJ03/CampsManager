package GestorCampamentos;
import java.util.ArrayList;
import Campamento.Campamento;
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
		private static GestorCampamentos instance = null;
		/**
		 * Lista de campamentos disponibles.
		 */
		private ArrayList<Campamento> listaCampamentos;
		/**
		 * Constructor privado que crea una lista de campamentos vacia.
		 */
		private GestorCampamentos() {
			this.listaCampamentos.clear();
		};
		/**
		 * Metodo que sirve de acceso a la instancia.
		 * @return Instancia de la clase GestorCampamentos.
		 */
		public static GestorCampamentos getInstance() {
			if(instance == null ) {
				instance = new GestorCampamentos();
			}
			return instance;
		}
		/**
		 * Metodo que crea campamentos, monitores y actividades.
		 * @param listaActividades Lista de actividades disponibles.
		 * @param listaMonitores Lista de los monitores.
		 */
		public void crearCampamentoMonitorActividad(ArrayList<Actividad> listaActividades, ArrayList<Monitor> listaMonitores) {
			Monitor mon = new Monitor();
			Actividad act = new Actividad();
			Campamento cam = new Campamento();
			this.listaCampamentos.add(cam);
			listaActividades.add(act);
			listaMonitores.add(mon);
		}
		/**
		 * Metodo para asociar una actividad a un monitor.
		 * @param Monitor Monitor que se va a asociar a una actividad.
		 * @param Actividad Actividad a la cual se va a asociar un monitor.
		 * @return Actividad con el monitor asociado.
		 */
		public Actividad AsociarMonitorActividad( Actividad actividad, Monitor monitor) {
			actividad.asociarMonitor(monitor);
			return actividad;
		}
		
		
		
	
		
		
}
