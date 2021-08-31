package practica7;

import java.util.Iterator; 
import java.util.LinkedList;
import java.util.Scanner;

class Graph{
    int V;
    LinkedList<Integer> adjArray[];
    
    Graph(int v){
        V = v;
        adjArray = new LinkedList[v]; 
        for (int i=0; i<v; ++i)
            adjArray[i] = new LinkedList();
    }

    void addEdgeDirected(int v,int w){
        adjArray[v].add(w);
    }
    
    void addEdgeIndirected(int v, int w){
        adjArray[v].add(w);
        adjArray[w].add(v);
    }

    void printGraph(Graph graph){
        for(int v = 0; v < graph.V; v++){
            System.out.println("Lista de Adyacencia del vertice "+ v); 
            System.out.print(v);
            for(Integer node: graph.adjArray[v]){
                System.out.print(" -> "+node); 
            }
            System.out.println("\n");
        }
    }
    
    void BFS(int s){
        boolean visited[] = new boolean[V];
        LinkedList<Integer> queue = new LinkedList();
        visited[s]=true;
        queue.add(s);
        while (queue.size() != 0)  {
            s = queue.poll();
            System.out.print(s+" ");
            Iterator<Integer> i = adjArray[s].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            } 
        }
    }
    void UseBFS(Scanner sc){
        int s;
        System.out.println("Desea implementar la busqueda por anchura (BFS) \n1. Si\n2. No");
        s =sc.nextInt();
        if(s==1){
            System.out.println("Dame el nodo inicial");
            s=sc.nextInt();
            BFS(s);
        }
            
    }
    
    void DFSUtil(int v,boolean visited[]){
        visited[v] = true;
        System.out.print(v+" ");
        Iterator<Integer> i = adjArray[v].listIterator(); 
        while (i.hasNext()){
            int n = i.next();
            if (!visited[n])
                DFSUtil(n, visited);
        }
    }
    
    void DFS(int v){
        boolean visited[] = new boolean[V];
        DFSUtil(v, visited);
    }
    
    void aristaNoDirigida(Scanner sc, Graph g){
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
        System.out.println("Dame el vertice con el que implementaras DFS");
        byte s=sc.nextByte();
        DFS(s);
        g.printGraph(g);
    }
}

