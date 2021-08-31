package alejandro.procesos;

import java.util.Random;
import java.util.LinkedList;

public class Proceso {
    
    private final String nombre;
    private final int proceso_id;
    private final int instrucciones;
    private final int memoria;
    private final int nPaginas;
    private boolean cargadoCompleto;
    private int instruccionesRestantes, paginasCargadas = 0;
    private int[][] tablaPaginas;
    private LinkedList<Integer> secuenciaEjecucion;
    
    public Proceso(String nombre, int proceso_id){
        this.nombre = nombre;
        this.proceso_id = proceso_id;
        
        //Generando el espacio en memoria e instrucciones
        Random rand = new Random();
        this.instrucciones = 10 + rand.nextInt(21);
        this.instruccionesRestantes = this.instrucciones;
        this.memoria = 32 * (int)(Math.pow(2, rand.nextInt(5)));
        this.nPaginas = this.memoria/16;
        this.tablaPaginas = new int[this.nPaginas][2];
        for (int j = 0; j<this.nPaginas; j++){
            this.tablaPaginas[j][0] = j+1;
            this.tablaPaginas[j][1] = -1;
        }
        this.secuenciaEjecucion = new LinkedList<>();
        for(int j = 0; j<this.instrucciones; j++)
            this.secuenciaEjecucion.add((1+rand.nextInt(this.nPaginas)));
    }
    
    public boolean ejecutarProceso(){
        this.instruccionesRestantes -= 5;
        if(this.instruccionesRestantes <= 0){
            this.instruccionesRestantes = 0;
            this.secuenciaEjecucion.clear();
            return true;
        }
        int i  = 0;
        if(this.cargadoCompleto)
            while (i++ < 5) this.secuenciaEjecucion.remove();
        else{
            while (i++ < 5) this.reemplazoPagina(this.secuenciaEjecucion.remove());

        }
        return false;
    }
    
    private void reemplazoPagina(int numPagina){
        if(this.tablaPaginas[numPagina - 1][1] != -1)
            return;
        int paginaRemplazo = -1, max = 0;
        boolean paginaUsada = false;
        for (int i = 0; i<this.nPaginas; i++){
            if (this.tablaPaginas[i][1] != -1){
                int pagina = this.tablaPaginas[i][0];
                System.out.print(pagina);
                for(int j = 0; j < this.secuenciaEjecucion.size() ; j++){
                    if(pagina == this.secuenciaEjecucion.get(j)){
                        if(j >= max){
                            paginaRemplazo = pagina;
                            max = j;
                            paginaUsada = true;
                        }
                        break;
                    } 
                }
                if(!paginaUsada){
                    paginaRemplazo = pagina;
                    paginaUsada = false;
                    break;
                }
            }
        }
        int marcoDesocupado = this.tablaPaginas[paginaRemplazo - 1][1];
        this.tablaPaginas[paginaRemplazo - 1][1] = -1;
        this.tablaPaginas[numPagina - 1][1] = marcoDesocupado;
    }
    
    public void mostrarProceso(){
        System.out.println("Proceso: " + this.nombre);
        System.out.println("ID: " + String.valueOf(this.proceso_id));
        System.out.println("Instrucciones totales: " + String.valueOf(this.instrucciones));
        System.out.println("Instrucciones ejecutadas: " + String.valueOf(this.instrucciones - this.instruccionesRestantes));
        System.out.println("Ocupa " + String.valueOf(this.memoria) + " localidades de memoria");
        System.out.println("Su cadena de ejecucion es la siguiente:");
        for(int pagina:this.secuenciaEjecucion){
            System.out.print(pagina);
            System.out.print(" ");
        }
        System.out.println();
        System.out.println("Su tabla de paginas se encuentra de la siguiente manera:");
        System.out.println("Numero Pagina   |   Marco");
        for (int j = 0; j<this.nPaginas; j++){
            System.out.println("\t" + String.valueOf(this.tablaPaginas[j][0])
            + "\t\t" + String.valueOf(this.tablaPaginas[j][1]));
        }
    }
    
    public void actualizarPaginas(int[] marcos){
        if(this.cargadoCompleto){
            for (int j = 0; j<this.nPaginas; j++)
                this.tablaPaginas[j][1] = marcos[j];
            this.paginasCargadas = this.nPaginas;
        }else{
            int i = 0;
            for (int j = 0; j<this.nPaginas; j++){
                if (this.tablaPaginas[j][1] == -1){
                    if (i == marcos.length) break;
                    this.tablaPaginas[j][1] = marcos[i];
                    i++;
                    this.paginasCargadas++;
                }
            }
        }
    }
    
    public int memoriaOcupada(){
        return this.nPaginas;
    }
    
    public int paginasCargadas(){
        return this.paginasCargadas;
    }
    
    public int procesoID(){
        return this.proceso_id;
    }
    
    public String nombreProceso(){
        return this.nombre;
    }
    
    public int instruccionesPendientes(){
        return this.instruccionesRestantes;
    }
    
    public boolean getCargado(){
        return this.cargadoCompleto;
    }
    
    public void setCargado(boolean cargado){
        this.cargadoCompleto = cargado;
    }
    
    public void resetPaginas(){
        for (int j = 0; j<this.nPaginas; j++)
                this.tablaPaginas[j][1] = -1;
    }
}
