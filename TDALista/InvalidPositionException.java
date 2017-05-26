package TDALista;


/**
 * Excepci�n lanzada cuando la posici�n pasada por par�metro no es una posici�n v�lida
 * @author Tob�as Molina Blanco
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
