package data;

/**
 * DTO utilizado para hacer uso de los métodos del DAO CampamentoActividad.
 * @author Enrique de los Reyes Montilla
 */
public class CampamentoActividadDTO {
	/**
	 * Variable privada que representa el id de un campamento.
	 */
	private int camId_;
	/**
	 * Variable privada que representa el id de una actividad.
	 */
	private int actId_;
	/**
	 * Método que devuelve el id de un campamento.
	 * @return int
	 */
	public int getCamId() {
		return camId_;
	}
	/**
	 * Método que asigna el id de un campamento a la variable privada.
	 * @param camId Id del campamento que se va a asignar.
	 */
	public void setCamId(int camId) {
		this.camId_ = camId;
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
