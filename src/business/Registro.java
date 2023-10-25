package business;

import java.time.LocalDate;
import java.io.Serializable;

  	/**
	* Clase que representa los registros que se pueden hacer en una inscripción.
 	@author Lucía Téllez López
	**/

public abstract class Registro implements Serializable{
	/**Método que indica la versión de la clase que se va a serializar.
	 */
	private static final long serialVersionUID = 1L;
	/**
 	* Estado del registro, true si se ha validado con el método registro()
  	**/
	private boolean reserva_;
	
	/**
 	* Construye un objeto de la clase registro estableciendo el valor de reserva.
 	**/
	public Registro(){
		this.reserva_=false;
	}

	/**
 	* Método que devuelve el estado del registro.
 	**/
	public boolean getStatus(){
		return this.reserva_;
	}

	/**
 	* Método para la modificación del estado del registro.
	 * @param status True o False según se quiera validar o cancelar un registro
  	**/
	public void setStatus(boolean status){
		this.reserva_=status;
	}
	
  /**
  * Método para la validación de un registro. Será definido por sus descendientes.
 * @throws Exception 
  **/
  public abstract void registro(LocalDate fechaInscripcion, LocalDate inicioCampamento) throws Exception;
  /**
  * Método para la cancelación de un registro. Será definido por sus descendientes.
 * @throws Exception 
  **/
  public abstract void cancelacion() throws Exception;
}
