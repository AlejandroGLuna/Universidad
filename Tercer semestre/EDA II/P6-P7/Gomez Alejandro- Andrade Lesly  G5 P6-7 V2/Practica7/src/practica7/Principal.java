package practica7;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int V =8;
        Graph graph =new Graph(V);
        /*graph.addEdgeIndirected(1,5);
        graph.addEdgeIndirected(5,7);
        graph.addEdgeIndirected(4,5);
        graph.addEdgeIndirected(4,7);
        graph.addEdgeIndirected(7,3);
        graph.addEdgeIndirected(4,0);
        graph.addEdgeIndirected(0,2);
        graph.addEdgeIndirected(0,6);
        */
        graph.aristaNoDirigida(sc, graph);
        WeightedGraph grafoPrimm=new WeightedGraph(7);
        grafoPrimm.addEdgeDirected(0, 1, 2);
        grafoPrimm.addEdgeDirected(0, 2, 7);
        grafoPrimm.addEdgeDirected(0, 3, 2);
        grafoPrimm.addEdgeDirected(1, 3, 4);
        grafoPrimm.addEdgeDirected(2, 3, 5);
        grafoPrimm.addEdgeDirected(2, 5, 4);
        grafoPrimm.addEdgeDirected(3, 4, 1);
        grafoPrimm.addEdgeDirected(4, 2, 2);
        grafoPrimm.addEdgeDirected(4, 6, 3);
        grafoPrimm.algoritmoDePrimm(0);
    }
}
