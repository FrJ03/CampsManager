package registro;

import java.time.LocalDate;
import java.time.Period;

	/**
	* Clase que representa los registros tardíos que se pueden hacer en una inscripción.
 	@author Lucía Téllez López
	**/

public class RegistroTardio extends Registro{

  /**
  *  Construye un objeto de la clase registro tardío
  **/
  public RegistroTardio(){
    super();
  }
  /**Método que indica la versión de la clase que se va a serializar.
	 */
	private static final long serialVersionUID = 2L;
  /**
	* Método que valida un registro.
	* @param fechaInscripcion Fecha en la que se solicita registrarse.
   	* @param inicioCampamento Fecha en la que comienza el campamento.
    	* Transforma el tiempo en días y compara los días que hay entre ambos
 * @throws Exception 
  */
  @Override
  public void registro(LocalDate fechaInscripcion, LocalDate inicioCampamento) throws Exception {
      // Calcular la diferencia entre las dos fechas
      Period periodo = fechaInscripcion.until(inicioCampamento);

      // Obtener el número de días de la diferencia
      int diferenciaDias = periodo.getDays();
    
    if(diferenciaDias<15 && diferenciaDias>2){
        this.setStatus(true);
        //Se ha realizado el registro correctamente
      }
    else{
    	throw new Exception("Error: La solicitud de un registro tardío se debe realizar como muy tarde 2 días antes y como muy pronto 15 días antes del comienzo del campamento. No se ha permitido el registro.");
    }
  }
  
  /**
  * Método que cancela un registro.
  */
  @Override
  public void cancelacion() throws Exception {
	throw new Exception("Error: No se permite cancelar un registro tardío. No se ha cancelado.");
  }
    
}
