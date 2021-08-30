package practica4_gomezalejandro;

public class Pais {
    String nombre;
    String capital;
    float size;
    
    void saludarPais(){
        System.out.println("El pais "+this.nombre+"tiene una extension de"+this.size);
    }
    
    Pais(String nombre, String capital, float size){
        this.nombre=nombre.toUpperCase();
        this.capital=capital.toUpperCase();
        this.size=size;
    }
}
