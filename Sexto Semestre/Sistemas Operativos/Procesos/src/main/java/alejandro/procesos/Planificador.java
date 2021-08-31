package alejandro.procesos;

import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

public class Planificador {
    
    private Queue<Proceso> colaProcesosListos;
    private Queue<Proceso> colaProcesosFinalizados;
    private Queue<Proceso> colaProcesosAniquilados;
    private LinkedList<BloqueMemoria> listaMemoria;
    private boolean hayEspacio = true;
    private Proceso enEspera;
    private int[][] tablaMemoria;
    private int idProcesoActual = 1, espaciosFaltantes = 0;
    
    public Planificador(){
        this.colaProcesosListos = new LinkedList<>();
        this.colaProcesosFinalizados = new LinkedList<>();
        this.colaProcesosAniquilados = new LinkedList<>();
        this.tablaMemoria = new int[64][16];
        this.listaMemoria = new LinkedList<>();
        this.listaMemoria.add(new BloqueMemoria('H',0,64));
    }
    
    //Metodo para crear un nuevo proceso
    private void crearProceso(String nombre){
        Proceso p = new Proceso(nombre,this.idProcesoActual++);
        
        //Calculando el espacio en memoria
        int bloquesMemoria = p.memoriaOcupada(), i = 0, x = 0;
        int[] indicesMemoria = new int[bloquesMemoria];
        boolean cabe = false;
        
        //Encontrando el espacio en memoria
        for(BloqueMemoria b: this.listaMemoria){
            if(b.id == 'H'){
                x += b.longitud;
                i = 0;
                while(i < x && i<indicesMemoria.length) indicesMemoria[i] = b.registroBase + i++;
                if(x >= bloquesMemoria){
                    cabe = true;
                    break;
                }
            }
        }

        if(!cabe && !this.hayEspacio){
            System.out.println("No se encontro espacio disponible para el proceso");
            System.out.println("Es necesario ejecutar o aniquilar procesos listos");
            this.hayEspacio = false;
        }else if(!cabe && this.hayEspacio){
            this.hayEspacio = false;
            this.espaciosFaltantes = bloquesMemoria - x;
            int[] indicesActuales = new int[x];
            
            //Actualizando el estado en memoria del proceso agregado
            p.setCargado(false);
            
            for(int j = 0; j < x; j++){
                this.tablaMemoria[indicesMemoria[j]][0] = p.procesoID();
                indicesActuales[j] = indicesMemoria[j];
            }
            this.colaProcesosListos.add(p);
            
            //Actualizando su tabla de paginas
            p.actualizarPaginas(indicesActuales); 
            
            //Almacenando el proceso sin cargar
            this.enEspera = p;
            
            //Actualizando la lista ligada de memoria
            this.actualizarLista();
        }else{
            //Actualizando el estado en memoria del proceso agregado
            p.setCargado(true);
            //Actualizando su tabla de paginas
            p.actualizarPaginas(indicesMemoria);
            
            for(int j = 0; j < bloquesMemoria; j++)
                this.tablaMemoria[indicesMemoria[j]][0] = p.procesoID();
            this.colaProcesosListos.add(p);

            //Actualizando la lista ligada de memoria
            this.actualizarLista();
        }
    }
    
    //Metodo para actualizar la lista ligada de memoria
    private void actualizarLista(){
        int i=0;
        this.listaMemoria.clear();
        while(i<64){
            int actual = this.tablaMemoria[i][0];
            int indiceBase = i++;
            while(i < 64 && actual == this.tablaMemoria[i][0]) i++;
            char id  = (actual == 0 ? 'H':'P');
            this.listaMemoria.add(new BloqueMemoria(id, indiceBase, (i-indiceBase)));
        }
    }

    //Metodo para ver estado actual del sistema
    private void verEstadoActual(){
        try{
            System.out.println("Existen " + String.valueOf(this.colaProcesosListos.size()) + " procesos listos para ser ejecutados");
        }catch(NullPointerException ex){
            System.out.println("No hay procesos listos para ser ejecutados.");
        }catch(Exception ex){
            System.out.println("Ocurrio un error inesperado");
        }
        
        try{
            System.out.println("Los procesos que ya han finalizado son: ");
            this.colaProcesosFinalizados.forEach(p -> {
                System.out.println("\t" + String.valueOf(p.procesoID()) + " -> " + p.nombreProceso());
            });
        }catch(NullPointerException ex){
            System.out.println("No hay procesos que hayan finalizado.");
        }catch(Exception ex){
            System.out.println("Ocurrio un error inesperado");
        }
        
        try{
            System.out.println("Los procesos que fueron aniquilados son: ");
            this.colaProcesosAniquilados.forEach(p -> {
                System.out.println("\t" + String.valueOf(p.procesoID()) + " -> " + p.nombreProceso());
            });
        }catch(NullPointerException ex){
            System.out.println("No hay procesos que hayan sido aniquilados.");
        }catch(Exception ex){
            System.out.println("Ocurrio un error inesperado");
        }
        
        System.out.println("El estado actual de la memoria es:");
        for(int j = 0; j < 64; j++){
            int id = this.tablaMemoria[j][0];
            if(id == 0)
                System.out.println("["+ String.valueOf(j) +"] -> 16 LOCALIDADES DE MEMORIA LIBRES");
            else
                System.out.println("["+ String.valueOf(j) +"] -> 16 LOCALIDADES DE MEMORIA OCUPADOS POR EL PROCESO CON ID"+String.valueOf(id));
        } 
    }
    
    //Metodo para ver la lista ligada de procesos
    private void verLista(){
        BloqueMemoria tail = this.listaMemoria.peekLast();
        this.listaMemoria.forEach(b -> {
            if (b.equals(tail))
                System.out.print(b);
            else
                System.out.print(b + " -> ");
        });
        System.out.println();
    }
    
    //Metodo para imprimir la cola de procesos actuales
    private void mostrarProcesosActuales(){
        try{
            System.out.println("Los procesos listos para ejecutarse son: ");
            this.colaProcesosListos.forEach(p -> {
                System.out.println("\t" + String.valueOf(p.procesoID()) + " -> " + p.nombreProceso());
            });
            System.out.println("El proceso activo es: "+this.colaProcesosListos.peek().nombreProceso());
        }catch(NullPointerException ex){
            System.out.println("No hay procesos listos para ejecutarse.");
        }catch(Exception ex){
            System.out.println("Ocurrio un error inesperado");
        }
    }
    
    //Metodo para agregar las paginas faltantes de algun proceso incompleto
    private void agregarPaginasFaltantes(int memoriaDesocupada, int[] indices){
        int memoriaRestante;
        if(memoriaDesocupada > this.espaciosFaltantes){
            memoriaRestante = this.espaciosFaltantes;
            this.hayEspacio = true;
            this.espaciosFaltantes = 0;
        }else{
            this.espaciosFaltantes -= memoriaDesocupada;
            memoriaRestante = memoriaDesocupada;
        }

        int idProceso = this.enEspera.procesoID();
        
        for(int i = 0; i<memoriaRestante; i++)
            this.tablaMemoria[indices[i]][0] = idProceso;
            
        this.enEspera.actualizarPaginas(indices);
    }
    
    //Metodo para ejecutar proceso actual
    private void ejecutarProceso(){
        try{
            Proceso p = this.colaProcesosListos.poll();
            if(p.ejecutarProceso()){
                System.out.println("El proceso ha finalizado su ejecucion");
                int id = p.procesoID(), bloques = p.paginasCargadas(), i = 0;
                int[] indicesDesocupados = new int[bloques];
                for(int j = 0; j < 64; j++){
                    if(this.tablaMemoria[j][0] == id){
                        this.tablaMemoria[j][0] = 0;
                        indicesDesocupados[i] = j;
                        bloques--;
                        i++;
                    }else if(bloques == 0)
                        break;
                }
                this.colaProcesosFinalizados.add(p);
                if(this.espaciosFaltantes > 0 && p.getCargado())
                        this.agregarPaginasFaltantes(p.paginasCargadas(), indicesDesocupados);
                else{
                    this.hayEspacio = true;
                    this.espaciosFaltantes = 0;
                } 
                this.actualizarLista();
            }else
                this.colaProcesosListos.add(p);
        }catch(Exception ex){
            System.out.println("No hay procesos para ejecutarse.");
        }
    }
    
    //Metodo para ver al proceso actual
    private void procesoActual(){
        try{
            Proceso p = this.colaProcesosListos.peek();
            p.mostrarProceso();
            int id = p.procesoID();
            int bloques = p.paginasCargadas();
            int memoriaTotal = p.memoriaOcupada();
            System.out.println("La cantidad de localidades que ocupa es: "+String.valueOf(memoriaTotal));
            System.out.println("Las localidades de memoria que ocupa son: ");
            for(int j = 0; j < 64; j++){
                if(this.tablaMemoria[j][0] == id){
                    System.out.println("["+ String.valueOf(j) +"] -> 16 LOCALIDADES DE MEMORIA OCUPADAS POR EL ACTUAL PROCESO");
                    bloques--;
                }else if(bloques == 0)
                    break;
            }
        }catch(Exception ex){
            System.out.println("No hay procesos para ver.");
        }
        
    }
    
    //Metodo para pasar al siguiente proceso
    private void siguiente(){
        try{
            this.colaProcesosListos.add(this.colaProcesosListos.poll());
        }catch(Exception ex){
            System.out.println("No hay procesos para saltar.");
        }
    }
    
    //Metodo para aniquilar al proceso actual
    private void aniquilarProceso(){
        System.out.println("Aniquilando al proceso...");
        try{
            Proceso p = this.colaProcesosListos.poll();
            int id = p.procesoID(), bloques = p.paginasCargadas(), i = 0;
            int[] indicesDesocupados = new int[bloques];
            for(int j = 0; j < 64; j++){
                if(this.tablaMemoria[j][0] == id){
                    this.tablaMemoria[j][0] = 0;
                    indicesDesocupados[i] = j;
                    bloques--;
                    i++;
                }else if(bloques == 0)
                    break;
            }
            this.colaProcesosAniquilados.add(p);
            if(this.espaciosFaltantes > 0 && p.getCargado())
                        this.agregarPaginasFaltantes(p.paginasCargadas(), indicesDesocupados);
            else{
                this.hayEspacio = true;
                this.espaciosFaltantes = 0;
            } 
            System.out.println("Quedaban "+String.valueOf(p.instruccionesPendientes())+" instrucciones pendientes");
        }catch(Exception ex){
            System.out.println("No hay procesos para aniquilar.");
            ex.printStackTrace();
        }
        //Actualizar lista ligada de memoria
        this.actualizarLista();
    }
    
    //Metodo para aniquilar a todos los procesos actuales
    private void aniquilarTodo(){
        System.out.println("Aniquilando todos los procesos...");
        try{
            System.out.println("Estos son todos los procesos que seran aniquilados: ");
            for(Proceso p:this.colaProcesosListos){
                System.out.println("\t"+p.nombreProceso());
                int id = p.procesoID(), bloques = p.memoriaOcupada();
                for(int j = 0; j < 64; j++){   
                    if(this.tablaMemoria[j][0] == id){
                        System.out.println("["+ String.valueOf(j) +"] -> 16 LOCALIDADES DE MEMORIA ERAN OCUPADAS POR EL ACTUAL PROCESO");
                        this.tablaMemoria[j][0] = 0;
                        bloques--;
                    }else if(bloques == 0)
                        break;
                }
                this.colaProcesosAniquilados.add(p);
                this.hayEspacio = true;
                this.espaciosFaltantes = 0;
            }
            this.colaProcesosListos.clear();
        }catch(Exception ex){
            System.out.println("No hay procesos para aniquilar.");
        }
        //Actualizar lista ligada de memoria
        this.actualizarLista();
    }
    
    //Metodo para desfragmentar la memoria
    private void desfragmentar(){
        Queue<Proceso> procesos = new LinkedList<>();
        int n = this.colaProcesosListos.size();
        int[] ids = new int[n], espacios = new int[n];
        int i = 0;
        try{
            for(Proceso p:this.colaProcesosListos){
                ids[i] = p.procesoID();
                espacios[i] = p.paginasCargadas();
                procesos.add(p);
                i++;
            }
        }catch(Exception ex){
            System.out.println("No hay procesos en memoria.");
            return;
        }

        i = 0;
        for(int j = 0; j<64; j++){
            if (i == n){
                while(j<64)
                    this.tablaMemoria[j++][0] = 0; 
                break;
            }
            int idActual = ids[i];
            int inicial = j;
            while(espacios[i]-- > 0){
                this.tablaMemoria[j++][0] = idActual;
            }
            i++;
            int last = j--;
            int[] marcos = new int[last - inicial];
            for(int l = 0; l<(last - inicial); l++)
                marcos[l] = inicial + l;
            Proceso actual = procesos.poll();
            if(!actual.getCargado()){
                actual.resetPaginas();
                actual.actualizarPaginas(marcos);
            }else
                actual.actualizarPaginas(marcos);
        }
        this.actualizarLista();
    }
    
    //Metodo para mostrar el menu principal
    public void mostrarMenu(){
        Scanner sc = new Scanner(System.in);
        boolean ejecutar = true;
        
        while(ejecutar){
            //Generando menu
            System.out.println("\t\t*****PLANIFICADOR DE PROCESOS*****");
            System.out.println("Seleccione la opcion a realizar: ");
            System.out.println("1) Crear proceso nuevo");
            System.out.println("2) Ver estado de los procesos");
            System.out.println("3) Ver estado de la memoria");
            System.out.println("4) Imprimir cola de procesos ");
            System.out.println("5) Ejecutar proceso actual");
            System.out.println("6) Ver proceso actual");
            System.out.println("7) Pasar al proceso siguiente");
            System.out.println("8) Aniquilar proceso actual");
            System.out.println("9) Aniquilar todo y terminar");
            System.out.println("10) Desfragmentar memoria");
            System.out.println("11) Salir del planificador");
            
            int opcion;
            try{
                opcion = Integer.valueOf(sc.nextLine());
            }catch(Exception ex){
                System.out.println("Entrada incorrecta. Ingrese unicamente el numero");
                continue;
            }
            
            switch(opcion){
                case 1:
                    System.out.println("Ingrese el nombre del nuevo proceso: ");
                    String nombre;
                    try{
                        nombre = sc.nextLine();
                    }catch(Exception ex){
                        System.out.println("Nombre no valido. Intente nuevamente");
                        break;
                    }
                    this.crearProceso(nombre);
                    break;
                   
                case 2:
                    this.verEstadoActual();
                    break;
                    
                case 3:
                    this.verLista();
                    break;
                    
                case 4:
                    this.mostrarProcesosActuales();
                    break;
                   
                case 5:
                    this.ejecutarProceso();
                    break;
                
                case 6:
                    this.procesoActual();
                    break;
                   
                case 7:
                    this.siguiente();
                    break;
                
                case 8:
                    this.aniquilarProceso();
                    break;
                   
                case 9:
                    this.aniquilarTodo();
                    break;
                
                case 10:
                    this.desfragmentar();
                    break;
                    
                case 11:
                    System.out.println("\t\t*****PLANIFICADOR DE PROCESOS FINALIZADO*****");
                    ejecutar = false;
                    break;
                   
                default:
                    System.out.println("Opcion no valida. Intente nuevamente");
            }
        }
    }
    
    private class BloqueMemoria{
        char id;
        int registroBase, longitud;
        
        BloqueMemoria(char id, int registroBase, int longitud){
            this.id = id;
            this.registroBase = registroBase;
            this.longitud = longitud;
        }
        
        @Override
        public String toString(){
            return "[ " + this.id + " | " + String.valueOf(this.registroBase) + 
                    " | "+ String.valueOf(this.longitud)+ " ]";
        }
    }
}
