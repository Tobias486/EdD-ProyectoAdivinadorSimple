package TDALista;

/**
 * Interfaz para una posición de una lista
 * @author Tobías Molina Blanco
 *
 * @param <E>
 */
public interface Position<E> {
	
	/**
	 * Consulta el elemento encapsulado en la posición
	 * @return el elemento encapsulado en la posición
	 */
    public E element();
}