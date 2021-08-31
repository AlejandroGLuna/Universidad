import java.util.Random;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;

public class ArchivoRandom{
	ArchivoRandom() throws IOException{
		File numsRandom=new File("Numeros.txt");
		Random n=new Random();
		BufferedWriter archivo=new BufferedWriter(new FileWriter(numsRandom,true));
		for(short x=0;x<256;x++){
			Float a=(float)(Math.round(((n.nextFloat()*120)*100.0))/100.0);
			System.out.print(a+" ");
			archivo.write(a.toString()+",");
		}
		System.out.print("123");
		archivo.write("123");
		archivo.close();
	}

	public static void main(String[] args) throws IOException{
		ArchivoRandom x= new ArchivoRandom();
	}
}
