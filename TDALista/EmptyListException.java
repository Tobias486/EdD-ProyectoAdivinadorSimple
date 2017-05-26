package TDALista;

/**
 * Excepción lanzada cuando la lista está vacía
 * @author Tobías Molina Blanco
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
