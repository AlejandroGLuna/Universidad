package practica6gomezalejandro;

import java.util.Hashtable;
import java.util.Scanner;

class WeightedGraph extends Graph{
    private Hashtable<String,Integer> ponderado;
    
    WeightedGraph(int v){
        super(v);
        this.ponderado=new Hashtable();
    }
    
    void addEdgeDirected(int v,int w, int valor){
        adjArray[v].add(w);
        String arista=Integer.toString(v)+" a "+Integer.toString(w)+" ";
        this.ponderado.put(arista,valor);
    }
    
    void imprimirGrafo(){
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
}

