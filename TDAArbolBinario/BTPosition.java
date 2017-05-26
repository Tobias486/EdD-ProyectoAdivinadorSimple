package TDAArbolBinario;

/**
 * Interfaz BTPosition para TDAArbolBinario
 * @author Tobías Molina Blanco, Teo Vogel
 *
 * @param <E>
 */

public interface BTPosition<E> extends Position<E> {

	/**
	 * Consulta el padre de la posición
	 * @return el padre de la posición
	 */
	public BTPosition<E> getParent();
	
	/**
	 * Consulta el hijo izquierdo de la posición
	 * @return el hijo izquierdo de la posición
	 */
	public BTPosition<E> getLeft();
	
	/**
	 * Consulta el hijo derecho de la posición
	 * @return el hijo derecho de la posición
	 */
	public BTPosition<E> getRight();
	
	/**
	 * Modifica el padre de la posición
	 * @param parent, el nuevo padre
	 */
	public void setParent(BTPosition<E> parent); 

	/**
	 * Modifica el hijo izquierdo de la posición
	 * @param left, el nuevo hijo izquierdo
	 */
	public void setLeft(BTPosition<E> left);

	/**
	 * Modifica el hijo derecho de la posición
	 * @param left, el nuevo hijo derecho
	 */
	public void setRight(BTPosition<E> right);
	
}
