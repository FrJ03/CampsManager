package controller.dto.assistant;

import java.time.LocalDate;
import java.io.Serializable;
import controller.dto.person.Person;

/**
 * Class that representa an assistant
 * @author Francisco José Mellado Ortiz
 */
 public class AssistantDTO extends Person implements Serializable, Comparable<AssistantDTO>{
	 /**
	  * Especial assistant requirements. True if it is needed, false otherwise
	  */
     private boolean especial_;
     /**
	  * Email address
	  */
     private String email_;
     /**
      * Birth date
      */
     private LocalDate fechaNacimiento_;

     /**
      * Empty constructor
      */
     public AssistantDTO(){

     }
     /**
      * Serial version attribute
      */
 	 private static final long serialVersionUID = 3L;
     /**
      * Constructor.
      * @param id Assistant identifier
      * @param nombre Assistant name.
      * @param apellidos Assistant lastname.
      * @param fechaNacimiento Assistant birth date.
      * @param especial Especial requirements. True if it's needed, false otherwise
      * @param email Email address
      * */
     public AssistantDTO(int id, String email, String nombre, String apellidos, LocalDate fechaNacimiento,boolean especial){
    	 super(id, nombre, apellidos);
        this.fechaNacimiento_ = fechaNacimiento;
        this.especial_ = especial;
        this.email_ = email;
     }
     /**
      * Birth date get method
      * @return LocalDate Birth date
      */
     public LocalDate get_fechaNacimiento() {
    	 return this.fechaNacimiento_;
     }
     /**
      * Especial requirements get method
      * @return boolean true if the assistant require special attention, false otherwise
      */
     public boolean getEspecial() {
    	 return especial_;
     }
     /**
      * Email get method
      * @return String Assistant email
      */
     public String getEmail() {
    	 return email_;
     }
     /**
      *	Birth date set method
      * @param fechaNacimiento New birth date
      */
     public void setFechaNacimiento(LocalDate fechaNacimiento) {
    	 this.fechaNacimiento_ = fechaNacimiento;
     }
     /**
      * Special requirements set method
      * @param boolean true if the assistant require special attention, false otherwise
      */
     public void setEspecial(boolean especial) {
    	 this.especial_ = especial;
     }
     /**
      * Email set method
      * @param String New email
      */
     public void setEmail(String email) {
    	 this.email_ = email;
     }
     /**
      * Method that returns a String that contains assistant data
      * @return String The structure is:
      * Id: (id)\n
      * Nombre Completo: (nombreCompleto)\n
      * Fecha de Nacimiento: (fechaNacimiento)\n
      * Email del assistente: (email)\n
      * Grupo Especial: (Si/No)\n
      */
     public String toString() {
    	 String ret ="Id: " + this.getId()+ 
    			 	"\nNombre Completo: " + this.getNombreCompleto()+ 
    			 	"\nFecha de Nacimiento: " + this.fechaNacimiento_.toString() +
    			 	"\nEmail del asistente: " + this.email_ +
    			 	"\nGrupo Especial: ";
    	 if(this.especial_) {
    		 ret += "Si\n";
    	 }
    	 else {
    		 ret += "No\n";
    	 }
    	 return ret;
     }
     /**
      * Method that compare assistants
      * @return int 0 if both assistants has the same id, -1 if this assistant has an id less than the other or 1 otherwise
      */
     @Override
     public int compareTo(AssistantDTO a) {
		if(a.getId() > this.getId())
			return -1;
		else if(a.getId() > this.getId()) 
			return 1;
		else 
			return 0;
    	 
     }
 };
