import java.util.ArrayList;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList; 

public class RadixSort{
	ArrayList<Integer> elementos;
    private Queue<Integer> q0;
	private Queue<Integer> q1;
	private Queue<Integer> q2;
	private Queue<Integer> q3;
	private Queue<Integer> q4;

	RadixSort(){
		this.elementos=new ArrayList<Integer>();
        this.q0=new LinkedList<>();
        this.q1=new LinkedList<>();
        this.q2=new LinkedList<>();
        this.q3=new LinkedList<>();
        this.q4=new LinkedList<>();
	}

	void radixSort(){
		int repeticiones=((String.valueOf(this.elementos.get(0)).length())-1);
		for(;repeticiones>-1;repeticiones--){
			for(byte x=0;x<this.elementos.size();x++){
				char digitoActual=String.valueOf(this.elementos.get(x)).charAt(repeticiones);
				switch(digitoActual){
					case '0':
						q0.add(this.elementos.get(x));
						break;
					case '1':
						q1.add(this.elementos.get(x));
						break;
					case '2':
						q2.add(this.elementos.get(x));
						break;
					case '3':
						q3.add(this.elementos.get(x));
						break;
					case '4':
						q4.add(this.elementos.get(x));
						break;
					default:
						System.out.println("Ha ingresado una cantidad de digitos incorrecta");
				}
			}
			this.elementos.clear();
			while(q0.peek()!=null)
				this.elementos.add(q0.poll());
			while(q1.peek()!=null)
				this.elementos.add(q1.poll());
			while(q2.peek()!=null)
				this.elementos.add(q2.poll());
			while(q3.peek()!=null)
				this.elementos.add(q3.poll());
			while(q4.peek()!=null)
				this.elementos.add(q4.poll());

			System.out.print("Valores de la lista: ");
			for(Integer numeros:this.elementos){
				System.out.print(numeros.intValue()+" ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		RadixSort ordenar=new RadixSort();
		byte opcion;
		boolean repetir=true;
		while(repetir){
			System.out.println("\n\t***RADIX SORT***\nSeleccione la opcion que desea:");
			System.out.println("1)Insertar un nuevo elemento\n2)Ordenar los elementos\n3)Salir");
			opcion=sc.nextByte();
			switch(opcion){
				case 1:
					System.out.println("Ingrese un numero de cuatro digitos: ");
					ordenar.elementos.add(sc.nextInt());
					break;
				case 2:
					System.out.print("Arreglo inicial: ");
					for(Integer inicial:ordenar.elementos){
						System.out.print(inicial.intValue()+" ");
					}
					System.out.println();
					ordenar.radixSort();
					System.out.print("Arreglo ordenado: ");
					for(Integer sorted:ordenar.elementos){
						System.out.print(sorted.intValue()+" ");
					}
					break;
				case 3:
					repetir=false;
					break;
				default:
					System.out.println("Opcion no valida");
			}
		}
	}
}
