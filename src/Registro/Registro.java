package Registro;
import java.util.Date;

  	/**
	* Clase que representa datos básicos de registros que se pueden hacer en una inscripcion
 	@author Lucía Téllez López
	**/

public abstract class Registro{

	/**
 	* Atributos del registro, "reserva" expresa si la reserva se ha registrado correctamente
  	* y "idAsociado" muestra el ID del participante del campamento que se asocia a la reserva
  	**/
	private boolean reserva;
	
	/**
 	* Constructor sin parámetros
 	**/
	public Registro(){}
	/**
 	* Constructor con parámetros, en la función registro
  	* se cambiará el valor de la reserva a true si se
   	* cumplen los requisitos
 	**/
	public Registro(boolean reserva){
		this.reserva=false;
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
 	* Función que permite cambiar la validez
  	* del registro, para poder registrarlo o
   	* cancelarlo
  	**/
	public void setStatus(boolean status){
		this.reserva=status;
	}
	
  /**
  * Funciones que permiten registrar o cancelar un registro
  **/
  public abstract void registro(Date fechaInscripcion, Date inicioCampamento);
  public abstract void cancelacion();
}
