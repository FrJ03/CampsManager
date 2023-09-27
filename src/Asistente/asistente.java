
/**
 * Clase que representa a un asistente al campamento
 * @author Francisco José Mellado Ortiz
 */

 public class Asistente{
     private int id;
     private String nombre;
     private String apellidos;
     /*Contiene si el Asistente requiere una atención
      especial. Será verdadero si la requiere o falso
      en caso contrario.*/
     private boolean especial;

     public Asistente(){

     }
     public Asistente(int id, String nombre, String apellido, String especial){
        this.id = id;
        this.nombre = nombre;
     }
 }