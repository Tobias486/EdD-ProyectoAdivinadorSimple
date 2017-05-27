package Juego;

import TDAArbolBinario.*;

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
        String pregunta = "";
        try {
            if(arbol.isInternal(pp)){
                pregunta +=  "¿"+pp.element()+"?";
            }
            else {
                pregunta += "Estás pensando en "+pp.element()+"?"; 
            }
        }
        catch(InvalidPositionException e){}
        return pregunta;
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
    
    public int cantidadObjetos(){
    	return objetos;
    }
    public int cantidadPreguntas(){
    	return preguntas;
    }
    public Stack<Position<String>> listaInternos(){
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
    
    public void eliminarSubarbol(Position<String> p){
    	auxEliminar(p);
    }
    
}
