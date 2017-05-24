package Juego;

import TDAArbolBinario.*;

public class LogicaAdivinador {
    BinaryTree<String> arbol;
    Position<String> pp;
    
    public LogicaAdivinador(){
        arbol = new ArbolBinario<String>();
        try {
            arbol.createRoot("una guitarra");
            pp = arbol.root();
        } catch (InvalidOperationException e){}
        catch(EmptyTreeException e){}
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
    }
}
