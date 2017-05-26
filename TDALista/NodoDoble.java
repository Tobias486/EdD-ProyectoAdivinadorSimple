package TDALista;

/**
 * Nodo que encapsula un elemento gen�rico y mantiene referencias hacia un nodo previo y otro sucesor
 *  
 * @author Tob�as Molina Blanco
 *
 * @param <E>
 */

public class NodoDoble<E> implements Position<E> {
    
    NodoDoble<E> siguiente; // Referencia al siguiente nodo
    NodoDoble<E> anterior; // Referencia al nodo anterior
    E elemento; // Elemento gen�rico de tipo E que contiene el nodo.
    
    //CONSTRUCTORES
    
    /**
     * Constructor sin par�metros formales
     */
    public NodoDoble(){
        elemento = null;
        siguiente = null;
        anterior = null;
    }
    
    /**
     * Constructor con un par�metro formal que representa el elemento que encapsula el nodo
     * @param el, elemento que encapsula el nodo
     */
    public NodoDoble(E el){
        elemento = el;
        siguiente = null;
        anterior = null;
    }
    
    /**
     * Constructor con tres par�metros formales que representan el elemento que encapsula  el nodo y los nodos adyacentes
     * @param el, elemento que encapsula el nodo
     * @param ant, referencia al nodo anterior
     * @param sig, referencia al nodo siguiente
     */
    public NodoDoble(E el, NodoDoble<E> ant, NodoDoble<E> sig){
        elemento = el;
        anterior = ant;
        siguiente = sig;
    }
    
    /**
     * Constructor con dos par�metros formales que representan los nodos adyacentes.
     * @param ant, referencia al nodo anterior
     * @param sig, referencia al nodo siguiente
     */
    public NodoDoble(NodoDoble<E> ant, NodoDoble<E> sig) {
        elemento = null;
        anterior = ant;
        siguiente = sig;
        
    }
    
    //CONSULTAS
    
    /**
     * Consulta el elemento que encapsula el nodo
     * @return elemento que encapsula el nodo
     */
    public E getElemento(){return elemento;}
    
    /**
     * Consulta el elemento que encapsula el nodo
     * @return elemento que encapsula el nodo
     */
    public E element(){return elemento;}
    
    /**
     * Consulta la referencia al nodo siguiente
     * @return referencia al nodo siguiente
     */
    public NodoDoble<E> getSiguiente(){return siguiente;}
    
    /**
     * Consulta la referencia al nodo anterior
     * @return referencia al nodo anterior
     */
    public NodoDoble<E> getAnterior(){return anterior;}
    
    //COMANDOS
    
    /**
     * Modifica la referencia al nodo siguiente
     * @param sig nueva referencia al nodo siguiente
     */
    public void setSiguiente(NodoDoble<E> sig) {siguiente = sig;}
    
    /**
     * Modifica la referencia al nodo anterior
     * @param ant nueva referencia al nodo anterior
     */
    public void setAnterior(NodoDoble<E> ant){anterior = ant;}
    
    /**
     * Modifica el elemento que encapsula el nodo
     * @param el nuevo elemento
     */
    public void setElemento(E el){elemento = el;}
    
     
}