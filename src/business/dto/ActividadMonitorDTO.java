package business.dto;

/**
 * DTO utilizado para hacer uso de los métodos del DAO ActividadMonitor.
 * @author Manuel García Obrero
 */
public class ActividadMonitorDTO {
	/**
	 * Variable privada que representa el id de un Monitor.
	 */
	private int monId_;
	/**
	 * Variable privada que representa el id de una actividad.
	 */
	private int actId_;
	/**
	  * Costruye un objeto con todos sus datos.
	  * @return void
	  * */
	public ActividadMonitorDTO(int actId, int monId) {
		this.monId_ = monId;
		this.actId_=actId;
	};
	/**
	 * Método que devuelve el id de un Monitor.
	 * @return int
	 */
	
	public int getMonId() {
		return monId_;
	}
	/**
	 * Método que asigna el id de un Monitor a la variable privada.
	 * @param monId Id del Monitor que se va a asignar.
	 */
	public void setMonId(int monId) {
		this.monId_ = monId;
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
	 * @param actId Id de la actividad que se va a asignar.
	 */
	public void setActId(int actId) {
		this.actId_ = actId;
	}	

}
