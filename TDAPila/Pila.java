//TODO !!!! USAR LAS INTERFACES PROVISTAS!

//Ejemplo de JavaDoc para interfaz:

/**
 * Interfaz de Pila
 * @author Teo Vogel
 *
 * @param <E>
 */

//La implementación hereda los JavaDoc, no reescribir
//Hacer JavaDoc para clases que no implementan JavaDoc, los contstructores de las que las implementan

public class Pila<E> implements Stack<E> {

	private Node<E> head; //Referencia al elemento en el tope de la pila
	private int size; //Cantidad de elementos en la pila

	//TODO: CONSTRUCTOR NO NECESARIO, LO AGREGO?

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
		Node<E> nuevo = new Node<E>(elem, head);
		head = nuevo;
		size++;
	}
	
}
