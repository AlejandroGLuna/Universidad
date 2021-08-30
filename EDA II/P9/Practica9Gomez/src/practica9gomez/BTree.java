package practica9gomez;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BTree {
    int m;
    BNode root;
    
    public BTree( int m ){
        this.m = m;
        root = new BNode();
        root.setM(m);
    }
    
    public void add( int n ){
        if(find(n)){
            System.out.println("La clave ya existe en el árbol.");
        }
        else{
            BNode hoja = leafNode(root,n);
            addToNode(hoja,n);
        }
    
    }
    
    private BNode leafNode(BNode nodo,int n){
        if(nodo.leaf){
            return nodo;
        }
        else{
            int i = 0;
            for(; i < nodo.key.size() ; i++ ){
                if( n < nodo.getKey(i) ){
                    i++;
                    break;
                }
            }
            if( n < nodo.getKey(i-1) )
                i--;
            return leafNode(nodo.getChild(i),n);  
        }
    }
    
    private void addToNode( BNode nodo, int n ){
        if( nodo.key.size() < m - 1 )  {
            System.out.println("nodo key sizeeeeeeee"+nodo.key.size());
            insert( nodo, n );
        }
        else  
            divisionCelular( nodo, n );    
    }
    
    private void insert( BNode nodo, int n ){
        int i = 0;
        while( i < nodo.key.size()  && n > nodo.getKey(i) ){
            i++;
        }   
        nodo.key.add(i, n);
    }
    
    private void divisionCelular( BNode nodo, int n ){
        int h = (m-1)/2;    
        System.out.println("valor de h   "+h);
        if( m % 2 != 0){
            System.out.println("holis   "+n);
            insert(nodo,n);
        }
        int medio = nodo.key.get(h);
            
        ArrayList<Integer> key1 = new ArrayList<>(nodo.key.subList( 0, h ));
        ArrayList<Integer> key2 = new ArrayList<>(nodo.key.subList( h + 1 , 2*h + 1));
        ArrayList<BNode> child1 = new ArrayList<>();
        ArrayList<BNode> child2 = new ArrayList<>();
        
        if( nodo == root ){
            BNode nuevoNodo1 = new BNode();
            BNode nuevoNodo2 = new BNode();
            nuevoNodo1.leaf = nuevoNodo2.leaf = nodo.leaf;
            
            nuevoNodo1.setKeys(key1);
            nuevoNodo2.setKeys(key2);
            nodo.key.clear();
            nodo.key.add(medio);
            
            if( m % 2 == 0 ){
                if( n < medio )
                    insert(nuevoNodo1, n);
                else
                    insert(nuevoNodo2, n);
            }
             
            if( !nodo.leaf ){
                if( m % 2 != 0 ){
                    child1 = new ArrayList<>( nodo.child.subList( 0, h+1 ) );
                    child2 = new ArrayList<>( nodo.child.subList( h+1, m+1 ) );
                }
                else{
                    if( n < medio ){
                        child1 = new ArrayList<>( nodo.child.subList( 0, h+2 ) );
                        child2 = new ArrayList<>( nodo.child.subList( h+2, m+1 ) );
                    }
                    if( n > medio ){
                        child1 = new ArrayList<>( nodo.child.subList( 0, h+1 ) );
                        child2 = new ArrayList<>( nodo.child.subList( h+1, m+1 ) );
                    }  
                }
                
                nuevoNodo1.setChildren( child1 );
                nuevoNodo2.setChildren( child2 );
                for( BNode i : nuevoNodo1.child )
                    i.parent = nuevoNodo1;
                for( BNode i : nuevoNodo2.child )
                    i.parent = nuevoNodo2;
            }
            nodo.child.clear();
            nodo.child.add(nuevoNodo1);
            nodo.child.add(nuevoNodo2);
            nuevoNodo1.parent = nuevoNodo2.parent = root;
            nodo.leaf = false;             
        }else{
            BNode nuevoNodo = new BNode();
            nuevoNodo.leaf = nodo.leaf;
            nuevoNodo.parent = nodo.parent;

            int childIndex = nodo.getChildIndex();
            
            nodo.setKeys(key1);
            nuevoNodo.setKeys(key2);
            
            if( m % 2 == 0 ){
                if( n < medio )
                    insert(nodo, n);
                else
                    insert(nuevoNodo, n);
            }
            
            if( !nodo.leaf ){
                if( m % 2 != 0 ){
                    child1 = new ArrayList<>( nodo.child.subList( 0, h+1 ) );
                    child2 = new ArrayList<>( nodo.child.subList( h+1, m+1 ) );
                }
                else{
                    if( n < medio ){
                        child1 = new ArrayList<>(nodo.child.subList( 0, h+2 ));
                        child2 = new ArrayList<>(nodo.child.subList( h+2, m+1 ));
                    }
                    if( n > medio ){
                        child1 = new ArrayList<>( nodo.child.subList( 0, h+1 ) );
                        child2 = new ArrayList<>( nodo.child.subList( h+1, m+1 ) );
                    }   
                }
                nodo.setChildren( child1 );
                nuevoNodo.setChildren( child2 );
                for( BNode i : nodo.child )
                    i.parent = nodo;
                for( BNode i : nuevoNodo.child )
                    i.parent = nuevoNodo;
            } 
            nodo.parent.child.add( childIndex + 1, nuevoNodo );
            
            addToNode( nodo.parent, medio );
        }
    }
    
    public void mostrarArbol(){
        if(root.child.isEmpty()==true && root.key.isEmpty()==true)
            System.out.println("El arbol esta vacio");
        else{
            Queue<BNode> nodos = new LinkedList<>();
            nodos.add(this.root);
            BNode parent=null;
            while(!nodos.isEmpty()){
                BNode v = nodos.poll();
                if(v.parent==null)
                    System.out.print("Nodo Raiz: ");
                if(parent!=v.parent){
                    System.out.print("\n\n\nNodo Padre: ");
                    v.parent.mostrarLlaves();
                    parent=v.parent;
                    System.out.print("\n\t\tNodos:");
                }
                System.out.print("\n\t\t");
                v.mostrarLlaves();
                for( int i = 0 ; i < v.child.size() ; i ++ )
                    nodos.add( v.child.get(i) );
            }
            System.out.println("\n");
        }
    }
    
    public boolean find(int value){
        if(root.child.isEmpty()==true && root.key.isEmpty()==true)
            return false;
        return find(value,root);
    }
    
    private boolean find(int v,BNode n){
        if(n==null)
            return false;
        int i;
        if( n.getKey(0) > v )
            return find(v,n.getChild(0));
        for(i=0;i<n.key.size()-1;i++){
            if(n.getKey(i)==v)
                return true;
            if(n.getKey(i)<v && n.getKey(i+1)>v)
                return find(v,n.getChild(i+1));  
        }
        if(n.getKey(i)==v)
            return true;
        else{
            if( n.getKey(i) < v)
                return find(v,n.getChild(i+1));
            else
                return find(v,n.getChild(i));
        }
    }
    
    //--------------------------------------------------------------------------
    //Metodos implementados para la eliminacion
    //Busca el nodo donde se encuentra la llave a eliminar
    private BNode findNode(int value, BNode n){
        int i;
        if(n.getKey(0)>value)
            return findNode(value,n.getChild(0));
        
        for(i=0;i<n.key.size()-1;i++){
            if(n.getKey(i)==value)
                return n;
            if(n.getKey(i)<value && n.getKey(i+1)>value)
                return findNode(value,n.getChild(i+1));  
        }
        if(n.getKey(i)==value)
            return n;
        else{
            if( n.getKey(i) < value)
                return findNode(value,n.getChild(i+1));
            else
                return findNode(value,n.getChild(i));
        }
    }
    
    //Encontrar el elemento que sustituye a una llave
    private BNode sustitutoArbol(BNode n, boolean arbolIzquierdo){
        BNode actualSustituto=null;
        if(!n.child.isEmpty())
            actualSustituto=arbolIzquierdo?n.child.get(n.child.size()-1):n.child.get(0);
        if(actualSustituto==null)
            return n;
        else
            return sustitutoArbol(actualSustituto,arbolIzquierdo);
    }
    
    //Prestamo de llave
    private boolean prestamo(BNode n){
        int indice=n.getChildIndex(),izq,der;
        int minimoNumClaves=(int)(Math.ceil((this.m*1.0)/2)-1);
        izq=der=-1;
        //Verificando si es nodo raiz
        if(indice==-1)
            return false;
        //Verificando si solo tiene vecino derecho
        if(indice>0)
            izq=indice-1;
        //Verificando si solo tiene vecino izquierdo
        if(indice<(n.parent.child.size()-1))
            der=indice+1;
        //Verificando el vecino derecho
        if(izq==-1){
            BNode vecinoDerecho=n.parent.getChild(indice+1);
            if(vecinoDerecho.key.size()>minimoNumClaves){
                n.key.add(n.parent.key.get(indice));
                if(!vecinoDerecho.leaf){
                    BNode hijos=vecinoDerecho.child.remove(0);
                    n.child.add(hijos);
                    hijos.parent=n;
                }
                n.parent.key.set(indice,vecinoDerecho.key.remove(0));
                return true;
            }
            return false;
        //Verificando el vecino izquierdo
        }else if(der==-1){
            BNode vecinoIzquierdo=n.parent.getChild(indice-1);
            if(vecinoIzquierdo.key.size()>minimoNumClaves){
                n.key.add(0,n.parent.key.get(indice-1));
                if(!vecinoIzquierdo.leaf){
                    BNode hijos=vecinoIzquierdo.child.remove(vecinoIzquierdo.child.size()-1);
                    n.child.add(0,hijos);
                    hijos.parent=n;
                }
                n.parent.key.set(indice-1,vecinoIzquierdo.key.remove(vecinoIzquierdo.key.size()-1));
                return true;
            }
            return false;
        //Verificando los vecinos derecho e izquierdo
        }else{
            BNode vecinoDerecho=n.parent.getChild(der);
            BNode vecinoIzquierdo=n.parent.getChild(izq);
            if(vecinoDerecho.key.size()>minimoNumClaves){
                n.key.add(n.parent.key.get(indice));
                if(!vecinoDerecho.leaf){
                    BNode hijos=vecinoDerecho.child.remove(0);
                    n.child.add(hijos);
                    hijos.parent=n;
                }
                n.parent.key.set(indice,vecinoDerecho.key.remove(0));
                return true;
            }else if(vecinoIzquierdo.key.size()>minimoNumClaves){
                n.key.add(0,n.parent.key.get(indice-1));
                if(!vecinoIzquierdo.leaf){
                    BNode hijos=vecinoIzquierdo.child.remove(vecinoIzquierdo.child.size()-1);
                    n.child.add(0,hijos);
                    hijos.parent=n;
                }
                n.parent.key.set(indice-1,vecinoIzquierdo.key.remove(vecinoIzquierdo.key.size()-1));
                return true;
            }else
                return false;
        }
    }
    
    //Fusion de nodos
    private void fusion(BNode n){
        int indice=n.getChildIndex(),izq,der;
        int minimoNumClaves=(int)(Math.ceil((this.m*1.0)/2)-1);
        izq=der=-1;
        //Verificando si es nodo raiz
        if(indice==-1)
            return;
        //Verificando si solo tiene vecino derecho
        if(indice>0)
            izq=indice-1;
        //Verificando si solo tiene vecino izquierdo
        if(indice<(n.parent.child.size()-1))
            der=indice+1;
        //Fusionando con el vecino derecho
        if(izq==-1){
            BNode vecinoDerecho=n.parent.child.remove(indice+1);
            n.key.add(n.parent.key.remove(n.getChildIndex()));
            //Adicionando las claves del nodo derecho
            while(!vecinoDerecho.key.isEmpty())
                n.key.add(vecinoDerecho.key.remove(0));
            //Adicionando los hijos del nodo derecho
            while(!vecinoDerecho.child.isEmpty()){
                BNode hijo=vecinoDerecho.child.remove(vecinoDerecho.child.size()-1);
                hijo.parent=n;
                n.child.add(0,hijo);
            }
        //Fusionando con el vecino izquierdo
        }else{
            BNode vecinoIzquierdo=n.parent.child.remove(indice-1);
            n.key.add(0,n.parent.key.remove(indice-1));
            //Adicionando las claves del nodo izquierdo
            while(!vecinoIzquierdo.key.isEmpty())
                n.key.add(0,vecinoIzquierdo.key.remove(vecinoIzquierdo.key.size()-1));
            //Adicionando los hijos del nodo izquierdo
            while(!vecinoIzquierdo.child.isEmpty()){
                BNode hijo=vecinoIzquierdo.child.remove(vecinoIzquierdo.child.size()-1);
                hijo.parent=n;
                n.child.add(0,hijo);
            }   
        }
        //Si el nodo padre ya no tiene claves, este nodo es la nueva raiz
        if(n.parent.key.isEmpty()){
            this.root=n;
            n.parent=null;
        }
        //Verificando si el nodo padre no incumple el minimo de claves
        else if(n.parent.key.size()<minimoNumClaves){
            if(!this.prestamo(n.parent))
                this.fusion(n.parent);
        }
    }
    
    //Metodo principal de eliminacion
    public void eliminarElemento(int n){
        if(this.find(n)){
            int minimoNumClaves=(int)(Math.ceil((this.m*1.0)/2)-1);
            //Obteniendo el nodo con el elemento a eliminar
            BNode nodoConElemento = this.findNode(n,this.root);
            //Verificando si se puede eliminar directamente
            if(nodoConElemento.leaf && nodoConElemento.key.size()>minimoNumClaves || nodoConElemento.leaf && nodoConElemento == this.root)
                nodoConElemento.key.remove(Integer.valueOf(n));
            //Verificando si se pude reemplazar por otro elemento
            else if(!nodoConElemento.leaf){
                int i=0;
                for(;i<nodoConElemento.key.size();i++){
                    if(nodoConElemento.getKey(i)==n)
                        break;
                }
                BNode izquierdo=this.sustitutoArbol(nodoConElemento.getChild(i),true);
                BNode derecho=this.sustitutoArbol(nodoConElemento.getChild(i+1),false);
                if(izquierdo.key.size() > minimoNumClaves)
                    nodoConElemento.key.set(i, izquierdo.key.remove(izquierdo.key.size()-1));
                else if(derecho.key.size() > minimoNumClaves)
                    nodoConElemento.key.set(i, derecho.key.remove(0));
                else{
                    nodoConElemento.key.set(i, derecho.key.remove(0));
                    //Realizando las respectivas redistribuciones
                    if(!this.prestamo(derecho))
                        this.fusion(derecho);
                }            
            }else{
                nodoConElemento.key.remove(Integer.valueOf(n));
                //Realizando las respectivas redistribuciones
                if(!this.prestamo(nodoConElemento))
                        this.fusion(nodoConElemento);
            }
        }else
            System.out.println("El elemento no se encuentra en el arbol");
    }
}