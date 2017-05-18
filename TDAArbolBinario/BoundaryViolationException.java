package TDAArbolBinario;

/**
 * Excepción lanzada si la posición pasada por parámetro es la raíz del arbol y se le pide el padre
 * @author Teo Vogel
 *
 */

public class BoundaryViolationException extends Exception { 
    
	public BoundaryViolationException() {
        super();
    }
	
}