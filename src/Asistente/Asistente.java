package Asistente;
/**
 * Clase que representa a un asistente al campamento
 * @author Francisco José Mellado Ortiz
 */
//Paquete para la Clase Date
import java.util.Date;
import Persona.Persona;

 public class Asistente extends Persona{
     /*Contiene si el Asistente requiere una atención
      especial. Será verdadero si la requiere o falso
      en caso contrario.*/
     private boolean especial;
     private Date fecha_nacimiento;

     public Asistente(){

     }
     public Asistente(int id, String nombre, String apellidos, Date fecha_nacimiento,boolean especial){
    	 super(id, nombre, apellidos);
        this.fecha_nacimiento = fecha_nacimiento;
        this.especial = especial;
     }
     public Date get_fecha_nacimiento() {
    	 return this.fecha_nacimiento;
     }
     public boolean get_especial() {
    	 return especial;
     }
     public void set_fecha_nacimiento(Date fecha_nacimiento) {
    	 this.fecha_nacimiento = fecha_nacimiento;
     }
     public void set_especial(boolean especial) {
    	 this.especial = especial;
     }
     public String toString() {
    	 String ret ="Id: " + this.get_id()+ 
    			 	"\nNombre Completo: " + this.get_nombre_completo()+ 
    			 	"\nFecha de nacimiento: " + this.fecha_nacimiento.toString() + 
    			 	"\nGrupo especial: ";
    	 if(this.especial) {
    		 ret.concat("Si\n");
    	 }
    	 else {
    		 ret.concat("No\n");
    	 }
    	 return ret;
     }
 };
