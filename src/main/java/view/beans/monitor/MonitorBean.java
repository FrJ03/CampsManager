package view.beans.monitor;

import view.beans.person.*;
import java.io.Serializable;

/**Esta  clase  representa  a  una  persona  que  se  encarga  de  dirigir  las  actividades  que  suceden  en  el  campamento
 * @author Francisco José Mellado Ortiz
 */
public class MonitorBean extends Person implements Serializable, Comparable<MonitorBean>{
	
/**Representa si el educador esta cualificado para monitorizar actividades donde participen asistentes con necesidades especiales
 */
private boolean especial_;
/**Método que indica la versión de la clase que se va a serializar.
 */
private static final long serialVersionUID = 1L;

/**
 * Construye un objeto de la clase monitor vacío.
 */
public MonitorBean() {}
/**
 * Construye un objeto de la clase monitor con la información facilitada.
 * @param id Identificador del monitor.
 * @param nombre Nombre del monitor.
 * @param apellidos Apellidos del monitor
 * @param especial Representa si el monitor puede realizar actividades para asistentes con necesidades especiales (true) o no (false)
 */
public MonitorBean(int id, String nombre, String apellidos, boolean especial){
	super(id, nombre, apellidos);
  this.especial_=especial;
}
/**
 * Método que devuelve si un monitor puede impartir actividades en las que participen asistentes con necesidades especiales.
 * @return true si puede impartir las actividades o false en caso contrario.
 */
public boolean getEspecial() {
	  return this.especial_;
}
/**
 * Establece si el monitor puede impartir actividades para asistentes con necesidades especiales.
 * @param especial Valdrá true si puede impartir las actividades o false en caso contrario.
 */
public void setEspecial(boolean especial) {
	  this.especial_ = especial;
}
/**
 * Función que devuelve una cadena de caracteres con información del monitor.
 * @return La estrucutra es la siguiente:
 * Id: (id)\n
 * Nombre Completo: (nombreCompleto)\n
 * Grupo Especial: (Si/No)\n
 */
public String toString() {
	 String ret ="Id: " + this.getId() + 
			 	"\nNombre Completo: " + this.getNombreCompleto()+ 
			 	"\nGrupo especial: ";
	 if(this.especial_) {
		 ret += "Si\n";
	 }
	 else {
		 ret += "No\n";
	 }
	 return ret;
}
@Override
public int compareTo(MonitorBean m) {
	if(m.getId() > this.getId())
		return -1;
	else if(m.getId() > this.getId()) 
		return 1;
	else 
		return 0;
	 
}
}
