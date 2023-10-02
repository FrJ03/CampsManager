package Campamento;
import java.util.ArrayList;
import java.util.Date;
import Monitor.Monitor;
import Actividad.Actividad;
import Actividad.nivel;

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
	  * Construye un objeto con todos sus datos, a excepción de todas las listas que se inicializan vacías
	  * */
	public Campamento(int id, Date iniciocampamento, Date fincampamento, nivel nivel, int asistentesMax) {

		this.id_=id;
		this.iniciocampamento_=iniciocampamento;
		this.fincampamento_=fincampamento;
		this.nivel_=nivel;
		this.asistentesMax_=asistentesMax;
		
		
		this.listaActividad_.clear();
		this.listaMonitor_.clear();
	}
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
	/**
	  * Función que devuelve un string con la información del campamento
	  * */
	public String toString(){
		String aux = "Información del Campamento\nId:" + this.id_ + "\nFecha de Inicio:" + this.iniciocampamento_ + 
				"\nFin del campamento:" + this.fincampamento_ + "\nNivel Academico:"+ this.nivel_ + "\nNumero maximo de asistentes:" + this.asistentesMax_ + "\n";
		
		
		for(Monitor mon : this.listaMonitor_) {
			aux.concat(mon.get_nombre() + mon.get_apellidos());
		}
		
		for(Actividad act : this.listaActividad_) {
			aux.concat(act.getName_());
		}
		
		return aux;
	}
	 	
	/**
	  * Función permita añadir una actividad al campamento si la actividad es del mismo nivel educativo que el campamento y te devuelve si se ha hecho correctamente o no. 
	  * */
	public String asociarActividad(Actividad act) {
		
		if(this.nivel_==act.getNivel_()){
			listaActividad_.add(act);
			return "Se ha añadido correctamente la actividad al campamento";
		}
				
		return "Error, tienen diferente nivel educativo";
	}
	/**
	  * Función permita establecer el monitor responsable de entre aquellos que están encargados de alguna de las actividades que conforman el campamento.  
	  * */
	public void asociarMonitor(Monitor mon) {
		this.listaMonitor_.add(mon);
	}
	 /**
	  * Función permita establecer un segundo monitor responsable entre aquellos identificados como de atención especial. Dicho monitor no puede estar asociado a ninguna de las actividades que componen el campamento, pues se trata de un refuerzo que estará de apoyo a todas ellas.  
	  * */
	public String asociarMonitorEspecial(Monitor mon) {
	
		if(mon.get_especial()==true) { 
			for(int i=0; i< listaActividad_.size(); i++) {
				int x=0;
				if(listaActividad_[i].getListaMonitores_()[x].get_nombre_completo()==mon.get_nombre_completo()) {
					return "Error, el monitor no es un monitor de atencion especial";
				}
				x++;
			}
			this.listaMonitor_.add(mon);
			return "Se ha incorporado correctamente";
		}
		return "Error, el monitor no es un monitor de atencion especial";
	}
}
}
