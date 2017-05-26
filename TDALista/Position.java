package TDALista;

/**
 * Interfaz para una posici�n de una lista
 * @author Tob�as Molina Blanco
 *
 * @param <E>
 */
public interface Position<E> {
	
	/**
	 * Consulta el elemento encapsulado en la posici�n
	 * @return el elemento encapsulado en la posici�n
	 */
    public E element();
}