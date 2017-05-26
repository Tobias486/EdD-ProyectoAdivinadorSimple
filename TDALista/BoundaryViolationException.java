package TDALista;

/**
 * Excepción lanzada cuando se invoca un método con una posición pasada por parámetro con la que el método tiene conflicto
 * @author Tobías Molina Blanco
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
