package controller.dto.assistant;

import java.time.LocalDate;
import java.io.Serializable;
import controller.dto.person.Person;

/**
 * Clase que representa a un asistente al campamento
 * @author Francisco José Mellado Ortiz
 */
 public class AssistantDTO extends Person implements Serializable, Comparable<AssistantDTO>{
	 /**
	  * Representa si el asistente necesita atención especial
	  * */
     private boolean especial_;
     /**
	  * Representa el email del asistente
	  * */
     private String email_;
     /**
      * Fecha de nacimiento del asistente
      * */
     private LocalDate fechaNacimiento_;

     /**
      * Construye un objeto vacio*/
     public AssistantDTO(){

     }
     /**Método que indica la versión de la clase que se va a serializar.
 	 */
 	 private static final long serialVersionUID = 3L;
     /**
      * Construye un objeto con todos sus datos.
      * @param id Identificador único del asistente.
      * @param nombre Nombre del asistente.
      * @param apellidos Apellidos del asistente.
      * @param fechaNacimiento Representa la fecha de nacimiento del asistente.
      * @param especial Indica si en asistente pertenece a un grupo especial (true) o no (false).
      * @param email Email del asistente
      * */
     public AssistantDTO(int id, String email, String nombre, String apellidos, LocalDate fechaNacimiento,boolean especial){
    	 super(id, nombre, apellidos);
        this.fechaNacimiento_ = fechaNacimiento;
        this.especial_ = especial;
        this.email_ = email;
     }
     /**
      * Devuelve la fecha de nacimiento del asistente.
      * */
     public LocalDate get_fechaNacimiento() {
    	 return this.fechaNacimiento_;
     }
     /**
      * Devuelve si asistente necesita atención especial.
      * @return true si es necesaria una atención especial, false en caso contrario.
      * */
     public boolean getEspecial() {
    	 return especial_;
     }
     /**
      * Devuelve el email del asistente.
      * @return string Email del asistente.
      * */
     public String getEmail() {
    	 return email_;
     }
     /**
      * Establece una nueva fecha de nacimiento del asistente.
      * @param fechaNacimiento fecha de nacimiento a establecer.
      */
     public void setFechaNacimiento(LocalDate fechaNacimiento) {
    	 this.fechaNacimiento_ = fechaNacimiento;
     }
     /**
      * Modifica si el asistente necesita atención especial.
      * @param especial true si es requerida una atención especial, false en caso contrario.
      */
     public void setEspecial(boolean especial) {
    	 this.especial_ = especial;
     }
     /**
      * Modifica el email del asitente.
      * @param String Nuevo email del asistente.
      */
     public void setEmail(String email) {
    	 this.email_ = email;
     }
     /**
      * Función que devuelve una cadena de caracteres con información del asistente.
      * @return La estrucutra es la siguiente:
      * Id: (id)\n
      * Nombre Completo: (nombreCompleto)\n
      * Fecha de Nacimiento: (fechaNacimiento)\n
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
