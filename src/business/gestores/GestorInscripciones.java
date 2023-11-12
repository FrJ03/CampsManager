package business.gestores;

import java.util.ArrayList;

import data.dao.AsistenteDAO;
import data.dao.CampamentoDAO;
import data.dao.InscripcionCompletaDAO;
import data.dao.InscripcionDAO;
import data.dao.InscripcionParcialDAO;
import business.inscripciones.*;
import business.campamento.*;
import java.time.Period;
import java.time.LocalDate;

/**
 * Clase que implementa el patrón Singleton para poder ser utilizada en la validación de Inscripciones.
 * @author Enrique de los Reyes Montilla
 * @author Manuel Garcia Obrero
 */
public class GestorInscripciones {
	/**
	 * Variable privada Singleton.
	 */
	private static GestorInscripciones instance_= null;
	/**
	 * Metodo que sirve de acceso a la instancia.
	 * @return Instancia de la clase GestorCampamentos.
	 */
	public static GestorInscripciones getInstance() {
		if(instance_ == null) {
			return new GestorInscripciones();
		}
		return instance_;
	}
	private GestorInscripciones() {}
	/**
	 * Método que devuelve la lista de InscripcionesParciales del gestor.
	 * @return ArrayList<InscripcionParcial>.
	 */
	public ArrayList<InscripcionParcial> getListaInscripcionParcial() {
		InscripcionParcialDAO db = InscripcionParcialDAO.getInstance();
		return db.readAll();
	}
	/**
	 * Método que devuelve la lista de campamentos del gestor.
	 * @return ArrayList<InscripcionCompleta>.
	 */
	public ArrayList<InscripcionCompleta> getListaInscripcionCompleta() {
			InscripcionCompletaDAO db = InscripcionCompletaDAO.getInstance();
		return db.readAll();
	}
	/**
	 * Método que añade una inscripción a la lista de inscripciones parciales.
	 * @param idC Identificador del campamento.
	 * @param idA Identificador del asistente.
	 * @return boolean.
	 */
	public boolean realizarRegistroCompleto(int idC, int idA){
		//Compruebo que el campamento y asistente exista y que no esté el usuario ya escrito
		CampamentoDAO dbC = CampamentoDAO.getInstance();
		AsistenteDAO dbA = AsistenteDAO.getInstance();
		InscripcionDAO dbI = InscripcionDAO.getInstance();
		Campamento campamento;
		if((campamento = dbC.readAvailable(idC)) == null || dbA.read(idA) == null || dbI.read(idC, idA) != null)
			return false;
		//Calculo el precio
		float precio = calcularPrecioCompleto(campamento.getListaActividad().size());
		//Establezco la información a la inscripcion
		InscripcionCompleta inscripcion = new InscripcionCompleta();
		inscripcion.setIdCampamento(idC);
		inscripcion.setIdParticipante(idA);
		inscripcion.setPrecio(precio);
		inscripcion.setTipo("Completa");
		inscripcion.setFechaInscripcion(LocalDate.now());
		// Calcular la diferencia entre la fecha del campamento y la de inscripcion
        Period periodo = inscripcion.getFechaInscripcion().until(campamento.getInicioCampamento());

        // Obtener el número de días de la diferencia
        int diferenciaDias = periodo.getDays();
        
        InscripcionCompletaDAO db = InscripcionCompletaDAO.getInstance();
		if(diferenciaDias >= 15 )
			return db.createTemprano(inscripcion);
		else if(diferenciaDias >= 2)
			return db.createTardio(inscripcion);
		else
			return false;
	}
	/**
	 * Método que añade una inscripción a la lista de inscripciones parciales.
	 * @param idC Identificador del campamento.
	 * @param idA Identificador del asistente.
	 * @return boolean.
	 */
	public boolean realizarRegistroParcial(int idC, int idA){
		//Compruebo que el campamento y asistente exista y que no esté el usuario ya escrito
		CampamentoDAO dbC = CampamentoDAO.getInstance();
		AsistenteDAO dbA = AsistenteDAO.getInstance();
		InscripcionDAO dbI = InscripcionDAO.getInstance();
		Campamento campamento;
		if((campamento = dbC.readAvailable(idC)) == null || dbA.read(idA) == null || dbI.read(idC, idA) != null)
			return false;
		//Calculo el precio
		float precio = calcularPrecioParcial(campamento.getListaActividad().size());
		//Establezco la información a la inscripcion
		InscripcionParcial inscripcion = new InscripcionParcial();
		inscripcion.setIdCampamento(idC);
		inscripcion.setIdParticipante(idA);
		inscripcion.setPrecio(precio);
		inscripcion.setTipo("Parcial");
		inscripcion.setFechaInscripcion(LocalDate.now());
		// Calcular la diferencia entre la fecha del campamento y la de inscripcion
        Period periodo = inscripcion.getFechaInscripcion().until(campamento.getInicioCampamento());

        // Obtener el número de días de la diferencia
        int diferenciaDias = periodo.getDays();
        
        InscripcionParcialDAO db = InscripcionParcialDAO.getInstance();
		if(diferenciaDias >= 15 )
			return db.createTemprano(inscripcion);
		else if(diferenciaDias >= 2)
			return db.createTardio(inscripcion);
		else
			return false;
	}
	/**
	 * Método que calcula el precio de la inscripción y lo devuelve como un int. Si el resultado es -1,  no existe el campamento al que está inscrito
	 * @param nActividades Número de actividades del campamento
	 * @return precio. 
	 */
	
	private float calcularPrecioCompleto(int nActividades) {
		return 300 + 20 * nActividades;
	}
	/**
	 * Método que calcula el precio de la inscripción y lo devuelve como un int. Si el resultado es -1,  no existe el campamento al que está inscrito
	 * @param nActividades Número de actividades del campamento
	 * @return precio. 
	 */
	
	private float calcularPrecioParcial(int nActividades) {
		return 100 + 20 * nActividades;
	}
	/**
	 * Método que devuelve una lista con todos los campamentos disponibles.
	 * @param listaCampamentos
	 * @return ArrayList<Campamento>. 
	 * @throws Exception 
	 */
	public String obtenerCampamentosDisponibles(){
		CampamentoDAO db = CampamentoDAO.getInstance();
		ArrayList<Campamento> disponibles = db.readAllAvailable();
		
		String listaAux = "";
		
		if(disponibles.size() > 0) 		
			for(Campamento cam : disponibles) 
				listaAux += cam.toString();
		
		return listaAux;
	}
	
	
}