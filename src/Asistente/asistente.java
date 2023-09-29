package Asistente;
/**
 * Clase que representa a un asistente al campamento
 * @author Francisco José Mellado Ortiz
 */
//Paquete para la Clase Date
import java.util.Date;

 public class Asistente extends Persona{
     /*Contiene si el Asistente requiere una atención
      especial. Será verdadero si la requiere o falso
      en caso contrario.*/
     private boolean especial;
     private Date fecha_nacimiento;

     public Asistente(){

     }
     public Asistente(int id, String nombre, String apellido, Date fecha_nacimiento,String especial){
        this.id = id;
        this.nombre = nombre;
        this.fecha_nacimiento = fecha_nacimiento;
        this.especial = especial;
     }
     public Date get_fecha_nacimiento() {
    	 return this.fecha_nacimiento;
     }
     public boolean get_especial() {
    	 return especial;
     }
     public set_fecha_nacimiento(Date fecha_nacimiento) {
    	 this.fecha_nacimiento = fecha_nacimiento;
     }
     public set_especial(boolean especial) {
    	 this.especial;
     }
     public String toString() {
    	 String ret("Id: " + this.id + 
    			 	"\nNombre Completo: " + this.nombre + " " + this.apellidos + 
    			 	"\nFecha de nacimiento: " + this.fecha_nacimiento.toString() + 
    			 	"\nGrupo especial: ");
    	 if(this.especial) {
    		 ret.concat("Si\n");
    	 }
    	 else {
    		 ret.concat("No\n");
    	 }
     }
 }