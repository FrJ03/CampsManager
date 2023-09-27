//Esta  clase  representa  a  una  persona  que  se  encarga  de  dirigir  las  actividades  que  suceden  en  el  campamento

public class Monitor{
  //Identificador de la persona, de tipo entero
  private int id;
  //Nombre y apellidos, de tipo cadena de caracteres
  private String NomApe;
  //Si es o no un educador especial, de tipo booleano, para indicar si es elegible para  campamentos donde participan asistentes que requieren atención especial
  private bool EducadorEspecial;

  //Constructor vacío (sin parámetros)
  public Monitor();
  //Constructor parametrizado, cuyos parámetros sean todos los atributos de la clase
  public Monitor(int id, String NomApe, bool EducadorEspecial){
    this.id=id;
    this.NomApe=NomApe;
    this.EducadorEspecial=EducadorEspecial;
  }

  //Métodos get/set para todos los atributos
  

  //Método toString que imprima la información del monitor

  
}
