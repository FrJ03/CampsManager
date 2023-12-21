package controller.dto.person;

import java.io.Serializable;

/**
* Class that represents a person
* @author Lucía Téllez López
*/
public class Person implements Serializable{
	/**
	 * Serial version attribute
	 */
		private static final long serialVersionUID = 1L;
	/**
	* Person identifier
	*/
	  private int id_;
	  /**
	   * Persona name
	   */
	  private String nombre_;
	  /**
	   * Persona lastname
	   */
	  private String apellidos_;
	  /**
	   * Empty constructor
	   */
	  public Person() {}
	  /**
	   * Constructor
	   * @param id Person identifier
	   * @param nombre Person name
	   * @param apellidos Person lastname
	   */
	  public Person(int id, String nombre, String apellidos) {
		  this.id_ = id;
		  this.nombre_ = nombre;
		  this.apellidos_ = apellidos;
	  }
	  /**
	   * Id get method
	   * @return int Person id
	   */
	  public int getId() {return id_;}
	  /**
	   * Name get method
	   * @return String Person name
	   */
	  public String getNombre() {return nombre_;}
	  /**
	   * Lastname get method
	   * @return String Person lastname
	   */
	  public String getApellidos() {return apellidos_;}
	  /**
	   * Fullname get method
	   * @return String Person fullname
	   */
	  public String getNombreCompleto() {
		  return this.nombre_+ " " + this.apellidos_;
      }
	  /**
	   * Id set method
	   * @param id New person id
	   */
	  public void setId(int id) {this.id_=id;}
	  /**
	   * Name set method
	   * @param nombre New Name
	   */
	  public void setNombre(String nombre) {this.nombre_=nombre;}
	  /**
	   * Lastname set method
	   * @param apellidos New lastname
	   */
	  public void setApellidos(String apellidos) {this.apellidos_=apellidos;}
}
