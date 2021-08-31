
public class Principal {

    public static void main(String args[]) 
    { 
        MergeSort ordenar=new MergeSort();
        int[] arr={20,12,47,68,54,39,100,99,78,65,47,58,90,11,5};
        System.out.print("Arreglo original: ");
        for(int x:arr)
            System.out.print(x+" ");
        System.out.println();
        ordenar.sort(arr, 0, 14);
        System.out.print("Arreglo ordenado: ");
        for(int x:arr)
            System.out.print(x+" ");
        System.out.println();
    } 
} 
    
    

