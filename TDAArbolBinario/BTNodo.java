package TDAArbolBinario;

/**
 * Clase que implementa la interfaz BTPosition de Arbol Binaro
 * @author Tobías Molina Blanco, Teo Vogel
 * @param <E>
 */

public class BTNodo<E> implements BTPosition<E> {

	private BTPosition<E> parent, left, right; //referencias al nodo padre y nodos hijos
	private E elem; //elemento encapsulado por el nodo
	
	public BTNodo (E elem, BTPosition<E> parent, BTPosition<E> left, BTPosition<E> right) {
		this.elem = elem;
		this.parent = parent;
		this.left = left;
		this.right = right;
	}
	
	public BTNodo (E elem) {
		this.elem = elem;
		parent = null;
		left = null;
		right = null;
	}
	
	public BTNodo () {
		elem = null;
		parent = null;
		left = null;
		right = null;
	}
	
	public E element () { return elem; }
	
	/**
	 * Consulta el Nodo padre
	 * @return Nodo padre
	 */
	public BTPosition<E> getParent () { return parent; }
	
	/**
	 * Consulta el Nodo hijo izquierdo
	 * @return Nodo hijo izquierdo
	 */
	public BTPosition<E> getLeft () { return left; }
	
	/**
	 * Consulta el Nodo
	 * @return
	 */
	public BTPosition<E> getRight () { return right; }
	
	public void setElement (E elem) { this.elem = elem; }
	
	public void setParent (BTPosition<E> parent) { this.parent = parent; }
	
	public void setLeft (BTPosition<E> left) { this.left = left; }

	public void setRight (BTPosition<E> right) { this.right = right; }
	
}
