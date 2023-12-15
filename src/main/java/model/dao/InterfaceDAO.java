package model.dao;

/**
 * DAO interface
 * @author Enrique de los Reyes Montilla
 */
public interface InterfaceDAO<T> {
	/**
	 * Add a new object.
	 * @param object Objeto to add.
	 * @return boolean true if the object has been added, false otherwise
	 */
	boolean create(T object);
	/**
	 * Read an object.
	 * @param object Object that contains the object id to read.
	 * @return T Object if the object exists, null otherwise
	 */
	T read(T object);
	/**
	 * Delete an object.
	 * @param object Object to be deleted.
	 * @return boolean true if the object has been deleted
	 */
	boolean delete(T object);
}
