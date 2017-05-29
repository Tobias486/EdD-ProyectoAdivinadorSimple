package TDAPila;

/**
 * Clase que encapsula los elementos de la clase Pila
 * @author Teo Vogel
 *
 * @param <E>
 */
public class Nodo<E> implements java.io.Serializable {

	private E elem; //Elemento que encapsula el nodo
	private Nodo<E> next; //Referencia al siguiente nodo
	
	// CONSTRUCTORES
	
	/**
     * Constructor con dos parámetros formales que representan el elemento que encapsula el nodo y el nodo siguiente
	 * @param elem, elemento que encapsula el nodo
	 * @param next, referencia al siguiente nodo
	 */
	public Nodo (E elem, Nodo<E> next) {
		this.elem = elem;
		this.next = next;
	}
	
	/**
     * Constructor con un parámetro formal que representa el elemento que encapsula el nodo
	 * @param elem, elemento que elcapsula el nodo
	 */
	public Nodo (E elem) {
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
	public Nodo<E> getNext () { return next; }
	
	// COMANDOS
	
	/**
	 * Modifica el elemento encapsulado por el nodo
	 * @param elem nuevo elemento
	 */
	public void setElement (E elem) { this.elem = elem; }
	
	/**
	 * Modifica la referencia al siguiente nodo
	 * @param next nueva referencia al nodo siguiente
	 */
	public void setNext(Nodo<E> next) { this.next = next; }
	
}
