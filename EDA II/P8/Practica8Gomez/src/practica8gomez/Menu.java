package practica8gomez;

import java.util.Scanner;

public class Menu {
    static void generarMenu(){
        Scanner sc=new Scanner(System.in);
        boolean repetirse=true;
        do{
            System.out.println("\t***Bienvenido al programa de arboles, seleccione el arbol sobre el que quiere trabajar:");
            System.out.println("1)Arboles binarios\n2)Arboles binarios de busqueda");
            byte opcion=sc.nextByte();
            boolean repetirseArBin=true;
            if(opcion==1){
                do{
                    System.out.println("Seleccione la accion a realizar:");
                    System.out.println("1.Agregar\n2.Eliminar\n3.BFS\n4.Notacion Prefija\n5.Notacion Infija\n6.Notacion Posfija\n7.Salir");
                    opcion=sc.nextByte();
                    switch(opcion){
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                        case 5:
                            break;
                        case 6:
                            break;
                        case 7:
                            repetirseArBin=false;
                            break;
                        default:
                            System.out.println("Opcion incorrecta");
                    }
                }while(repetirseArBin);
            }else if(opcion==2){
                do{
                    System.out.println("Seleccione la accion a realizar:");
                    System.out.println("1.Agregar\n2.Eliminar\n3.Buscar\n4.Imprimir Arbol(BFS)\n5.Salir");
                    opcion=sc.nextByte();
                    switch(opcion){
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                        case 5:
                            repetirseArBin=false;
                            break;
                        default:
                            System.out.println("Opcion incorrecta");
                    }
                }while(repetirseArBin);
            }else
                System.out.println("Opcion incorrecta");
        }while(repetirse);
    }
}
