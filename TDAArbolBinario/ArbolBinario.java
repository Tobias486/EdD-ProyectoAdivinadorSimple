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
	
	public Position<E> left(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		BTNodo<E> ver = checkPosition(v);
		if(ver.getLeft() == null)
			throw new BoundaryViolationException();
		return ver.getLeft();
    	}
    
	public Position<E> right(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		BTNodo<E> ver = checkPosition(v);
		if(ver.getRight() == null)
			throw new BoundaryViolationException();
		return ver.getRight();
	}
	
	public boolean hasLeft(Position<E> v) throws InvalidPositionException {
		BTNodo<E> ver = checkPosition(v);
		return ver.getLeft() != null;
	}	

	public boolean hasRight(Position<E> v) throws InvalidPositionException {
		BTNodo<E> ver = checkPosition(v);
		return ver.getRight() != null;
	}

	public Position<E> addLeft(Position<E> v, E r) throws InvalidOperationException, InvalidPositionException {
		BTNodo<E> ver = checkPosition(v);
		if(ver.getLeft() != null)
			throw new InvalidOperationException();
		ver.setLeft(new BTNodo<E>(r, ver));
		size++;
		return ver.getLeft();
	}

	public Position<E> addRight(Position<E> v, E r) throws InvalidOperationException, InvalidPositionException {
		BTNodo<E> ver = checkPosition(v);
		if(ver.getRight() != null)
		    throw new InvalidOperationException();
		ver.setRight(new BTNodo<E>(r, ver));
		size++;
		return ver.getRight();
	}

	public void Attach(Position<E> v, BinaryTree<E> T1, BinaryTree<E> T2) throws InvalidPositionException {
		BTNodo<E> ver = checkPosition(v);
		BTNodo<E> meter;
		try {
			if(!isExternal(ver))
				throw new InvalidPositionException();
			if(!T1.isEmpty()){
				ver.setLeft((BTNodo<E>) T1.root());
				meter = (BTNodo<E>) T1.root(); meter.setParent(ver);
			}
			if(!T2.isEmpty()){
				ver.setRight((BTNodo<E>) T2.root());
				meter = (BTNodo<E>) T2.root(); meter.setParent(ver);
			}
		} 
		catch(ClassCastException e) {throw new InvalidPositionException();}
		catch(EmptyTreeException e){};
		size+= T1.size() + T2.size();
	}
	
	public Iterator<E> iterator(){
		return new BTIterator<E>(this);
	}

	private void DFS(Position<E> v, Lista<Position<E>> a) throws InvalidPositionException{
		a.addLast(v);
		try{
			if(hasLeft(v)) DFS(right(v),a);
			if(hasRight(v)) DFS(left(v),a);
	  	}
		catch(BoundaryViolationException e){}
	}

	public Iterable<Position<E>> children(Position<E> v)throws InvalidPositionException{
		BTNodo poner = checkPosition(v);
		Lista<Position<E>> a = new Lista<Position<E>>();
		a.addLast(poner);
		DFS(v,a);
		return a;
	}
	public Iterable<Position<E>> positions(){
		Lista<Position<E>> list = new Lista<Position<E>>();
		Position<E> a;
		try{if(!isEmpty()){ a = root(); DFS(a,list);}}
		catch(EmptyTreeException e) {}
		catch(InvalidPositionException e){}

		return list;
	}  
	
}
