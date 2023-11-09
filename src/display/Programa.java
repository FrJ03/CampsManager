package display;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Properties;
import java.time.LocalDate;
import business.asistente.Asistente;
import business.gestores.*;
import business.actividad.*;
import business.campamento.*;
import business.monitor.*;
import business.inscripciones.*;

/**
 * Clase que representa el programa principal.
 */
public class Programa {
	/**
	 * Función principal del sistema.
	 * @param args
	 */
	public static void main(String args[]){
		int opcion = 0;
		GestorAsistentes asistentes = GestorAsistentes.getInstance();
		GestorCampamentos campamentos = GestorCampamentos.getInstance();
		GestorInscripciones inscripciones = GestorInscripciones.getInstance();
		
		//Buffer para leer datos por teclado
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		
		//Bucle del menú principal
		do {
			opcion = menuPrincipal();
						
			//Gestor de Asistentes
			if(opcion == 1) {	
				
				//Bucle del menu de asistentes
				do {
					opcion = menuGestorAsistentes();
					//Listar asistentes
					if(opcion == 1) {
						ArrayList<Asistente> listaAsistentes = asistentes.getListaAsistente();
						for(Asistente asistente : listaAsistentes) {
							System.out.println(asistente.toString());
						}
					}
					//Dar de alta un asistente
					else if(opcion == 2) {
						Asistente nuevo = getAsistente();						
						asistentes.darAltaAsistente(nuevo);
					}
					//Modificar asistente
					else if(opcion == 3) {
						int id = -1;
						String aux = null;		
						
						do {
							System.out.print("Inserte el id del asistente a modificar: ");
							aux = teclado.readLine();
						}while(!isNumber(aux));
						
						id = Integer.parseInt(aux);
						
						System.out.print("Inserte el nuevo nombre: ");
						String nombre = null;
						nombre = teclado.readLine();
						
						System.out.print("Inserte los nuevos apellidos: ");
						String apellidos = null;
						apellidos = teclado.readLine();
						
						
						aux = null;
						do {
							System.out.print("Inserte la fecha de nacimiento (yyyy/mm/dd): ");
							aux = teclado.readLine();
						}while(!isDateFormat(aux) || !dateValid(Integer.parseInt(aux.substring(0, 4)), Integer.parseInt(aux.substring(5, 7)), Integer.parseInt(aux.substring(8, 10))));							
						
						LocalDate fecha = LocalDate.of(Integer.parseInt(aux.substring(0, 4)), Integer.parseInt(aux.substring(5, 7)), Integer.parseInt(aux.substring(8, 10)));
						
						aux= null;
						boolean especial = false;
						do {
							System.out.print("¿Necesita atención especial? (S/N): ");
							aux = teclado.readLine();
							
							if(aux.equalsIgnoreCase("s"))
								especial = true;
							else if(aux.equalsIgnoreCase("n"))
								especial = false;
								
						}while(!aux.equalsIgnoreCase("s") && !aux.equalsIgnoreCase("n"));
						
						asistentes.modificarAsistente(id, nombre, apellidos, fecha, especial);
					}
					//Volver al menú principal
					else if(opcion == 4) 
						System.out.println("Volviendo al menú principal..............");
					//Error
					else
						System.out.println("Opción incorrecta.");
				}while(opcion != 4);
			}
			//Gestor de campamentos
			else if(opcion == 2) {
				do {
					opcion = menuGestorCampamentos();
					
					//Listar campamentos
					if(opcion == 1) {
						System.out.println(campamentos.getListaCampamentos());
					}
					//Listar actividades
					else if(opcion == 2) {
						System.out.println(campamentos.getListaActividades());
					}
					//Listar monitores
					else if(opcion == 3) {
						System.out.println(campamentos.getListaMonitores());
					}
					//Crear campamento
					else if(opcion == 4) {
						LocalDate fechaFin;
						LocalDate fechaInicio;
						String aux = null;
						do {
							do {
								System.out.print("Inserte la fecha de inicio (yyyy/mm/dd): ");
								aux = teclado.readLine();
							}while(!isDateFormat(aux) || !dateValid(Integer.parseInt(aux.substring(0, 4)), Integer.parseInt(aux.substring(5, 7)), Integer.parseInt(aux.substring(8, 10))));							
							
							fechaInicio = LocalDate.of(Integer.parseInt(aux.substring(0, 4)), Integer.parseInt(aux.substring(5, 7)), Integer.parseInt(aux.substring(8, 10)));
							
							aux = null;
							do {
								System.out.print("Inserte la fecha de finalización (yyyy/mm/dd): ");
								aux = teclado.readLine();
							}while(!isDateFormat(aux) || !dateValid(Integer.parseInt(aux.substring(0, 4)), Integer.parseInt(aux.substring(5, 7)), Integer.parseInt(aux.substring(8, 10))));;			
							
							fechaFin = LocalDate.of(Integer.parseInt(aux.substring(0, 4)), Integer.parseInt(aux.substring(5, 7)), Integer.parseInt(aux.substring(8, 10)));
						}while(fechaFin.compareTo(fechaInicio) <= 0);
						
						aux = null;
						Nivel nivel = null;
						do {
							System.out.print("Indique para quién va dirigido (I (Infantil) / J (Juvenil) / A (Adolescente)): ");
							aux = teclado.readLine();
							
							if(aux.equalsIgnoreCase("i"))
								nivel = Nivel.Infantil;
							else if(aux.equalsIgnoreCase("j"))
								nivel = Nivel.Juvenil;
							else if(aux.equalsIgnoreCase("a"))
								nivel = Nivel.Adolescente;
								
						}while(!aux.equalsIgnoreCase("i") && !aux.equalsIgnoreCase("j") && !aux.equalsIgnoreCase("a"));
						
						aux = null;
						int max = 0;
						do {
							do {
								System.out.print("Inserte el número máximo de participantes: ");
								aux = teclado.readLine();
							}while(!isNumber(aux));
							
							max = Integer.parseInt(aux);
						}while(max < 1);
						
						campamentos.crearCampamento(new Campamento(-1, fechaInicio, fechaFin, nivel, max));
					}
					//Crear actividad
					else if(opcion == 5) {
						
						System.out.print("Inserte el nombre de la actividad: ");
						String nombre = null;
						nombre = teclado.readLine();
						
						String aux = null;
						Nivel nivel = null;
						do {
							System.out.print("Indique para quién va dirigido (I (Infantil) / J (Juvenil) / A (Adolescente)): ");
							aux = teclado.readLine();
					
							if(aux.equalsIgnoreCase("i"))
								nivel = Nivel.Infantil;
							else if(aux.equalsIgnoreCase("j"))
								nivel = Nivel.Juvenil;
							else if(aux.equalsIgnoreCase("a"))
								nivel = Nivel.Adolescente;
								
						}while(!aux.equalsIgnoreCase("i") && !aux.equalsIgnoreCase("j") && !aux.equalsIgnoreCase("a"));
						
						aux = null;
						int maxP = 0;
						do {
							do {
								System.out.print("Inserte el número máximo de participantes: ");
								aux = teclado.readLine();
							}while(!isNumber(aux));
							
							maxP = Integer.parseInt(aux);
						}while(maxP < 1);
						
						aux = null;
						int maxM = 0;
						do {
							do {
								System.out.print("Inserte el número máximo de monitores: ");
								aux = teclado.readLine();
							}while(!isNumber(aux));
							
							maxM = Integer.parseInt(aux);
						}while(maxM < 1);
						
						aux = null;
						Turno turno = null;
						do {
							System.out.print("¿La actividad se realizará por la mañana (M) o por la tarde (T)?: ");
							aux = teclado.readLine();
							
							if(aux.equalsIgnoreCase("t"))
								turno = Turno.Tarde;
							else if(aux.equalsIgnoreCase("m"))
								turno = Turno.Mañana;
						}while (!aux.equalsIgnoreCase("t") && !aux.equalsIgnoreCase("m"));
						
						campamentos.crearActividad(new Actividad(-1, nombre, nivel, maxP, maxM, turno));
					}
					//Crear monitor
					else if(opcion == 6) {
						Monitor nuevo = new Monitor();
						System.out.print("Inserte el nombre del monitor: ");
						nuevo.setNombre(teclado.readLine());
						
						System.out.print("Inserte los apellidos del monitor: ");
						nuevo.setApellidos(teclado.readLine());
						
						String aux = null;
						do {
							System.out.print("¿Puede dirigir actividades con asistentes que necesiten atención especial? (S/N): ");
							aux = teclado.readLine();
							
							if(aux.equalsIgnoreCase("s"))
								nuevo.setEspecial(true);
							else if(aux.equalsIgnoreCase("n"))
								nuevo.setEspecial(false);
								
						}while(!aux.equalsIgnoreCase("s") && !aux.equalsIgnoreCase("n"));
						
						campamentos.crearMonitor(nuevo);
					}
					//Asociar un monitor a una actividad
					else if(opcion == 7) {
						
						int idMonitor = -1;
						String aux = null;
						
						do {
							System.out.println("Inserte el id del monitor que quiera asociar a la actividad: ");
							aux = teclado.readLine();
						}while(!isNumber(aux));
							
							idMonitor = Integer.parseInt(aux);
						
						ArrayList<Monitor> monitores = campamentos.getListaMonitores();
						Monitor monitor = null;
						for(Monitor m : monitores)
							if(m.getId() == idMonitor)
								monitor = m;
						
						if(monitor == null){
							System.out.println("No existe un monitor con ese id.");
						}
						else{
							aux = null;
							int idActividad = -1;
							do {
								System.out.println("Inserte el identificador de la actividad que quiera asociar al monitor: ");
								aux = teclado.readLine();
							}while(!isNumber(aux));
							
							idActividad = Integer.parseInt(aux);
							
							ArrayList<Actividad> acts=campamentos.getListaActividades();
							Actividad actividad = null;
							for (Actividad a : acts)
								if(a.getId() == idActividad)
									actividad = a;
							
							if(actividad==null){
								System.out.println("No existe una actividad con ese id.");
							}
							else{
								campamentos.asociarMonitorActividad(actividad,monitor);
							}
						}
					}
					//Asociar actividad a un campamento
					else if(opcion == 8) {
						int idCampamento = -1;
						String aux = null;
						do {
							System.out.println("Inserte el id del campamento que quiera asociar: ");
							aux = teclado.readLine();
						}while(!isNumber(aux));
						
						idCampamento = Integer.parseInt(aux);
						
						int idActividad = -1;
						aux = null;
						do {
							System.out.println("Inserte el id de la actividad que quiera asociar: ");
							aux = teclado.readLine();
						}while(!isNumber(aux));
						
						idActividad = Integer.parseInt(aux);
						
						ArrayList<Actividad> acts=campamentos.getListaActividades();
						Actividad actividad = null;
						for (Actividad a : acts)
							if(a.getId() == idActividad)
								actividad = a;
						
						if(actividad==null){
							System.out.println("No existe una actividad con ese id.");
						}
						else{
							campamentos.asociarActividadCampamento(idCampamento,actividad);
						}
					}
					//Asociar un monitor NO especial a un campamento
					else if(opcion == 9) {
						int idCampamento = -1;
						String aux = null;
						do {
							System.out.println("Inserte el id del campamento que quiera asociar: ");
							aux = teclado.readLine();
						}while(!isNumber(aux));
						
						idCampamento = Integer.parseInt(aux);
					
						aux = null;
						int idMonitor = -1;
						do {
							System.out.println("Inserte el id del monitor que quiera asociar: ");
							aux = teclado.readLine();
						}while(!isNumber(aux));
						
						idMonitor = Integer.parseInt(aux);
						
						ArrayList<Monitor> mons=campamentos.getListaMonitores();
						Monitor monitor = null;
						for(Monitor m : mons)
							if(m.getId() == idMonitor)
								monitor = m;
						
						if(monitor==null){
							System.out.println("No existe un monitor con ese id.");
						}
						else{
							boolean status = campamentos.asociarMonitorCampamento(idCampamento, monitor);
							if(status==false){
								System.out.println("Hubo un error.");
							}
						}
					}
					//Asignar un monitor especial a un campamento
					else if(opcion == 10) {
						String aux = null;
						int idCampamento = -1;
						do {
							System.out.println("Inserte el id del campamento que quiera asociar: ");
							aux = teclado.readLine();
						}while(!isNumber(aux));
						
						idCampamento = Integer.parseInt(aux);
						
						aux = null;
						int idMonitor = -1;
						do {
							System.out.println("Inserte el id del monitor que quiera asociar: ");
							aux = teclado.readLine();
						}while(!isNumber(aux));
						
						idMonitor = Integer.parseInt(aux);
						
						ArrayList<Monitor> mons=campamentos.getListaMonitores();
						Monitor monitor = null;
						for(Monitor m : mons)
							if(m.getId() == idMonitor)
								monitor = m;
						
						if(monitor==null){
							System.out.println("No existe un monitor con ese id.");
						}
						else{
							boolean status = campamentos.asociarMonitorEspCampamento(idCampamento, monitor);
							if(status==false){
								System.out.println("Hubo un error.");
							}
						}
					}
					//Volver al menú principal
					else if(opcion == 11) 
						System.out.println("Volviendo al menú principal..............");
					//Error
					else
						System.out.println("Opción incorrecta.");
				}while(opcion != 11);
			}
			//Gestor de inscripciones
			else if(opcion == 3) {
				do {
					opcion = menuGestorInscripciones();
					
					//Listar inscripciones parciales
					if(opcion == 1) {
						ArrayList<InscripcionParcial> lista = inscripciones.getListaInscripcionParcial();
						for (InscripcionParcial inscripcion : lista)
							System.out.println(inscripcion.toString());
					}
					//Listar inscripciones completas
					else if(opcion == 2) {
						ArrayList<InscripcionCompleta> lista = inscripciones.getListaInscripcionCompleta();
						for (InscripcionCompleta inscripcion : lista)
							System.out.println(inscripcion.toString());
					}
					//Crear una inscripción parcial
					else if(opcion == 3) {
						ArrayList<Asistente> asists = asistentes.getListaAsistente();
						InscripcionParcial nuevo = new InscripcionParcial();
						String aux = null;
						do {
							System.out.println("Introduce el id del participante: ");
							aux = teclado.readLine();
						}while(!isNumber(aux));
						
						nuevo.setIdParticipante(Integer.parseInt(aux));
						
						aux = null;
						do {
							System.out.println("Introduce el id del campamento: ");
							aux = teclado.readLine();
						}while(!isNumber(aux));		
						
						nuevo.setIdCampamento(Integer.parseInt(aux));
						
						ArrayList<Campamento> camps=campamentos.getListaCampamentos();
						Campamento campamento = null;
						for(Campamento c : camps)
							if(c.getId() == nuevo.getIdCampamento())
								campamento = c;
								
						if(campamento==null){
							System.out.println("El campamento no existe.");
						}
						else{
							LocalDate fecha = campamento.getInicioCampamento();
							nuevo.setPrecio(-1);
							
							boolean status = false;
							status = inscripciones.realizarRegistro(nuevo,fecha,asists);
							
							inscripciones.asignarPrecio(nuevo.getIdCampamento(), nuevo.getIdParticipante(), camps);
							if(status==true){
								System.out.println("Registro realizado correctamente. El asistente necesitará una atención especial.");
							}
							else{
								System.out.println("Registro realizado correctamente.");
							}
						}
					}
					//Realiza una inscripción completa
					else if(opcion == 4) {
						
						ArrayList<Asistente> asists=asistentes.getListaAsistente();
						InscripcionCompleta nuevo=new InscripcionCompleta();
						String aux = null;
						do {
							System.out.println("Introduce el id del participante: ");
							aux = teclado.readLine();
						}while(!isNumber(aux));		
						nuevo.setIdParticipante(Integer.parseInt(aux));
						
						aux = null;
						do {
							System.out.println("Introduce el id del campamento: ");
							aux = teclado.readLine();
						}while(!isNumber(aux));	
						nuevo.setIdCampamento(Integer.parseInt(aux));
						
						ArrayList<Campamento> camps=campamentos.getListaCampamentos();
						Campamento campamento = null;
						for(Campamento c : camps)
							if(c.getId() == nuevo.getIdCampamento())
								campamento = c;
								
						if(campamento==null){
							System.out.println("El campamento no existe.");
						}
						else{
							LocalDate fecha=campamento.getInicioCampamento();
							nuevo.setPrecio(-1);
							
							boolean status = false;
							status = inscripciones.realizarRegistro(nuevo,fecha,asists);
							
							inscripciones.asignarPrecio(nuevo.getIdCampamento(), nuevo.getIdParticipante(), camps);
							if(status==true){
								System.out.println("Registro realizado correctamente. El asistente necesitará una atención especial.");
							}
							else{
								System.out.println("Registro realizado correctamente.");
							}
						}
					}
					//Listar campamentos disponibles.
					else if(opcion == 5) {
						System.out.println(inscripciones.obtenerCampamentosDisponibles();
						
					}
					//Volver al menú principal
					else if(opcion == 6) 
						System.out.println("Volviendo al menú principal..............");
					//Error
					else
						System.out.println("Opción incorrecta.");
				}while(opcion != 6);
			}
			//Salir del sistema
			else if(opcion == 4) {
				System.out.println("Saliendo del sistema.................");
			}
			//Error
			else {
				System.out.println("Opción incorrecta.");
			}
		}while(opcion != 4);
	}
	
	/**
	 * Función que muestra el menú principal del sistema y devuelve la opción elegida por el usuario.
	 * @return int
	 * @throws IOException
	 */
	private static int menuPrincipal(){
		String aux = null;
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		do {
			System.out.println("----------------------------------");
			System.out.println("1. Gestión de Asistentes.");
			System.out.println("2. Gestión de Campamentos.");
			System.out.println("3. Gestión de Inscripciones.");
			System.out.println("4. Salir.");
			System.out.println("----------------------------------");
			System.out.print("Seleccione una opción: ");
			try {
				aux = teclado.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}while(!isNumber(aux));
        int opcion = Integer.parseInt(aux);
        System.out.println("----------------------------------");
        return opcion;
	}
	
	/**
	 * Función que muestra el menú de gestíon de asistentes y devuelve la opción elegida por el usuario.
	 * @return
	 */
	private static int menuGestorAsistentes(){
		String aux = null;
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		do {
			System.out.println("----------------------------------");
			System.out.println("1. Lista de asistentes.");
			System.out.println("2. Dar de alta asistente.");
			System.out.println("3. Modificar asistente.");
			System.out.println("4. Atrás.");
			System.out.println("----------------------------------");
			System.out.print("Seleccione una opción: ");
			try {
				aux = teclado.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}while(!isNumber(aux));
        int opcion = Integer.parseInt(aux);
        System.out.println("----------------------------------");
        return opcion;
	}
	
	/**
	 * Función que muestra el menú de gestíon de campamentos y devuelve la opción elegida por el usuario.
	 * @return
	 */
	private static int menuGestorCampamentos(){
		String aux = null;
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		do {
			System.out.println("----------------------------------");
			System.out.println("1. Lista de campamentos.");
			System.out.println("2. Lista de actividades.");
			System.out.println("3. Lista de monitores.");
			System.out.println("4. Crear campamento.");
			System.out.println("5. Crear actividad.");
			System.out.println("6. Crear monitor.");
			System.out.println("7. Asociar monitor a una actividad.");
			System.out.println("8. Asociar actividad a un campamento.");
			System.out.println("9. Asociar monitor a un campamento.");
			System.out.println("10. Asociar monitor especial a un campamento.");
			System.out.println("11. Atrás.");
			System.out.println("----------------------------------");
			System.out.print("Seleccione una opción: ");
			try {
				aux = teclado.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}while(!isNumber(aux));
        int opcion = Integer.parseInt(aux);
        System.out.println("----------------------------------");
        return opcion;
	}
	
	/**
	 * Función que muestra el menú de gestíon de inscripciones y devuelve la opción elegida por el usuario.
	 * @return
	 */
	private static int menuGestorInscripciones(){
		String aux = null;
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		do {
			System.out.println("----------------------------------");
			System.out.println("1. Lista de inscripciones parciales.");
			System.out.println("2. Lista de inscripciones completas.");
			System.out.println("3. Realizar registro parcial.");
			System.out.println("4. Realizar registro completo.");
			System.out.println("5. Listar campamentos disponibles.");
			System.out.println("6. Atrás.");
			System.out.println("----------------------------------");
			System.out.print("Seleccione una opción: ");
			try {
				aux = teclado.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}while(!isNumber(aux));
        int opcion = Integer.parseInt(aux);
        System.out.println("----------------------------------");
        return opcion;
	}
	
	/**
	 * Función que comprueba si una fecha es válida.
	 * @param year Año de la fecha
	 * @param month Mes de la fecha
	 * @param day Día de la fecha
	 * @return
	 */
	private static boolean dateValid(int year, int month, int day) {
		if((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) && day <= 31 && day > 0)
			return true;
		else if((month == 4 || month == 6 || month == 9 || month == 11) && day <= 30 && day > 0)
			return true;
		else if(month == 2) {
			if((year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) && day <= 29 && day > 0)
				return true;
			else if(!(year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) && day <= 28 && day > 0)
				return true;
			else 
				return false;
		}
		else 
			return false;
	}
		
	/**
	 * Función que comprueba si una cadena es un número.
	 * @param s Cadena a comprobar
	 * @return Boolean
	 */
	private static boolean isNumber(String s) {
		return s != null && s.matches("[0-9]+");
	}
	/**
	 * Función que comprueba si una cadena de caracteres tiene el formato de fecha 'yyyy/mm/dd'.
	 * @param date Cadena a comprobar
	 * @return Booelan
	 */
	private static boolean isDateFormat(String date) {
		return date.length() == 10 && isNumber(date.substring(0, 4)) && date.charAt(4) == '/' && isNumber(date.substring(5, 7)) && date.charAt(7) == '/' && isNumber(date.substring(8, 10));
	}
	/**
	 * Función que pide por pantalla los datos de un asistente
	 * @return Asistente
	 */
	private static Asistente getAsistente() {
		String aux = null;
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		Asistente asistente = new Asistente();
		
		System.out.print("Assistant firstname: ");
		try {
			asistente.setNombre(teclado.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.print("Assistant lastname: ");
		try {
			asistente.setApellidos(teclado.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		do {
			System.out.print("Birth date (yyyy/mm/dd): ");
			try {
				aux = teclado.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}							
		}while(!isDateFormat(aux) || !dateValid(Integer.parseInt(aux.substring(0, 4)), Integer.parseInt(aux.substring(5, 7)), Integer.parseInt(aux.substring(8, 10))));							
		
		LocalDate fecha = LocalDate.of(Integer.parseInt(aux.substring(0, 4)), Integer.parseInt(aux.substring(5, 7)), Integer.parseInt(aux.substring(8, 10)));
		asistente.setFechaNacimiento(fecha);
		
		aux = null;
		do {
			System.out.print("¿Special Attention? (Y/N): ");
			try {
				aux = teclado.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
				
			if(aux.equalsIgnoreCase("y"))
				asistente.setEspecial(true);
			else if(aux.equalsIgnoreCase("n"))
				asistente.setEspecial(false);
				
		}while(!aux.equalsIgnoreCase("y") && !aux.equalsIgnoreCase("n"));
		return asistente;
	}
}
