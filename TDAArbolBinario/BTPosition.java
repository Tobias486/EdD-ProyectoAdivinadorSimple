package TDAArbolBinario;

/**
 * Interfaz BTPosition para TDAArbolBinario
 * @author Tob�as Molina Blanco, Teo Vogel
 *
 * @param <E>
 */

public interface BTPosition<E> extends Position<E> {

	/**
	 * Devuelve el padre de la posici�n
	 * @return el padre de la posici�n
	 */
	public BTPosition<E> getParent();
	
	/**
	 * Devuelve el hijo izquierdo de la posici�n
	 * @return el hijo izquierdo de la posici�n
	 */
	public BTPosition<E> getLeft();
	
	/**
	 * Devuelve el hijo derecho de la posici�n
	 * @return el hijo derecho de la posici�n
	 */
	public BTPosition<E> getRight();
	
	/**
	 * Modifica el padre de la posici�n
	 * @param parent, el nuevo padre
	 */
	public void setParent(BTPosition<E> parent); 

	/**
	 * Modifica el hijo izquierdo de la posici�n
	 * @param left, el nuevo hijo izquierdo
	 */
	public void setLeft(BTPosition<E> left);

	/**
	 * Modifica el hijo derecho de la posici�n
	 * @param left, el nuevo hijo derecho
	 */
	public void setRight(BTPosition<E> right);
	
}
