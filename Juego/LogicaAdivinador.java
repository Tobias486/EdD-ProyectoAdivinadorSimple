package Juego;


import TDAArbolBinario.*;

/*USANDO LOS QUICKFIXES DE ECLIPSE SE IMPORTARON LAS SIGUIENTES COSAS, SIN EMBARGO, NO SÃ‰ COMO SE IMPORTA DE MANERA MÃ�S COMPRIMIDA
PERO CREO QUE VOS SÃ�
import java.util.Iterator;
import java.util.NoSuchElementException;

import TDAArbolBinario.*;
import TDAArbolBinario.BoundaryViolationException;
import TDAArbolBinario.InvalidPositionException;
import TDAArbolBinario.Position;
import TDAPila.Pila;
import TDAPila.Stack;
*/

/*
 * CREO QUE LOS ÚNICOS NECESARIOS ADICIONALES SON LOS SIGUIENTES:
 */
import TDAPila.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LogicaAdivinador {
    BinaryTree<String> arbol;
    Position<String> pp;
    
    int objetos;
    int preguntas;
    
    public LogicaAdivinador(){
        arbol = new ArbolBinario<String>();
        objetos = 1;
        preguntas = 0;
        try {
            pp = arbol.createRoot("una guitarra");
        } catch (InvalidOperationException e){}
    }
    
    
    public void siguientePregunta(boolean resp) throws BoundaryViolationException{
        try {
            if(resp)
               pp = arbol.right(pp);
            else
               pp = arbol.left(pp);
        }
        catch(InvalidPositionException e){}
    
    }
    
    public boolean haySiguientePregunta(){
        boolean hay = false;
        try{
            if(!arbol.isExternal(pp))
                hay = true;
        }
        catch (InvalidPositionException e){}
        return hay;
        
            
    }
    
    public void reiniciar(){
        try{
            pp = arbol.root();
        }
        catch(EmptyTreeException e){}
    }
    
    public String preguntaActual(){
        return pp.element();
    }
    
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
    	// consulta para la cantidad de objetos
    public int cantidadObjetos(){
    	return objetos;
    }
	// consulta para la cantidad de preguntas
    public int cantidadPreguntas(){
    	return preguntas;
    }
    // consulta la altura del árbol
    public int alturaArbol(){
    	try {
			return alturaArbolAux(arbol.root(), 0);
		} catch (EmptyTreeException e) {}
    	
    	return 0;
    }
    // método auxiliar, privado para calcular la altura del árbol
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
	// Devuelve una pila con las positions de los nodos internos, en el pdf dice que se debe usar una pila, puedo cambiarlo
	// para que devuelva una lista con las posiciones invertidas, pero eso depende de como se vaya a hacer en la GUI
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
	
	// metodo privado para eliminar
    private String auxEliminar(Position<String> p){
    	String s;
    	s = p.element();
    	try{
	    	if(arbol.hasRight(p)){
	    		s = auxEliminar(arbol.right(p));
	    		arbol.remove(arbol.right(p));
	    		
	    	}
	    	if(arbol.hasLeft(p)){
	    		s = auxEliminar(arbol.left(p));
	    		arbol.remove(arbol.left(p));
	    	}
	    	arbol.replace(p,s);
    	}
    	catch(InvalidPositionException e){}
    	catch(InvalidOperationException e){}
    	catch(BoundaryViolationException e){}
    	return s;
    }
    // Se llama a este mÃ©todo utilizando uno de las Positions presentes en pilaInternos
    public void eliminarSubarbol(Position<String> p){
    	auxEliminar(p);
    }
	
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
		
	
}
