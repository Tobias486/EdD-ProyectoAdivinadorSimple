package TDALista;

/*
 * Estructura iterable de una lista doblemente enlazada que implementa la interfaz PositionList
 */
import java.util.Iterator;
import java.util.NoSuchElementException;
public class Lista<E> implements PositionList<E> {

    private int longitud; // Atributo que lleva la cuenta de la cantidad de elementos en la lista
    private NodoDoble<E> head; // Nodo centinela al principio de la lista
    private NodoDoble<E> tail; // Nodo centinela al final de la lista

    public Lista(){
        longitud = 0;
        head = new NodoDoble<E>();
        tail = new NodoDoble<E>();
        
        head.setSiguiente(tail);
        tail.setAnterior(head);
    }

    protected NodoDoble<E> checkPosition(Position<E> p) throws InvalidPositionException{
        if(p == null || isEmpty())
            throw new InvalidPositionException("Invalid position.");
        if(p == head)
            throw new InvalidPositionException("The header node is not a valid position.");
        if(p == tail)
            throw new InvalidPositionException("The trailer node is not a valid position.");
        
        try {
            NodoDoble<E> temp = (NodoDoble<E>) p;
            if((temp.getAnterior()) == null || temp.getSiguiente() == null)
                throw new InvalidPositionException("Position does not belong to a valid PositionList");
            return temp;
        }
        catch(ClassCastException e) {
               throw new InvalidPositionException("Position is of wrong type for this list.");
        }
    }
    
    public int size(){return longitud;}
    public boolean isEmpty(){return longitud==0;}
    
    public Position<E> first() throws EmptyListException{
        if(isEmpty()) throw new EmptyListException("The list is empty.");
        
        return head.getSiguiente();
    }
    
    public Position<E> last() throws EmptyListException{
        if(isEmpty()) throw new EmptyListException("The list is empty.");
        
        return tail.getAnterior();
    }
    
    public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
        NodoDoble<E> a = checkPosition(p);
        
        if(a.getSiguiente() == tail) throw new BoundaryViolationException("Boundary violation.");
        
        return a.getSiguiente();
    }
    public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
        NodoDoble<E> a = checkPosition(p);
        
        if(a.getAnterior() == head) throw new BoundaryViolationException("Boundary violation.");
        
        return a.getAnterior();
    }
    public void addFirst(E e){
        NodoDoble<E> p = new NodoDoble<E>(e);
        longitud++;
        p.setAnterior(head);
        p.setSiguiente(head.getSiguiente());
        head.getSiguiente().setAnterior(p);
        head.setSiguiente(p);
    }
    public void addLast(E e){
        NodoDoble<E> p = new NodoDoble<E>(e);
        longitud++;
        p.setAnterior(tail.getAnterior());
        p.setSiguiente(tail);
        tail.getAnterior().setSiguiente(p);
        tail.setAnterior(p);
    }
    public void addAfter(Position<E> p, E element) throws InvalidPositionException{
        NodoDoble<E> a = checkPosition(p);
        NodoDoble<E> insertar = new NodoDoble<E>(element);
        
        longitud++;
        insertar.setAnterior(a);
        insertar.setSiguiente(a.getSiguiente());
        a.getSiguiente().setAnterior(insertar);
        a.setSiguiente(insertar);
        
    }
    public void addBefore(Position<E> p, E element) throws InvalidPositionException{
        NodoDoble<E> a = checkPosition(p);
        NodoDoble<E> insertar = new NodoDoble<E>(element);
        
        longitud++;
        insertar.setSiguiente(a);
        insertar.setAnterior(a.getAnterior());
        a.getAnterior().setSiguiente(insertar);
        a.setAnterior(insertar);
    
    }
    public E remove(Position<E> p) throws InvalidPositionException {
        NodoDoble<E> a = checkPosition(p);
        
        longitud--;
        a.getAnterior().setSiguiente(a.getSiguiente());
        a.getSiguiente().setAnterior(a.getAnterior());
        return a.getElemento();
    }
    public E set(Position<E> p, E element) throws InvalidPositionException {
        NodoDoble<E> a = checkPosition(p);
        E devolver = a.getElemento();
       
        a.setElemento(element);
        
        return devolver;
    }
    
    public Iterator<E> iterator(){
        return new ElementIterator(this);
    }
    
    public Iterable<Position<E>> positions(){
        Lista<Position<E>> listaP= new Lista<Position<E>>();
        Position<E> meter = head;
        try{
	        int tam = 0;
	        if(!isEmpty()) {
	            meter = first();
	            listaP.addLast(meter);
	            for(int i = 1; i < size(); i++) {
	                meter = next(meter);
	                listaP.addLast(meter);
	            }
	        }
        } catch(EmptyListException e){}
        catch(BoundaryViolationException e){}
        catch(InvalidPositionException e){}
        
        return listaP;
    }
    
    
}
