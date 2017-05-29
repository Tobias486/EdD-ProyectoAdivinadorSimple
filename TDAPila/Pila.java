package TDAPila;

/**
 * Implementación de la interfaz Stack
 * @author Teo Vogel
 *
 * @param <E>
 */

public class Pila<E> implements Stack<E>, java.io.Serializable {

	private Nodo<E> head; //Referencia al elemento en el tope de la pila
	private int size; //Cantidad de elementos en la pila

	/**
	 * Constructor vacío
	 */
	public Pila () {
		head = null;
		size = 0;
	}

	public int size () { 
		return size; 
	}
	
	public boolean isEmpty () { 
		return size == 0; 
	}

	public E top () throws EmptyStackException {
		if (isEmpty())
			throw new EmptyStackException();
		return head.getElement();
	}

	public E pop () throws EmptyStackException {
		if (isEmpty())
			throw new EmptyStackException();
		E result = head.getElement();
		head = head.getNext();
		size--;
		return result;
	}

	public void push (E elem) {
		Nodo<E> nuevo = new Nodo<E>(elem, head);
		head = nuevo;
		size++;
	}
	
}
