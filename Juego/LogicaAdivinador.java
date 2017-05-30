package Juego;

import java.io.*;
import TDAArbolBinario.*;
import TDALista.Lista;
import TDALista.PositionList;
import TDAPila.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LogicaAdivinador {
    BinaryTree<String> arbol; //estructura de datos que almacena la información de la partida
    Position<String> pp; //posición en la que se encuentra la partida
    
    int objetos; //cantidad de objetos en la partida
    int preguntas; //cantidad de preguntas en la partida
    
    /**
     * Contructor vacío
     */
    public LogicaAdivinador(){
        arbol = new ArbolBinario<String>();
        objetos = 1;
        preguntas = 0;
        try {
            pp = arbol.createRoot("una guitarra");
        } catch (InvalidOperationException e){}
    }
    
    /**
     * Cambia la posición de la partida a la siguiente pregunta dependiendo del dato pasado por parámetro
     * @param resp de tipo boolean, falso indica que el usuario respondió por "No" y debe ir a la posición correspondiente, true para el caso contrario
     * @throws BoundaryViolationException cuando se llama a este método con la partida habiendo llegado al punto de adivinar
     */
    public void siguientePregunta(boolean resp) throws BoundaryViolationException{
        try {
            if(resp)
               pp = arbol.right(pp);
            else
               pp = arbol.left(pp);
        }
        catch(InvalidPositionException e){
        	System.out.println("No hay siguiente pregunta!");
        }
    
    }
    
    /**
     * Consulta si la partida está en una posición donde hay una siguiente pregunta por hacer
     * @return true si hay una siguiente pregunta por hacer, false si no hay más preguntas y solo queda adivinar
     */
    public boolean haySiguientePregunta(){
        boolean hay = false;
        try{
            if(!arbol.isExternal(pp))
                hay = true;
        }
        catch (InvalidPositionException e){}
        return hay;
        
            
    }
    
    /**
     * Reinicia la partida a la primer posición
     */
    public void reiniciar(){
        try{
            pp = arbol.root();
        }
        catch(EmptyTreeException e){}
    }
    
    /**
     * Consulta la pregunta (el instrumento) en donde se encuentra la partida 
     * @return la pregunta en forma de String. Si la partida está en una posición donde solo queda adivinar el método devuelve el instrumento en un intento por adivinar
     */
    public String preguntaActual(){
        return pp.element();
    }
    
    /**
     * Método que añade un nuevo instrumento para que el adivinador aprenda
     * @param obj nuevo instrumento
     * @param dif diferencia con el instrumento en donde la partida está parada
     */
    public void agregarObjeto(String obj, String dif) {
        String rotulo = pp.element();
        try {
            arbol.replace(pp, dif);
            arbol.addRight(pp,obj);
            arbol.addLeft(pp,rotulo);
        }
        catch(InvalidPositionException e){}
        catch(InvalidOperationException e){}
        objetos++;
        preguntas++;
    }

    /**
     * Consulta la cantidad de objetos en la partida
     * @return cantidad de objetos en la partida
     */
    public int cantidadObjetos(){
    	return objetos;
    }

    /**
     * Consulta la cantidad de preguntas en la partida
     * @return cantidad de preguntas en la partida
     */
    public int cantidadPreguntas(){
    	return preguntas;
    }

    /**
     * Consulta la altura de la estructura de datos que almacena los datos de la partida
     * @return la altura de la estructura de datos que almacena los datos de la partida
     */
    public int alturaArbol(){
    	try {
			return alturaArbolAux(arbol.root(), 0);
		} catch (EmptyTreeException e) {}
    	
    	return 0;
    }
    
     /**
      * Método auxiliar, privado para calcular la altura del árbol
      * @param pos posición desde donde se desea calcular la altura
      * @param i profundidad de la posición pasada por parámetro
      * @return la profundidad de la posición pasada por parámetro
      */
    private int alturaArbolAux(Position<String> pos, int i) {
    	try {
			return Math.max(
					arbol.hasRight(pos) ? alturaArbolAux(arbol.right(pos), i+1) : i, 
					arbol.hasLeft(pos) ? alturaArbolAux(arbol.left(pos), i+1) : i
					);
		} 
    	catch (InvalidPositionException e) {} 
    	catch (BoundaryViolationException e) {}
    	
    	return 0;
    }
    
    /**
     * Devuelve una pila con los nodos internos de la estructura de datos
     * @return una pila con los nodos internos 
     */
    public Stack<Position<String>> pilaInternos(){
    	Iterable<Position<String>> nodosArbol = arbol.positions();
    	Stack<Position<String>> internos = new Pila<Position<String>>();

    	Iterator<Position<String>> it = nodosArbol.iterator();
    	Position<String> meterP;
    	
    	try{
	    	while(it.hasNext()){
	    		meterP = it.next();
	    		
		    	if(arbol.isInternal(meterP))
		    		internos.push(meterP);
    		}
    	}
    	catch(InvalidPositionException e){}
    	catch(NoSuchElementException e){}
 
    	return internos;
    }
	
    /**
     * Método privado para conseguir el elemento que va a reemplazar al rótulo de la pregunta eliminada
     * @param p Raíz del subárbol en el que se realiza la búsqueda
     * @return Rótulo de la hoja que se encuentra más a la izquierda en el subárbol
     */
    private String getLeftmost(Position<String> p){
    	String s="";
    	try{
	    	if(arbol.isExternal(p))
	    		s = p.element();
	    	else{
	    		s = getLeftmost(arbol.left(p));
	    	}
    	}
    	catch(BoundaryViolationException e){}
    	catch(InvalidPositionException e){}
    	
    	return s;
    }
    /**
     * Método privado que borra todo un subárbol cuya raíz es pasada por parámetro
     * @param p la raíz del subárbol a eliminar
     */
    private void auxEliminar(Position<String> p){
    	try{
	    	if(arbol.isInternal(p)){
	    		preguntas--;
		    	if(arbol.hasLeft(p))
		    		auxEliminar(arbol.left(p));
		    	if(arbol.hasRight(p))
		    		auxEliminar(arbol.right(p));
		    	
	    	}
	    	else
	    		objetos--;
	    	
	    	arbol.remove(p);
	    	
    	}
    	catch(InvalidPositionException e){System.out.println("IPS");}
    	catch(BoundaryViolationException e){System.out.println("BVE");}
    	catch(InvalidOperationException e){System.out.println("IOE");}
    }
    
    /**
     * Método que elimina un subárbol cuya raíz es pasada por parámetro
     * @param p raíz del subárbol
     */
    public void eliminarSubarbol(Position<String> p){
	objetos++;
    	preguntas--;
    	try{
    		arbol.replace(p, getLeftmost(p));
    		if(arbol.hasLeft(p))
    			auxEliminar(arbol.left(p));
    		
	    	if(arbol.hasRight(p))
	    		auxEliminar(arbol.right(p));
	    }
	    catch(InvalidPositionException e){}
	    catch(BoundaryViolationException e){}
    }

    /**
     * Método auxiliar para generar las descripciones
     * @param descs Lista en la que se almacenarán los objetos y sus respectivas descripciones
     * @param s Concatenación de características de un objeto.
     * @param p Posición 
     */
    private void auxDescripciones (PositionList<String> descs, String s, Position<String> p){
    	try{
    		if(arbol.isExternal(p)){
    			descs.addLast(p.element());
    			descs.addLast(s);
    		}
    	
    		else {
    			if(arbol.hasLeft(p)){
    				
    				if(arbol.isRoot(p) && arbol.isExternal(arbol.left(p))){
        				descs.addLast(arbol.left(p).element());
        				descs.addLast(s+" no "+p.element());	
        			}
    				else if(arbol.isExternal(arbol.left(p)))
    					auxDescripciones(descs, s+" y no "+p.element(), arbol.left(p));
    				
    				else{
    					if(arbol.isRoot(p))
    						auxDescripciones(descs, s+" no "+p.element(), arbol.left(p));	
    					else
    						auxDescripciones(descs, s+", no "+p.element(), arbol.left(p));
    				}
    			}
    			
    			if(arbol.hasRight(p)){
    				if(arbol.isRoot(p) && arbol.isExternal(arbol.right(p))){
        				descs.addLast(arbol.right(p).element());
        				descs.addLast(s+" "+p.element());
        			}
    				else if(arbol.isExternal(arbol.right(p)))
    					auxDescripciones(descs, s+" y "+p.element(), arbol.right(p));
    				else{
    					if(arbol.isRoot(p))
    						auxDescripciones(descs, s+" "+p.element(), arbol.right(p));
    					else
    						auxDescripciones(descs, s+" "+p.element(), arbol.right(p));
    				}
    			}
    			
    		}
    	}
    	catch(InvalidPositionException e){}
    	catch(BoundaryViolationException e){}
    }

    /**
     * Método que devuelve la descripción de cada instrumento de la partida
     * @return una estructura iterable que contiene las descripciones
     */
    public Iterable<String> generarDescripciones(){
    	PositionList<String> lista = new Lista<String>();
    	try{
    		if(arbol.size() == 1){
    			lista.addFirst(arbol.root().element());
    			lista.addLast(" es un instrumento");
    		}
    		else
    			auxDescripciones(lista," es un instrumento que",arbol.root());
    	} catch(EmptyTreeException e){}
    	return lista;
    }
	
    /**
     * Método que genera un archivo de partida
     * @param file dirección del archivo de partida en el sistema
     */
    public void grabarPartida(java.io.File file){
    	try{
    		ObjectOutputStream output = new ObjectOutputStream( new FileOutputStream( file ) );
    	
    		output.writeObject( arbol );
    		output.writeObject( objetos);
    		output.writeObject( preguntas);
    		output.flush();
    		output.close();
    	}
    	catch(IOException e){}
    }
    
    /**
     * Método que carga un archivo de partida
     * @param file dirección del archivo de partida en el sistema
     */
    public void cargarPartida(java.io.File file){
    	try{
    		ObjectInputStream input = new ObjectInputStream( new FileInputStream(file) );
    		arbol = (BinaryTree<String>) input.readObject();
    		objetos = (int) input.readObject();
    		preguntas = (int) input.readObject();
    		input.close();
    		pp = arbol.root();
    	}
    	catch(EmptyTreeException e){}
    	catch(ClassNotFoundException e){}
    	catch(IOException e){}
    }	
    
    /**
     * Reinicia la partida
     */
    public void clear () {
        arbol = new ArbolBinario<String>();
        objetos = 1;
        preguntas = 0;
        try {
            pp = arbol.createRoot("una guitarra");
        } catch (InvalidOperationException e){}    	
    }
	
}
