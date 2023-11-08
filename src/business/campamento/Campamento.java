package business.campamento;

import java.util.ArrayList;
import java.io.Serializable;
import java.time.LocalDate;
import business.monitor.*;
import business.actividad.*;

/**
 * Clase que representa  la organización de un conjunto de actividades durante un periodo de tiempo limitado
 * @author Manuel García Obrero
 */
 public class Campamento implements Comparable<Campamento>, Serializable{
     
	/**Método que indica la versión de la clase que se va a serializar.
	 */
	private static final long serialVersionUID = 1L;
	/**
	  * Representa el identificador del campamento
	  * */
	private int id_;
	/**
	  * Representa el monitor responsable del campamento
	  * */
	private Monitor responsable_;
	/**
	  * Representa eñ monitor especial del campamento
	  * */
	private Monitor responsableEspecial_;
	/**
	  * Representa la fecha del inicio del campamento
	  * */
	
	private LocalDate iniciocampamento_;
	    /**
	  * Representa la fecha del fin del campamento
	  * */
	private LocalDate fincampamento_;
	/**
	  * Representa el nivel educativo del campamento
	  * */
	private Nivel nivel_;
	/**
	  * Representa el numero máximo de asistentes
	  * */
	private int asistentesMax_;
	/**
	  * Representa una lista con las Actividades
	  * */
	private ArrayList<Actividad>	listaActividad_;

	/**
	  * Construye un objeto vacio
	  * */
	public Campamento() {};
	/**
	  * Construye un objeto con todos sus datos, a excepción de todas las listas que se inicializan vacías
	  * @param id Representa el identificador del campamento
	  * @param iniciocampamento Representa la fecha del inicio del campamento
	  * @param fincampamento Representa la fecha del fin del campamento
	  * @param nivel Representa el nivel educativo del campamento
	  * @param asistentesMax Representa el numero máximo de asistentes
	  * */
	public Campamento(int id, LocalDate iniciocampamento, LocalDate fincampamento, Nivel nivel, int asistentesMax) {

		this.id_=id;
		this.iniciocampamento_=iniciocampamento;
		this.fincampamento_=fincampamento;
		this.nivel_=nivel;
		this.asistentesMax_=asistentesMax;
		this.listaActividad_ = new ArrayList<Actividad>();
		this.responsable_=null;
		this.responsableEspecial_ = null;
	}
	 /**
	  * Observador de la varible id
	  * @return id_ del campamento.
	  * */
	public int getId() {
		return id_;
	}
	/**
	  * Modificador de la variable id
	  * @param id del campamento.
	  * @return void.
	  * */
	public void setId(int id) {
		this.id_ = id;
	}
	/**
	  * Observador de la varible iniciocampamento
	  * @return iniciocampamento_.
	  * */
	public LocalDate getInicioCampamento() {
		return iniciocampamento_;
	}
	/**
	  * Modificador de la variable iniciocampamento
	  * @param iniciocampamento_
	  * @return void.
	  * */
	public void setInicioCampamento(LocalDate iniciocampamento) {
		this.iniciocampamento_ = iniciocampamento;
	}
	/**
	  * Observador de la varible fincampamento
	  * @return fincampamento_.
	  * */
	public LocalDate getFinCampamento() {
		return fincampamento_;
	}
	/**
	  * Modificador de la variable fincampamento
	  * @param fincampamento_
	  * @return void.
	  * */
	public void setFinCampamento(LocalDate fincampamento) {
		this.fincampamento_ = fincampamento;
	}
	/**
	  * Observador de la varible nivel
	  * @return nivel_ del campamento.
	  * */
	public Nivel getNivel() {
		return nivel_;
	}
	/**
	  * Modificador de la variable nivel
	  * @param nivel del campamento.
	  * @return void.
	  * */
	public void setNivel(Nivel nivel) {
		this.nivel_ = nivel;
	}
	/**
	  * Observador de la varible asistentesmax
	  * @return asistentesMax_ del campamento.
	  * */
	public int getAsistentesMax() {
		return asistentesMax_;
	}
	/**
	  * Modificador de la variable asistentesmax
	  * @param asistentesMax del campamento.
	  * @return void.
	  * */
	public void setAsistentesMax(int asistentesMax) {
		this.asistentesMax_ = asistentesMax;
	}
	/**
	  * Observador de la varible ListaActividad
	  * @return listaActividad_.
	  * */
	public ArrayList<Actividad> getListaActividad() {
		return listaActividad_;
	}
	/**
	  * Modificador de la variable ListaActividad
	  * @param listaActividad.
	  * @return void.
	  * */
	public void setListaActividad(ArrayList<Actividad> listaActividad) {
		this.listaActividad_ = listaActividad;
	}
	/**
	  * Observador de la varible responsable_
	  * @return responsable_.
	  * */
	public Monitor getResponsable() {
		return responsable_;
	}
	/**
	  * Modificador de la variable responsable_
	  * @param responsable.
	  * @return void.
	  * */
	public void setResponsable(Monitor responsable) {
		this.responsable_ = responsable;
	}
	/**
	  * Observador de la varible responsableEspecial
	  * @return responsableEspecial.
	  * */
	public Monitor getResponsableEspecial() {
		return responsableEspecial_;
	}
	/**
	  * Modificador de la variable responsableEspecial_
	  * @param responsableEspecial_.
	  * @return void.
	  * */
	public void setResponsableEspecial(Monitor responsableEspecial_) {
		this.responsableEspecial_ = responsableEspecial_;
	}
	/**
	  * Función que devuelve un string con la información del campamento
	  * @return La estrucutra es la siguiente:
	  * Información del Campamento
      * Id: (id)\n
      * Fecha de Inicio: (iniciocampamento_)\n
      * Fin del campamento: (fincampamento_)\n
      * Nivel Academico: (nivel)\n
      * Numero maximo de asistentes: (asistentesMax_)\n
      * listaMonitor_
      * listaActividad_
	  * */
	public String toString(){
		String aux = "Información del Campamento\n\tId:" + this.id_ + "\n\tFecha de Inicio:" + this.iniciocampamento_ + 
				"\n\tFin del campamento:" + this.fincampamento_ + "\n\tNivel Academico:"+ this.nivel_ + "\n\tNumero maximo de asistentes:" + this.asistentesMax_ + "\n\tMonitor Responsable:"+
				this.getResponsable() + "\n\tMonitor especial: " + this.getResponsableEspecial();		
		aux += "\n\tLista de Actividades:";
		for(Actividad act : this.listaActividad_) {
			aux += "\n\t\tId: " + act.getId() + "\n\t\tNombre: " + act.getName() + "\n";
		}
		aux += "\n";
		return aux;
	}
	 	
	/**
	  * Función permita añadir una actividad al campamento si la actividad es del mismo nivel educativo que el campamento y te devuelve true si se ha hecho correctamente o false si hay un error.
	  * @param act.
	  * @return boolean(True se ha añadido correctamente/ False error). 
	  * */
	public boolean asociarActividad(Actividad act) {
		
		if(this.nivel_==act.getNivel()){
			listaActividad_.add(act);
			return true;//Se ha añadido correctamente la actividad al campamento
		}
				
		return false; //Error, tienen diferente nivel educativo
	}
	@Override
    public int compareTo(Campamento c) {
		if(c.getId() > this.getId())
			return -1;
		else if(c.getId() > this.getId()) 
			return 1;
		else 
			return 0;
   	 
    }
}
