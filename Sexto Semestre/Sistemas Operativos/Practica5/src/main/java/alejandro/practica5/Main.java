package alejandro.practica5;

//Clase principal para ejecutar
public class Main {
    public static void main(String[] args) {
        //Creando la mesa en donde se encuentran los tenedores y platos, asi como la que sera asignada a los filosofos
        Mesa m = new Mesa(new int[5]);
        
        //Se le asgina a cada filosofo los tenedores que puede usar en la Mesa, asi como la respectiva mesa en donde se encuentran sentados todos
        Filosofo a = Filosofo.iniciar("Nietzsche", 0, 1, m);
        Filosofo b = Filosofo.iniciar("Descartes", 1, 2, m);
        Filosofo c = Filosofo.iniciar("Lao-Tse", 2, 3, m);
        Filosofo d = Filosofo.iniciar("Socrates", 3, 4, m);
        Filosofo e = Filosofo.iniciar("Kant", 4, 0, m); 
    }
}

//Clase que sirve para modelar los atributos y acciones de cada uno de los filosofos
class Filosofo implements Runnable{
    Thread hilo;    //Hilo de ejecucion asociado
    String nombre;  //Nombre del filosofo
    int tenedorIzq, tenedorDer; //Indices a los que podra acceder, que representan sus respectivos tenedores izquierdo y derecho.
    Mesa m;         //La mesa asociada en la que se encuentra el filosofo

    //Se inicializan los atributos de la clase
    public Filosofo(String nombre, int indiceIzq, int indiceDer, Mesa m){
        this.nombre = nombre;
        this.hilo = new Thread(this,nombre);
        this.tenedorIzq = indiceIzq;
        this.tenedorDer = indiceDer;
        this.m = m;
    }
    
    //Metodo con el cual, en cuanto se crea un filosofo, inicie su ejecucion
    public static Filosofo iniciar (String nombre,int indiceIzq, int indiceDer, Mesa m){
        Filosofo miHilo=new Filosofo(nombre, indiceIzq, indiceDer, m);
        miHilo.hilo.start(); //Inicia el hilo
        return miHilo;
    }

    /*En este metodo se implementa la accion de comer de cada filosofo, en la cual se le pasa como parametro
    un tiempo de espera determinado, el cual representa el tiempo que se le dara a cada filosofo para que 
    coma. Asimismo, en este metodo se habilita que dos filosofos no adyacentes puedan comer al mismo 
    tiempo, pues no comparten tenedores.
    */
    private void comer(int tiempoEspera){
        //Se muestra en pantalla que un determinado filosofo esta dispuesto a comer
        System.out.println("El filosofo " + this.nombre + " quiere comer");
        
        /* Se utiliza un bloque synchronized para que solamente un hilo pueda acceder al monitor 
        asociado con el objeto mesa a la vez y pueda ejecutar sus diversos metodos adecuadamente.
        */
        synchronized(this.m){
            //Primero, se verifica que sus tenedores izquierdo y derecho esten disponibles
            if (this.m.disponible(this.tenedorIzq) && this.m.disponible(this.tenedorDer)){
                //En caso de estar disponibles, los ocupa y libera el monitor.
                this.m.ocuparTenedor(this.tenedorIzq);
                this.m.ocuparTenedor(this.tenedorDer);
            }else if(this.m.disponible(this.tenedorIzq)){
                /*En caso de que solo este disponible su tenedor izquierdo, lo ocupa y revisa cada
                segundo durante tres segundos si se desocupa el tenedor derecho. 
                En caso de que se desocupe, lo ocupa y libera el monitor. En caso contrario, 
                desocupa su tenedor y posteriormente intentara comer nuevamente. 
                */
                this.m.ocuparTenedor(this.tenedorIzq);
                int x = 0;
                try{
                    while(x < 3 && !this.m.disponible(this.tenedorDer)){
                        Thread.sleep(1000);
                        x++;
                    }
                    if(x == 3){
                        this.m.liberarTenedor(this.tenedorIzq);
                        return;
                    }else
                        this.m.ocuparTenedor(this.tenedorDer);
                }catch(InterruptedException iE){
                    System.out.println("Hubo un error" + iE.getMessage());
                }
            }else if(this.m.disponible(this.tenedorDer)){
                //Mismo procedimiento que en el caso anterior pero con su tenedor derecho y en espera del izquierdo
                this.m.ocuparTenedor(this.tenedorDer);
                int x = 0;
                try{
                    while(x < 3 && !this.m.disponible(this.tenedorIzq)){
                        Thread.sleep(1000);
                        x++;
                    }
                    if(x == 3){
                        this.m.liberarTenedor(this.tenedorDer);
                        return;
                    }else
                        this.m.ocuparTenedor(this.tenedorIzq);
                }catch(InterruptedException iE){
                    System.out.println("Hubo un error" + iE.getMessage());
                }
            }else
                //Si no hay ningun tenedor listo, solamente libera el monitor y posteriormente intentara comer
                return;
        }
        
        //El filosofo ha empezado a comer
        System.out.println("El filosofo " + this.nombre + " ha empezado a comer");
        
        //Durmiendo al hilo, que es el tiempo en el que un filosofo esta comiendo
        try{
            Thread.sleep(tiempoEspera);
            /*El filosofo ha terminado de comer, por lo tanto libera sus tenedores.
            Para este caso no es necesario el bloque synchronized, ya que pueden acceder dos hilos
            a la vez al arreglo para liberar sus respectivos localidades en el arrelgo tenedores, 
            pues solo puede haber dos filosofos no adyacentes comiendo simultaneamente, por lo que
            modificaran regiones distintas del arreglo. 
            */
            this.m.liberarTenedores(tenedorIzq, tenedorDer);
            System.out.println("El filosofo " + this.nombre + " ha terminado de comer");
            Thread.sleep(1000);
        }catch(InterruptedException iE){
            System.out.println("Ha ocurrido un problema con el filosofo");
            System.out.println(iE.getMessage());
        }
    }

    @Override
    public void run(){
        /*Se ejecutara por siempre este metodo, teniendo que siempre existe un intervalo de dos segundos
        entre cada vez que un filosofo come o intenta comer. 
        */
        while(true){
            try{
                this.comer(5000);
                Thread.sleep(1000);
            }catch(InterruptedException iE){
                System.out.println("Hubo un error" + iE.getMessage());
            }
        }
    }
}

//Clase que sirve para modelar la mesa con los tenedores
class Mesa{
    /*Arreglo que simula cada uno de los tenedores en la mesa y al cual solo podran acceder dos 
    filosofos no adyacentes a la vez. 
    */
    public int[] tenedores;
    
    //Se inicializa el arreglo de tenedores
    Mesa(int[] tenedores){
        this.tenedores = tenedores;
    }
    
    //Metodo para verificar si un tenedor esta disponible
    public boolean disponible(int i){
        return (this.tenedores[i] == 0);
    }
    
    //Metodo para ocupar un tenedor. El 1 significa que esta en uso
    public void ocuparTenedor(int i){
        this.tenedores[i] = 1;
    }
    
    //Metodo para liberar ambos tenedores. El 0 significa que estan disponibles
    public void liberarTenedores(int izq, int der){
        this.tenedores[izq] = 0;
        this.tenedores[der] = 0;
    }
    
    //Metodo para liberar un tenedor. El 0 significa que esta disponible
    public void liberarTenedor(int izq){
        this.tenedores[izq] = 0;
    }
}