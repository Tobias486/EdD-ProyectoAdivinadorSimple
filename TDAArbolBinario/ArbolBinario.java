package TDAArbolBinario;

import java.util.Iterator;
import TDALista.Lista;

// TODO: cambiar todos los tipos estáticos del TDA por BTNodo o BTPosition (checkPosition debe retornar el tipo idéntico)
// TODO: comentar los métodos privados restantes (luego les doy forma de JavaDoc)

/**
 * Estructura iterable de un árbol que implementa la interfaz BinaryTree
 * @author Tobías Molina Blanco, Teo Vogel
 *
 * @param <E>
 */

public class ArbolBinario<E> implements BinaryTree<E> {

	private BTNodo<E> root; //referencia a la raíz del árbol
	private int size; //cantidad de nodos del árbol
	
	/**
	 * Constructor vacío
	 */
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
	
    /**
     * Verifica que la posición pasada por prámetro no sea nula y del tipo correcto (además de verificar que el árbol no esté vacío)
     * @param p, posición a verificar
     * @return el nodo al cual apuntaba la posición
     * @throws InvalidPositionException si la posición no cumple con las mencionadas condiciones
     */
	private BTNodo<E> checkPosition(Position<E> p) throws InvalidPositionException {
		// if(p == null || isEmpty())
		if (p == null || isEmpty())
			throw new InvalidPositionException();
		try {
			return (BTNodo<E>) p;
		} catch (ClassCastException e) { throw new InvalidPositionException(); }
	}
	
	public Position<E> createRoot (E e) throws InvalidOperationException {
		if (!isEmpty())
			throw new InvalidOperationException();
		root = new BTNodo<E>(e);
		size++;
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
		BTPosition<E> hijo = (aux.getLeft() != null) ? aux.getLeft() : aux.getRight();
		
		if(aux == root && hijo == null)
			root = null;
		
		if(aux == root && hijo != null){
			root = (BTNodo<E>) hijo;
			hijo.setParent(null);
		}
		if(aux != root && hijo == null){
			if(aux.getParent().getLeft() == aux)
				aux.getParent().setLeft(null);
			if(aux.getParent().getRight() == aux)
				aux.getParent().setRight(null);
		}
		if(aux != root && hijo != null){
			hijo.setParent(aux.getParent());
			if(aux.getParent().getLeft() == aux)
				aux.getParent().setLeft(null);
			if(aux.getParent().getRight() == aux)
				aux.getParent().setRight(null);
		}
		
		
		size--;
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

	private BTPosition<E> clonePosition(BTPosition<E> p){
        return new BTNodo<E>(p.element());
    }
    
    private BTPosition<E> cloneSubtree(BTPosition<E> raiz, BinaryTree<E> T)throws InvalidPositionException{
        BTPosition<E> clon = clonePosition(raiz);
        try {
            if(T.hasLeft(raiz)) {
                clon.setLeft(cloneSubtree(checkPosition(T.left(raiz)),T));
                clon.getLeft().setParent(clon);
            }
            if(T.hasRight(raiz)){
                clon.setRight(cloneSubtree(checkPosition(T.right(raiz)),T));
                clon.getRight().setParent(clon);
            }
        }
        catch(BoundaryViolationException e){}
        return clon;
    }
    
    public void Attach(Position<E> v, BinaryTree<E> T1, BinaryTree<E> T2) throws InvalidPositionException {
          BTPosition<E> ver = checkPosition(v);
          BTPosition<E> meter;
          
          try {
          if(!isExternal(ver))
               throw new InvalidPositionException();
          if(!T1.isEmpty()){
               meter = cloneSubtree(checkPosition(T1.root()),T1);
               ver.setLeft(meter);
               meter.setParent(ver);
          }
          if(!T2.isEmpty()){
               meter = cloneSubtree(checkPosition(T2.root()),T2); 
               ver.setRight(meter);
               meter.setParent(ver);
          }
       } 
       catch(ClassCastException e) {throw new InvalidPositionException();}
       catch(EmptyTreeException e){}
       size+= T1.size() + T2.size();
    }
    public Iterator<E> iterator(){
        return new BTIterator<E>(this);
    }
    
    private void DFS(Position<E> v, Lista<Position<E>> a) throws InvalidPositionException{
        a.addLast(v);
        try{
           if(hasLeft(v)) DFS(left(v),a);
           if(hasRight(v)) DFS(right(v),a);
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
       try{
           if(!isEmpty()){
               a = root(); 
               DFS(a,list);
            }
        }
       catch(EmptyTreeException e) {}
       catch(InvalidPositionException e){}
       
       return list;
    }  
}
