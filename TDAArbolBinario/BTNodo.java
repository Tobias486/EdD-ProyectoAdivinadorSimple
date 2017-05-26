package TDAArbolBinario;

/**
 * Clase que implementa la interfaz BTPosition de Arbol Binaro
 * @author Tobías Molina Blanco, Teo Vogel
 * @param <E>
 */

public class BTNodo<E> implements BTPosition<E> {

	private BTPosition<E> parent, left, right; //referencias al nodo padre y nodos hijos
	private E elem; //elemento encapsulado por el nodo
	
	/**
	 * Constructor que recibe el elemento encapsulado por el nodo y las referencias al padre e hijos del nodo
	 * @param elem elemento encapsulado por el nodo
	 * @param parent referencia al nodo padre
	 * @param left referencia al nodo hijo izquierdo
	 * @param right referencia al nodo hijo derecho
	 */
	public BTNodo (E elem, BTPosition<E> parent, BTPosition<E> left, BTPosition<E> right) {
		this.elem = elem;
		this.parent = parent;
		this.left = left;
		this.right = right;
	}
	
	/**
	 * Constructor que recibe el elemento encapsulado por el nodo y la referencias al padre del nodo
	 * @param elem elemento encapsulado por el nodo
	 * @param parent referencia al nodo padre
	 */
	public BTNodo(E elem, BTPosition<E> parent){
		this.elem = elem;
		this.parent = parent;
		this.left = null;
		this.right = null;
	}

	/**
	 * Constructor que recibe el elemento encapsulado por el nodo
	 * @param elem elemento encapsulado por el nodo
	 */
	public BTNodo (E elem) {
		this.elem = elem;
		parent = null;
		left = null;
		right = null;
	}
	
	/**
	 * Constructor vacío
	 */
	public BTNodo () {
		elem = null;
		parent = null;
		left = null;
		right = null;
	}
	
	/**
	 * Consulta el elemento encapsulado por el nodo
	 * @return elemento encapsulado por el nodo
	 */
	public E element () { return elem; }
	
	/**
	 * Consulta el nodo padre
	 * @return referencia al nodo padre
	 */
	public BTPosition<E> getParent () { return parent; }
	
	/**
	 * Consulta el nodo hijo izquierdo
	 * @return referencia al nodo hijo izquierdo
	 */
	public BTPosition<E> getLeft () { return left; }
	
	/**
	 * Consulta el nodo hijo derecho
	 * @return referencia al nodo hijo derecho
	 */
	public BTPosition<E> getRight () { return right; }
	
	/**
	 * Modifica el elemento encapsulado por el nodo
	 * @param elem nuevo elemento
	 */
	public void setElement (E elem) { this.elem = elem; }
	
	/**
	 * Modifica (cambia) el nodo padre
	 * @param parent referencia al nuevo padre
	 */
	public void setParent (BTPosition<E> parent) { this.parent = parent; }
	
	/**
	 * Modifica (cambia) el nodo hijo izquierdo
	 * @param left referencia al hijo izquierdo
	 */
	public void setLeft (BTPosition<E> left) { this.left = left; }

	/**
	 * Modifica (cambia) el nodo hijo derecho
	 * @param left referencia al hijo derecho
	 */
	public void setRight (BTPosition<E> right) { this.right = right; }
	
}
