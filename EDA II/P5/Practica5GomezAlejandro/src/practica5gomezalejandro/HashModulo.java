package practica5gomezalejandro;

import java.util.LinkedList;
import java.util.Scanner;

public class HashModulo {
    private LinkedList<Integer> lista=new LinkedList();
    
    HashModulo(){
        for(byte x=0;x<20;x++)
            lista.add(null);
    }
    
    private void agregarElemento(int x){
        int h=x%19;
        if(lista.get(h)==null)
            lista.set(h,x);
        else{
            int posI=h;
            if((h++)==(lista.size()-1))
                    h=0;
            while(lista.get(h)!=null && h!=posI){
                if((++h)>=(lista.size()-1))
                    h=0;
            }
            if(posI==h){
                System.out.println("La lista esta llena");
                return;
            }
            else
                lista.set(h,x);
        }
        System.out.println("Elemento "+x+" agreagdo en la posicion "+h);
    }
    
    private void imprimirLista(){
        for(Integer x:lista)
            System.out.println(x);
    }
    
    private void buscarElemento(int x){
        int h=x%19;
        if(lista.get(h)==null){
            System.out.println("El elemento no se encuentra en la lista");
            return;
        }
        if(lista.get(h)==x)
            System.out.println("Elemento "+x+" encontrado en la posicion "+h);
        else{
            boolean encontrado=false;
            int posI=h;
            if((h++)==(lista.size()-1))
                h=0;
            while(lista.get(h)!=null && h!=posI){
                if(lista.get(h)==x){
                    encontrado=true;
                    break;
                }
                if((++h)>=(lista.size()-1))
                    h=0;
            }
            if(encontrado)
                System.out.println("Elemento "+x+" encontrado en la posicion "+h);
            else
                System.out.println("El elemento no se encuentra en la lista");
        }
    }
    
    void menuHashModulo(){
        boolean repetir=true;
        Scanner sc=new Scanner(System.in);
        byte opcion;
        while(repetir){
            System.out.println("Selecciona una de las siguientes opciones:\n1)Agregar elemento\n2)Imprimir lista\n3)Buscar elemento\n4)Salir");
            opcion=sc.nextByte();
            switch(opcion){
                case 1:
                    int o;
                    System.out.println("Ingrese el elemento a adicionar a la lista:");
                    o=sc.nextInt();
                    agregarElemento(o);
                    break;
                case 2:
                    System.out.println("Los elementos en la lista son:");
                    imprimirLista();
                    break;
                case 3:
                    int a;
                    System.out.println("Ingrese el elemento a buscar en la lista:");
                    a=sc.nextInt();
                    buscarElemento(a);
                    break;
                case 4:
                    repetir=false;
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        }
    }
}
