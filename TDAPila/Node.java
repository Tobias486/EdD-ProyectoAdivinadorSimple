package TDAPila;

/**
 * Clase que encapsula los elementos de la clase Pila
 * @author Teo Vogel
 *
 * @param <E>
 */
public class Node<E> {

	private E elem; //Elemento que encapsula el nodo
	private Node<E> next; //Referencia al siguiente nodo
	
	// CONSTRUCTORES
	
	public Node (E elem, Node<E> next) {
		this.elem = elem;
		this.next = next;
	}
	
	public Node (E elem) {
		this.elem = elem;
	}
	
	// CONSULTAS

	/**
	 * Consulta el elemento encapsulado
	 * @return elemento que encapsula el nodo
	 */
	public E getElement () { return elem; }
	
	/**
	 * Consulta el nodo al cual está apuntando
	 * @return nodo siguiente
	 */
	public Node<E> getNext () { return next; }
	
	// COMANDOS
	
	/**
	 * Setea el elemento encapsulado
	 * @param elem Elemento a setear
	 */
	public void setElement (E elem) { this.elem = elem; }
	
	/**
	 * Setea el nodo al cual está apuntando
	 * @param next Nodo a setear
	 */
	public void setNext(Node<E> next) { this.next = next; }
	
}
