package view.beans.person;

import java.io.Serializable;

/**
	* Clase que representa a una persona
 	@author Lucía Téllez López
	**/
public class Persona implements Serializable{
	/**Método que indica la versión de la clase que se va a serializar.
	 */
		private static final long serialVersionUID = 1L;
	/**
	* Identificador de la persona, de tipo entero
	**/
	  private int id_;
	/**
	* Nombre y apellidos, de tipo cadena de caracteres
	**/
	  private String nombre_;
	  private String apellidos_;
	
	/**
	* Creadores de la clase con y sin parámetros
	**/
	  public Persona() {}
	  public Persona(int id, String nombre, String apellidos) {
		  this.id_ = id;
		  this.nombre_ = nombre;
		  this.apellidos_ = apellidos;
	  }

	/**
	* Métodos get/set para todos los atributos
	**/
	  public int getId() {return id_;}
	  public String getNombre() {return nombre_;}
	  public String getApellidos() {return apellidos_;}
	  public String getNombreCompleto() {   String nombrecompleto=this.nombre_+ " " + this.apellidos_;
	                                          return nombrecompleto;}
	  public void setId(int id) {this.id_=id;}
	  public void setNombre(String nombre) {this.nombre_=nombre;}
	  public void setApellidos(String apellidos) {this.apellidos_=apellidos;}
}
