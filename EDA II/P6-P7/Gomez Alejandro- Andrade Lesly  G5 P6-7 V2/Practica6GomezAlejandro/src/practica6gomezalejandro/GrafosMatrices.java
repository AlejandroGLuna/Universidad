package practica6gomezalejandro;

public class GrafosMatrices{
    private int V;
    private byte[][] matrizAdyacencia;
    
    GrafosMatrices(int v){
        this.V=v;
        matrizAdyacencia=new byte[v][v];
    }
    
    void addEdgeIndirected(int u, int v){
        matrizAdyacencia[u][v]=1;
        matrizAdyacencia[v][u]=1;
    }
    
    void addEdgeDirected(int u, int v){
        matrizAdyacencia[u][v]=1;
    }
    
    void imprimirMatriz(){
        System.out.println("Matriz de Adyacencia: ");    
        for(byte x=0;x<this.V;x++){
            for(byte y=0;y<this.V;y++)
                System.out.print(this.matrizAdyacencia[x][y]+" ");
            System.out.println();
        }
    }
}
