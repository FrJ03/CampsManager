package controller.dto.monitor;

import controller.dto.person.Person;
import java.io.Serializable;

/**
 * Class that represents the camp monitors
 * @author Francisco José Mellado Ortiz
 */
public class MonitorDTO extends Person implements Serializable, Comparable<MonitorDTO>{
	
/**
 * Qualification for assistants with special requirements. True if the monitor has it, false otherwise
 */
private boolean especial_;
/**
 * Serial version attribute
 */
private static final long serialVersionUID = 1L;

/**
 * Empty constructor
 */
public MonitorDTO() {}
/**
 * Constructor
 * @param id Monitor identifier
 * @param nombre Monitor name
 * @param apellidos Monitor lastname
 * @param especial Qualification for assistants with special requirements. True if the monitor has it, false otherwise
 */
public MonitorDTO(int id, String nombre, String apellidos, boolean especial){
	super(id, nombre, apellidos);
  this.especial_=especial;
}
/**
 * Special qualification get method
 * @return boolean Special qualification. True if the monitor has it, false otherwise
 */
public boolean getEspecial() {
	  return this.especial_;
}
/**
 * Special qualification set method
 * @param especial New special qualification. True if the monitor has it, false otherwise
 */
public void setEspecial(boolean especial) {
	  this.especial_ = especial;
}
/**
 * Method that returns a string with the monitor data
 * @return String The structure is:
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
/**
 * Method that compares monitors
 * @return int 0 if both monitors has the same id, -1 if this monitor id is less than the other, 1 otherwise
 */
@Override
public int compareTo(MonitorDTO m) {
	if(m.getId() > this.getId())
		return -1;
	else if(m.getId() > this.getId()) 
		return 1;
	else 
		return 0;
	 
}
}
