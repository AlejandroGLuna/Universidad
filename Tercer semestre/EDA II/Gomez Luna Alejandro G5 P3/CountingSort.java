import java.util.Scanner;
import java.util.Arrays;

public class CountingSort{
	char[] elementos;
    int[] arregloAuxiliar;
    char[] arregloOrdenado;

	CountingSort(){
		this.elementos=new char[20];
        this.arregloAuxiliar=new int[20];
        this.arregloOrdenado=new char[21];
	}

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		CountingSort ordenar=new CountingSort();
		for(byte i=0;i<20;i++){
			System.out.println("Ingrese una letra en el rango A-J: ");
			ordenar.elementos[i]=sc.next().charAt(0);
			ordenar.elementos[i]=Character.toUpperCase(ordenar.elementos[i]);
		}
		System.out.println("Arreglo inicial:");
		for(byte a=0;a<20;a++)
			System.out.print(ordenar.elementos[a]+" ");

		System.out.println();
		for(byte x=0;x<20;x++)
			ordenar.arregloAuxiliar[(int)(ordenar.elementos[x]-65)]++;

		for(byte a=1;a<20;a++)
			ordenar.arregloAuxiliar[a]+=ordenar.arregloAuxiliar[a-1];

		for(byte a=0;a<20;a++)
			System.out.print(ordenar.arregloAuxiliar[a]+" ");

		System.out.println();
		for(byte j=19;j>-1;j--){
			ordenar.arregloOrdenado[ordenar.arregloAuxiliar[(int)(ordenar.elementos[j]-65)]]=ordenar.elementos[j];
			ordenar.arregloAuxiliar[(int)(ordenar.elementos[j]-65)]--;
			for(byte y=0;y<21;y++)
				System.out.print(ordenar.arregloOrdenado[y]+" ");
			System.out.println();
		}

		System.out.println("Arreglo final ordenado:");
		for(byte a=0;a<21;a++)
			System.out.print(ordenar.arregloOrdenado[a]+" ");

		System.out.println();
		for(byte a=0;a<20;a++)
			System.out.print(ordenar.arregloAuxiliar[a]+" ");
	}
}
