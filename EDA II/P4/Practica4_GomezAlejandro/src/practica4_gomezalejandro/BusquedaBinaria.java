package practica4_gomezalejandro;

import java.util.List;

public class BusquedaBinaria {
    public static boolean busquedaBinariaAparicion(List<Integer> lista, Integer x, int primero, int ultimo){
        if(primero>ultimo)
            return false;
        int central=((ultimo+primero)/2);
        int elementoCentro=lista.get(central);
        if(elementoCentro == x)
            return true;
        else{
            if(x<elementoCentro)
                return busquedaBinariaAparicion(lista,x,primero,central-1);
            else
                return busquedaBinariaAparicion(lista,x,central+1,ultimo);
        }
    }
    
    public static int busquedaBinariaApariciones(List<Integer> lista, Integer x, int primero, int ultimo){
        if(primero>ultimo)
            return 0;
        int apariciones=0;
        int central=((ultimo+primero)/2);
        int elementoCentro=lista.get(central);
        if(elementoCentro == x){
            apariciones++;
            if(central==0){
                int j=lista.get(central+1),b=1;
                while(j==x && (central+(++b))<=(lista.size()-1)){
                    apariciones++;
                    j=lista.get(central+b);
                }
                if(j==x)
                    apariciones++;
            }else if(central==lista.size()-1){
                int i=lista.get(central-1),a=1;
                while(i==x && (central-(++a))>=0){
                    apariciones++;
                    i=lista.get(central-a);
                }
                if(i==x)
                    apariciones++;
            }else{
                int i=lista.get(central-1),a=1;
                while(i==x && (central-(++a))>=0){
                    apariciones++;
                    i=lista.get(central-a);
                }
                if(i==x)
                    apariciones++;
                int j=lista.get(central+1),b=1;
                while(j==x && (central+(++b))<=(lista.size()-1)){
                    apariciones++;
                    j=lista.get(central+b);
                }
                if(j==x)
                    apariciones++;
            } 
        }
        else{
            if(x<elementoCentro)
                return busquedaBinariaApariciones(lista,x,primero,central-1);
            else
                return busquedaBinariaApariciones(lista,x,central+1,ultimo);
        }
        return apariciones;
    }
    
    public static boolean busquedaBinariaAparicion(List<Pais> paises, String capital, int primero, int ultimo){
        if(primero>ultimo)
            return false;
        capital=capital.toUpperCase();
        int central=((ultimo+primero)/2);
        Pais x=paises.get(central);
        if(x.capital.equals(capital))
            return true;
        else{
            if(capital.charAt(0)<=x.capital.charAt(0)){
                return busquedaBinariaAparicion(paises,capital,primero,central-1);
            }
            else{
                return busquedaBinariaAparicion(paises,capital,central+1,ultimo);
            }
        }
    }
    
    public static int busquedaBinariaApariciones(List<Pais> paises, String capital, int primero, int ultimo){
        if(primero>ultimo)
            return 0;
        capital=capital.toUpperCase();
        int apariciones=0;
        int central=((ultimo+primero)/2);
        Pais elementoCentro=paises.get(central);
        if(elementoCentro.capital.equals(capital)){
            apariciones++;
            if(central==0){
                Pais j=paises.get(central+1);
                int b=1;
                while(j.capital.equals(capital) && (central+(++b))<=(paises.size()-1)){
                    apariciones++;
                    j=paises.get(central+b);
                }
                if(j.capital.equals(capital))
                    apariciones++;
            }else if(central==paises.size()-1){
                Pais i=paises.get(central-1);
                int a=1;
                while(i.capital.equals(capital) && (central-(++a))>=0){
                    apariciones++;
                    i=paises.get(central-a);
                }
                if(i.capital.equals(capital))
                    apariciones++;
            }else{
                Pais i=paises.get(central-1);
                int a=1;
                while(i.capital.equals(capital) && (central-(++a))>=0){
                    apariciones++;
                    i=paises.get(central-a);
                }
                if(i.capital.equals(capital))
                    apariciones++;
                Pais j=paises.get(central+1);
                int b=1;
                while(j.capital.equals(capital) && (central+(++b))<=(paises.size()-1)){
                    apariciones++;
                    j=paises.get(central+b);
                }
                if(j.capital.equals(capital))
                    apariciones++;
            } 
        }
        else{
            if(capital.charAt(0)<=elementoCentro.capital.charAt(0))
                return busquedaBinariaApariciones(paises,capital,primero,central-1);
            else
                return busquedaBinariaApariciones(paises,capital,central+1,ultimo);
        }
        return apariciones;
    }
    
}
