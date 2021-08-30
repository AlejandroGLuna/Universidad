package practica8gomez;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ArbolBin{
    Nodo root;
    static int raiz;
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
        public void crearABin(Scanner sc){
        int val,nodo,pos,resp=0;
        System.out.println("Cual sera el valor de la raiz del arbol");
         val=Integer.parseInt(sc.next());
         this.root=new Nodo(val);
       // ArbolBin arbol=new ArbolBin(val);
        do{
            System.out.println("Desea agregar un nodo\n1)Si \n2)No");
            resp=Integer.parseInt(sc.next());
            if(resp==1){
                System.out.println("Cual es el valor del nodo");
                val=Integer.parseInt(sc.next());
                Nodo n=new Nodo(val);
                System.out.println("Cual es el valor del nodo padre de este nodo");
                nodo=Integer.parseInt(sc.next());
                if(busquedaV(nodo).izq==null && busquedaV(nodo).der==null){
                    System.out.println("Escribe 0 si quieres que el nuevo nodo sea hijo izquierdo \nEscrie 1 si quieres que el nodo sea hijo derecho");
                    pos=Integer.parseInt(sc.next());
                    add(busquedaV(nodo),n , pos);
                }
                else
                    if(busquedaV(nodo).der !=null){
                        System.out.println("El nuevo nodo sera insertado como hijo izquierdo por que es el esapcio que se encuentra vacio");
                        add(busquedaV(nodo),n,0);
                    }
                    else{
                       System.out.println("El nuevo nodo sera insertado como hijo derecho por que es el esapcio que se encuentra vacio");
                        add(busquedaV(nodo),n,1); 
                    }
                }    
        }while(resp!=2);
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
   public void recuperardatos(Nodo r,LinkedList<Integer> datos){
        Queue<Nodo> queue=new LinkedList();
        if(r!=null){
            queue.add(r);
            while(!queue.isEmpty()){
                r=(Nodo)queue.poll();
                datos.add(r.valor);
              //  System.out.printf(datos.get(datos.size()-1)+" ");
                if(r.izq!=null)
                    queue.add(r.izq);
                if(r.der!=null)
                    queue.add(r.der);
            }
            datos.remove(0);
        } 
   }
        public Nodo busquedaV(int x){
        Nodo r=root;
        Queue<Nodo> queue=new LinkedList();
        if(r!=null){
            queue.add(r);
            while(!queue.isEmpty()){
                r=(Nodo)queue.poll();
                if(r.valor==x)
                    return r;
                else{
                    if(r.izq!=null)
                        queue.add(r.izq);
                    if(r.der!=null)
                        queue.add(r.der);
                }
            }
        }
        return new Nodo();
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
    public void Prefija(Nodo in){
            if(in==null)
                System.out.printf("");
            else{
                System.out.printf(in.valor+ " ");
                Prefija(in.izq);
                Prefija(in.der);
            }    
        }
        public void Infija(Nodo in){
           if(in==null)
                System.out.printf("");
            else{
               Infija(in.izq);
                System.out.printf(in.valor+ " ");
                Infija(in.der);
            }  
        }
        public void Postfija(Nodo in){
            if(in==null)
                System.out.printf("");
            else{
                Postfija(in.izq);
                Postfija(in.der);
                System.out.printf(in.valor+ " ");
            } 
        }
        public void eliminar(int val){
            if(busquedaV(val).valor==0)
                System.out.println("El Nodo no existe");
            else{
               LinkedList<Integer> datos=new LinkedList<>();
               recuperardatos(busquedaV(val),datos);
               Queue<Nodo> ref=new LinkedList<>();
               Nodo b=busquedaV(val);
               ref.add(b);
               int x;
               while(!datos.isEmpty()){
                   b=ref.poll();
                   x=datos.get(0);
                   b.valor=x;
                   System.out.printf(b.valor+" ");
                   datos.remove();
                   if(b.izq!=null)
                        ref.add(b.izq);
                   if(b.der!=null)
                        ref.add(b.der);   
               }
               b=ref.poll();
               b.valor=0;
               b.izq=b.der=null;
            }
            
            
        }
}