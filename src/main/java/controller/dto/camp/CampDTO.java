package controller.dto.camp;

import java.io.Serializable;
import java.time.LocalDate;
import controller.dto.activity.Nivel;
import controller.dto.monitor.MonitorDTO;

/**
 * Class that represents a camp
 * @author Manuel García Obrero
 */
 public class CampDTO implements Comparable<CampDTO>, Serializable{
     
	/**
	 * Serial version attribute
	 */
	private static final long serialVersionUID = 1L;
	/**
	  * Camp identifier
	  */
	private int id_;
	/**
	  * Monitor responsible
	  */
	private MonitorDTO responsable_;
	/**
	  * Special monitor responsible
	  */
	private MonitorDTO responsableEspecial_;
	/**
	  * Camp Start date
	  */
	private LocalDate iniciocampamento_;
    /**
	  * Camp End date
	  */
	private LocalDate fincampamento_;
	/**
	  * Age level of the camp
	  */
	private Nivel nivel_;
	/**
	  * Max number of assistants
	  */
	private int asistentesMax_;

	/**
	  * Empty constructor
	  */
	public CampDTO() {};
	/**
	  * Constructor
	  * @param id Camp identifier
	  * @param iniciocampamento Camp start date
	  * @param fincampamento Camp end date
	  * @param nivel Representa Camp level
	  * @param asistentesMax Max number of assistants
	  */
	public CampDTO(int id, LocalDate iniciocampamento, LocalDate fincampamento, Nivel nivel, int asistentesMax) {

		this.id_=id;
		this.iniciocampamento_=iniciocampamento;
		this.fincampamento_=fincampamento;
		this.nivel_=nivel;
		this.asistentesMax_=asistentesMax;
		this.responsable_=null;
		this.responsableEspecial_ = null;
	}
	 /**
	  * Id get method
	  * @return int Camp id
	  * */
	public int getId() {
		return id_;
	}
	/**
	  * Id set method
	  * @param id New id
	  */
	public void setId(int id) {
		this.id_ = id;
	}
	/**
	  * Start date get method
	  * @return LocalDate Start date
	  */
	public LocalDate getInicioCampamento() {
		return iniciocampamento_;
	}
	/**
	  * Start date set method
	  * @param iniciocampamento Start date
	  */
	public void setInicioCampamento(LocalDate iniciocampamento) {
		this.iniciocampamento_ = iniciocampamento;
	}
	/**
	  * End date get method
	  * @return LocalDate End date
	  */
	public LocalDate getFinCampamento() {
		return fincampamento_;
	}
	/**
	  * End date set method
	  * @param fincampamento New end date
	  * @return void.
	  */
	public void setFinCampamento(LocalDate fincampamento) {
		this.fincampamento_ = fincampamento;
	}
	/**
	  * Level get method
	  * @return Nivel Camp level
	  */
	public Nivel getNivel() {
		return nivel_;
	}
	/**
	  * Level set method
	  * @param Nivel New level
	  */
	public void setNivel(Nivel nivel) {
		this.nivel_ = nivel;
	}
	/**
	  * Max number of assistants get method
	  * @return int Max number of assistants
	  */
	public int getAsistentesMax() {
		return asistentesMax_;
	}
	/**
	  * Max number of assistant set method
	  * @param asistentesMax New max number
	  */
	public void setAsistentesMax(int asistentesMax) {
		this.asistentesMax_ = asistentesMax;
	}
	/**
	  * Responsible monitor get method
	  * @return MonitorDTO
	  */
	public MonitorDTO getResponsable() {
		return responsable_;
	}
	/**
	  * Monitor responsible set method
	  * @param responsable New monitor responsible
	  */
	public void setResponsable(MonitorDTO responsable) {
		this.responsable_ = responsable;
	}
	/**
	  * Special monitor responsible get method
	  * @return MonitorDTO Special monitor responsible
	  * */
	public MonitorDTO getResponsableEspecial() {
		return responsableEspecial_;
	}
	/**
	  * Special monitor responsible set method
	  * @param responsableEspecial Special responsible mointor
	  * @return void.
	  * */
	public void setResponsableEspecial(MonitorDTO responsableEspecial) {
		this.responsableEspecial_ = responsableEspecial;
	}
	/**
	  * Method that returns a String with camp data
	  * @return The structure is:
	  * Información del Campamento
      * Id: (id)\n
      * Fecha de Inicio: (iniciocampamento_)\n
      * Fin del campamento: (fincampamento_)\n
      * Nivel Academico: (nivel)\n
      * Numero maximo de asistentes: (asistentesMax_)\n
      * Monitor Responsable:\n\t
      * Id: (responsable.id)\n\t
      * Nombre: (responsable.name)\n
      * Monitor Especial:\n\t
      * Id: (responsableEspecial.id)\n\t
      * Nombre: (responsableEspecial.name)\n
	  * */
	public String toString(){
		String aux = "Información del Campamento\n\tId: " + this.id_ + "\n\tFecha de Inicio: " + this.iniciocampamento_ + 
				"\n\tFin del campamento: " + this.fincampamento_ + "\n\tNivel Academico: "+ this.nivel_ + "\n\tNumero maximo de asistentes: " + this.asistentesMax_ + "\n\tMonitor Responsable: \n\t\tID: " +
				responsable_.getId() + "\n\t\tNombre: "+responsable_.getNombreCompleto() + "\n\tMonitor Especial: ";
		if(responsableEspecial_ == null)
			aux += "Empty";
		else
			aux += "\n\t\tID: " + responsableEspecial_.getId() + "\n\t\tNombre: " + this.getResponsableEspecial().getNombreCompleto();
		aux += "\n";
		return aux;
	}
	/**
	 * Method that compares camps
	 * @return int 0 if both camps has the same id, -1 if this camp id is less than the other, 1 otherwise
	 */
	@Override
    public int compareTo(CampDTO c) {
		if(c.getId() > this.getId())
			return -1;
		else if(c.getId() > this.getId()) 
			return 1;
		else 
			return 0;
   	 
    }
}
