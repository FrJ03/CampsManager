package GestorCampamentos;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import Campamento.Campamento;
import Monitor.Monitor;
import Actividad.Actividad;
import Actividad.nivel;
import Actividad.turno;
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
		 * Metodo que crea monitores mediante valores introducidos por el usuario.
		 * @return void.
		 */
		public void crearMonitor() {
			Scanner aux = new Scanner(System.in);
			Monitor mon = new Monitor();
			
			System.out.println("Introduzca el Id del monitor");
			mon.set_id(aux.nextInt());
			
			System.out.println("Introduzca el nombre del monitor");
			mon.set_nombre(aux.nextLine());
			
			System.out.println("Introduzca el apellido del monitor");
			mon.set_apellidos(aux.nextLine());
			
			System.out.println("Introduzca si el monitor es un educador especial");
			System.out.println("1. Si");
			System.out.println("2. No");
			int eleccion = aux.nextInt();
			
			if(eleccion == 1) {
				mon.set_especial(true);
			}
			else if(eleccion == 2) {
				mon.set_especial(false);
			}
			else {
				System.out.println("Error, valor introducido no reconocido");
				return;
			}
			
			this.listaMonitores_.add(mon);
		}
		/**
		 * Metodo que crea actividades mediante valores introducidos por el usuario.
		 * @retun void.
		 */
		public void crearActividad() {
			Actividad act = new Actividad();
			Scanner var = new Scanner(System.in);
			
			System.out.println("Introduzca el nombre de la actividad");
			act.setName_(var.nextLine());
			
			System.out.println("Introduzca el nivel de la actividad");
			System.out.println("1. Infantil");
			System.out.println("2. Juvenil");
			System.out.println("3. Adolescente");
			int eleccion = var.nextInt();
			
			if(eleccion == 1) {
				act.setNivel_(nivel.Infantil);
			}
			else if(eleccion == 2) {
				act.setNivel_(nivel.Juvenil);
			}
			else if(eleccion == 3){
				act.setNivel_(nivel.Adolescente);
			}
			else {
				System.out.println("Error, valor introducido no reconocido");
				return;
			}
			
			System.out.println("Introduzca el turno de la actividad");
			System.out.println("1. Manana");
			System.out.println("2. Tarde");
			eleccion = var.nextInt();
			
			if(eleccion == 1) {
				act.setTurno_(turno.Mañana);
			}
			else if(eleccion == 2) {
				act.setTurno_(turno.Tarde);
			}
			else {
				System.out.println("Error, valor introducido no reconocido");
				return;
			}
			
			System.out.println("Introduzca el numero maximo de monitores de la actividad");
			act.setMonitoresMax_(var.nextInt());
			
			System.out.println("Introduzca el numero maximo de monitores de la actividad");
			act.setParticipantesMax_(var.nextInt());
			
		}
		/**
		 * Metodo que crea Campamnetos mediante valores introducidos por el usuario.
		 * @return void
		 */
		public void crearCampamento() {
			Scanner aux = new Scanner(System.in);
			Campamento cam = new Campamento();
			
			System.out.println("Introduzca el Id de la actividad");
			cam.setId_(aux.nextInt());
			
			System.out.println("Introduzca el nivel de la actividad");
			System.out.println("1. Infantil");
			System.out.println("2. Juvenil");
			System.out.println("3. Adolescente");
			int eleccion = aux.nextInt();
			
			if(eleccion == 1) {
				cam.setNivel_(nivel.Infantil);
			}
			else if(eleccion == 2) {
				cam.setNivel_(nivel.Juvenil);
			}
			else if(eleccion == 3){
				cam.setNivel_(nivel.Adolescente);
			}
			else {
				System.out.println("Error, valor introducido no reconocido");
				return;
			}
			
			System.out.println("Introduzca el aforo de la actividad");
			cam.setAsistentesMax_(aux.nextInt());
			
			System.out.println("Introduzca el anio en el que empezará el campamento");
			int anio = aux.nextInt();
			
			System.out.println("Introduzca el mes del anio(Entero) en el que empezara el campamento");
			int mes = aux.nextInt();
			
			System.out.println("Introduzca el dia del mes en el que empezara el campamento");
			int dia = aux.nextInt();
			
			Date d = new Date(anio, mes, dia);
			cam.setIniciocampamento_(d);

			System.out.println("Introduzca el anio en el que terminara el campamento");
			anio = aux.nextInt();
			
			System.out.println("Introduzca el mes del anio(Entero) en el que terminara el campamento");
			mes = aux.nextInt();
			
			System.out.println("Introduzca el dia del mes en el que terminara el campamento");
			dia = aux.nextInt();
			
			d = new Date(anio, mes, dia);
			cam.setFincampamento_(d);	
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
