package practica10;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class ControladorArchivos{
    
    private void crear(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Cual es el nombre completo del archivo a crear");
        String name=sc.nextLine();
        System.out.println("Indica la ruta donde deseas guardar el archivo");
        String ruta=sc.nextLine();
        File arch;
        int op=0;
        try{
            arch=new File(ruta+"/"+name);
            arch.createNewFile();
            if(arch.exists()){
            PrintWriter pw= new PrintWriter(new FileWriter(arch,true));    
                do{
                System.out.println("Ingresa el contenido del archivo");
                String contenido=sc.nextLine();
                pw.write(contenido+"\n");
                pw.flush();
                System.out.println("Desea escribir algo mas en el archivo \n1)Si\n2)No");
                op=Integer.parseInt(sc.nextLine());
                }while(op!=2);  
            }
            else
                System.out.println("Lo siento, no se ha creado el archivo");
        }catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
    }

    private void sobreescritura(File f){
        Scanner sc=new Scanner(System.in);
        try{
            PrintWriter p=new PrintWriter(new FileWriter(f));
            String texto="";
            while(!texto.equals("ESC")){
                System.out.println("Introduzca una nueva linea. Si desea salir, escriba ESC");
                texto=sc.nextLine();
                if(!texto.equals("ESC"))
                    p.println(texto);
                else
                    break;
            }
            p.close();
        }catch(IOException eIO){
            System.out.println("Ha ocurrido un error en el archivo\n"+eIO.getMessage());
        }
    }
    
    private void escribir(File f){
        Scanner sc=new Scanner(System.in);
        try{
            PrintWriter p=new PrintWriter(new FileWriter(f,true));
            String texto="";
            p.println();
            while(!texto.equals("ESC")){
                System.out.println("Introduzca una nueva linea. Si desea salir, escriba ESC");
                texto=sc.nextLine();
                if(!texto.equals("ESC"))
                    p.println(texto);
                else
                    break;
            }
            p.close();
        }catch(IOException eIO){
            System.out.println("Ha ocurrido un error en el archivo\n"+eIO.getMessage());
        }
    }
    
    private boolean eliminar(File f){
        if(f.delete()){
            System.out.println("El archivo se borro con exito");
            return true;
        }else{
            System.out.println("El archivo no se pudo eliminar");
            return false;
        }   
    }
    
    static void menu(){
        boolean salir=false;
        do{
            byte o=0;
            Scanner xd=new Scanner(System.in);
            System.out.println("\t***Bienvenido al programa de archivos***");
            System.out.println("Selecciona la opcion a realizar:\n1)Crear archivo\n2)Sobreescribir archivo\n3)Editar archivo\n4)Eliminar archivo\n5)Salir");
            o=xd.nextByte();
            xd.nextLine();
            File f=null;
            if(o>=2 && o <=4){
                System.out.println("Introduzca la ruta completa del archivo junto con su nombre y extension: ");
                f=new File(xd.nextLine());
                if(!f.exists()){
                    System.out.println("No existe el archivo. Intente de nuevo");
                    continue;
                }
            }
            ControladorArchivos a=new ControladorArchivos();
            switch(o){
                case 1:
                    a.crear();
                    break;
                case 2:
                    a.sobreescritura(f);
                    break;
                case 3:
                    a.escribir(f);
                    break;
                case 4:
                    a.eliminar(f);
                    break;
                case 5:
                    salir=true;
                    xd.close();
                    break;
                default:
                    System.out.println("Opcion incorrecta, intente de nuevo");
            }
        }while(!salir); 
    }
    
    public static void main(String[] args) {
        menu();
    } 
}
