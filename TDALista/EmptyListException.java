package TDALista;

/**
 * Excepci�n lanzada cuando la lista est� vac�a
 * @author Tob�as Molina Blanco
 * 
 */
public class EmptyListException extends Exception {
	
	/**
	 * Constructor que recibe una cadena de caracteres que representa el mensaje de error
	 * @param s cadena de caracteres que representa el mensaje de error
	 */
    public EmptyListException(String s){
        System.out.println(s);
    }
}
