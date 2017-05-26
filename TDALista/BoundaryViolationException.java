package TDALista;

/**
 * Excepci�n lanzada cuando se invoca un m�todo con una posici�n pasada por par�metro con la que el m�todo tiene conflicto
 * @author Tob�as Molina Blanco
 * 
 */
public class BoundaryViolationException extends Exception {
	
	/**
	 * Constructor que recibe una cadena de caracteres que representa el mensaje de error
	 * @param s cadena de caracteres que representa el mensaje de error
	 */
    public BoundaryViolationException(String s){
        System.out.println(s);
    }
}
