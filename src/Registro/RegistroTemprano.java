package Registro
import java.uil.Date;

public class RegistroTemprano extends Registro{
  /**
  *Constructor sin parámetros
  **/
  public RegistroTemprano(){
    super();
  }
  /**
  *Constructor con parámetros
  **/
  public RegistroTemprano(boolean reserva, int idAsociado){
    super(reserva,idAsociado);
  }


  /**
  * Registra el registro para que se considerede válido
  Recibe las fechas de inscripción e inicio del campamento, las transforma para poder
  restarlas y comparar los días de diferencia.
  Si hay 15 días o más entre la fecha de inscripción y la fecha de comienzo del
  campamento, se puede realizar el registro.
  **/
  @Override
  public void registro(Date fechaInscripcion, Date inicioCampamento){
      long diaInscripcion = fechaInscripcion.getTime() ; 
      long diaCampamento = fechaHasta.getTime();
      long diaInicioInsc = (long) Math.floor(diaInscripcion / (1000*60*60*24));
      long diaInicioCamp = (long) Math.floor(diaCampamento / (1000*60*60*24));
      long diferencia = diaInicioCamp - diaInicioInsc;
    
    if(diferencia<15){
        System.out.println("Se ha pedido un registro a un campamento que empieza en menos de 15 días, no se ha permitido.");
      }
    else{
        this.reserva=true;
        System.out.println("Se ha realizado el registro correctamente.");
    }
  }
  /**
  * Cancela el registro
  **/
  @Override
  public void cancelacion(){
    this.reserva=false;
    System.out.println("Se ha cancelado la reserva");
  }
    
}
