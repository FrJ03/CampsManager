package data;
/**
 * Interfaces que los DAO deberán de implementa/Interface which the others DAO have to implement.
 * @author Enrique de los Reyes Montilla
 */
public interface InterfaceDAO<T> {
	/**
	 * Add a new object to the data base.
	 * @param object Object which will be added to the date base.
	 */
	void create(T object);
	/**
	 * Delete a object of the data base.
	 * @return T
	 */
	T delete();
	/**
	 * Read a object of the data base.
	 * @param object Object which will be read of the date base.
	 */
	void delete(T object);
}
