package Registro;
import java.util.Date;

  	/**
	* Clase que representa datos básicos de registros que se pueden hacer en una inscripcion
 	@author Lucía Téllez López
	**/

public abstract class Registro(){

	/**
 	* Atributos del registro, "reserva" expresa si la reserva se ha registrado correctamente
  	* y "idAsociado" muestra el ID del participante del campamento que se asocia a la reserva
  	**/
	private boolean reserva;
	private int idAsociado;
	
	/**
 	* Constructor sin parámetros
 	**/
	public abstract Registro(){}
	/**
 	* Constructor con parámetros, en la función registro
  	* se cambiará el valor de la reserva a true si se
   	* cumplen los requisitos
 	**/
	public abstract Registro(boolean reserva, int idAsociado){
		this.reserva=false;
		this.idAsociado=idAsociado;
	}

	/**
 	* Devuelve el estado del registro, es decir,
  	* si se ha registrado correctamente o por el contrario
   	* aún no se ha llamado a la función registro() o no se puede
    	* validar por incompatibilidad de fechas.
 	**/
	public boolean getStatus(){
		return this.reserva;
	}
	/**
 	* Devuelve el ID del participante que solicita el registro
  	**/
	public boolean getIdAsociado(){
		return this.idAsociado;
	}
	
  /**
  * Funciones que permiten registrar o cancelar un registro
  **/
  public abstract registro(Date fechaInscripcion, Date inicioCampamento);
  public abstract cancelacion();
}
