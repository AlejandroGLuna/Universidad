import java.util.concurrent.Semaphore;
import java.util.Arrays;

class EscritoresLectores{
	public static Semaphore mutexLectores = new Semaphore(5);
	public static Semaphore mutexEscritor = new Semaphore(1);
	public static int lectores = 0;

	public static void main(String[] args) {
		System.out.println("Inicio de programa");
		String[] vector = {"vacio", "vacio", "vacio", "vacio", "vacio", "vacio"};


		Escritor[] escritores = new Escritor[5];
		Thread[] escritoresThreads = new Thread[5];
		for(int i = 0; i<5; i++){
			escritores[i] = new Escritor(vector, ("Escritor " + String.valueOf(i)));
			escritoresThreads[i] = new Thread(escritores[i]);
			escritoresThreads[i].start();
		}

		Lector[] lectores = new Lector[5];
		Thread[] lectoresThreads = new Thread[5];
		for(int i = 0; i<5; i++){
			lectores[i] = new Lector(vector, ("Lector " + String.valueOf(i)));
			lectoresThreads[i] = new Thread(lectores[i]);
			lectoresThreads[i].start();
		}
	}
}

class Escritor implements Runnable{
	private String[] v;
	private String nombreEscritor;

	Escritor(String[] v, String nombre){
		this.v = v;
		this.nombreEscritor = nombre;
	}

	public void run(){ 
		try{
			while(true){
				System.out.println(this.nombreEscritor + " quiere escribir");
				if(EscritoresLectores.lectores == 0){
					EscritoresLectores.mutexEscritor.acquire();
					EscritoresLectores.mutexLectores.acquire(5);
					int n = (int) (Math.random() * 6);
					this.v[n] = this.nombreEscritor;
					System.out.println(this.nombreEscritor + " escribira en la posicion:" + String.valueOf(n));
					Arrays.stream(this.v).forEach( s -> System.out.println(this.nombreEscritor + " dice " + s));
					System.out.println(this.nombreEscritor + " temino de escribir");
					EscritoresLectores.mutexLectores.release(5);
				}
				Thread.sleep(2000);
			}
		}
		catch (InterruptedException iE){
			System.out.print("Hubo un error");
		}
	}
 
}

class Lector implements Runnable{
	private String[] v;
	private String nombreLector;
	private int contadorLecturas = 0;

	Lector(String[] v, String nombre){
		this.v = v;
		this.nombreLector = nombre;
	}

	public void run(){
		try{
			while(true){
				System.out.println(this.nombreLector + " quiere leer");
				
				EscritoresLectores.mutexLectores.acquire();
				EscritoresLectores.lectores++;
				System.out.println(this.nombreLector + " esta leyendo");
				Arrays.stream(this.v).forEach( s -> System.out.println(this.nombreLector + " dice " + s + " en la posicion " + String.valueOf(this.contadorLecturas++)) );
				System.out.println(this.nombreLector + " termino de leer");
				this.contadorLecturas = 0;
				EscritoresLectores.lectores--;
				EscritoresLectores.mutexLectores.release();

				if(EscritoresLectores.lectores == 0)
					EscritoresLectores.mutexEscritor.release();

				Thread.sleep(2000);
			}
		}catch (InterruptedException iE){
			System.out.println("Hubo un error");
		}
	}
}
