package view.beans.registration;

import java.time.LocalDate;
import java.time.Period;


	  /**
	  * Clase que representa los registros tardíos que se pueden hacer en una inscripción.
 	  @author Lucía Téllez López
	  **/

public class RegistroTemprano extends Registro{

  /**
  * Construye un objeto de la clase registro temprano
  **/
  public RegistroTemprano(){
    super();
  }
  /**Método que indica la versión de la clase que se va a serializar.
   */
	private static final long serialVersionUID = 3L;

   /**
   * Método que valida un registro.
   * @param fechaInscripcion Fecha en la que se solicita registrarse.
   * @param inicioCampamento Fecha en la que comienza el campamento.
   * Transforma el tiempo en días y compara los días que hay entre ambos
   */
  @Override
  public void registro(LocalDate fechaInscripcion, LocalDate inicioCampamento) throws Exception {
	  // Calcular la diferencia entre las dos fechas
      Period periodo = fechaInscripcion.until(inicioCampamento);

      // Obtener el número de días de la diferencia
      int diferenciaDias = periodo.getDays();
    
    if(diferenciaDias<15){
	throw new Exception("Error: Se ha pedido un registro a un campamento que empieza en menos de 15 días, no se ha permitido.");
      }
    else{
        this.setStatus(true);
        //Se ha realizado el registro correctamente
    }
  }
  /**
  * Método que cancela un registro.
  **/
  @Override
  public void cancelacion(){
    this.setStatus(false);
    //Se ha cancelado el registro
  }
    
}
