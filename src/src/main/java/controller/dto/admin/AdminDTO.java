package controller.dto.admin;

import java.io.Serializable;
import controller.dto.person.Person;

/**
 * Class that represents an admin
 */
public class AdminDTO extends Person implements Serializable,  Comparable<AdminDTO>{
    /**
	  * Representa el email del admin
	  * */
    private String email_;
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
     * */
    public AdminDTO(int id, String email, String nombre, String apellidos){
   	 super(id, nombre, apellidos);
       this.email_ = email;
    }
    /**
     * Devuelve el email del admin.
     * @return string Email del admin.
     * */
    public String getEmail() {
   	 return email_;
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
   			 	"\nEmail del admin: " + this.email_;
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
