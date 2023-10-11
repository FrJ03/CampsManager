package Programa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Programa {
	public static void main(String args[]) throws IOException {
		int opcion = 0;
		do {
			opcion = menuPrincipal();
			if(opcion == 1) {
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
