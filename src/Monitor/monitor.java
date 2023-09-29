package Monitor;
//Esta  clase  representa  a  una  persona  que  se  encarga  de  dirigir  las  actividades  que  suceden  en  el  campamento

public class Monitor extends Persona{
  //Si es o no un educador especial, de tipo booleano, para indicar si es elegible para  campamentos donde participan asistentes que requieren atención especial
  private boolean especial;

  //Constructor vacío (sin parámetros)
  public Monitor();
  //Constructor parametrizado, cuyos parámetros sean todos los atributos de la clase
  public Monitor(int id, String nombre, String apellidos, boolean especial){
    this.id=id;
    this.nombre =nombre;
    this.apellidos = apellidos;
    this.especial=especial;
  }

  //Métodos get/set para todos los atributos
  public boolean get_especial() {
	  return this.especial;
  }
  public set_especial(boolean especial) {
	  this.especial = especial;
  }
  //Método toString que imprima la información del monitor
  public String toString() {
 	 String ret("Id: " + this.id + 
 			 	"\nNombre Completo: " + this.nombre + " " + this.apellidos + 
 			 	"\nGrupo especial: ");
 	 if(this.especial) {
 		 ret.concat("Si\n");
 	 }
 	 else {
 		 ret.concat("No\n");
 	 }
 	 return ret;
  }
  
}
