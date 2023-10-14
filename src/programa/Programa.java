package programa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.time.LocalDate;
import actividad.*;
import asistente.Asistente;
import campamento.Campamento;
import gestorAsistentes.GestorAsistentes;
import gestorCampamentos.GestorCampamentos;
import gestorDatos.GestorDatos;
import gestorInscripciones.GestorInscripciones;
import inscripcion.InscripcionCompleta;
import inscripcion.InscripcionParcial;
import monitor.Monitor;

public class Programa {
	private static String dir_ = "rutas.txt";
	
	public static void main(String args[]){
		int opcion = 0;
		
		Properties p = new Properties();
		BufferedReader reader = null;
		GestorDatos datos = null;
		GestorAsistentes asistentes = null;
		GestorCampamentos campamentos = null;
		GestorInscripciones inscripciones = null;
		try {
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			datos = GestorDatos.getInstance();
			
			asistentes = GestorAsistentes.getInstance();
			asistentes.setListaAsistentes(datos.getAsistentes(p.getProperty("asistentes")));
			campamentos = GestorCampamentos.getInstance();
			campamentos.setListaActividades(datos.getActividades(p.getProperty("actividades")));
			campamentos.setListaCampamentos(datos.getCampamentos(p.getProperty("campamentos")));
			campamentos.setListaMonitores(datos.getMonitores(p.getProperty("monitores")));
			inscripciones = GestorInscripciones.getInstance();
			inscripciones.setListaInscripcionCompleta(datos.getInscripcionesCompletas(p.getProperty("inscripcionesCompletas")));
			inscripciones.setListaInscripcionParcial(datos.getInscripcionesParciales(p.getProperty("inscripcionesParciales")));
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
		
		
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		do {
			try {
				opcion = menuPrincipal();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				salir();
				System.exit(0);
			}
			if(opcion == 1) {				
				do {
					try {
						opcion = menuGestorAsistentes();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						salir();
						System.exit(0);
					}
					if(opcion == 1) {
						ArrayList<Asistente> listaAsistentes = asistentes.getListaAsistente();
						for(Asistente asistente : listaAsistentes) {
							System.out.println(asistente.toString());
						}
					}
					else if(opcion == 2) {
						Asistente nuevo = new Asistente();
						System.out.print("Inserte el nombre del asistente: ");
						try {
							nuevo.setNombre(teclado.readLine());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							salir();
							System.exit(0);
						}
						System.out.print("Inserte los apellidos del asistente: ");
						try {
							nuevo.setApellidos(teclado.readLine());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							salir();
							System.exit(0);
						}
						System.out.print("Inserte la fecha de nacimiento (yyyy/mm/dd): ");
						String aux = null;
						try {
							aux = teclado.readLine();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							salir();
							System.exit(0);
						}
						Date fecha = new Date(Integer.parseInt(aux.substring(0, 3)), Integer.parseInt(aux.substring(5, 6)), Integer.parseInt(aux.substring(8, 9)));
						nuevo.setFechaNacimiento(fecha);
						String letra = null;
						do {
							System.out.print("¿Necesita atención especial? (S/N): ");
							try {
								letra = teclado.readLine();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								salir();
								System.exit(0);
							}
							if(letra.equalsIgnoreCase("s"))
								nuevo.setEspecial(true);
							else if(letra.equalsIgnoreCase("n"))
								nuevo.setEspecial(false);
								
						}while(!letra.equalsIgnoreCase("s") && !letra.equalsIgnoreCase("n"));
						asistentes.darAltaAsistente(nuevo);
					}
					else if(opcion == 3) {
						System.out.print("Inserte el id del asistente a modificar: ");
						int idAntiguo = -1;
						try {
							idAntiguo = Integer.parseInt(teclado.readLine());
						} catch (NumberFormatException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							salir();
							System.exit(0);
						}
						System.out.print("Inserte el nuevo id: ");
						int idNuevo = -1;
						try {
							idNuevo = Integer.parseInt(teclado.readLine());
						} catch (NumberFormatException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							salir();
							System.exit(0);
						}
						System.out.print("Inserte el nuevo nombre: ");
						String nombre = null;
						try {
							nombre = teclado.readLine();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							salir();
							System.exit(0);
						}
						System.out.print("Inserte los nuevos apellidos: ");
						String apellidos = null;
						try {
							apellidos = teclado.readLine();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							salir();
							System.exit(0);
						}
						System.out.print("Inserte la nueva fecha de nacimiento (yyyy/mm/dd): ");
						String aux = null;
						try {
							aux = teclado.readLine();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							salir();
							System.exit(0);
						}
						Date fecha = new Date(Integer.parseInt(aux.substring(0, 3)), Integer.parseInt(aux.substring(5, 6)), Integer.parseInt(aux.substring(8, 9)));
						String letra = null;
						boolean especial = false;
						do {
							System.out.print("¿Necesita atención especial? (S/N): ");
							try {
								letra = teclado.readLine();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								salir();
								System.exit(0);
							}
							if(letra.equalsIgnoreCase("s"))
								especial = true;
							else if(letra.equalsIgnoreCase("n"))
								especial = false;
								
						}while(!letra.equalsIgnoreCase("s") && !letra.equalsIgnoreCase("n"));
						asistentes.modificarAsistente(idAntiguo, idNuevo, nombre, apellidos, fecha, especial);
					}
					else if(opcion == 4) {
						try {
							System.out.println(asistentes.listaAsistencia());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							salir();
							System.exit(0);
						}						
					}
					else if(opcion == 5) 
						System.out.println("Volviendo al menú principal..............");
					else
						System.out.println("Opción incorrecta.");
				}while(opcion != 5);
			}
			else if(opcion == 2) {
				do {
					try {
						opcion = menuGestorCampamentos();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						salir();
						System.exit(0);
					}
					if(opcion == 1) {
						System.out.println(campamentos.getListaCampamentos());
					}
					else if(opcion == 2) {
						System.out.println(campamentos.getListaActividades());
					}
					else if(opcion == 3) {
						System.out.println(campamentos.getListaMonitores());
					}
					else if(opcion == 4) {
						int day = 1;
						int month = 1;
						int year = 1;
						LocalDate fechaFin;
						LocalDate fechaInicio;
						do {
							do {
								System.out.print("Inserte el día de inicio: ");
								try {
									day = Integer.parseInt(teclado.readLine());
								} catch (NumberFormatException | IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									salir();
									System.exit(0);
								}
								System.out.print("Inserte el mes de inicio: ");
								try {
									month = Integer.parseInt(teclado.readLine());
								} catch (NumberFormatException | IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									salir();
									System.exit(0);
								}
								System.out.print("Inserte el año de inicio: ");
								try {
									year = Integer.parseInt(teclado.readLine());
								} catch (NumberFormatException | IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									salir();
									System.exit(0);
								}
							}while(!dateValid(year, month, day));							
							fechaInicio = LocalDate.of(year, month, day);
							do {
								System.out.print("Inserte el día de finalización: ");
								try {
									day = Integer.parseInt(teclado.readLine());
								} catch (NumberFormatException | IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									salir();
									System.exit(0);
								}
								System.out.print("Inserte el mes de finalización: ");
								try {
									month = Integer.parseInt(teclado.readLine());
								} catch (NumberFormatException | IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									salir();
									System.exit(0);
								}
								System.out.print("Inserte el año de finalización: ");
								try {
									year = Integer.parseInt(teclado.readLine());
								} catch (NumberFormatException | IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									salir();
									System.exit(0);
								}
							}while(!dateValid(year, month, day));							
							fechaFin = LocalDate.of(year, month, day);
						}while(fechaFin.compareTo(fechaInicio) <= 0);
						String n = null;
						Nivel nivel = null;
						do {
							System.out.print("Indique para quién va dirigido (I (Infantil) / J (Juvenil) / A (Adolescente)): ");
							try {
								n = teclado.readLine();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								salir();
								System.exit(0);
							}
							if(n.equalsIgnoreCase("i"))
								nivel = Nivel.Infantil;
							else if(n.equalsIgnoreCase("j"))
								nivel = Nivel.Juvenil;
							else if(n.equalsIgnoreCase("a"))
								nivel = Nivel.Adolescente;
								
						}while(!n.equalsIgnoreCase("i") && !n.equalsIgnoreCase("j") && !n.equalsIgnoreCase("a"));
						int max = 0;
						do {
							System.out.print("Inserte el número máximo de participantes: ");
							try {
								max = Integer.parseInt(teclado.readLine());
							} catch (NumberFormatException | IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								salir();
								System.exit(0);
							}
						}while(max < 1);
						campamentos.crearCampamento(new Campamento(-1, fechaInicio, fechaFin, nivel, max));
					}
					else if(opcion == 5) {
						System.out.print("Inserte el nombre de la actividad: ");
						String nombre = null;
						try {
							nombre = teclado.readLine();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							salir();
							System.exit(0);
						}
						String n = null;
						Nivel nivel = null;
						do {
							System.out.print("Indique para quién va dirigido (I (Infantil) / J (Juvenil) / A (Adolescente)): ");
							try {
								n = teclado.readLine();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								salir();
								System.exit(0);
							}
							if(n.equalsIgnoreCase("i"))
								nivel = Nivel.Infantil;
							else if(n.equalsIgnoreCase("j"))
								nivel = Nivel.Juvenil;
							else if(n.equalsIgnoreCase("a"))
								nivel = Nivel.Adolescente;
								
						}while(!n.equalsIgnoreCase("i") && !n.equalsIgnoreCase("j") && !n.equalsIgnoreCase("a"));
						int maxP = 0;
						do {
							System.out.print("Inserte el número máximo de participantes: ");
							try {
								maxP = Integer.parseInt(teclado.readLine());
							} catch (NumberFormatException | IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								salir();
								System.exit(0);
							}
						}while(maxP < 1);
						int maxM = 0;
						do {
							System.out.print("Inserte el número máximo de monitores: ");
							try {
								maxM = Integer.parseInt(teclado.readLine());
							} catch (NumberFormatException | IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								salir();
								System.exit(0);
							}
						}while(maxM < 1);
						String t = null;
						Turno turno = null;
						do {
							System.out.print("¿La actividad se realizará por la mañana (M) o por la tarde (T)?: ");
							try {
								t = teclado.readLine();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								salir();
								System.exit(0);
							}
							if(t.equalsIgnoreCase("t"))
								turno = Turno.Tarde;
							else if(t.equalsIgnoreCase("m"))
								turno = Turno.Mañana;
						}while (!t.equalsIgnoreCase("t") && !t.equalsIgnoreCase("m"));
						Actividad actividad = new Actividad(-1, nombre, nivel, maxP, maxM, turno);
						campamentos.crearActividad(actividad);
					}
					else if(opcion == 6) {
						Monitor nuevo = new Monitor();
						System.out.print("Inserte el nombre del monitor: ");
						try {
							nuevo.setNombre(teclado.readLine());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							salir();
							System.exit(0);
						}
						System.out.print("Inserte los apellidos del monitor: ");
						try {
							nuevo.setApellidos(teclado.readLine());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							salir();
							System.exit(0);
						}
						String letra = null;
						do {
							System.out.print("¿Puede dirigir actividades con asistentes que necesiten atención especial? (S/N): ");
							try {
								letra = teclado.readLine();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								salir();
								System.exit(0);
							}
							if(letra.equalsIgnoreCase("s"))
								nuevo.setEspecial(true);
							else if(letra.equalsIgnoreCase("n"))
								nuevo.setEspecial(false);
								
						}while(!letra.equalsIgnoreCase("s") && !letra.equalsIgnoreCase("n"));
						campamentos.crearMonitor(nuevo);
					}
					else if(opcion == 7) {
						/* Encuentra el monitor, encuentra la actividad, los asocia */
						System.out.println("Inserte el id del monitor que quiera asociar a la actividad: ");
						int idm = -1;
						try {
							idm = Integer.parseInt(teclado.readLine());
						} catch (NumberFormatException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							salir();
							System.exit(0);
						}
						ArrayList<Monitor> mons=campamentos.getListaMonitores();
						Monitor monitor = null;
						for(Monitor aux : mons)
							if(aux.getId() == idm)
								monitor = aux;
						if(monitor == null){
							System.out.println("No existe un monitor con ese id.");
						}
						else{
							System.out.println("Inserte el identificador de la actividad que quiera asociar al monitor: ");
							int ida = -1;
							try {
								ida = Integer.parseInt(teclado.readLine());
							} catch (NumberFormatException | IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								salir();
								System.exit(0);
							}
							ArrayList<Actividad> acts=campamentos.getListaActividades();
							Actividad actividad = null;
							for (Actividad aux : acts)
								if(aux.getId() == ida)
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
						int idc = -1;
						try {
							idc = Integer.parseInt(teclado.readLine());
						} catch (NumberFormatException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							salir();
							System.exit(0);
						}
						System.out.println("Inserte el id de la actividad que quiera asociar: ");
						int ida = -1;
						try {
							ida = Integer.parseInt(teclado.readLine());
						} catch (NumberFormatException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							salir();
							System.exit(0);
						}
						ArrayList<Actividad> acts=campamentos.getListaActividades();
						Actividad actividad = null;
						for (Actividad aux : acts)
							if(aux.getId() == ida)
								actividad = aux;
						if(actividad==null){
							System.out.println("No existe una actividad con ese id.");
						}
						else{
							campamentos.asociarActividadCampamento(idc,actividad);
						}
					}
					else if(opcion == 9) {
						/* Coge el id campamento, encuentra el monitor NO ESPECIAL, los asocia */
						System.out.println("Inserte el id del campamento que quiera asociar: ");
						int idc = -1;
						try {
							idc = Integer.parseInt(teclado.readLine());
						} catch (NumberFormatException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							salir();
							System.exit(0);
						}
					
						System.out.println("Inserte el id del monitor que quiera asociar: ");
						int idm = -1;
						try {
							idm = Integer.parseInt(teclado.readLine());
						} catch (NumberFormatException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							salir();
							System.exit(0);
						}
						ArrayList<Monitor> mons=campamentos.getListaMonitores();
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
						/* Coge el id campamento, encuentra el monitor ESPECIAL, los asocia */
						System.out.println("Inserte el id del campamento que quiera asociar: ");
						int idc = -1;
						try {
							idc = Integer.parseInt(teclado.readLine());
						} catch (NumberFormatException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							salir();
							System.exit(0);
						}
						
						System.out.println("Inserte el id del monitor que quiera asociar: ");
						int idm = -1;
						try {
							idm = Integer.parseInt(teclado.readLine());
						} catch (NumberFormatException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							salir();
							System.exit(0);
						}
						ArrayList<Monitor> mons=campamentos.getListaMonitores();
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
					try {
						opcion = menuGestorInscripciones();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						salir();
						System.exit(0);
					}
					if(opcion == 1) {
						ArrayList<InscripcionParcial> lista = inscripciones.getListaInscripcionParcial();
						for (InscripcionParcial inscripcion : lista)
							System.out.println(inscripcion.toString());
					}
					else if(opcion == 2) {
						ArrayList<InscripcionCompleta> lista = inscripciones.getListaInscripcionCompleta();
						for (InscripcionCompleta inscripcion : lista)
							System.out.println(inscripcion.toString());
					}
					else if(opcion == 3) {
						/* Realiza una inscripción parcial */
						ArrayList<Asistente> asists=asistentes.getListaAsistente();
						InscripcionParcial nuevo=new InscripcionParcial();
						System.out.println("Introduce el id del participante: ");
						try {
							nuevo.setIdParticipante(Integer.parseInt(teclado.readLine()));
						} catch (NumberFormatException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							salir();
							System.exit(0);
						}
						System.out.println("Introduce el id del campamento: ");
						int idc = -1;
						try {
							idc = Integer.parseInt(teclado.readLine());
						} catch (NumberFormatException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							salir();
							System.exit(0);
						}
						nuevo.setIdCampamento(idc);
						
						ArrayList<Campamento> camps=campamentos.getListaCampamentos();
						Campamento campamento = null;
						for(Campamento aux : camps)
							if(aux.getId() == idc)
								campamento = aux;
								
						if(campamento==null){
							System.out.println("El campamento no existe.");
						}
						else{
							LocalDate fecha=campamento.getInicioCampamento();
							
							float pre= inscripciones.asignarPrecio(idc, nuevo.getIdParticipante(), camps);
							nuevo.setPrecio(pre);
													
							boolean status = false;
							try {
								status = inscripciones.realizarRegistro(nuevo,fecha,asists);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								salir();
								System.exit(0);
							}
							if(status==true){
								System.out.println("Registro realizado correctamente.");
							}
							else{
								System.out.println("Hubo un error.");
							}
						}
					}
					else if(opcion == 4) {
						/* Realiza una inscripción completa */
						ArrayList<Asistente> asists=asistentes.getListaAsistente();
						InscripcionCompleta nuevo=new InscripcionCompleta();
						System.out.println("Introduce el id del participante: ");
						try {
							nuevo.setIdParticipante(Integer.parseInt(teclado.readLine()));
						} catch (NumberFormatException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							salir();
							System.exit(0);
						}
						System.out.println("Introduce el id del campamento: ");
						int idc = -1;
						try {
							idc = Integer.parseInt(teclado.readLine());
						} catch (NumberFormatException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							salir();
							System.exit(0);
						}
						nuevo.setIdCampamento(idc);
						
						ArrayList<Campamento> camps=campamentos.getListaCampamentos();
						Campamento campamento = null;
						for(Campamento aux : camps)
							if(aux.getId() == idc)
								campamento = aux;
								
						if(campamento==null){
							System.out.println("El campamento no existe.");
						}
						else{
							LocalDate fecha=campamento.getInicioCampamento();

							float pre= inscripciones.asignarPrecio(idc, nuevo.getIdParticipante(), camps);
							nuevo.setPrecio(pre);
							
							boolean status = false;
							try {
								status = inscripciones.realizarRegistro(nuevo,fecha,asists);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								salir();
								System.exit(0);
							}
							if(status==true){
								System.out.println("Registro realizado correctamente.");
							}
							else{
								System.out.println("Hubo un error.");
							}
						}
					}
					else if(opcion == 5) {
						/* Asigna precio a una inscripción y comprueba que se ha realizado correctamente */
						System.out.println("Introduzca el id del campamento asociado a la inscripcion: ");
						int idc = -1;
						try {
							idc = Integer.parseInt(teclado.readLine());
						} catch (NumberFormatException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							salir();
							System.exit(0);
						}
						System.out.println("Introduzca el id del participante asociado a la inscripcion: ");
						int idp = -1;
						try {
							idp = Integer.parseInt(teclado.readLine());
						} catch (NumberFormatException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							salir();
							System.exit(0);
						}
						ArrayList<Campamento> camps=campamentos.getListaCampamentos();
						
						int resultado= inscripciones.asignarPrecio(idc, idp, camps);
						if(resultado==0){
							System.out.println("Precio asignado correctamente");
						}
						if(resultado==-1){
							System.out.println("Hubo un error, no se encontró el id.");
						}
					}
					else if(opcion == 6) {
						
						try {
							System.out.println(inscripciones.obtenerCampamentosDisponibles(campamentos.getListaCampamentos()));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							salir();
							System.exit(0);
						}
					}
					else if(opcion == 7) 
						System.out.println("Volviendo al menú principal..............");
					else
						System.out.println("Opción incorrecta.");
				}while(opcion != 7);
			}
			else if(opcion == 4) 
				salir();
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
	private static void salir() {
		Properties p = new Properties();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(new File(dir_)));
			p.load(reader);
			GestorDatos datos = GestorDatos.getInstance();
			
			GestorAsistentes asistentes = GestorAsistentes.getInstance();
			datos.setAsistentes(p.getProperty("asistentes"), asistentes.getListaAsistente());
			GestorCampamentos campamentos = GestorCampamentos.getInstance();
			datos.setActividades(p.getProperty("actividades"), campamentos.getListaActividades());
			datos.setCampamentos(p.getProperty("campamentos"), campamentos.getListaCampamentos());
			datos.setMonitores(p.getProperty("monitores"), campamentos.getListaMonitores());
			GestorInscripciones inscripciones = GestorInscripciones.getInstance();
			datos.setInscripcionesCompletas(p.getProperty("inscripcionesCompletas"), inscripciones.getListaInscripcionCompleta());
			datos.setInscripcionesParciales(p.getProperty("inscripcionesParciales"), inscripciones.getListaInscripcionParcial());
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		System.out.println("Saliendo del sistema.................");
	}
}
