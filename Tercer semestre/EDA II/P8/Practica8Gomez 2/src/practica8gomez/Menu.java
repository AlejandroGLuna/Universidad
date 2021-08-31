package practica8gomez;

import java.util.Scanner;
import java.util.LinkedList;

public class Menu {
    static void generarMenu(){
        Scanner sc=new Scanner(System.in);
        boolean repetirse=true;
        do{
            System.out.println("\t***Bienvenido al programa de arboles, seleccione el arbol sobre el que quiere trabajar:");
            System.out.println("1)Arboles binarios\n2)Arboles binarios de busqueda\n3)Salir");
            byte opcion=sc.nextByte();
            boolean repetirseArBin=true;
            if(opcion==1){
                ArbolBin Ab=new ArbolBin();
                do{
                    System.out.println("Seleccione la accion a realizar:");
                    System.out.println("1.Agregar\n2.Eliminar\n3.BFS\n4.Notacion Prefija\n5.Notacion Infija\n6.Notacion Postfija\n7.Salir");
                    opcion=sc.nextByte();
                    switch(opcion){
                        case 1:
                            System.out.println("Se creara tu arbol binario");
                            Ab.crearABin(sc);
                            break;
                        case 2:
                            System.out.println("Dame el valor del nodo a eliminar");
                            int x=Integer.parseInt(sc.next());
                            Ab.eliminar(x);
                            break;
                        case 3:
                            System.out.println("Busqueda BFS");
                            Ab.breadthFirst();
                            break;
                        case 4:
                            System.out.println("Se llevara a cabo el recorrido en forma Prefija");
                            Ab.Prefija(Ab.root);
                            break;
                        case 5:
                            System.out.println("Se llevara a cabo el recorrido en forma Infija");
                            Ab.Infija(Ab.root);
                            break;
                        case 6:
                            System.out.println("Se llevara a cabo el recorrido en forma Postfija");
                            Ab.Postfija(Ab.root);
                            break;
                        case 7:
                            repetirseArBin=false;
                            break;
                        default:
                            System.out.println("Opcion incorrecta");
                    }
                }while(repetirseArBin);
            }else if(opcion==2){
                ArbolBinBusqueda arbol=new ArbolBinBusqueda();
                LinkedList<Nodo> nodosArbol=new LinkedList<>();
                do{
                    System.out.println("Seleccione la accion a realizar:");
                    System.out.println("1.Agregar\n2.Eliminar\n3.Buscar\n4.Imprimir Arbol(BFS)\n5.Mostrar caracteristicas\n6.Salir");
                    opcion=sc.nextByte();
                    switch(opcion){
                        case 1:
                            System.out.println("Introduzca el valor a agregar al arbol: ");
                            int valor=sc.nextInt();
                            nodosArbol.add(new Nodo(valor));
                            arbol.add((Nodo)nodosArbol.getLast());
                            break;
                        case 2:
                            System.out.println("Introduzca el valor a eliminar del arbol: ");
                            int valorEliminar=sc.nextInt();
                            boolean noExiste=true;
                            for(Nodo x:nodosArbol){
                                if(x.valor==valorEliminar){
                                    arbol.eliminarNodo(x);
                                    noExiste=false;
                                    break;
                                }
                            }
                            if(noExiste)
                                System.out.println("El valor no se encuentra en el arbol");
                            break;
                        case 3:
                            System.out.println("Introduzca el valor a buscar del arbol: ");
                            int valorBuscar=sc.nextInt();
                            if(arbol.buscar(valorBuscar))
                                System.out.println("El valor se encuentra en el arbol");
                            else
                                System.out.println("El valor no se encuentra en el arbol");
                            break;
                        case 4:
                            System.out.println("Busqueda BFS");
                            arbol.breadthFirst();
                            break;
                        case 5:
                            System.out.println("Las caracteristicas de este arbol binario de busqueda son:");
                            arbol.datosArbol();
                            break;
                        case 6:
                            repetirseArBin=false;
                            break;
                        default:
                            System.out.println("Opcion incorrecta");
                    }
                }while(repetirseArBin);
            }else if(opcion==3){
                System.out.println("Hasta luego");
                break;
            }else
                System.out.println("Opcion incorrecta");
        }while(repetirse);
        sc.close();
    }
}
