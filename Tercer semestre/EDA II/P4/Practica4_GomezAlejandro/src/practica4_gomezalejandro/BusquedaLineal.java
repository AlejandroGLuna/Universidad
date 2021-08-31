package practica4_gomezalejandro;

import java.util.List;

public class BusquedaLineal {
    public static boolean busquedaLinealAparicion(List<Integer> lista, Integer x){
        for(int i=0;i<lista.size();i++){
            Integer a=lista.get(i);
            if(a.equals(x))
                return true;
        }
        return false;
    }
    
    public static int busquedaLinealIndice(List<Integer> lista, Integer x){
        for(int i=0;i<lista.size();i++){
            Integer a=lista.get(i);
            if(a.equals(x))
                return i;
        }
        return -1;
    }
    
    public static int busquedaLinealApariciones(List<Integer> lista, Integer x){
        int aparicionesElemento=0;
        for(int i=0;i<lista.size();i++){
            Integer a=lista.get(i);
            if(a.equals(x))
                aparicionesElemento++;
        }
        return aparicionesElemento;
    }
    
    public static boolean busquedaLinealAparicion(List<Pais> paises, String pais){
        pais=pais.toUpperCase();
        for(int i=0;i<paises.size();i++){
            Pais x=paises.get(i);
            if(x.nombre.equals(pais))
                return true;
        }
        return false;
    }
    
    public static int busquedaLinealIndice(List<Pais> paises, String capital){
        capital=capital.toUpperCase();
        for(int i=0;i<paises.size();i++){
            Pais x=paises.get(i);
            if(x.capital.equals(capital))
                return i;
        }
        return -1;
    }
    
    public static int busquedaLinealApariciones(List<Pais> paises, String pais){
        int aparicionesElemento=0;
        pais=pais.toUpperCase();
        for(int i=0;i<paises.size();i++){
            Pais x=paises.get(i);
            if(x.nombre.equals(pais))
                aparicionesElemento++;
        }
        return aparicionesElemento;
    }
}
