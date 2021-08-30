package practica5gomezalejandro;

import java.util.Arrays;
import java.util.Hashtable;

public class TablasHash {
    private Hashtable<String,int[]> tabla=new Hashtable();
    private final int[] a={8,9,10,9,9,10};
    
    TablasHash(){
        tabla.put("Alejandro",this.a);
        tabla.put("Edgar",new int[]{7,7,8,9,9,6});
        tabla.put("Bestia",new int[]{6,6,6,9,9,9});
        tabla.put("Zlatan",new int[]{4,2,1,3,5,5});
        tabla.put("Samantha",new int[]{9,9,10,9,8,10});
    }
    
    void probando(){
        System.out.println("La Hashtable tiene los siguientes elementos: "+tabla);
        System.out.println("A continuacion se probaran algunas de los metodos en la coleccion Hashtable: ");
        System.out.println("Contiene al alumno Raul: "+tabla.contains("Raul"));
        System.out.println("Contiene a la alumna Samantha: "+tabla.containsKey("Samantha"));
        System.out.println("Contiene el arreglo de calificaciones [8,9,10,9,9,10]: "+tabla.containsValue(this.a));
        System.out.println("Contiene el arreglo de calificaciones [6,9,8,7,0,2]: "+tabla.containsValue(new int[]{6,9,8,7,0,2}));
        System.out.println("Comparando esta Hashtable con una copia suya: "+tabla.equals(tabla.clone()));
        System.out.println("Obteniendo las calificaciones del alumno Zlatan en la Hashtable: "+Arrays.toString(tabla.get("Zlatan")));
        System.out.println("Agregando el alumno Nadie con las calificaciones [6,7,8,9,10,10]: ");
        tabla.put("Nadie",new int[]{6,7,8,9,10,10});
        System.out.println("Eliminando al alumno Bestia: ");
        tabla.remove("Bestia");
        System.out.println("El tamaño final de la Hashtable es: "+tabla.size());
    }
}
