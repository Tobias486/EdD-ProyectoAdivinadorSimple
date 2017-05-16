/*
 * Iterador que implementa la interfaz Iterator que se encarga de recorrer una lista
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ElementIterator<E> implements Iterator<E> {
    
    Position<E> cursor; // Cursor que indica la siguiente posición a devolver
    PositionList<E> lista; // Lista que se recorre
    
    
    // Constructor que recibe la lista a recorrer.
    public ElementIterator(PositionList<E> l){
        lista = l;
        if (lista.isEmpty()) cursor = null;
        else {
            try{ 
                cursor = lista.first();
            }
            catch(EmptyListException e){}
        }
    }
    
    // Consulta que returna verdadero si la lista no fue recorrida en su totalidad o falso en caso contrario
    public boolean hasNext(){
        return (cursor != null);
    }
    
    // Consulta que devuelve el siguiente elemento en la lista
    // Lanza una excepción de tipo NoSuchElementException si no quedan elementos que devolver
    public E next() throws NoSuchElementException{
        if(cursor == null)
            throw new NoSuchElementException("No next element.");
        E devolver = cursor.element();
        try {
            if(cursor == lista.last())
                cursor = null;
            else
                cursor = lista.next(cursor);
        }
        catch(InvalidPositionException e) {throw new NoSuchElementException("No next element.");}
        catch(BoundaryViolationException e){throw new NoSuchElementException("No next element.");}
        
        return devolver;
    }

}
