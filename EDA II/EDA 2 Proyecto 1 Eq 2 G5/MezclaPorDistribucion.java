
/*Importando las clases necesarias para poder trabajar con creacion de archivos, escribir sobre un archivo, leer elementos de un
archivo y poder realizar un manejo correcto de las excepciones provocadas.
*/
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.NoSuchElementException;

public class MezclaPorDistribucion{
	/*Atributo de la clase donde se guardara el archivo que se ordenara. Se pone privado porque no se
	no se quiere que pueda ser modificado ni visto por otras clases.*/
	private File archivoOrdenar;

	//Constructor para inicializar el atributo de la clase el cual almacena el archivo que se ordenara.
	MezclaPorDistribucion(File archivoOrdenar){
		this.archivoOrdenar=archivoOrdenar;
		//En caso de que se hayan dejado los archivos auxiliares en alguna ejecucion pasada, se eliminaran o, en su defecto, vaciaran.
		for(byte y=0;y<10;y++){
			File auxiliarResidual=new File("colaAux"+String.valueOf(y)+".txt");
			if(auxiliarResidual.exists()){
				if(!auxiliarResidual.delete()){
					try{
						PrintWriter emptyfile=new PrintWriter(auxiliarResidual);
						emptyfile.close();
					}catch(FileNotFoundException eFNF){
						System.out.println("No se ha podido vaciar el archivo auxiliar residual");
					}
				}
			}
		}
	}

	void ordenarPorDistribucion(boolean ascendente){
		float numeroMayor=0f;
		Scanner leerArchivo=null;
		//Objeto de la clase Scanner usado para leer el contenido del archivo
		try{
			leerArchivo=new Scanner(this.archivoOrdenar);
		}catch(FileNotFoundException eFNF){
			System.out.println("Se ha producido un error para leer el archivo con los elementos");
		}
		//Modificando el delimitante por default para hacer que los elementos se lean separados por comas.
		leerArchivo.useDelimiter(",");
		//Leyendo cada elemento del archivo.
		while(leerArchivo.hasNext()){
			try{
				float a=Float.parseFloat(leerArchivo.next());
				//Encontrando el numero mas grande en el archivo.
				if(a>numeroMayor)
					numeroMayor=a;
			}catch(NoSuchElementException eNSE){}
		}
		leerArchivo.close();
		/*Multiplicando el numero mas grande por cien y obteniendo su longitud, pues esto nos indicara
		que tantas veces sera necesario repetir el programa para ordenar todos los elementos.*/
		numeroMayor*=100;
		String num=Float.toString(numeroMayor);
		char caracterActual=0;
		//Realizando el ordnamiento por distribucion.
		for(byte p=1;p<num.length();p++){
			Scanner leerNumeros=null;
			try{
				leerNumeros=new Scanner(this.archivoOrdenar);
			}catch(FileNotFoundException eFNF){
				System.out.println("Se ha producido un error para leer el archivo con los elementos");
			}
			leerNumeros.useDelimiter(",");
			while(leerNumeros.hasNext()){
				//Multiplicando el numero actual por cien, con la finalidad de ver todo el numero como un entero.
				Integer numActualSinDecimal=Math.round((Float.parseFloat(leerNumeros.next())*100));
				String elementoActual=numActualSinDecimal.toString();
				/*Si el numero actual ya no tiene mas digitos, entonces los digitos faltantes para igualar a la longitud del
				numero mas grande se tomaran en cuanto como ceros. Cuando se lanza la excepcion StringIndexOutOfBoundsException
				es cuando sabemos que el numero ya no tiene mas digitos*/
				try{
					caracterActual=(elementoActual.charAt(elementoActual.length()-p));
				}catch(StringIndexOutOfBoundsException stringIOBE) {
					caracterActual='0';
				}
				//Se trata la excepcion en caso de que se interrumpa el flujo de datos con el archivo.
				try{
					//Creando un BufferedWriter para escribir el elemnto actual en el archivo correspondiente.
					BufferedWriter archivo=new BufferedWriter(new FileWriter("cola"+String.valueOf(caracterActual)+".txt",true));
					//Dividiendo el elemento actual entre cien para regresarlo a su estado original y escribirlo en el archivo.
					String numActualConDecimal=String.valueOf((Float.valueOf(elementoActual)/100));
					archivo.write(numActualConDecimal+",");
					archivo.close();
				}catch(IOException eIO){
					System.out.println("Se ha interrumpido el flujo de datos");
				}
			}
			leerNumeros.close();
			//Vaciando el contenido del archivo original para acomodar todos los elementos en este.
			try{
				PrintWriter emptyfile=new PrintWriter(this.archivoOrdenar);
				emptyfile.close();
			}catch(FileNotFoundException eFNF){
				System.out.println("No se ha podido vaciar el archivo original");
			}
			System.out.print("\t\nNumero de iteracion "+p);
			if(ascendente){
				//Recuperando los elementos escritos en los archivos creados por cada digito de manera ascendente.
				for(byte n=0;n<10;n++){
					File archivoActual=new File("cola"+String.valueOf(n)+".txt");
					//Creando un archivo auxiliar que sera visualizado por el usuario
					File archivoAuxiliarActual=new File("colaAux"+String.valueOf(n)+".txt");
					if(archivoActual.exists()){
						Scanner sc=null;
						try{
							sc=new Scanner(archivoActual);
						}catch(FileNotFoundException eFNF){
							System.out.println("Se ha producido un error para leer los elementos en los archivos auxiliares");
						}
						sc.useDelimiter(",");
                        System.out.print("\n*Elementos en el archivo auxiliar cola"+String.valueOf(n)+": ");
						try{
							BufferedWriter archivo=new BufferedWriter(new FileWriter(this.archivoOrdenar,true));
							BufferedWriter archivoAux=new BufferedWriter(new FileWriter(archivoAuxiliarActual,true));
							archivoAux.write("***Iteracion "+String.valueOf(p));
							archivoAux.newLine();
							while(sc.hasNext()){
								/*Escribiendo todos los elementos del archivo correspondiente al digito de la iteracion
								en el archivo original y archivo auxiliar correspondiente.*/
								String numAEscribir=sc.next();
								System.out.print(numAEscribir+",");
								archivo.write(numAEscribir+",");
								archivoAux.write(numAEscribir+",");
							}
							archivoAux.newLine();
							archivo.close();
							archivoAux.close();
						}catch(Exception e){}
						sc.close();
						/*Borrando el archivo auxiliar correspondiente al digito de la iteracion.
						En caso de que no se pueda borrar, se vacia el contenido del archivo*/
						if(!archivoActual.delete()){
							try{
								PrintWriter emptyfile=new PrintWriter(archivoActual);
								emptyfile.close();
							}catch(FileNotFoundException eFNF){
								System.out.println("No se ha podido vaciar el archivo actual");
							}
						}
					}
				}
			}else{
				//Recuperando los elementos escritos en los archivos creados por cada digito de manera descendente
				for(byte n=9;n>=0;n--){
					File archivoActual=new File("cola"+String.valueOf(n)+".txt");
					//Creando un archivo auxiliar que sera visualizado por el usuario
					File archivoAuxiliarActual=new File("colaAux"+String.valueOf(n)+".txt");
					if(archivoActual.exists()){
						Scanner sc=null;
						try{
							sc=new Scanner(archivoActual);
						}catch(FileNotFoundException eFNF){
							System.out.println("Se ha producido un error para leer los elementos en los archivos auxiliares");
						}
						sc.useDelimiter(",");
						System.out.print("\n*Elementos en el archivo auxiliar cola"+String.valueOf(n)+": ");
						try{
							BufferedWriter archivo=new BufferedWriter(new FileWriter(this.archivoOrdenar,true));
							BufferedWriter archivoAux=new BufferedWriter(new FileWriter(archivoAuxiliarActual,true));
							archivoAux.write("***Iteracion "+String.valueOf(p));
							archivoAux.newLine();
							while(sc.hasNext()){
								/*Escribiendo todos los elementos del archivo correspondiente al digito de la iteracion
								en el archivo original y archivo auxiliar correspondiente.*/
								String numAEscribir=sc.next();
								System.out.print(numAEscribir+",");
								archivo.write(numAEscribir+",");
								archivoAux.write(numAEscribir+",");
							}
							archivoAux.newLine();
							archivo.close();
							archivoAux.close();
						}catch(Exception e){}
						sc.close();
						/*Borrando el archivo auxiliar correspondiente al digito de la iteracion.
						En caso de que no se pueda borrar, se vacia el contenido del archivo*/
						if(!archivoActual.delete()){
							try{
								PrintWriter emptyfile=new PrintWriter(archivoActual);
								emptyfile.close();
							}catch(FileNotFoundException eFNF){
								System.out.println("No se ha podido vaciar el archivo actual");
							}
						}
					}
				}
			}
		}
		//Imprimiendo los elementos ya ordenados en pantalla.
		System.out.println("\n***Lista ordenada:");
		Scanner resultado=null;
		try{
			resultado=new Scanner(this.archivoOrdenar);
		}catch(FileNotFoundException eFNF){
			System.out.println("Se ha producido un error para mostrar la lista ordenada");
		}
		while(resultado.hasNext()){
			System.out.print(resultado.next()+" ");
		}
		resultado.close();
	}

	public static void main(String[] args) {
		File numeros=new File("Numeros.txt");
		MezclaPorDistribucion a=new MezclaPorDistribucion(numeros);
		a.ordenarPorDistribucion(true);
	}
}
