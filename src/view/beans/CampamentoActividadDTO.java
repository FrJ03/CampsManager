package view.beans;

/**
 * DTO utilizado para hacer uso de los métodos del DAO CampamentoActividad.
 * @author Enrique de los Reyes Montilla
 * @author Manuel García Obrero
 * 
 */
public class CampamentoActividadDTO {
	/**
	 * Variable privada que representa el id de un campamento.
	 */
	private int campId_;
	/**
	 * Variable privada que representa el id de una actividad.
	 */
	private int actId_;
	/**
	  * Costruye un objeto con todos sus datos.
	  * @return void
	  * */
	public CampamentoActividadDTO(int actId, int campId) {
		this.campId_ = campId;
		this.actId_=actId;
	};
	/**
	 * Método que devuelve el id de un campamento.
	 * @return int
	 */
	public int getCampId() {
		return campId_;
	}
	/**
	 * Método que asigna el id de un campamento a la variable privada.
	 * @param campId Id del campamento que se va a asignar.
	 */
	public void setCampId(int camId) {
		this.campId_ = camId;
	}
	/**
	 * Método que devuelve el id de una actividad.
	 * @return int
	 */
	public int getActId() {
		return actId_;
	}
	/**
	 * Método que asigna el id de una actividad a la variable privada.
	 * @param camId Id de la actividad que se va a asignar.
	 */
	public void setActId(int actId) {
		this.actId_ = actId;
	}	
	
}
