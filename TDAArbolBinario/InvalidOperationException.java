package TDAArbolBinario;

/**
 * Excepción lanzada si el método no es permitido (addRoot existiendo la raíz, addLeft existiendo el hijo izquierdo, addRight existiendo el hijo derecho, remove en un nodo con dos hijos)
 * @author Teo Vogel
 *
 */

public class InvalidOperationException extends Exception {

	/**
	 * Constructor vacío
	 */
	public InvalidOperationException () {
		super();
	}
	
}
