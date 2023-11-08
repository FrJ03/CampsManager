package business.asistente;

import java.time.LocalDate;
import business.persona.*;

/**
 * Clase que representa a un asistente al campamento
 * @author Francisco José Mellado Ortiz
 */
 public class Asistente extends Persona implements Comparable<Asistente>{
     
	 /**
	  * Representa si el asistente necesita atención especial
	  * */
     private boolean especial_;
     /**
      * Fecha de nacimiento del asistente
      * */
     private LocalDate fechaNacimiento_;

     /**
      * Construye un objeto vacio*/
     public Asistente(){

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
      * */
     public Asistente(int id, String nombre, String apellidos, LocalDate fechaNacimiento,boolean especial){
    	 super(id, nombre, apellidos);
        this.fechaNacimiento_ = fechaNacimiento;
        this.especial_ = especial;
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
     public int compareTo(Asistente a) {
		if(a.getId() > this.getId())
			return -1;
		else if(a.getId() > this.getId()) 
			return 1;
		else 
			return 0;
    	 
     }
 };
