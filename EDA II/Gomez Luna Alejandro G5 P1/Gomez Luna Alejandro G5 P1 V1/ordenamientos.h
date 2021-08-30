#include "utilidades.h"

void selectionSort(int arreglo[], int n){
	int indiceMenor, i, j;
	for (i=0; i<n-1; i++){
		indiceMenor=i;
		for(j = i+1; j<n; j++){
			if(arreglo[j]<arreglo[indiceMenor])
				indiceMenor=j;
		}
		if (i!=indiceMenor){
			swap(&arreglo[i],&arreglo[indiceMenor]);
		}
        printArray(arreglo, n);
	}		
}
	

void insertionSort(int arreglo[], int n){
	int i,j;
	int aux;
	
	for(i=1; i<n; i++){
		j=i;
		aux=arreglo[i];
		while (j>0 && aux < arreglo[j-1]){
			arreglo[j] = arreglo[j-1];
			j--;
		}
		arreglo[j]=aux;
        printArray(arreglo, n);
	}
	
	
}



