package Registro
import java.uil.Date;

	/**
	* Clase que representa los registros tardíos que se pueden hacer en una inscripción.
 	@author Lucía Téllez López
	**/

public class RegistroTardio extends Registro{
  /**
  *  Construye un objeto de la clase registro tardío sin información.
  **/
  public RegistroTardio(){
    super();
  }
  /**
  *  Construye un objeto de la clase registro tardío estableciendo el valor de reserva.
  **/
  public RegistroTardio(){
    super();
  }

  /**
	 * Método que valida un registro.
	 * @param fechaInscripcion Fecha en la que se solicita registrarse.
   * @param inicioCampamento Fecha en la que comienza el campamento.
	 */
  @Override
  public void registro(Date fechaInscripcion, Date inicioCampamento){
      long diaInscripcion = fechaInscripcion.getTime() ; 
      long diaCampamento = fechaHasta.getTime();
      long diaInicioInsc = (long) Math.floor(diaInscripcion / (1000*60*60*24));
      long diaInicioCamp = (long) Math.floor(diaCampamento / (1000*60*60*24));
      long diferencia = diaInicioCamp - diaInicioInsc;
    
    if(diferencia<15 && diferencia>2){
        this.setStatus(true);
        System.out.println("Se ha realizado el registro correctamente.");
      }
    else{
        System.out.println("La solicitud de un registro tardío se debe realizar como muy tarde 2 días antes y como muy pronto 15 días antes del comienzo del campamento. No se ha permitido el registro.");
    }
  }
  
  /**
	 * Método que cancela un registro.
	 */
  @Override
  public void cancelacion(){
    System.out.println("No se permite cancelar un registro tardío. No se ha cancelado.");
  }
    
}
