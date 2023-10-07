package Registro
import java.uil.Date;

	  /**
	  * Clase que representa los registros tardíos que se pueden hacer en una inscripción.
 	  @author Lucía Téllez López
	  **/

public class RegistroTemprano extends Registro{
  /**
  * Construye un objeto de la clase registro temprano sin información.
  **/
  public RegistroTemprano(){
    super();
  }
  /**
  * Construye un objeto de la clase registro temprano estableciendo el valor de reserva.
  **/
  public RegistroTemprano(){
    super();
  }


   /**
   * Método que valida un registro.
   * @param fechaInscripcion Fecha en la que se solicita registrarse.
   * @param inicioCampamento Fecha en la que comienza el campamento.
   * Transforma el tiempo en días y compara los días que hay entre ambos
   */
  @Override
  public void registro(Date fechaInscripcion, Date inicioCampamento){
      long diaInscripcion = fechaInscripcion.getTime() ; 
      long diaCampamento = fechaHasta.getTime();
      long diaInicioInsc = (long) Math.floor(diaInscripcion / (1000*60*60*24));
      long diaInicioCamp = (long) Math.floor(diaCampamento / (1000*60*60*24));
      long diferencia = diaInicioCamp - diaInicioInsc;
    
    if(diferencia<15){
        //System.out.println("Se ha pedido un registro a un campamento que empieza en menos de 15 días, no se ha permitido.");
      }
    else{
        this.setStatus(true);
        //System.out.println("Se ha realizado el registro correctamente.");
    }
  }
  /**
  * Método que cancela un registro.
  **/
  @Override
  public void cancelacion(){
    this.setStatus(false);
    //System.out.println("Se ha cancelado el registro.");
  }
    
}
