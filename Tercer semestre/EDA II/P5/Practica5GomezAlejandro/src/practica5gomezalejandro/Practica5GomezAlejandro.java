package practica5gomezalejandro;

import java.util.Scanner;
        
public class Practica5GomezAlejandro {

    public static void main(String[] args) {
       Scanner sc=new Scanner(System.in);
       byte opcion;
       boolean repetir=true;
       while(repetir){
        System.out.println("Ingresa la opcion a realizar:\n1)Funcion hash por modulo\n2)Encadenamiento\n3)Manejo de Tablas Hash en Java\n4)Salir");
        opcion=sc.nextByte();
        switch(opcion){
            case 1:
                HashModulo a=new HashModulo();
                a.menuHashModulo();
                break;
            case 2:
                Encadenamiento b=new Encadenamiento();
                b.menu();
                break;
            case 3:
                TablasHash c=new TablasHash();
                c.probando();
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
