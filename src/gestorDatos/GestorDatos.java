package gestorDatos;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import actividad.Actividad;
import asistente.Asistente;
import campamento.Campamento;
import inscripcion.InscripcionCompleta;
import inscripcion.InscripcionParcial;
import monitor.Monitor;

/**
 * Clase diseñada para interactuar con los datos permanentes.
 * @author Francisco José Mellado Ortiz
 * @author Enrique de los Reyes Montilla
 * */
public class GestorDatos {
	/**
	 * Método que lee todos los asistentes del fichero pasado.
	 * @param ruta Ruta del fichero
	 * @return ArrayList de asistentes
	 * @throws IOException, ClassNotFoundException
	 */
	public ArrayList<Asistente> getAsistentes(String ruta) throws IOException, ClassNotFoundException{
		ArrayList<Asistente> list = new ArrayList<Asistente>();
		try {
            // Crea un ObjectInputStream y lo conecta a un FileInputStream
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(ruta));

            // Lee la lista de Asistentees desde el archivo
             list  = (ArrayList<Asistente>) inputStream.readObject();


            // Cierra el flujo
            inputStream.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
		
		return list;
	}
	/**
	 * Método que lee todos los monitores del fichero pasado.
	 * @param ruta Ruta del fichero
	 * @return ArrayList de monitores
	 * @throws IOException
	 */
	public ArrayList<Monitor> getMonitores(String ruta) throws IOException, ClassNotFoundException{
		ArrayList<Monitor> list = new ArrayList<Monitor>();
		try {
            // Crea un ObjectInputStream y lo conecta a un FileInputStream
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(ruta));

            // Lee la lista de Monitores desde el archivo
             list  = (ArrayList<Monitor>) inputStream.readObject();


            // Cierra el flujo
            inputStream.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
		
		return list;

	}
	/**
	 * Método que lee todos los campamentos del fichero pasado.
	 * @param ruta Ruta del fichero
	 * @return ArrayList de campamentos
	 * @throws IOException
	 */
	public ArrayList<Campamento> getCampamentos(String ruta) throws IOException, ClassNotFoundException{
		ArrayList<Campamento> list = new ArrayList<Campamento>();
		try {
            // Crea un ObjectInputStream y lo conecta a un FileInputStream
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(ruta));

            // Lee la lista de Campamentos desde el archivo
             list  = (ArrayList<Campamento>) inputStream.readObject();


            // Cierra el flujo
            inputStream.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
		
		return list;

	}
	

	/**
	 * Método que lee todos las actividades del fichero pasado.
	 * @param ruta Ruta del fichero
	 * @return ArrayList de actividades
	 * @throws IOException
	 */
	public ArrayList<Actividad> getActividades(String ruta) throws IOException, ClassNotFoundException{
		ArrayList<Actividad> list = new ArrayList<Actividad>();
		try {
            // Crea un ObjectInputStream y lo conecta a un FileInputStream
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(ruta));

            // Lee la lista de Actividades desde el archivo
             list  = (ArrayList<Actividad>) inputStream.readObject();


            // Cierra el flujo
            inputStream.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
		
		return list;

	}
	/**
	 * Método que lee todos las inscripciones completas del fichero pasado.
	 * @param ruta Ruta del fichero
	 * @return ArrayList de inscripciones completas
	 * @throws IOException
	 */
	public ArrayList<InscripcionCompleta> getInscripcionesCompletas(String ruta) throws IOException, ClassNotFoundException{
		ArrayList<InscripcionCompleta> list = new ArrayList<InscripcionCompleta>();
		try {
            // Crea un ObjectInputStream y lo conecta a un FileInputStream
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(ruta));

            // Lee la lista de InscripcionCompletas desde el archivo
             list  = (ArrayList<InscripcionCompleta>) inputStream.readObject();


            // Cierra el flujo
            inputStream.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
		
		return list;

	}
	/**
	 * Método que lee todos las inscripciones parciales del fichero pasado.
	 * @param ruta Ruta del fichero
	 * @return ArrayList de inscripciones parciales
	 * @throws IOException
	 */
	public ArrayList<InscripcionParcial> getInscripcionesParciales(String ruta) throws IOException, ClassNotFoundException{
		ArrayList<InscripcionParcial> list = new ArrayList<InscripcionParcial>();
		try {
            // Crea un ObjectInputStream y lo conecta a un FileInputStream
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(ruta));

            // Lee la lista de InscripcionesParciales desde el archivo
             list  = (ArrayList<InscripcionParcial>) inputStream.readObject();


            // Cierra el flujo
            inputStream.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
		
		return list;
	}

	/**
	 *  Método que guarda la lista de asistentes en un fichero.
	 * @param ruta Cadena con la ruta del fichero.
	 * @param lista ArrayList con la informacion de los asistentes que se desean guardar.
	 */
	public void setAsistentes(String ruta, ArrayList<Asistente> lista) throws IOException{
		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(ruta))) {
            outputStream.writeObject(lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	/**
	 * Método que guarda la lista de monitores en un fichero.
	 * @param ruta Cadena con la ruta del fichero.
	 * @param lista ArrayList con la informacion de los monitores que se desean guardar.
	 */
	public void setMonitores(String ruta, ArrayList<Monitor> lista) throws IOException{
		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(ruta))) {
            outputStream.writeObject(lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	/**
	 * Método que guarda la lista de campamentos en un fichero.
	 * @param ruta Cadena con la ruta del fichero.
	 * @param lista ArrayList con la informacion de los campamentos que se desean guardar.
	 */
	public void setCampamentos(String ruta, ArrayList<Campamento> lista) throws IOException{
		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(ruta))) {
            outputStream.writeObject(lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	/**
	 * Método que guarda la lista de actividades en un fichero.
	 * @param ruta Cadena con la ruta del fichero.
	 * @param lista ArrayList con la informacion de las actividades que se desean guardar.
	 */
	public void setActividades(String ruta, ArrayList<Actividad> lista) throws IOException{
		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(ruta))) {
            outputStream.writeObject(lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	/**
	 * Método que guarda la lista de inscripciones completas en un fichero.
	 * @param ruta Cadena con la ruta del fichero.
	 * @param lista ArrayList con la informacion de las inscripciones completas que se desean guardar.
	 */
	public void setInscripcionesCompletas(String ruta, ArrayList<InscripcionCompleta> lista) throws IOException{
		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(ruta))) {
            outputStream.writeObject(lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	/**
	 * Método que guarda la lista de inscripciones parciales en un fichero.
	 * @param ruta Cadena con la ruta del fichero.
	 * @param lista ArrayList con la informacion de las inscripciones parciales que se desean guardar.
	 */
	public void setInscripcionesParciales(String ruta, ArrayList<InscripcionParcial> lista) throws IOException{
		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(ruta))) {
            outputStream.writeObject(lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
	
	
