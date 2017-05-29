package TDALista;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterador que implementa la interfaz Iterator que se encarga de recorrer una lista
 * @author Tobías Molina Blanco
 *
 * @param <E>
 */

public class ElementIterator<E> implements Iterator<E>, java.io.Serializable {
    
    Position<E> cursor; // Cursor que indica la siguiente posiciÃ³n a devolver
    PositionList<E> lista; // Lista que se recorre
    
    
    /**
     * Constructor que recibe la lista a recorrer.
     * @param l lista a recorrer
     */
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
    
    /**
     * Consulta si la lista fue recorrida en su totalidad
     * @return verdadero si la lista no fue recorrida en su totalidad, falso en caso contrario
     */
    public boolean hasNext(){
        return (cursor != null);
    }
    
    /**
     * Consulta el siguiente elemento en la lista
     * @return el siguiente elemento en la lista
     * @throws NoSuchElementException si no quedan elementos que devolver
     */
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
        catch(EmptyListException e){throw new NoSuchElementException("No next element.");}
        
        return devolver;
    }

}
