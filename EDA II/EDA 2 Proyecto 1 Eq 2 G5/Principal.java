//Importando las clases necesarias para leer datos ingresados por el usuario, buscar el archivo a ordenar, y tratar excepciones.
import java.util.Scanner;
import java.io.File;
import java.util.InputMismatchException;

public class Principal{
	//Metodo donde se crea el menu principal.
	void menu(){
		Scanner sc=new Scanner(System.in);
		do{
			String nombreArchivo;
			Byte opcionOrdenamiento=0;
			char opcionForma,repetir;
			boolean ascendente;
			System.out.println("\t***Bienvenido al programa de ordenamiento externo***");
			System.out.println("Ingrese el nombre completo del archivo que quiere ordenar:");
			nombreArchivo=sc.next();
			//Creando un archivo con el nombre brindado por el usuario y verificando que efectivamente existe.
			File archivoAOrdenar=new File(nombreArchivo);
			if(archivoAOrdenar.exists()){
				System.out.println("Ingrese el metodo de ordenamiento que quiere realizar:\n1)Polifase\n2)Mezcla Equilibrada\n3)Mezcla por Distribucion");
				//Verficando que el usuario ingrese un numero, y que este no exceda el valor de un byte.
				try{
					opcionOrdenamiento=sc.nextByte();
				}catch(InputMismatchException eIM){
					System.out.println("Opcion no valida, intenta nuevamente");
					continue;
				}
				System.out.println("Ingrese el criterio a seguir:\na)Ascendente\nb)Descendente");
				//Forzando a que solamente se lea un caracter.
				opcionForma=sc.next().charAt(0);
				opcionForma=Character.toUpperCase(opcionForma);
				if(Character.compare(opcionForma,'A')==0)
					ascendente=true;
				else if(Character.compare(opcionForma,'B')==0)
					ascendente=false;
				else{
					System.out.println("Opcion no valida, intenta nuevamente");
					continue;
				}
				//Realizando el ordenamiento correspondiente, si se ingreso una opcion valida, junto con su criterio correspondiente.
				switch(opcionOrdenamiento){
					case 1:
						break;
					case 2:
						break;
					case 3:
						MezclaPorDistribucion a=new MezclaPorDistribucion(archivoAOrdenar);
						a.ordenarPorDistribucion(ascendente);
						break;
					default:
						System.out.println("Opcion ingresada no valida");
				}
			}else
				System.out.println("Archivo no encontrado");
			//Sentencias para verificar si el usuario quiere o no repetir el programa.
			System.out.println("Desea salir del programa?(S/N)");
			repetir=sc.next().charAt(0);
			repetir=Character.toUpperCase(repetir);
			if(Character.compare(repetir,'S')==0)
				break;
		}while(true);
		sc.close();
	}
	//Metodo principal, donde se ejecutara todo el programa correspondiente a ordenamiento externo.
	public static void main(String[] args) {
		Principal a=new Principal();
		a.menu();
	}
}