package TDAArbolBinario;

/**
 * Clase que implementa la interfaz BTNodo de Arbol Binaro
 * @author Tobías Molina Blanco, Teo Vogel
 * @param <E>
 */

public class BTNodo<E> implements Position<E>, java.io.Serializable {

	private BTNodo<E> parent, left, right; //referencias al nodo padre y nodos hijos
	private E elem; //elemento encapsulado por el nodo
	
	/**
	 * Constructor que recibe el elemento encapsulado por el nodo y las referencias al padre e hijos del nodo
	 * @param elem elemento encapsulado por el nodo
	 * @param parent referencia al nodo padre
	 * @param left referencia al nodo hijo izquierdo
	 * @param right referencia al nodo hijo derecho
	 */
	public BTNodo (E elem, BTNodo<E> parent, BTNodo<E> left, BTNodo<E> right) {
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
	public BTNodo(E elem, BTNodo<E> parent){
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
	public BTNodo<E> getParent () { return parent; }
	
	/**
	 * Consulta el nodo hijo izquierdo
	 * @return referencia al nodo hijo izquierdo
	 */
	public BTNodo<E> getLeft () { return left; }
	
	/**
	 * Consulta el nodo hijo derecho
	 * @return referencia al nodo hijo derecho
	 */
	public BTNodo<E> getRight () { return right; }
	
	/**
	 * Modifica el elemento encapsulado por el nodo
	 * @param elem nuevo elemento
	 */
	public void setElement (E elem) { this.elem = elem; }
	
	/**
	 * Modifica (cambia) el nodo padre
	 * @param parent referencia al nuevo padre
	 */
	public void setParent (BTNodo<E> parent) { this.parent = parent; }
	
	/**
	 * Modifica (cambia) el nodo hijo izquierdo
	 * @param left referencia al hijo izquierdo
	 */
	public void setLeft (BTNodo<E> left) { this.left = left; }

	/**
	 * Modifica (cambia) el nodo hijo derecho
	 * @param left referencia al hijo derecho
	 */
	public void setRight (BTNodo<E> right) { this.right = right; }
	
	public String toString () {
		return elem.toString();
	}
	
}
