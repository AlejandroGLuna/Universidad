package practica8gomez;

import java.util.LinkedList;
import java.util.Queue;

public class ArbolBinBusqueda {
    Nodo root;
    private int nodos, alturaArIzq, alturaArDer;
    
    public ArbolBinBusqueda(){
        root=null;
    }

    public ArbolBinBusqueda(int val){
        root=new Nodo(val);
    }

    public ArbolBinBusqueda(Nodo root){
        this.root=root;
    }
    
    public void add(Nodo nuevo){
        if(this.root==null)
            this.root=nuevo;
        else
            this.addNodo(this.root,nuevo);
    }
    
    private void addNodo(Nodo base, Nodo nuevo){
        if(nuevo.valor<base.valor){
            if(base.izq==null){
                base.setIzq(nuevo);
            }
            else{
                this.addNodo(base.izq,nuevo);
            }
        }else if(nuevo.valor>base.valor){
            if(base.der==null){
                base.setDer(nuevo);
            }else{
                this.addNodo(base.der,nuevo);
            }
        }else
            System.out.println("No se permiten elementos repetidos");
    }
    
    public void eliminarNodo(Nodo borrado){
        LinkedList<Nodo> hijos=new LinkedList<>();
        //Recuperar nodos hijos al que se borrara
        this.breadthFirstDelete(borrado,hijos);
        //Eliminando el nodo
        this.breadthFirstDelete(borrado);
        //Insertando los nodos hijos nuevamente
        while(!hijos.isEmpty()){
            Nodo actual=hijos.get(0);
            actual.setDer(null);
            actual.setIzq(null);
            this.add(hijos.remove());
        }
    }
    
    //Recuperar hijos del nodo borrado y sus consecuentes
    private void breadthFirstDelete(Nodo borrar, LinkedList<Nodo> hijos){
        Nodo r=borrar;
        Queue<Nodo> queue=new LinkedList();
        if(r!=null){
            queue.add(r);
            while(!queue.isEmpty()){
                r=(Nodo)queue.poll();
                if(r!=borrar)
                    hijos.add(r);
                if(r.izq!=null){
                    queue.add(r.izq);
                }
                if(r.der!=null){
                    queue.add(r.der);
                }
            }
        }
    }
    
    //Eliminar nodo
    private boolean breadthFirstDelete(Nodo borrar){
        Nodo r=root;
        if(r==borrar){
            this.root=null;
            return true;
        }else{
            Queue<Nodo> queue=new LinkedList();
            if(r!=null){
                queue.add(r);
                while(!queue.isEmpty()){
                    r=(Nodo)queue.poll();
                    if(r.izq!=null){
                        queue.add(r.izq);
                        if(r.izq==borrar){
                            r.izq=null;
                            return true;
                        }
                    }
                    if(r.der!=null){
                        queue.add(r.der);
                        if(r.der==borrar){
                            r.der=null;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }   
    
    public void datosArbol(){
        this.alturaArIzq=this.alturaArDer=this.nodos=0;
        int altura=this.alturaArbol();
        this.numNodos(this.root);
        System.out.println("La altura del arbol es: "+altura);
        System.out.println("Los nodos en el arbol son: "+this.nodos);
    }
    
    private int alturaArbol(){
        if(this.root==null){
            return -1;
        }else if(this.root.izq==null && this.root.der==null){
            return 0;
        }else{
            this.alturaArIzq=1;
            this.alturaArDer=1;
            alturaArbol(this.root.izq,true);
            alturaArbol(this.root.der,false);
            if(this.alturaArIzq==(this.alturaArDer+1) || this.alturaArIzq==(this.alturaArDer-1) || this.alturaArIzq==this.alturaArDer){
                System.out.println("El arbol esta balanceado");
            }else
                System.out.println("El arbol no esta balanceado");
            int alturaTotal= this.alturaArIzq>=this.alturaArDer?this.alturaArIzq:this.alturaArDer;
            return alturaTotal;
        }
    }
    
    private void alturaArbol(Nodo n,boolean arbolIzq){
        if(n!=null && (n.der!=null || n.izq!=null)){
            if(arbolIzq)
                this.alturaArIzq++;
            else
                this.alturaArDer++;
            alturaArbol(n.izq,arbolIzq);
            alturaArbol(n.der,arbolIzq);
        }
    }
    
    private void numNodos(Nodo n){
        if(n!=null){
            this.nodos++;
            numNodos(n.izq);
            numNodos(n.der);
        }
    }
    
    protected void visit(Nodo n){
        System.out.println(n.valor+" ");
    }

    public void breadthFirst(){
        Nodo r=root;
        Queue<Nodo> queue=new LinkedList();
        if(r!=null){
            queue.add(r);
            while(!queue.isEmpty()){
                r=(Nodo)queue.poll();
                visit(r);
                if(r.izq!=null)
                    queue.add(r.izq);
                if(r.der!=null)
                    queue.add(r.der);
            }
        }
    }
    
    public boolean buscar(int x){
        if(this.root.valor == x)
            return true;
        else{
            if(x<this.root.valor)
                return buscar(this.root.izq,x);
            else
                return buscar(this.root.der,x);
        }
    }
    
    private boolean buscar(Nodo n, int x){
        if(n==null)
            return false;
        else if(n.valor == x)
            return true;
        else{
            if(x<n.valor)
                return buscar(n.izq,x);
            else
                return buscar(n.der,x);
        }
    }
}
