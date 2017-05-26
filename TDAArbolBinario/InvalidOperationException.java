package TDAArbolBinario;

/**
 * Excepci�n lanzada si el m�todo no es permitido (addRoot existiendo la ra�z, addLeft existiendo el hijo izquierdo, addRight existiendo el hijo derecho, remove en un nodo con dos hijos)
 * @author Teo Vogel
 *
 */

public class InvalidOperationException extends Exception {

	/**
	 * Constructor vac�o
	 */
	public InvalidOperationException () {
		super();
	}
	
}
