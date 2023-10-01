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
	 /**
	  * Observador de la varible id
	  * */
	public int getId_() {
		return id_;
	}
	/**
	  * Modificador de la variable id
	  * */
	public void setId_(int id) {
		this.id_ = id;
	}
	/**
	  * Observador de la varible iniciocampamento
	  * */
	public Date getIniciocampamento_() {
		return iniciocampamento_;
	}
	/**
	  * Modificador de la variable iniciocampamento
	  * */
	public void setIniciocampamento_(Date iniciocampamento) {
		this.iniciocampamento_ = iniciocampamento;
	}
	/**
	  * Observador de la varible fincampamento
	  * */
	public Date getFincampamento_() {
		return fincampamento_;
	}
	/**
	  * Modificador de la variable fincampamento
	  * */
	public void setFincampamento_(Date fincampamento) {
		this.fincampamento_ = fincampamento;
	}
	/**
	  * Observador de la varible nivel
	  * */
	public nivel getNivel_() {
		return nivel_;
	}
	/**
	  * Modificador de la variable nivel
	  * */
	public void setNivel_(nivel nivel) {
		this.nivel_ = nivel;
	}
	/**
	  * Observador de la varible asistentesmax
	  * */
	public int getAsistentesMax_() {
		return asistentesMax_;
	}
	/**
	  * Modificador de la variable asistentesmax
	  * */
	public void setAsistentesMax_(int asistentesMax) {
		this.asistentesMax_ = asistentesMax;
	}
	/**
	  * Observador de la varible ListaActividad
	  * */
	public ArrayList<Actividad> getListaActividad_() {
		return listaActividad_;
	}
	/**
	  * Modificador de la variable ListaActividad
	  * */
	public void setListaActividad_(ArrayList<Actividad> listaActividad) {
		this.listaActividad_ = listaActividad;
	}
	/**
	  * Observador de la varible ListaMonitor
	  * */
	public ArrayList<Monitor> getListaMonitor_() {
		return listaMonitor_;
	}
	/**
	  * Modificador de la variable ListaMonitor
	  * */
	public void setListaMonitor_(ArrayList<Monitor> listaMonitor) {
		this.listaMonitor_ = listaMonitor;
	};
}
