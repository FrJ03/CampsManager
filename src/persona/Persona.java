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
	  public int getId() {return id;}
	  public String getNombre() {return nombre;}
	  public String getApellidos() {return apellidos;}
	  public String getNombreCompleto() {   String nombrecompleto=this.nombre+this.apellidos;
	                                          return nombrecompleto;}
	  public void setId(int id) {this.id=id;}
	  public void setNombre(String nombre) {this.nombre=nombre;}
	  public void setApellidos(String apellidos) {this.apellidos=apellidos;}
}
