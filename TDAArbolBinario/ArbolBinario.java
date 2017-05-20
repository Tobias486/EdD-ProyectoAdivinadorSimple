package TDAArbolBinario;

// TODO: por qué usamos BTPosition??? Los tipos estáticos en ArbolBinario deben ser BTPosition o BTNodo?

public class ArbolBinario<E> implements BinaryTree<E> {

	private BTNodo<E> root;
	private int size;
	
	public ArbolBinario () {
		root = null;
		size = 0;
	}
	
	public int size () { return size; }
	
	public boolean isEmpty () { return size == 0; }
	
	public Position<E> root () throws EmptyTreeException {
		if (isEmpty())
			throw new EmptyTreeException();
		return root;			
	}
	
	public Position<E> parent (Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		BTNodo<E> aux = checkPosition(p);
		if (aux == root) 
			throw new BoundaryViolationException();
		return aux.getParent();
	}
	
	public boolean isInternal (Position<E> p) throws InvalidPositionException {
		BTNodo<E> aux = checkPosition(p);
		return aux.getLeft() != null || aux.getRight() != null;
	}

	public boolean isExternal (Position<E> p) throws InvalidPositionException {
		BTNodo<E> aux = checkPosition(p);
		return aux.getLeft() == null && aux.getRight() == null;
	}	
	
	public boolean isRoot (Position<E> p) throws InvalidPositionException {
		BTNodo<E> aux = checkPosition(p);
		return aux == root;
	}
	
	private BTNodo<E> checkPosition(Position<E> p) throws InvalidPositionException {
		if (p == null) //TODO: chequear lista vacía?
			throw new InvalidPositionException();
		try {
			return (BTNodo<E>) p;
		} catch (ClassCastException e) { throw new InvalidPositionException(); }
	}
	
	public Position<E> createRoot (E e) throws InvalidOperationException {
		if (!isEmpty())
			throw new InvalidOperationException();
		root = new BTNodo<E>(e);
		return root;
	}
	
	public E replace (Position<E> p, E e) throws InvalidPositionException {
		BTNodo<E> aux = checkPosition(p);
		E result = aux.element();
		aux.setElement(e);
		return result;
	}
	
	public E remove (Position<E> p) throws InvalidOperationException, InvalidPositionException {
		BTNodo<E> aux = checkPosition(p);
		E result = aux.element();
		if (aux.getLeft() != null && aux.getRight() != null)
			throw new InvalidOperationException();
		
		BTPosition<E> padre = aux.getParent();
		BTPosition<E> hijo = aux.getLeft() != null ? aux.getLeft() : aux.getRight();
		
		if (padre.getLeft() == aux)
			padre.setLeft(hijo);
		else
			padre.setRight(hijo);
		hijo.setParent(padre);
		
		return result;
	}
	
}
