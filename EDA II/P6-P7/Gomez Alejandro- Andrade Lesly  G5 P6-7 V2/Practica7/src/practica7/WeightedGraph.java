package practica7;

import java.util.Hashtable;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.ArrayList;

class WeightedGraph extends Graph{
    private Hashtable<String,Integer> ponderado;
    
    WeightedGraph(int v){
        super(v);
        this.ponderado=new Hashtable();
    }
    
    void addEdgeDirected(int v,int w, int valor){
        adjArray[v].add(w);
        String arista=Integer.toString(v)+Integer.toString(w);
        this.ponderado.put(arista,valor);
    }
    
    void imprimirGrafo(){
        System.out.println();
        for(int v = 0; v < this.V; v++){
            System.out.println("Lista de Adyacencia del vertice "+ v); 
            System.out.print(v);
            for(Integer node: this.adjArray[v]){
                System.out.print(" -> "+node); 
            }
            System.out.println("\n");
        }
        System.out.println("Valor de las aristas: ");
        String tabla=this.ponderado.toString();
        System.out.println(tabla);
    }
    
    void menu(Scanner sc){
        byte salir;
        do{
            System.out.println("Ingrese el nodo inicial:");
            byte v1=sc.nextByte();
            System.out.println("Ingrese el nodo final:");
            byte v2=sc.nextByte();
            System.out.println("Ingrese el valor de la arista que los une:");
            int val=sc.nextInt();
            this.addEdgeDirected(v1, v2,val);
            System.out.println("Si desea salir del programa oprima 1");
            salir=sc.nextByte();
            if(salir==1)
                break;
        }while(salir!=1);
        imprimirGrafo();
    }
    
    void algoritmoDePrimm(int v){
        boolean visitados[]=new boolean[this.V];
        ArrayList<String> mst=new ArrayList();
        visitados[v]=true;
        Comparator<String> comparator = new CompararAristas();
        PriorityQueue<String> queue = new PriorityQueue(this.V, comparator);
        byte z=0;
        while(z<adjArray[v].size()){
            int w=adjArray[v].get(z);
            String aristaActual=Integer.toString(v)+Integer.toString(w);
            queue.add(aristaActual);
            z++;
        }
        z=0;
        while(!queue.isEmpty()){
            String aristaActual=queue.poll();
            int vActual=Character.getNumericValue(aristaActual.charAt(1));
            if(!visitados[vActual]){
                mst.add(aristaActual);
                visitados[vActual]=true;
                byte x=0;
                while(x<adjArray[vActual].size()){
                    int w=adjArray[vActual].get(x);
                    String a=Integer.toString(vActual)+Integer.toString(w);
                    queue.add(a);
                    x++;
                }
                z++;
            } 
        }
        this.imprimirGrafo();
        System.out.print("\nM.S.T: ");
        for(String arista:mst){
            try{
            System.out.print(arista.charAt(0)+"-"+arista.charAt(1)+" ");
            }catch(NullPointerException eNPE){}
        }
    }
    
    class CompararAristas implements Comparator<String>{
        @Override
        public int compare(String x, String y){
            int valor1=ponderado.get(x);
            int valor2=ponderado.get(y);
            if(valor1<valor2)
                return -1;
            if(valor1>valor2)
                return 1;
            return 0;
        }
    }
}
