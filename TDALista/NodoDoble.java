/*
 * Nodo que contiene un elemento genérico y mantiene referencias hacia un nodo previo y otro sucesor.
 */
public class NodoDoble<E> implements Position<E> {
    
    NodoDoble<E> siguiente; // Referencia al siguiente nodo
    NodoDoble<E> anterior; // Referencia al nodo anterior
    E elemento; // Elemento genérico de tipo E que contiene el nodo.
    
    //CONSTRUCTORES
    
    //Constructor sin parámetros formales
    public NodoDoble(){
        elemento = null;
        siguiente = null;
        anterior = null;
    }
    
    //Constructor con un parámetro formal que representa el elemento que contiene el nodo
    public NodoDoble(E el){
        elemento = el;
        siguiente = null;
        anterior = null;
    }
    
    //Constructor con tre parámetros formales que representan el elemento que contiene el nodo y los nodos adyacentes
    public NodoDoble(E el, NodoDoble<E> ant, NodoDoble<E> sig){
        elemento = el;
        anterior = ant;
        siguiente = sig;
    }
    
    //Constructor con tres parámetros formales que representan los nodos adyacentes.
    public NodoDoble(NodoDoble<E> ant, NodoDoble<E> sig) {
        elemento = null;
        anterior = ant;
        siguiente = sig;
        
    }
    //CONSULTAS
    public E getElemento(){return elemento;}
    public E element(){return elemento;}
    public NodoDoble<E> getSiguiente(){return siguiente;}
    public NodoDoble<E> getAnterior(){return anterior;}
    
    //COMANDOS
    public void setSiguiente(NodoDoble<E> sig) {siguiente = sig;}
    public void setAnterior(NodoDoble<E> ant){anterior = ant;}
    public void setElemento(E el){elemento = el;}
    
     
}