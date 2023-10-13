package GestorDatos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import Asistente.Asistente;
import Monitor.Monitor;
import Campamento.Campamento;
import Actividad.Actividad;
import Inscripcion.InscripcionCompleta;
import Inscripcion.InscripcionParcial;

/**
 * Clase diseñada para interactuar con los datos permanentes
 * @author Francisco José Mellado Ortiz
 * */
public class GestorDatos {
	/**
	 * Función que lee todos los asistentes del fichero pasado. Cada línea corresponde con un asistente.
	 * @param ruta Ruta del fichero
	 * @return ArrayList de asistentes
	 * @throws IOException
	 */
	public ArrayList<Asistente> getAsistentes(String ruta) throws IOException{
		ArrayList<Asistente> list = new ArrayList<Asistente>();
		BufferedReader br = new BufferedReader(new FileReader(ruta));
		String line;
		while((line = br.readLine()) != null) {
			list.add(readLineAsistente(line));
		}
		br.close();
		return list;
	}
	/**
	 * Función que lee todos los monitores del fichero pasado. Cada línea corresponde con un monitor.
	 * @param ruta Ruta del fichero
	 * @return ArrayList de monitores
	 * @throws IOException
	 */
	public ArrayList<Monitor> getMonitores(String ruta) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(ruta));
		ArrayList<Monitor> list = new ArrayList<Monitor>();
		String line;
		while((line = br.readLine()) != null) {
			list.add(readLineMonitor(line));
		}
		br.close();
		return list;
	}
	/**
	 * Función que lee todos los campamentos del fichero pasado. Cada línea corresponde con un campamento.
	 * @param ruta Ruta del fichero
	 * @return ArrayList de campamentos
	 * @throws IOException
	 */
	public ArrayList<Campamento> getCampamentos(String ruta) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(ruta));
		ArrayList<Campamento> list = new ArrayList<Campamento>();
		String line;
		while((line = br.readLine()) != null) {
			list.add(readLineCampamento(line));
		}
		br.close();
		return list;
	}
	/**
	 * Función que lee todos las actividades del fichero pasado. Cada línea corresponde con una actividad.
	 * @param ruta Ruta del fichero
	 * @return ArrayList de actividades
	 * @throws IOException
	 */
	public ArrayList<Actividad> getActividades(String ruta) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(ruta));
		ArrayList<Actividad> list = new ArrayList<Actividad>();
		String line;
		while((line = br.readLine()) != null) {
			list.add(readLineActividad(line));
		}
		br.close();
		return list;
	}
	/**
	 * Función que lee todos las inscripciones completas del fichero pasado. Cada línea corresponde con una inscripción.
	 * @param ruta Ruta del fichero
	 * @return ArrayList de inscripciones completas
	 * @throws IOException
	 */
	public ArrayList<InscripcionCompleta> getInscripcionesCompletas(String ruta) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(ruta));
		ArrayList<InscripcionCompleta> list = new ArrayList<InscripcionCompleta>();
		String line;
		while((line = br.readLine()) != null) {
			list.add(readLineInscripcionCompleta(line));
		}
		br.close();
		return list;
	}
	/**
	 * Función que lee todos las inscripciones parciales del fichero pasado. Cada línea corresponde con una inscripción.
	 * @param ruta Ruta del fichero
	 * @return ArrayList de inscripciones parciales
	 * @throws IOException
	 */
	public ArrayList<InscripcionParcial> getInscripcionesTardias(String ruta) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(ruta));
		ArrayList<InscripcionParcial> list = new ArrayList<InscripcionParcial>();
		String line;
		while((line = br.readLine()) != null) {
			list.add(readLineInscripcionParcial(line));
		}
		br.close();
		return list;
	}
	/**
	 * Función que convierte una línea de información en un objeto de la clase Asistente. La estructura de la línea es:'id' 'nombre' 'apellidos' 'fecha de nacimiento' 'especial'.
	 * @param line Línea de información.
	 * @return Asistente
	 */
	private static Asistente readLineAsistente(String line) {
		Asistente elemento = new Asistente();
		
		return elemento;
	}
	/**
	 * Función que convierte una línea de información en un objeto de la clase Monitor. La estructura de la línea es:'id' 'nombre' 'apellidos' 'especial'.
	 * @param line Línea de información.
	 * @return Monitor
	 */
	private static Monitor readLineMonitor(String line) {
		Monitor elemento = new Monitor();
		return elemento;
	}
	/**
	 * Función que convierte una línea de información en un objeto de la clase Campamento. La estructura de la línea es:'id' 'inicio' 'fin' 'nivel' 'capacidad' 'actividad1'&'actividad2'&'...'&'actividadN' 'monitor1'&'monitor2'&'...'&'monitorN'. Los datos de los monitores y actividades se separan con '%'
	 * @param line Línea de información.
	 * @return Campamento
	 */
	private static Campamento readLineCampamento(String line) {
		Campamento elemento = new Campamento();
		return elemento;
	}
	/**
	 * Función que convierte una línea de información en un objeto de la clase Actividad. La estructura de la línea es:'nombre' 'nivel' 'turno' 'participantes máximos' 'monitores máximos' 'monitor1'&'monitor2'&'...'&'monitorN'. Los datos de los monitores se separan con '%'.
	 * @param line Línea de información.
	 * @return Actividad
	 */
	private static Actividad readLineActividad(String line) {
		Actividad elemento = new Actividad();
		return elemento;
	}
	/**
	 * Función que convierte una línea de información en un objeto de la clase InscripcionCompleta. La estructura de la línea es:'id asistete' 'id campamento' 'fecha de inscripcion' 'precio'.
	 * @param line Línea de información.
	 * @return InscripcionCompleta
	 */
	private static InscripcionCompleta readLineInscripcionCompleta(String line) {
		InscripcionCompleta elemento = new InscripcionCompleta();
		return elemento;
	}
	/**
	 * Función que convierte una línea de información en un objeto de la clase asistente. La estructura de la línea es:'id asistete' 'id campamento' 'fecha de inscripcion' 'precio'.
	 * @param line Línea de información.
	 * @return Asistente
	 */
	private static InscripcionParcial readLineInscripcionParcial(String line) {
		InscripcionParcial elemento = new InscripcionParcial();
		return elemento;
	}
}
