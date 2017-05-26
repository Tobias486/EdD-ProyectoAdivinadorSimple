package TDALista;


/**
 * Excepción lanzada cuando la posición pasada por parámetro no es una posición válida
 * @author Tobías Molina Blanco
 * 
 */
public class InvalidPositionException extends Exception{
	
	/**
	 * Constructor que recibe una cadena de caracteres que representa el mensaje de error
	 * @param s cadena de caracteres que representa el mensaje de error
	 */
    public InvalidPositionException(String s){
        System.out.println(s);
    }
}
