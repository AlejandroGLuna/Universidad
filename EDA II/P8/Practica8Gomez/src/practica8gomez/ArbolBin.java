package practica8gomez;

import java.util.LinkedList;
import java.util.Queue;

public class ArbolBin{
    Nodo root;

    public ArbolBin(){
        root=null;
    }

    public ArbolBin(int val){
        root=new Nodo(val);
    }

    public ArbolBin(Nodo root){
        this.root=root;
    }

    public void add(Nodo padre, Nodo hijo, int lado){
        if(lado==0)
            padre.setIzq(hijo);
        else
            padre.setDer(hijo);
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

    public boolean busqueda(int x){
        Nodo r=root;
        Queue<Nodo> queue=new LinkedList();
        if(r!=null){
            queue.add(r);
            while(!queue.isEmpty()){
                r=(Nodo)queue.poll();
                if(r.valor==x)
                    return true;
                else{
                    if(r.izq!=null)
                        queue.add(r.izq);
                    if(r.der!=null)
                        queue.add(r.der);
                }
            }
        }
        return false;
    }
}