package controller.gestores;

import java.util.ArrayList;

import model.dao.AsistenteDAO;
import model.dao.CampamentoDAO;
import model.dao.InscripcionDAO;
import controller.dto.camp.*;
import controller.dto.registration.*;

import java.time.Period;
import java.time.LocalDate;

/**
 * Class that manage registrations
 * @author Enrique de los Reyes Montilla
 * @author Manuel Garcia Obrero
 */
public class GestorInscripciones {
	/**
	 * Singleton private instance
	 */
	private static GestorInscripciones instance_= null;
	/**
	 * Instance access method
	 * @return GestorInscripciones Instance
	 */
	public static GestorInscripciones getInstance() {
		if(instance_ == null) {
			return new GestorInscripciones();
		}
		return instance_;
	}
	private GestorInscripciones() {}
	/**
	 * Method that returns all partial registrations
	 * @return ArrayList<RegistrationDTO>.
	 */
	public ArrayList<RegistrationDTO> getListaInscripcionParcial() {
		InscripcionDAO db = InscripcionDAO.getInstance();
		return db.readAllPartial();
	}
	/**
	 * Method that returns all full registrations
	 * @return ArrayList<RegistrationDTO>.
	 */
	public ArrayList<RegistrationDTO> getListaInscripcionCompleta() {
			InscripcionDAO db = InscripcionDAO.getInstance();
		return db.readAllComplete();
	}
	/**
	 * Method that add a new full registration
	 * @param idC Camp id
	 * @param idA Assistant id
	 * @return boolean.
	 */
	public boolean realizarRegistroCompleto(int idC, int idA){
		//Compruebo que el campamento y asistente exista y que no esté el usuario ya escrito
		CampamentoDAO dbC = CampamentoDAO.getInstance();
		AsistenteDAO dbA = AsistenteDAO.getInstance();
		InscripcionDAO dbI = InscripcionDAO.getInstance();
		CampDTO campamento;
		if((campamento = dbC.readAvailable(idC)) == null || dbA.read(idA) == null || dbI.read(idC, idA) != null)
			return false;
		//Calculo el precio
		float precio = calcularPrecioCompleto(idC);
		//Establezco la información a la inscripcion
		RegistrationDTO inscripcion = new RegistrationDTO();
		inscripcion.setIdCampamento(idC);
		inscripcion.setIdParticipante(idA);
		inscripcion.setPrecio(precio);
		inscripcion.setTipo("Completa");
		inscripcion.setFechaInscripcion(LocalDate.now());
		// Calcular la diferencia entre la fecha del campamento y la de inscripcion
        Period periodo = inscripcion.getFechaInscripcion().until(campamento.getInicioCampamento());

        // Obtener el número de días de la diferencia
        int diferenciaDias = periodo.getDays();
        
		if(diferenciaDias >= 15 )
			inscripcion.setTemporalidad("temprano");
		else if(diferenciaDias >= 2)
			inscripcion.setTemporalidad("tardio");
		else
			return false;
		
		return dbI.create(inscripcion);
	}
	/**
	 * Method that add a new partial registration
	 * @param idC Camp id
	 * @param idA Assistant id
	 * @return boolean.
	 */
	public boolean realizarRegistroParcial(int idC, int idA){
		//Compruebo que el campamento y asistente exista y que no esté el usuario ya escrito
		CampamentoDAO dbC = CampamentoDAO.getInstance();
		AsistenteDAO dbA = AsistenteDAO.getInstance();
		InscripcionDAO dbI = InscripcionDAO.getInstance();
		CampDTO campamento;
		if((campamento = dbC.readAvailable(idC)) == null || dbA.read(idA) == null || dbI.read(idC, idA) != null)
			return false;
		//Calculo el precio
		float precio = calcularPrecioParcial(idC);
		//Establezco la información a la inscripcion
		RegistrationDTO inscripcion = new RegistrationDTO();
		inscripcion.setIdCampamento(idC);
		inscripcion.setIdParticipante(idA);
		inscripcion.setPrecio(precio);
		inscripcion.setTipo("Parcial");
		inscripcion.setFechaInscripcion(LocalDate.now());
		// Calcular la diferencia entre la fecha del campamento y la de inscripcion
        Period periodo = inscripcion.getFechaInscripcion().until(campamento.getInicioCampamento());

        // Obtener el número de días de la diferencia
        int diferenciaDias = periodo.getDays();
        
        if(diferenciaDias >= 15 )
			inscripcion.setTemporalidad("temprano");
		else if(diferenciaDias >= 2)
			inscripcion.setTemporalidad("tardio");
		else
			return false;
		
		return dbI.create(inscripcion);
	}
	/**
	 * Method that calculate the price of the full registrations
	 * @param nActividades Number of activities
	 * @return float Price
	 */
	public float calcularPrecioCompleto(int idC) {
		CampamentoDAO dbC = CampamentoDAO.getInstance();
		if(dbC.readAvailable(idC) != null) {
			return 300 + 20 * dbC.readActivitiesCamp(idC).size();
		}
		else
			return -1.0;
	}
	/**
	 * Method that calculate the price of the partial registrations
	 * @param nActividades Number of activities
	 * @return float Price 
	 */
	public float calcularPrecioParcial(int idC) {
		CampamentoDAO dbC = CampamentoDAO.getInstance();
		if(dbC.readAvailable(idC) != null) {
			return 100 + 20 * dbC.readActivitiesCamp(idC).size();
		}
		else
			return -1.0;
	}
	/**
	 * Method that returns all available camps
	 * @return String List of camps
	 */
	public String obtenerCampamentosDisponibles(){
		CampamentoDAO db = CampamentoDAO.getInstance();
		ArrayList<CampDTO> disponibles = db.readAllAvailable();
		
		String listaAux = "";
		
		if(disponibles.size() > 0) 		
			for(CampDTO cam : disponibles) 
				listaAux += cam.toString();
		
		return listaAux;
	}
	/**
	 * Method that check the modality
	 * @param idC Camp ID to check
	 * @return String Modality
	 */
	public String calcularModalidad(int idC) {
		
		CampamentoDAO dbC = CampamentoDAO.getInstance();
		CampDTO c = dbC.readAvailable(idC);
		if(c != null) {
			LocalDate now = LocalDate.now());
			// Calcular la diferencia entre la fecha del campamento y la de inscripcion
	        Period periodo = now.until(c.getInicioCampamento());

	        // Obtener el número de días de la diferencia
	        int diferenciaDias = periodo.getDays();
	        
	        if(diferenciaDias >= 15 )
				return "temprano";
			else if(diferenciaDias >= 2)
				return "tardio";
			else
				return "No se puede registrar";
		}
		else 
			return "No se puede registrar";	
		
	}
	/**
	 * Delete a registration
	 * @param idCamp Camp id
	 * @param idAssistant Assistant id
	 * @return boolean True if the registration has been deleted, false otherwise
	 */
	public boolean deleteRegistration(int idCamp, int idAssistant) {
		InscripcionDAO dbI = InscripcionDAO.getInstance();
		CampamentoDAO dbC = CampamentoDAO.getInstance();
		AsistenteDAO dbA = AsistenteDAO.getInstance();
		if(dbC.read(idCamp) == null || dbA.read(idAssistant) == null || dbI.read(idCamp, idAssistant) == null)
			return false;
		
		return dbI.delete(idCamp, idAssistant);
	}
}