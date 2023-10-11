package Programa;

import GestorAsistentes.GestorAsistentes;
import GestorCampamentos.GestorCampamentos;
import GestorInscripciones.GestorInscripciones;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import Asistente.Asistente;
import java.util.ArrayList;
import java.util.Date;

public class Programa {
	public static void main(String args[]) throws IOException {
		int opcion = 0;
		GestorAsistentes asistentes = GestorAsistentes.getInstance();
		GestorCampamentos campamentos = GestorCampamentos.getInstance();
		GestorInscripciones inscripciones = GestorInscripciones.getInstance();
		do {
			opcion = menuPrincipal();
			if(opcion == 1) {				
				do {
					opcion = menuGestorAsistentes();
					if(opcion == 1) {
						ArrayList<Asistente> listaAsistentes = asistentes.getListaAsistente_();
						for(Asistente asistente : listaAsistentes) {
							System.out.println(asistente.toString());
						}
					}
					else if(opcion == 2) {
						Asistente nuevo = new Asistente();
						BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
						//Llamar al módulo de datos para obtener el último id
						System.out.print("Inserte el nombre del asistente: ");
						nuevo.set_nombre(teclado.readLine());
						System.out.print("Inserte los apellidos del asistente: ");
						nuevo.set_apellidos(teclado.readLine());
						System.out.print("Inserte la fecha de nacimiento (yyyy/mm/dd): ");
						String aux = teclado.readLine();
						Date fecha = new Date(Integer.parseInt(aux.substring(0, 3)), Integer.parseInt(aux.substring(5, 6)), Integer.parseInt(aux.substring(8, 9)));
						nuevo.setFechaNacimiento(fecha);
						char letra = 'a';
						do {
							System.out.print("¿Necesita atención especial? (S/N): ");
							letra = (char)teclado.read();
							if(letra == 'S' || letra == 's')
								nuevo.setEspecial(true);
							else if(letra == 'N' || letra == 'n')
								nuevo.setEspecial(false);
								
						}while(letra != 'S' && letra != 'N' && letra != 's' && letra != 'n');
						asistentes.DarAltaAsistente(nuevo);
					}
					else if(opcion == 3) {
						BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
						System.out.print("Inserte el id del asistente a modificar: ");
						int idAntiguo = Integer.parseInt(teclado.readLine());
						System.out.print("Inserte el nuevo id: ");
						int idNuevo = Integer.parseInt(teclado.readLine());
						System.out.print("Inserte el nuevo nombre: ");
						String nombre = teclado.readLine();
						System.out.print("Inserte los nuevos apellidos: ");
						String apellidos = teclado.readLine();
						System.out.print("Inserte la nueva fecha de nacimiento (yyyy/mm/dd): ");
						String aux = teclado.readLine();
						Date fecha = new Date(Integer.parseInt(aux.substring(0, 3)), Integer.parseInt(aux.substring(5, 6)), Integer.parseInt(aux.substring(8, 9)));
						char letra = 'a';
						boolean especial = false;
						do {
							System.out.print("¿Necesita atención especial? (S/N): ");
							letra = (char)teclado.read();
							if(letra == 'S' || letra == 's')
								especial = true;
							else if(letra == 'N' || letra == 'n')
								especial = false;
								
						}while(letra != 'S' && letra != 'N' && letra != 's' && letra != 'n');
						asistentes.ModificarAsistente(idAntiguo, idNuevo, nombre, apellidos, fecha, especial);
					}
					else if(opcion == 4) {
						System.out.println(asistentes.ListaAsistencia());						
					}
					else if(opcion == 5) 
						System.out.println("Volviendo al menú principal..............");
					else
						System.out.println("Opción incorrecta.");
				}while(opcion != 5);
			}
			else if(opcion == 2) {
				do {
					opcion = menuGestorAsistentes();
					if(opcion == 1) {
					
					}
					else if(opcion == 2) {
						
					}
					else if(opcion == 3) {
						
					}
					else if(opcion == 4) {
						
					}
					else if(opcion == 5) {
						
					}
					else if(opcion == 6) {
						
					}
					else if(opcion == 7) {
						
					}
					else if(opcion == 8) {
						
					}
					else if(opcion == 9) {
						
					}
					else if(opcion == 10) {
						
					}
					else if(opcion == 11) 
						System.out.println("Volviendo al menú principal..............");
					else
						System.out.println("Opción incorrecta.");
				}while(opcion != 11);
			}
			else if(opcion == 3) {
				do {
					opcion = menuGestorAsistentes();
					if(opcion == 1) {
					
					}
					else if(opcion == 2) {
						
					}
					else if(opcion == 3) {
						
					}
					else if(opcion == 4) {
						
					}
					else if(opcion == 5) {
						
					}
					else if(opcion == 6) {
						
					}
					else if(opcion == 7) 
						System.out.println("Volviendo al menú principal..............");
					else
						System.out.println("Opción incorrecta.");
				}while(opcion != 7);
			}
			else if(opcion == 4) {
				System.out.println("Saliendo del sistema.................");
			}
			else {
				System.out.println("Opción incorrecta.");
			}
		}while(opcion != 4);
	}
	private static int menuPrincipal() throws IOException {
		System.out.println("----------------------------------");
		System.out.println("1. Gestión de Asistentes.");
		System.out.println("2. Gestión de Campamentos.");
		System.out.println("3. Gestión de Inscripciones.");
		System.out.println("4. Salir.");
		System.out.println("----------------------------------");
		System.out.print("Seleccione una opción: ");
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        int opcion = Integer.parseInt(teclado.readLine());
        System.out.println("----------------------------------");
        return opcion;
	}
	
	private static int menuGestorAsistentes() throws IOException {
		System.out.println("----------------------------------");
		System.out.println("1. Lista de asistentes.");
		System.out.println("2. Dar de alta asistente.");
		System.out.println("3. Modificar asistente.");
		System.out.println("4. Lista de asistencia.");
		System.out.println("5. Atrás.");
		System.out.println("----------------------------------");
		System.out.print("Seleccione una opción: ");
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        int opcion = Integer.parseInt(teclado.readLine());
        System.out.println("----------------------------------");
        return opcion;
	}
	private static int menuGestorCampamentos() throws IOException {
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
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        int opcion = Integer.parseInt(teclado.readLine());
        System.out.println("----------------------------------");
        return opcion;
	}
	private static int menuGestorInscripciones() throws IOException {
		System.out.println("----------------------------------");
		System.out.println("1. Lista de inscripciones parciales.");
		System.out.println("2. Lista de inscripciones completas.");
		System.out.println("3. Realizar registro parcial.");
		System.out.println("4. Realizar registro completo.");
		System.out.println("5. Asignar precio.");
		System.out.println("6. Listar cursos disponibles.");
		System.out.println("7. Atrás.");
		System.out.println("----------------------------------");
		System.out.print("Seleccione una opción: ");
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        int opcion = Integer.parseInt(teclado.readLine());
        System.out.println("----------------------------------");
        return opcion;
	}
}
