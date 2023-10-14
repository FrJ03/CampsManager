package persona;

	/**
	* Clase que representa a una persona
 	@author Lucía Téllez López
	**/
public class Persona {
	/**
	* Identificador de la persona, de tipo entero
	**/
	  private int id;
	/**
	* Nombre y apellidos, de tipo cadena de caracteres
	**/
	  private String nombre;
	  private String apellidos;
	
	/**
	* Creadores de la clase con y sin parámetros
	**/
	  public Persona() {}
	  public Persona(int id, String nombre, String apellidos) {
		  this.id = id;
		  this.nombre = nombre;
		  this.apellidos = apellidos;
	  }

	/**
	* Métodos get/set para todos los atributos
	**/
	  public int get_id() {return id;}
	  public String get_nombre() {return nombre;}
	  public String get_apellidos() {return apellidos;}
	  public String get_nombre_completo() {   String nombrecompleto=this.nombre+this.apellidos;
	                                          return nombrecompleto;}
	  public void set_id(int id) {this.id=id;}
	  public void set_nombre(String nombre) {this.nombre=nombre;}
	  public void set_apellidos(String apellidos) {this.apellidos=apellidos;}
}
