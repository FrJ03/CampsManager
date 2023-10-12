package Actividad;
import java.util.ArrayList;
import Monitor.Monitor;
/**
 * Clase que representa una actividad que se encuentra en un campamento.
 * @author Enrique de los Reyes Montilla
 */
public class Actividad	{
	/**
	  * Representa el nombre de la actividad
	  * */
	private String name_;
	/**
	  * Representa el nivel educativo de la actividad
	  * */
	private nivel nivel_;
	/**
	  * Representa el turno en el cual se realiza la actividad. Puede ser tanto mañanas como tardes.
	  * */
	private turno turno_;
	/**
	  * Representa el numero máximo de participantes
	  * */
	private int participantesMax_;
	/**
	  * Representa el numero máximo de monitores
	  * */
	private int monitoresMax_;
	/**
	  * Representa una lista con los nombres y apellidos de los monitores
	  * */
	private ArrayList<Monitor>	listaMonitores_;
	/**
	  * Construye un objeto vacio.
	  * @return void
	  * */
	public Actividad() {};
	/**
	  * COnstruye un objeto con todos sus datos, a excepción de la lista de monitores que se inicializa vacía.
	  * @return void
	  * */
	public Actividad(String name, nivel nivel, int participantesMax, 
			int monitoresMax, turno turno) {
		this.name_=name;
		this.nivel_=nivel;
		this.participantesMax_=participantesMax;
		this.monitoresMax_=monitoresMax;
		this.turno_=turno;
		this.listaMonitores_.clear();
	};
	/**
	  * Observador de la varible nombre
	  * @return string
	  * */
	public String getName_() {
		return name_;
	}
	/**
	  * Modificador de la variable nombre
	  * @param name Nombre de la actividad
	  * @return void
	  * */
	public void setName_(String name) {
		this.name_ = name;
	}
	/**
	  * Observador de la variable participantesMax
	  * @return int
	  * */
	public int getParticipantesMax_() {
		return participantesMax_;
	}
	/**
	  * Modificador de la variable participantesMax.
	  * @param participantesMax Numero de participantes maximmos en la actividad
	  * @return void
	  * */
	public void setParticipantesMax_(int participantesMax) {
		this.participantesMax_ = participantesMax;
	}
	/**
	  * Observador de la variable monitoresMax.
	  * @return int
	  * */
	public int getMonitoresMax_() {
		return monitoresMax_;
	}
	/**
	  * Modificador de la variable monitoresMax.
	  * @param monitoresMax Número de monitores maximos en la actividad
	  * */
	public void setMonitoresMax_(int monitoresMax) {
		this.monitoresMax_ = monitoresMax;
	}
	/**
	  * Observador de la variable listaMonitores.
	  * @return ArrayList<Monitor>
	  * */
	public ArrayList<Monitor> getListaMonitores_() {
		return listaMonitores_;
	}
	/**
	  * Modificador de la variable listaMonitores
	  * @param lista_monitores Lista de monitores de la actividad
	  * @return void
	  * */
	public void setListaMonitores_(ArrayList<Monitor> lista_monitores) {
		this.listaMonitores_ = lista_monitores;
	}
	/**
	  * Observador de la variable nivel.
	  * @return nivel
	  * */
	public nivel getNivel_() {
		return nivel_;
	}
	/**
	  * Modificador de la variable nivel.
	  * @param nivel Enumeración que india el nivel de la actividad
	  * @return void
	  * */
	public void setNivel_(nivel nivel) {
		this.nivel_=nivel;
	}
	/**
	  * Observador de la variable turno.
	  * @return turno
	  * */
	public turno getTurno_() {
		return turno_;
	}
	/**
	  * Modificador de la variable turno.
	  * @param turno Enumeración que india el turno de la actividad
	  * @return void
	  * */
	public void setTurno_(turno turno) {
		this.turno_=turno;
	}
	/**
	  * Método que devuelve un string con la información de la actividad.
	  * @return String
	  * */
	public String toString(){
		String aux = "Información de la Actividad\nNombre:" + this.name_ + "\nNivel Academico:"+ this.nivel_ + 
				"\nTurno:" + this.turno_ + "\nNumero maximo de participantes:" + this.participantesMax_ + 
				"\nNumero de monitores:" + this.monitoresMax_ + "\n";
		for(Monitor mon : this.listaMonitores_) {
			aux.concat(mon.get_nombre() + mon.get_apellidos());
		}
		
		return aux;
	}
	/**
	  * Añade un monitor a la lista de monitores, siempre que no se supere el número de monitores máximos.
	  * @param mon Monitor que se va a asociar a la actividad.
	  * @return boolean
	  * */
	public boolean asociarMonitor(Monitor mon) {
		if (this.listaMonitores_.size() < this.monitoresMax_) {
			
			if(!this.listaMonitores_.contains(mon)) {
				this.listaMonitores_.add(mon);
			}
			
		}
		
		return false;
	}
	
}
