package practica4_gomezalejandro;

import java.util.LinkedList;
import java.util.List;
import java.util.Collections;

public class Practica4_GomezAlejandro {
    public static void main(String[] args) {
        List<Integer> lista1= new LinkedList<>();
        
        lista1.add(15);
        lista1.add(25);
        lista1.add(35);
        lista1.add(45);
        lista1.add(55);
        lista1.add(65);
        lista1.add(80);
        
        System.out.println("Estado punto 1");
        imprimirLista(lista1);
        System.out.println(" *** ");
        
        lista1.add(1,300);
        lista1.add(3,500);
        lista1.add(5,700);
        
        System.out.println("Estado punto 2");
        imprimirLista(lista1);
        System.out.println(" *** ");
        
        
        lista1.set(0,4);
        lista1.set(2,6);
        lista1.set(7,8);
        
        System.out.println(" Estado punto 3");
        imprimirLista(lista1);
        System.out.println(" *** ");
        
        List<Integer> lista2;
        lista2=lista1.subList(3, 6);
        imprimirLista(lista2);
        System.out.println(" *** ");
        System.out.println(lista1.equals(lista2));
        
        System.out.println("Elemento eliminado: "+lista1.remove(2));
        System.out.println("La lista esta vacia: "+lista1.isEmpty());
        System.out.println("Buscando el elemento 65: "+lista1.contains(65));
        System.out.println("Buscando el elemento 10: "+lista1.contains(10));
        
        lista1.set(1, 4);
        lista1.set(6, 4);
        
        imprimirLista(lista1);
        System.out.println("Comprobando los metodos creados para busqueda lineal");
        System.out.println("Se encuentra el elemento 500: "+BusquedaLineal.busquedaLinealAparicion(lista1,500));
        System.out.println("Se encuentra el elemento 100: "+BusquedaLineal.busquedaLinealAparicion(lista1,100));
        System.out.println("Indice del elemento 80: "+BusquedaLineal.busquedaLinealIndice(lista1, 80));
        System.out.println("Indice del elemento 70: "+BusquedaLineal.busquedaLinealIndice(lista1, 70));
        System.out.println("Cantidad de veces que esta el elemento 4: "+BusquedaLineal.busquedaLinealApariciones(lista1, 4));
        System.out.println("Cantidad de veces que esta el elemento 5: "+BusquedaLineal.busquedaLinealApariciones(lista1, 5));
        
        
        Collections.sort(lista1);
        imprimirLista(lista1);
        System.out.println("Comprobando los metodos creados para busqueda binaria");
        System.out.println("Se encuentra el elemento 700: "+BusquedaBinaria.busquedaBinariaAparicion(lista1,700,0,8));
        System.out.println("Se encuentra el elemento 800: "+BusquedaBinaria.busquedaBinariaAparicion(lista1,800,0,8));
        System.out.println("Cantidad de veces que esta el elemento 4: "+BusquedaBinaria.busquedaBinariaApariciones(lista1,4,0,8));
        System.out.println("Cantidad de veces que esta el elemento 9: "+BusquedaBinaria.busquedaBinariaApariciones(lista1,9,0,8));
        System.out.println("Cantidad de veces que esta el elemento 700: "+BusquedaBinaria.busquedaBinariaApariciones(lista1,700,0,8));
        
        System.out.println("Creando una lista de paises con su capital y dimension...");
        List<Pais> paises=new LinkedList<>();
        paises.add(new Pais("Alemania","Berlin",2030.80f));
        paises.add(new Pais("Argentina","Buenos Aires",2010.80f));
        paises.add(new Pais("Chile","Santiago",2020.80f));
        paises.add(new Pais("EUA","Washington DC",2000.80f));
        paises.add(new Pais("Mexico","Ciudad de Mexico",1990.80f));
        paises.add(new Pais("Mexico","Ciudad de Mexico",1990.80f));
        
        imprimirListaPaises(paises);
        System.out.println("Comprobando los metodos creados para busqueda lineal en la lista de paises");
        System.out.println("Se encuentra el pais Argentina: "+BusquedaLineal.busquedaLinealAparicion(paises,"Argentina"));
        System.out.println("Se encuentra el pais Holanda: "+BusquedaLineal.busquedaLinealAparicion(paises,"Holanda"));
        System.out.println("Indice de la capital Berlin: "+BusquedaLineal.busquedaLinealIndice(paises,"Berlin"));
        System.out.println("Indice de la capital Belice: "+BusquedaLineal.busquedaLinealIndice(paises,"Belice"));
        System.out.println("Cantidad de veces que esta el pais Mexico: "+BusquedaLineal.busquedaLinealApariciones(paises,"Mexico"));
        System.out.println("Cantidad de veces que esta el pais Canada: "+BusquedaLineal.busquedaLinealApariciones(paises,"Canada"));
        
        
        List<Pais> capitales=new LinkedList<>();
        capitales.add(new Pais("Alemania","Berlin",2030.80f));
        capitales.add(new Pais("Argentina","Buenos Aires",2010.80f));
        capitales.add(new Pais("Mexico","Ciudad de Mexico",1990.80f));
        capitales.add(new Pais("Mexico","Ciudad de Mexico",1990.80f));
        capitales.add(new Pais("Chile","Santiago",2020.80f));
        capitales.add(new Pais("EUA","Washington DC",2000.80f));
        imprimirListaPaises(capitales);
        System.out.println("Comprobando los metodos creados para busqueda binaria en la lista de paises");
        System.out.println("Se encuentra la capital Berlin: "+BusquedaBinaria.busquedaBinariaAparicion(capitales,"Berlin",0,5));
        System.out.println("Se encuentra la capital Montevideo: "+BusquedaBinaria.busquedaBinariaAparicion(capitales,"Montevideo",0,5));
        System.out.println("Cantidad de veces que esta la capital Ciudad de Mexico: "+BusquedaBinaria.busquedaBinariaApariciones(capitales,"Ciudad de Mexico",0,5));
        System.out.println("Cantidad de veces que esta la capital Madrid: "+BusquedaBinaria.busquedaBinariaApariciones(capitales,"Madrid",0,5));
    }
    
    public static void imprimirLista(List<Integer> listaPrint){
        for(Integer var:listaPrint){
            System.out.println(var);
        }
    }
    
    public static void imprimirListaPaises(List<Pais> paises){
        for(Pais x:paises){
            System.out.print(x.nombre+" ");
            System.out.print(x.capital+" ");
            System.out.print(x.size+" ");
            System.out.println();
        }
    }
}
