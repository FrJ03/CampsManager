package display;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
						
						asistente.setFechaNacimiento(getFecha("Birth date (yyyy/mm/dd): "));
						
						asistente.setEspecial(getSpecialNeeds());
						
						if(asistentes.darAltaAsistente(asistente))
							System.out.println("Succesfull");
						else
							System.out.println("Error adding a new assistant");
					}
					//Modificar asistente
					else if(opcion == 3) {
						int id = -1;
						String aux = null;		
						
						do {
							System.out.print("Assistant ID: ");
							try {
								aux = teclado.readLine();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}while(!isNumber(aux));
						
						id = Integer.parseInt(aux);
						
						System.out.print("New Assistant Firstname: ");
						String nombre = null;
						try {
							nombre = teclado.readLine();
						} catch (IOException e) {
							e.printStackTrace();
						}
						
						System.out.print("New Assistant Lastname: ");
						String apellidos = null;
						try {
							apellidos = teclado.readLine();
						} catch (IOException e) {
							e.printStackTrace();
						}
						
						LocalDate fecha = getFecha("Birth date (yyyy/mm/dd): ");
						
						Boolean especial = getSpecialNeeds();
						
						if(asistentes.modificarAsistente(id, nombre, apellidos, fecha, especial))
							System.out.println("Succesfull.");
						else
							System.out.println("Error modifying the assistant.");
					}
					//Volver al menú principal
					else if(opcion == 4) 
						System.out.println("Returning to the Main Menu..............");
					//Error
					else
						System.out.println("Wrong option.");
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
							fechaInicio = getFecha("Start Date (yyyy/mm/dd): ");
							
							fechaFin = getFecha("End Date (yyyy/mm/dd): ");
						}while(fechaFin.compareTo(fechaInicio) <= 0);
						
						Nivel nivel = getNivel();
						
						int max = 0;
						do {
							do {
								System.out.print("Max Number of Assistant: ");
								try {
									aux = teclado.readLine();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}while(!isNumber(aux));
							
							max = Integer.parseInt(aux);
						}while(max < 1);
						
						if(campamentos.crearCampamento(new Campamento(-1, fechaInicio, fechaFin, nivel, max)))
							System.out.println("Succesfull.");
						else
							System.out.println("Error creating the camp.");
					}
					//Crear actividad
					else if(opcion == 5) {
						
						System.out.print("Activity Name: ");
						String nombre = null;
						try {
							nombre = teclado.readLine();
						} catch (IOException e) {
							e.printStackTrace();
						}
						
						String aux = null;
						Nivel nivel = getNivel();
						aux = null;
						int maxP = 0;
						do {
							do {
								System.out.print("Max Number of Participants: ");
								try {
									aux = teclado.readLine();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}while(!isNumber(aux));
							
							maxP = Integer.parseInt(aux);
						}while(maxP < 1);
						
						aux = null;
						int maxM = 0;
						do {
							do {
								System.out.print("Max Number of Monitor: ");
								try {
									aux = teclado.readLine();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}while(!isNumber(aux));
							
							maxM = Integer.parseInt(aux);
						}while(maxM < 1);
						
						aux = null;
						Turno turno = getTurno();
						
						if(campamentos.crearActividad(new Actividad(-1, nombre, nivel, maxP, maxM, turno)))
							System.out.println("Succesfull.");
						else
							System.out.println("Error creating the activity.");
					}
					//Crear monitor
					else if(opcion == 6) {
						Monitor nuevo = new Monitor();
						System.out.print("Monitor Firstname: ");
						try {
							nuevo.setNombre(teclado.readLine());
						} catch (IOException e) {
							e.printStackTrace();
						}
						
						System.out.print("Monitor Lastname: ");
						try {
							nuevo.setApellidos(teclado.readLine());
						} catch (IOException e) {
							e.printStackTrace();
						}
						
						nuevo.setEspecial(getSpecial());
						
						if(campamentos.crearMonitor(nuevo))
							System.out.println("Succesfull.");
						else
							System.out.println("Error creating the monitor.");
					}
					//Asociar un monitor a una actividad
					else if(opcion == 7) {
						
						int idMonitor = -1;
						String aux = null;
						
						do {
							System.out.println("Monitor ID: ");
							try {
								aux = teclado.readLine();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}while(!isNumber(aux));
							
						idMonitor = Integer.parseInt(aux);
						
						aux = null;
						int idActividad = -1;
						do {
							System.out.println("Activity ID: ");
							try {
								aux = teclado.readLine();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}while(!isNumber(aux));
						
						idActividad = Integer.parseInt(aux);
						if(campamentos.asociarMonitorActividad(idActividad, idMonitor))
							System.out.println("Succesfull.");
						else
							System.out.println("Error adding the monitor.");
					}
					//Asociar actividad a un campamento
					else if(opcion == 8) {
						int idCampamento = -1;
						String aux = null;
						do {
							System.out.println("Camp ID: ");
							try {
								aux = teclado.readLine();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}while(!isNumber(aux));
						
						idCampamento = Integer.parseInt(aux);
						
						int idActividad = -1;
						aux = null;
						do {
							System.out.println("Activity ID: ");
							try {
								aux = teclado.readLine();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}while(!isNumber(aux));
						
						idActividad = Integer.parseInt(aux);
					
						if(campamentos.asociarActividadCampamento(idCampamento,idActividad))
							System.out.println("Succesfull.");
						else
							System.out.println("Error adding the activity.");
					}
					//Asociar un monitor responsable a un campamento
					else if(opcion == 9) {
						int idCampamento = -1;
						String aux = null;
						do {
							System.out.println("Camp ID: ");
							try {
								aux = teclado.readLine();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}while(!isNumber(aux));
						
						idCampamento = Integer.parseInt(aux);
					
						aux = null;
						int idMonitor = -1;
						do {
							System.out.println("Monitor in charge ID: ");
							try {
								aux = teclado.readLine();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}while(!isNumber(aux));
						
						idMonitor = Integer.parseInt(aux);
						
						if(campamentos.asociarMonitorResponsable(idCampamento, idMonitor))
							System.out.println("Successful.");
						else
							System.out.println("Error adding the monitor.");
					}
					//Asignar un monitor especial a un campamento
					else if(opcion == 10) {
						String aux = null;
						int idCampamento = -1;
						do {
							System.out.println("CampID: ");
							try {
								aux = teclado.readLine();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}while(!isNumber(aux));
						
						idCampamento = Integer.parseInt(aux);
						
						aux = null;
						int idMonitor = -1;
						do {
							System.out.println("Monitor in chargeID: ");
							try {
								aux = teclado.readLine();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}while(!isNumber(aux));
						
						idMonitor = Integer.parseInt(aux);
						
						if(campamentos.asociarMonitorEspecial(idCampamento, idMonitor))
							System.out.println("Successful.");
						else
							System.out.println("Error adding the monitor.");
					}
					//Volver al menú principal
					else if(opcion == 11) 
						System.out.println("Returning to the Main Menu..............");
					//Error
					else
						System.out.println("Wrong Option.");
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
						String aux = null;
						int idAssistant, idCamp;
						do {
							System.out.println("Assistant ID: ");
							try {
								aux = teclado.readLine();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}while(!isNumber(aux));
						
						idAssistant = Integer.parseInt(aux);
						
						aux = null;
						do {
							System.out.println("Camp ID: ");
							try {
								aux = teclado.readLine();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}while(!isNumber(aux));		
						
						idCamp = Integer.parseInt(aux);
						
						if(inscripciones.realizarRegistroParcial(idCamp, idAssistant)){
							System.out.println("Successfull.");
						}
						else{
							System.out.println("Error while registering.");
						}
						
					}
					//Realiza una inscripción completa
					else if(opcion == 4) {
						String aux = null;
						int idAssistant, idCamp;
						do {
							System.out.println("Assistant ID: ");
							try {
								aux = teclado.readLine();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}while(!isNumber(aux));
						
						idAssistant = Integer.parseInt(aux);
						
						aux = null;
						do {
							System.out.println("Camp ID: ");
							try {
								aux = teclado.readLine();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}while(!isNumber(aux));		
						
						idCamp = Integer.parseInt(aux);
						
						if(inscripciones.realizarRegistroCompleto(idCamp, idAssistant)){
							System.out.println("Successfull.");
						}
						else{
							System.out.println("Error while registering.");
						}
					}
					//Listar campamentos disponibles.
					else if(opcion == 5) {
						System.out.println(inscripciones.obtenerCampamentosDisponibles());
						
					}
					//Volver al menú principal
					else if(opcion == 6) 
						System.out.println("Returning to the Main Menu..............");
					//Error
					else
						System.out.println("Wrong Option.");
				}while(opcion != 6);
			}
			//Salir del sistema
			else if(opcion == 4) {
				System.out.println("Closing System.................");
			}
			//Error
			else {
				System.out.println("Wrong Option.");
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
			System.out.println("1. Assistant Management.");
			System.out.println("2. Camps Management.");
			System.out.println("3. Registrations Management.");
			System.out.println("4. Exit.");
			System.out.println("----------------------------------");
			System.out.print("Choose an Option: ");
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
			System.out.println("1. List Assistants.");
			System.out.println("2. New Assistant.");
			System.out.println("3. Modify Assistant.");
			System.out.println("4. Back.");
			System.out.println("----------------------------------");
			System.out.print("Choose an Option: ");
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
			System.out.println("1. List Camps.");
			System.out.println("2. List Activities.");
			System.out.println("3. List Monitors.");
			System.out.println("4. New Camp.");
			System.out.println("5. New Activity.");
			System.out.println("6. New Monitor.");
			System.out.println("7. Add a Monitor to an Activity.");
			System.out.println("8. Add a Activity to a Camp.");
			System.out.println("9. Add a Monitor in Charge to a Camp.");
			System.out.println("10. Add a Special Monitor to a Camp.");
			System.out.println("11. Back.");
			System.out.println("----------------------------------");
			System.out.print("Choose an Option: ");
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
			System.out.println("1. List Partial Registrations.");
			System.out.println("2. List Complete Registrations.");
			System.out.println("3. New Partial Registration.");
			System.out.println("4. New Complete Registration.");
			System.out.println("5. List Availabel Camps.");
			System.out.println("6. Back.");
			System.out.println("----------------------------------");
			System.out.print("Choose an Option: ");
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
	 * Función que pide por pantalla una fecha y comprueba su validez
	 * @param Message Mensaje que imprime por pantalla
	 * @return LocalDate
	 */
	private static LocalDate getFecha(String message) {
		String aux = null;
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		do {
			System.out.print(message);
			try {
				aux = teclado.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}							
		}while(!isDateFormat(aux) || !dateValid(Integer.parseInt(aux.substring(0, 4)), Integer.parseInt(aux.substring(5, 7)), Integer.parseInt(aux.substring(8, 10))));							
		
		LocalDate fecha = LocalDate.of(Integer.parseInt(aux.substring(0, 4)), Integer.parseInt(aux.substring(5, 7)), Integer.parseInt(aux.substring(8, 10)));
		
		return fecha;
	}
	/**
	 * Consulta por pantalla si un asistente necesita atención especial y comprueba lo introducido
	 * @return Boolean true si necesita atención especial, false en caso contrario
	 */
	private static Boolean getSpecialNeeds() {
		String aux = null;
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		boolean especial = false;
		do {
			System.out.print("¿Special Attention? (Y/N): ");
			try {
				aux = teclado.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(aux.equalsIgnoreCase("y"))
				especial = true;
			else if(aux.equalsIgnoreCase("n"))
				especial = false;
				
		}while(!aux.equalsIgnoreCase("y") && !aux.equalsIgnoreCase("n"));
		return especial;
	}
	/**
	 * Consulta por pantalla si un monitor puede encargarse de grupos con necesidades especiales y se realiza la comprobación de errores
	 * @return Boolean true si puede encargarse de grupos con necesidades especiales, false en caso contrario
	 */
	private static Boolean getSpecial() {
		String aux = null;
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		boolean especial = false;
		do {
			System.out.print("¿Special Attention? (Y/N): ");
			try {
				aux = teclado.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(aux.equalsIgnoreCase("y"))
				especial = true;
			else if(aux.equalsIgnoreCase("n"))
				especial = false;
				
		}while(!aux.equalsIgnoreCase("y") && !aux.equalsIgnoreCase("n"));
		return especial;
	}
	/**
	 * Pide por pantalla el nivel de un campamento o una actividad y realiza la comprobación de errores
	 * @return Nivel
	 */
	private static Nivel getNivel() {
		String aux = null;
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		Nivel nivel = null;
		do {
			System.out.print("Choose the Target Public (I (Infantile) / J (Juvenile) / A (Adolescent)): ");
			try {
				aux = teclado.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
	
			if(aux.equalsIgnoreCase("i"))
				nivel = Nivel.Infantil;
			else if(aux.equalsIgnoreCase("j"))
				nivel = Nivel.Juvenil;
			else if(aux.equalsIgnoreCase("a"))
				nivel = Nivel.Adolescente;
				
		}while(!aux.equalsIgnoreCase("i") && !aux.equalsIgnoreCase("j") && !aux.equalsIgnoreCase("a"));
		return nivel;
	}
	/**
	 * Pide por pantalla el turno de un campamento o una actividad y realiza la comprobación de errores
	 * @return Turno
	 */
	private static Turno getTurno() {
		String aux = null;
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		Turno turno = null;
		do {
			System.out.print("Activity Schedule (Morning, M/Afternoon, A): ");
			try {
				aux = teclado.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(aux.equalsIgnoreCase("a"))
				turno = Turno.Tarde;
			else if(aux.equalsIgnoreCase("m"))
				turno = Turno.Mañana;
		}while (!aux.equalsIgnoreCase("a") && !aux.equalsIgnoreCase("m"));
		
		return turno;
	}
}
