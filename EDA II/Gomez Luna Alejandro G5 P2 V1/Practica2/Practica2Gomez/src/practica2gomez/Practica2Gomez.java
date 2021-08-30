package practica2gomez;

public class Practica2Gomez{
	public static void main(String[] args) {
		int arr[]={87,4,32,15,8,12,10,30,22};
		int n=arr.length;
		QuickSort ordenamiento=new QuickSort();
		ordenamiento.partition(arr,0,n-1);
		Utilidades.printArray(arr);
	}
}

