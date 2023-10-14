package asistente;

import java.util.Date;
import persona.Persona;

/**
 * Clase que representa a un asistente al campamento
 * @author Francisco José Mellado Ortiz
 */
 public class Asistente extends Persona implements Comparable<Asistente>{
     
	 /**
	  * Representa si el asistente necesita atención especial
	  * */
     private boolean especial;
     /**
      * Fecha de nacimiento del asistente
      * */
     private Date fechaNacimiento;

     /**
      * Construye un objeto vacio*/
     public Asistente(){

     }
     /**
      * Construye un objeto con todos sus datos.
      * @param id Identificador único del asistente.
      * @param nombre Nombre del asistente.
      * @param apellidos Apellidos del asistente.
      * @param fechaNacimiento Representa la fecha de nacimiento del asistente.
      * @param especial Indica si en asistente pertenece a un grupo especial (true) o no (false).
      * */
     public Asistente(int id, String nombre, String apellidos, Date fechaNacimiento,boolean especial){
    	 super(id, nombre, apellidos);
        this.fechaNacimiento = fechaNacimiento;
        this.especial = especial;
     }
     /**
      * Devuelve la fecha de nacimiento del asistente.
      * */
     public Date get_fechaNacimiento() {
    	 return this.fechaNacimiento;
     }
     /**
      * Devuelve si asistente necesita atención especial.
      * @return true si es necesaria una atención especial, false en caso contrario.
      * */
     public boolean getEspecial() {
    	 return especial;
     }
     /**
      * Establece una nueva fecha de nacimiento del asistente.
      * @param fechaNacimiento fecha de nacimiento a establecer.
      */
     public void setFechaNacimiento(Date fechaNacimiento) {
    	 this.fechaNacimiento = fechaNacimiento;
     }
     /**
      * Modifica si el asistente necesita atención especial.
      * @param especial true si es requerida una atención especial, false en caso contrario.
      */
     public void setEspecial(boolean especial) {
    	 this.especial = especial;
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
    			 	"\nFecha de Nacimiento: " + this.fechaNacimiento.toString() + 
    			 	"\nGrupo Especial: ";
    	 if(this.especial) {
    		 ret.concat("Si\n");
    	 }
    	 else {
    		 ret.concat("No\n");
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
