package practica6gomezalejandro;

import java.util.Iterator; 
import java.util.LinkedList;

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

}
