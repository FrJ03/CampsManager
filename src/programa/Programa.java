package programa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.time.LocalDate;
import actividad.*;
import asistente.Asistente;
import campamento.Campamento;
import gestorAsistentes.GestorAsistentes;
import gestorCampamentos.GestorCampamentos;
import gestorInscripciones.GestorInscripciones;
import inscripcion.InscripcionCompleta;
import inscripcion.InscripcionParcial;
import monitor.Monitor;

public class Programa {
	public static void main(String args[]) throws Exception {
		int opcion = 0;
		GestorAsistentes asistentes = GestorAsistentes.getInstance();
		GestorCampamentos campamentos = GestorCampamentos.getInstance();
		GestorInscripciones inscripciones = GestorInscripciones.getInstance();
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
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
						//Llamar al módulo de datos para obtener el último id
						System.out.print("Inserte el nombre del asistente: ");
						nuevo.setNombre(teclado.readLine());
						System.out.print("Inserte los apellidos del asistente: ");
						nuevo.setApellidos(teclado.readLine());
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
					opcion = menuGestorCampamentos();
					if(opcion == 1) {
						System.out.println(campamentos.getListaCampamentos_());
					}
					else if(opcion == 2) {
						System.out.println(campamentos.getListaActividades_());
					}
					else if(opcion == 3) {
						System.out.println(campamentos.getListaMonitores_());
					}
					else if(opcion == 4) {
						//Seleccionar id
						int id = -1;
						int day = 1;
						int month = 1;
						int year = 1;
						LocalDate fechaFin;
						LocalDate fechaInicio;
						do {
							do {
								System.out.print("Inserte el día de inicio: ");
								day = Integer.parseInt(teclado.readLine());
								System.out.print("Inserte el mes de inicio: ");
								month = Integer.parseInt(teclado.readLine());
								System.out.print("Inserte el año de inicio: ");
								year = Integer.parseInt(teclado.readLine());
							}while(!dateValid(year, month, day));							
							fechaInicio = LocalDate.of(year, month, day);
							do {
								System.out.print("Inserte el día de finalización: ");
								day = Integer.parseInt(teclado.readLine());
								System.out.print("Inserte el mes de finalización: ");
								month = Integer.parseInt(teclado.readLine());
								System.out.print("Inserte el año de finalización: ");
								year = Integer.parseInt(teclado.readLine());
							}while(!dateValid(year, month, day));							
							fechaFin = LocalDate.of(year, month, day);
						}while(fechaFin.compareTo(fechaInicio) <= 0);
						char n = 'b';
						Nivel nivel = null;
						do {
							System.out.print("Indique para quién va dirigido (I (Infantil) / J (Juvenil) / A (Adolescente)): ");
							n = (char)teclado.read();
							if(n == 'I' || n == 'i')
								nivel = Nivel.Infantil;
							else if(n == 'J' || n == 'j')
								nivel = Nivel.Juvenil;
							else if(n == 'A' || n == 'a')
								nivel = Nivel.Adolescente;
								
						}while(n != 'I' && n != 'i' && n != 'J' && n != 'j' && n != 'A' && n != 'a');
						int max = 0;
						do {
							System.out.print("Inserte el número máximo de participantes: ");
							max = Integer.parseInt(teclado.readLine());
						}while(max < 1);
						campamentos.crearCampamento(new Campamento(id, fechaInicio, fechaFin, nivel, max));
					}
					else if(opcion == 5) {
						System.out.print("Inserte el nombre de la actividad: ");
						String nombre = teclado.readLine();
						char n = 'b';
						Nivel nivel = null;
						do {
							System.out.print("Indique para quién va dirigido (I (Infantil) / J (Juvenil) / A (Adolescente)): ");
							n = (char)teclado.read();
							if(n == 'I' || n == 'i')
								nivel = Nivel.Infantil;
							else if(n == 'J' || n == 'j')
								nivel = Nivel.Juvenil;
							else if(n == 'A' || n == 'a')
								nivel = Nivel.Adolescente;
								
						}while(n != 'I' && n != 'i' && n != 'J' && n != 'j' && n != 'A' && n != 'a');
						int maxP = 0;
						do {
							System.out.print("Inserte el número máximo de participantes: ");
							maxP = Integer.parseInt(teclado.readLine());
						}while(maxP < 1);
						int maxM = 0;
						do {
							System.out.print("Inserte el número máximo de monitores: ");
							maxM = Integer.parseInt(teclado.readLine());
						}while(maxM < 1);
						char t = 'a';
						Turno turno = null;
						do {
							System.out.print("¿La actividad se realizará por la mañana o por la tarde?: ");
							t = (char) teclado.read();
							if(t == 'T' || t == 't')
								turno = Turno.Tarde;
							else if(t == 'M' || t == 'm')
								turno = Turno.Mañana;
						}while (t != 'M' && t != 'm' && t != 'T' && t != 't');
						Actividad actividad = new Actividad(nombre, nivel, maxP, maxM, turno);
						campamentos.crearActividad(actividad);
					}
					else if(opcion == 6) {
						Monitor nuevo = new Monitor();
						//Llamar al módulo de datos para obtener el último id
						System.out.print("Inserte el nombre del monitor: ");
						nuevo.setNombre(teclado.readLine());
						System.out.print("Inserte los apellidos del monitor: ");
						nuevo.setApellidos(teclado.readLine());
						char letra = 'a';
						do {
							System.out.print("¿Necesita atención especial? (S/N): ");
							letra = (char)teclado.read();
							if(letra == 'S' || letra == 's')
								nuevo.setEspecial(true);
							else if(letra == 'N' || letra == 'n')
								nuevo.setEspecial(false);
								
						}while(letra != 'S' && letra != 'N' && letra != 's' && letra != 'n');
						campamentos.crearMonitor(nuevo);
					}
					else if(opcion == 7) {
						/** Encuentra el monitor, encuentra la actividad, los asocia **/
						System.out.println("Inserte el id del monitor que quiera asociar a la actividad: ");
						int idm=Integer.parseInt(teclado.readLine());
						ArrayList<Monitor> mons=campamentos.getListaMonitores_();
						Monitor monitor = null;
						for(Monitor aux : mons)
							if(aux.getId() == idm)
								monitor = aux;
						if(monitor == null){
							System.out.println("No existe un monitor con ese id.");
						}
						else{
							System.out.println("Inserte el nombre de la actividad que quiera asociar al monitor: ");
							String ida=teclado.readLine();
							ArrayList<Actividad> acts=campamentos.getListaActividades_();
							Actividad actividad = null;
							for (Actividad aux : acts)
								if(aux.getName_() == ida)
									actividad = aux;
							if(actividad==null){
								System.out.println("No existe una actividad con ese id.");
							}
							else{
								campamentos.asociarMonitorActividad(actividad,monitor);
							}
						}
					}
					else if(opcion == 8) {
						/* Coge el id campamento, encuentra la actividad, los asocia */
						System.out.println("Inserte el id del campamento que quiera asociar: ");
						int idc=Integer.parseInt(teclado.readLine());
						System.out.println("Inserte el id de la actividad que quiera asociar: ");
						String ida=teclado.readLine();
						ArrayList<Actividad> acts=campamentos.getListaActividades_();
						Actividad actividad = null;
						for(Actividad aux : acts)
							if(aux.getName_() == ida)
								actividad = aux;
						if(actividad==null){
							System.out.println("No existe una actividad con ese id.");
						}
						else{
							campamentos.asociarActividadCampamento(idc,actividad);
						}
					}
					else if(opcion == 9) {
						/** Coge el id campamento, encuentra el monitor NO ESPECIAL, los asocia **/
						System.out.println("Inserte el id del campamento que quiera asociar: ");
						int idc=Integer.parseInt(teclado.readLine());
					
						System.out.println("Inserte el id del monitor que quiera asociar: ");
						int idm=Integer.parseInt(teclado.readLine());
						ArrayList<Monitor> mons=campamentos.getListaMonitores_();
						Monitor monitor = null;
						for(Monitor aux : mons)
							if(aux.getId() == idm)
								monitor = aux;
						if(monitor==null){
							System.out.println("No existe un monitor con ese id.");
						}
						else{
							if(monitor.getEspecial()==false){
								campamentos.asociarMonitorCampamento(idc,monitor);
							}
							else{
								System.out.println("Para asociar un monitor especial, seleccione la opción 10");
							}
						}
					}
					else if(opcion == 10) {
						/** Coge el id campamento, encuentra el monitor ESPECIAL, los asocia **/
						System.out.println("Inserte el id del campamento que quiera asociar: ");
						int idc=Integer.parseInt(teclado.readLine());
						
						System.out.println("Inserte el id del monitor que quiera asociar: ");
						int idm=Integer.parseInt(teclado.readLine());
						ArrayList<Monitor> mons=campamentos.getListaMonitores_();
						Monitor monitor = null;
						for(Monitor aux : mons)
							if(aux.getId() == idm)
								monitor = aux;
						if(monitor==null){
							System.out.println("No existe un monitor con ese id.");
						}
						else{
							if(monitor.getEspecial()==true){
									campamentos.asociarMonitorCampamento(idc,monitor);
							}
							else{
								System.out.println("Para asociar un monitor no especial, seleccione la opción 9");
							}
						}
					}
					else if(opcion == 11) 
						System.out.println("Volviendo al menú principal..............");
					else
						System.out.println("Opción incorrecta.");
				}while(opcion != 11);
			}
			else if(opcion == 3) {
				do {
					opcion = menuGestorInscripciones();
					if(opcion == 1) {
						ArrayList<InscripcionParcial> lista = inscripciones.getListaInscripcionParcial_();
						for (InscripcionParcial inscripcion : lista)
							System.out.println(inscripcion.toString());
					}
					else if(opcion == 2) {
						ArrayList<InscripcionCompleta> lista = inscripciones.getListaInscripcionCompleta_();
						for (InscripcionCompleta inscripcion : lista)
							System.out.println(inscripcion.toString());
					}
					else if(opcion == 3) {
						/** Realiza una inscripción parcial **/
						ArrayList<Asistente> asists=asistentes.getListaAsistente_();
						InscripcionParcial nuevo=new InscripcionParcial();
						System.out.println("Introduce el id del participante: ");
						nuevo.setIdParticipante(Integer.parseInt(teclado.readLine()));
						System.out.println("Introduce el id del campamento: ");
						int idc=Integer.parseInt(teclado.readLine());
						nuevo.setIdCampamento(idc);
						
						ArrayList<Campamento> camps=campamentos.getListaCampamentos_();
						Campamento campamento = null;
						for(Campamento aux : camps)
							if(aux.getId_() == idc)
								campamento = aux;
								
						if(campamento==null){
							System.out.println("El campamento no existe.");
						}
						else{
							LocalDate fecha=campamento.getIniciocampamento_();
							
							float pre= inscripciones.asignarPrecio(idc, nuevo.getIdParticipante(), camps);
							nuevo.setPrecio(pre);
													
							boolean status=inscripciones.realizarRegistro(nuevo,fecha,asists);
							if(status==true){
								System.out.println("Registro realizado correctamente.");
							}
							else{
								System.out.println("Hubo un error.");
							}
						}
					}
					else if(opcion == 4) {
						/** Realiza una inscripción completa **/
						ArrayList<Asistente> asists=asistentes.getListaAsistente_();
						InscripcionCompleta nuevo=new InscripcionCompleta();
						System.out.println("Introduce el id del participante: ");
						nuevo.setIdParticipante(Integer.parseInt(teclado.readLine()));
						System.out.println("Introduce el id del campamento: ");
						int idc=Integer.parseInt(teclado.readLine());
						nuevo.setIdCampamento(idc);
						
						ArrayList<Campamento> camps=campamentos.getListaCampamentos_();
						Campamento campamento = null;
						for(Campamento aux : camps)
							if(aux.getId_() == idc)
								campamento = aux;
								
						if(campamento==null){
							System.out.println("El campamento no existe.");
						}
						else{
							LocalDate fecha=campamento.getIniciocampamento_();

							float pre= inscripciones.asignarPrecio(idc, nuevo.getIdParticipante(), camps);
							nuevo.setPrecio(pre);
							
							boolean status=inscripciones.realizarRegistro(nuevo,fecha,asists);
							if(status==true){
								System.out.println("Registro realizado correctamente.");
							}
							else{
								System.out.println("Hubo un error.");
							}
						}
					}
					else if(opcion == 5) {
						/** Asigna precio a una inscripción y comprueba que se ha realizado correctamente **/
						System.out.println("Introduzca el id del campamento asociado a la inscripcion: ");
						int idc=Integer.parseInt(teclado.readLine());
						System.out.println("Introduzca el id del participante asociado a la inscripcion: ");
						int idp=Integer.parseInt(teclado.readLine());
						ArrayList<Campamento> camps=campamentos.getListaCampamentos_();
						
						int resultado= inscripciones.asignarPrecio(idc, idp, camps);
						if(resultado==0){
							System.out.println("Precio asignado correctamente");
						}
						if(resultado==-1){
							System.out.println("Hubo un error, no se encontró el id.");
						}
					}
					else if(opcion == 6) {
						
						System.out.println(inscripciones.obtenerCampamentosDisponibles(campamentos.getListaCampamentos_()));
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
		System.out.println("6. Listar campamentos disponibles.");
		System.out.println("7. Atrás.");
		System.out.println("----------------------------------");
		System.out.print("Seleccione una opción: ");
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        int opcion = Integer.parseInt(teclado.readLine());
        System.out.println("----------------------------------");
        return opcion;
	}
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
}
