package Campamento;
import java.util.ArrayList;
import java.util.Date;
import Monitor.Monitor;
import Actividad.Actividad;


/**
 * Clase que representa  la organización de un conjunto de actividades durante un periodo de tiempo limitado
 * @author Manuel García Obrero
 */
 public class Campamento {
     
	/**
	  * Representa el identificador del campamento
	  * */
	private int id_;
	/**
	  * Representa la fecha del inicio del campamento
	  * */
	private Date iniciocampamento_;
    /**
	  * Representa la fecha del fin del campamento
	  * */
	private Date fincampamento_;
	/**
	  * Representa el nivel educativo del campamento
	  * */
	private nivel nivel_;
	/**
	  * Representa el numero máximo de asistentes
	  * */
	private int asistentesMax_;
	/**
	  * Representa una lista con las Actividades
	  * */
	private ArrayList<Actividad>	listaActividad_;
    /**
	  * Representa una lista con los nombres y apellidos de los monitores
	  * */
	private ArrayList<Monitor>	listaMonitor_;

	/**
	  * Construye un objeto vacio
	  * */
	public Campamento() {};
	/**
	  * COnstruye un objeto con todos sus datos, a excepción de la lista de monitores que se inicializa vacía
	  * */
	public Campamento(String name, nivel nivel, int participantesMax, 
			int monitoresMax, turno turno) {

		this.listaMonitores_.clear();
	};
}
