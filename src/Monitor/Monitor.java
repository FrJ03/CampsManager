package Monitor;

import Persona.Persona;

/**Esta  clase  representa  a  una  persona  que  se  encarga  de  dirigir  las  actividades  que  suceden  en  el  campamento
 * @author Francisco José Mellado Ortiz
 */
public class Monitor extends Persona{
	
/**Representa si el educador esta cualificado para monitorizar actividades donde participen asistentes con necesidades especiales
 */
private boolean especial;

/**
 * Construye un objeto de la clase monitor vacío.
 */
public Monitor() {}
/**
 * Construye un objeto de la clase monitor con la información facilitada.
 * @param id Identificador del monitor.
 * @param nombre Nombre del monitor.
 * @param apellidos Apellidos del monitor
 * @param especial Representa si el monitor puede realizar actividades para asistentes con necesidades especiales (true) o no (false)
 */
public Monitor(int id, String nombre, String apellidos, boolean especial){
	super(id, nombre, apellidos);
  this.especial=especial;
}

/**
 * Método que devuelve si un monitor puede impartir actividades en las que participen asistentes con necesidades especiales.
 * @return true si puede impartir las actividades o false en caso contrario.
 */
public boolean get_especial() {
	  return this.especial;
}
/**
 * Establece si el monitor puede impartir actividades para asistentes con necesidades especiales.
 * @param especial Valdrá true si puede impartir las actividades o false en caso contrario.
 */
public void set_especial(boolean especial) {
	  this.especial = especial;
}
/**
 * Función que devuelve una cadena de caracteres con información del monitor.
 * @return La estrucutra es la siguiente:
 * Id: (id)\n
 * Nombre Completo: (nombreCompleto)\n
 * Grupo Especial: (Si/No)\n
 */
public String toString() {
	 String ret ="Id: " + this.get_id() + 
			 	"\nNombre Completo: " + this.get_nombre_completo()+ 
			 	"\nGrupo especial: ";
	 if(this.especial) {
		 ret.concat("Si\n");
	 }
	 else {
		 ret.concat("No\n");
	 }
	 return ret;
}

}
