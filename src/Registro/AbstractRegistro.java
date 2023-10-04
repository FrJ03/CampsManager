package Registro;

  /**
	* Clase que representa datos báscicos de registros que se pueden hacer en una inscripcion
 	@author Lucía Téllez López
	**/

public abstract class AbstractRegistro(){
  /**
  *Creador sin parámetros
  **/ 
  public Registro() {}
  
  /**
  *Funciones que permiten registrar o cancelar un registro
  **/
  public abstract registro();
  public abstract cancelacion();
}
