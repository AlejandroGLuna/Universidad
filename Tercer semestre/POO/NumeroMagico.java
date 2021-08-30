//Se importa la clase Scanner para poder trabajar con la entrada de datos del usuario
import java.util.Scanner;

//Se crea la clase NumeroMagico
public class NumeroMagico{
	//Esta clase tiene dos miembros: numeroMagico e intentos. Son de tipo Byte ambos, puesto que
	//sus valores estan entre un rango de 0 y de 7.
	byte numeroMagico=(byte)(Math.random()*7); //Usando el metodo random
    //dentro de la clase Math se genera un numero aleatorio entre 0 y 7.
	byte intentos=1;

	//Se crea el metodo principal main
	public static void main(String[] args) {
		System.out.println("\t***Bienvenido al juego del numero magico***");
		//Se crea el tipo de dato primitivo byte para almacenar el valor del usuario
		byte numeroUsuario=0;
		//Se crean laos objetos sc y juego de las clases Scanner y NumeroMagico respectivamente
		//con la finalidad de poder utilizar sus metodos y atributos.
		Scanner sc=new Scanner(System.in);
		NumeroMagico juego=new NumeroMagico();
		//Se entra a una estrcutra de control while que se dejara de ejecutar cuando se le acaben
		//los intentos al usuario.
		while(juego.intentos<4){
			System.out.println("Escriba un numero entre el 0 y el 7:");
			//Se guarda en la variable numeroUsuario el digito que ha puesto el usuario
			numeroUsuario=sc.nextByte();
			//Si el numero puesto por el usuario y el numero magico coinciden entonces se 
			//se mandara en pantalla que ha gando y se saldra del ciclo while actual.
			if(numeroUsuario==juego.numeroMagico){
				System.out.println("Ha ganado!\nEl numero magico era: "+juego.numeroMagico);
				break;
			}else{
				//En caso contrario, se mandara en pantalla que aun no ha adivinado el numero
				//y se aumentara en uno la variable que lleva la cuenta de los intentos realizados.
				System.out.println("Ese no es el numero magico. Siga intentando\n");
				juego.intentos++;
			}
		}
		//Si el usuario ha agotado sus intentos, entonces saldra este mensaje, diciendole que ha
		//perdido y cual era el numero magico.
		if(juego.intentos>3)
			System.out.println("Ha perdido.\nEl numero magico era: "+juego.numeroMagico);
		//Se cierra el flujo de datos de entrada que se utilizaba para leer el numero que
		//ingresaba el usuario.
		sc.close();
	}
}
