package controller.dto.admin;

import java.io.Serializable;
import java.time.LocalDate;

import controller.dto.person.Person;

public class AdminDTO extends Person implements Serializable,  Comparable<AdminDTO>{
	/**
	  * Representa si el admin necesita atención especial
	  * */
    private boolean especial_;
    /**
	  * Representa el email del admin
	  * */
    private String email_;
    /**
     * Fecha de nacimiento del admin
     * */
    private LocalDate fechaNacimiento_;

    /**
     * Construye un objeto vacio*/
    public AdminDTO(){

    }
    /**Método que indica la versión de la clase que se va a serializar.
	 */
	 private static final long serialVersionUID = 3L;
    /**
     * Construye un objeto con todos sus datos.
     * @param id Identificador único del admin.
     * @param email Email del admin
     * @param nombre Nombre del admin.
     * @param apellidos Apellidos del admin.
     * @param fechaNacimiento Representa la fecha de nacimiento del admin.
     * @param especial Indica si en admin pertenece a un grupo especial (true) o no (false).
     * */
    public AdminDTO(int id, String email, String nombre, String apellidos, LocalDate fechaNacimiento,boolean especial){
   	 super(id, nombre, apellidos);
       this.fechaNacimiento_ = fechaNacimiento;
       this.especial_ = especial;
       this.email_ = email;
    }
    /**
     * Devuelve la fecha de nacimiento del admin.
     * */
    public LocalDate get_fechaNacimiento() {
   	 return this.fechaNacimiento_;
    }
    /**
     * Devuelve si admin necesita atención especial.
     * @return true si es necesaria una atención especial, false en caso contrario.
     * */
    public boolean getEspecial() {
   	 return especial_;
    }
    /**
     * Devuelve el email del admin.
     * @return string Email del admin.
     * */
    public String getEmail() {
   	 return email_;
    }
    /**
     * Establece una nueva fecha de nacimiento del admin.
     * @param fechaNacimiento fecha de nacimiento a establecer.
     */
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
   	 this.fechaNacimiento_ = fechaNacimiento;
    }
    /**
     * Modifica si el admin necesita atención especial.
     * @param especial true si es requerida una atención especial, false en caso contrario.
     */
    public void setEspecial(boolean especial) {
   	 this.especial_ = especial;
    }
    /**
     * Modifica el email del asitente.
     * @param String Nuevo email del admin.
     */
    public void setEmail(String email) {
   	 this.email_ = email;
    }
    /**
     * Función que devuelve una cadena de caracteres con información del admin.
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
   			 	"\nEmail del admin: " + this.email_ +
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
    public int compareTo(AdminDTO a) {
		if(a.getId() > this.getId())
			return -1;
		else if(a.getId() > this.getId()) 
			return 1;
		else 
			return 0;
   	 
    }
}
