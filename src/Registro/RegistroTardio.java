package Registro
import java.uil.Date;

public class RegistroTardio extends Registro{
  /**
  *Constructor sin parámetros
  **/
  public RegistroTardio(){
    super();
  }
  /**
  *Constructor con parámetros
  **/
  public RegistroTardio(boolean reserva, int idAsociado){
    super(reserva,idAsociado);
  }

  /**
  * Registra el registro para que sea válido
  Recibe las fechas de inscripción e inicio del campamento, las transforma para poder
  restarlas y comparar los días de diferencia.
  Si hay menos de 15 días pero 2 o más, se puede realizar el registro correctamente.
  **/
  @Override
  public void registro(Date fechaInscripcion, Date inicioCampamento){
      long diaInscripcion = fechaInscripcion.getTime() ; 
      long diaCampamento = fechaHasta.getTime();
      long diaInicioInsc = (long) Math.floor(diaInscripcion / (1000*60*60*24));
      long diaInicioCamp = (long) Math.floor(diaCampamento / (1000*60*60*24));
      long diferencia = diaInicioCamp - diaInicioInsc;
    
    if(diferencia<15 && diferencia>2){
        this.reserva=true;
        System.out.println("Se ha realizado el registro correctamente.");
      }
    else{
        System.out.println("La solicitud de un registro tardío se debe realizar como muy tarde 2 días antes y como muy pronto 15 días antes del comienzo del campamento. No se ha permitido el registro.");
    }
  }
  /**
  * Cancela el registro
  **/
  @Override
  public void cancelacion(){
    System.out.println("No se permite cancelar un registro tardío. No se ha cancelado.");
  }
    
}
