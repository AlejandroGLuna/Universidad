package practica8gomez;

public class Nodo {
    int valor;
    Nodo izq=null;
    Nodo der=null;
    
    public Nodo(){
        izq=der=null;
    }
    
    public Nodo(int data){
        this(data,null,null);
    }
    
    public Nodo(int dta,Nodo lt,Nodo rt){
        valor=dta;
        izq=lt;
        der=rt;
    }
    
    public void setIzq(Nodo izq){
        this.izq=izq;
    }
    
    public void setDer(Nodo der){
        this.der=der;
    }
    
}