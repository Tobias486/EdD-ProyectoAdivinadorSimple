package TDAArbolBinario;

import java.util.Iterator;
import java.util.NoSuchElementException;

import TDAPila.*;

public class BTIterator<E> implements Iterator<E> {
    Stack<Position<E>> p;
    ArbolBinario<E> arbol;
    Position<E> cursor;
    
    
    public BTIterator(ArbolBinario<E> arbol){
        p = new Pila<Position<E>>();
        this.arbol = arbol;
        p.push(null);
        try {cursor = arbol.root();}
        catch(EmptyTreeException e) {cursor = null;}
    }
    // hasLeft(Position<E> v)
    private void pushChildren(){
        try{
        if(arbol.hasRight(cursor))
            p.push(arbol.right(cursor));
        if(arbol.hasLeft(cursor))
            p.push(arbol.left(cursor));
        }
        catch (InvalidPositionException e) {}
        catch (BoundaryViolationException e){}
    }
    
    public boolean hasNext(){
        return cursor != null;
    }
    public E next() throws NoSuchElementException {
        if(!hasNext())
            throw new NoSuchElementException("No next element");
        E devolver;
        devolver = cursor.element();
        pushChildren();
        try {
			cursor = p.pop();
		} catch (EmptyStackException e) {throw new NoSuchElementException("No next element");}
        return devolver;
    }
}