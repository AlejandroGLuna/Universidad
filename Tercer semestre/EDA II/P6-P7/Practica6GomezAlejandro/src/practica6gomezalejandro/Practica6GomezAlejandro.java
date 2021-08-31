package practica6gomezalejandro;

import java.util.Scanner;

public class Practica6GomezAlejandro {
    public static void main(String[] args) {
        int V = 5;
        GrafosMatrices graph = new GrafosMatrices(V);
        graph.addEdgeDirected(0, 1);
        graph.addEdgeDirected(0, 4);
        graph.addEdgeDirected(1, 2);
        graph.addEdgeDirected(1, 3);
        graph.addEdgeDirected(1, 4);
        graph.addEdgeDirected(2, 3);
        graph.addEdgeDirected(3, 4);
        graph.imprimirMatriz();
        Scanner sc=new Scanner(System.in);
        System.out.println("Ingrese la cantidad de vertices que tendra el grafo");
        int v=sc.nextInt();
        Graph grafoDirigido=new Graph(v);
        boolean respuesta=true;
        do{
            System.out.println("Ingrese la opcion a realizar:\n1)Crear grafo dirigido\n2)Crear grafo no dirigido\n3)Crear grafo ponderado diridigo\n4)Salir");
            byte opcion=sc.nextByte();
            switch(opcion){
                case 1:
                    aristaDirigida(sc,grafoDirigido);
                    break;
                case 2:
                    aristaNoDirigida(sc,grafoDirigido);
                    break;
                case 3: 
                    WeightedGraph grafoPonderado=new WeightedGraph(v);
                    grafoPonderado.menu(sc);
                    break;
                case 4:
                    respuesta=false;
                    break;
                default:
                    System.out.println("Opcion incorrecta");
            }
        }while(respuesta);
        
    }
    
    static void aristaDirigida(Scanner sc, Graph g){
        byte salir;
        do{
            System.out.println("Ingrese el nodo inicial:");
            byte v1=sc.nextByte();
            System.out.println("Ingrese el nodo final:");
            byte v2=sc.nextByte();
            g.addEdgeDirected(v1, v2);
            System.out.println("Si desea salir del programa oprima 1");
            salir=sc.nextByte();
            if(salir==1)
                break;
        }while(salir!=1);
        g.printGraph(g);
    }
    
    static void aristaNoDirigida(Scanner sc, Graph g){
        byte salir;
        do{
            System.out.println("Ingrese el nodo inicial:");
            byte v1=sc.nextByte();
            System.out.println("Ingrese el nodo final:");
            byte v2=sc.nextByte();
            g.addEdgeIndirected(v1, v2);
            System.out.println("Si desea salir del programa oprima 1");
            salir=sc.nextByte();
            if(salir==1)
                break;
        }while(salir!=1);
        g.printGraph(g);
    }
}
