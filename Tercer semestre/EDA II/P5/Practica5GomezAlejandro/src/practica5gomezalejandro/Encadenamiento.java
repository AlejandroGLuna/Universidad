package practica5gomezalejandro;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Encadenamiento {
    private LinkedList<LinkedList <Integer>> lista=new LinkedList();
    
    Encadenamiento(){
        for(byte x=0;x<15;x++)
            lista.add(new LinkedList());
    }
    
    private void imprimirLista(){
        byte n=0;
        for(LinkedList<Integer> x:lista){
            System.out.print("["+n+"] -> ");
            for(Integer y:x)
                System.out.print(y+" -> ");
            System.out.println();
            n++;
        }
    }
    
    private void agregarElemento(int x){
        Random n=new Random();
        byte posicion=(byte)n.nextInt(15);
        System.out.println("El elemento "+x+" se agregara en la poisicon "+posicion);
        lista.get(posicion).add(x);
        imprimirLista();
    }
    
    void menu(){
        boolean repetir=true;
        Scanner sc=new Scanner(System.in);
        byte opcion;
        while(repetir){
            System.out.println("Selecciona una de las siguientes opciones:\n1)Agregar elemento\n2)Salir");
            opcion=sc.nextByte();
            switch(opcion){
                case 1:
                    int o;
                    System.out.println("Ingrese el elemento a adicionar a la lista:");
                    o=sc.nextInt();
                    agregarElemento(o);
                    break;
                case 2:
                    repetir=false;
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        }
    }
}
