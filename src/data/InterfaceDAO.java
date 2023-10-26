package data;



/**
 * Interfaces que los DAO deberán de implementa
 * @author Enrique de los Reyes Montilla
 */
public interface InterfaceDAO<T> {
	/**
	 * Añade un nuevo objeto a la base de datos.
	 * @param object Objeto el cual se va a añadir a la base de datos.
	 * @return int
	 */
	int create(T object);
	/**
	 * Lee un objeto de la base de datos.
	 * @param id Id del objeto que se va a leer de la base de datos.
	 * @return T
	 */
	T read(int id);
	/**
	 * Elimina un objeto de la base de datos.
	 * @param object Objeto el cual se va a eliminar de la base de datos.
	 * @return int
	 */
	int delete(T object);
}
