//Se importa la clase Scanner para poder trabajar con la entrada de datos del usuario.
import java.util.Scanner;

//Se crea la clase Palindromo.
public class Palindromo{

	//Se crea el metodo principal main
	public static void main(String[] args) {
		//Se crea un objeto de la clase Scanner para poder leer los datos que ingrese el usuario.
		Scanner sc=new Scanner(System.in);
		System.out.println("Ingrese una frase o palabra para saber si es palindromo");
		//Se guarda la palabra o frase en la variable palabraElegida que es de tipo String
		String palabraElegida=sc.nextLine();
		//Con el metodo replaceAll dentro de la clase String se eliminan todos los espacios 
		//que se encuentren, con la finalidad de solo comparar las letras de la frase.
		palabraElegida=palabraElegida.replaceAll(" ","");
		//Con el metodo toLowerCase dentro de la clase String se asegura que todas las letras
		//de la palabra ingresada no sean caso sensitive.
		palabraElegida=palabraElegida.toLowerCase();
		//Se crea una instancia de la clase StringBuffer y se le pasa como parametro 
		//a su constructor el string que contiene la frase o palabra del usuario.
		StringBuffer palabraVolteada=new StringBuffer(palabraElegida);
		//Se utiliza el StringBuffer para poder acceder a su metodo reverse, el cual nos permite
		//que toda la frase o palabra sea puesta de manera inversa.
		palabraVolteada=palabraVolteada.reverse();
		//Por ultimo, se convierte el StringBuffer en un string, con la finalidad de poder 
		//compararlo con el string que contiene la frase original.
		String palindromo=palabraVolteada.toString();
		//Se hace la comparaciones entre la frase o palabra original y la frase o palabravolteada. 
		//Si resultan ser iguales entonces son palindromos, en caso contrario no lo son. 
		if(palabraElegida.equals(palindromo))
			System.out.println("\nEs un palindromo");
		else
			System.out.println("\nNO es un palindromo");
		//Se cierra el flujo de datos de entrada que se utilizaba para leer la frase o 
		//palabra que ingresaba el usuario.
		sc.close();
	}
}