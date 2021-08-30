#include "utilidades.h"

void selectionSort(int arreglo[], int n){
	int indiceMenor, i, j, comparaciones=0, intercambios=0;
	for (i=0; i<n-1; i++){
		indiceMenor=i;
		for(j = i+1; j<n; j++){
			if(arreglo[j]<arreglo[indiceMenor])
				indiceMenor=j;
                comparaciones++;
		}
		if (i!=indiceMenor){
			swap(&arreglo[i],&arreglo[indiceMenor]);
            intercambios++;
		}
        //printArray(arreglo, n);
	}
    printf("\tCantidad de comparaciones: %d\n",comparaciones);
    printf("\tCantidad de intercambios: %d\n",intercambios);
}
	

void insertionSort(int arreglo[], int n){
	int i,j, comparaciones=0, intercambios=0;
	int aux;
	
	for(i=1; i<n; i++){
		j=i;
		aux=arreglo[i];
		while (j>0 && aux < arreglo[j-1]){
            comparaciones++;
			arreglo[j] = arreglo[j-1];
            intercambios++;
			j--;
		}
		arreglo[j]=aux;
        intercambios++;
        //printArray(arreglo, n);
	}
    printf("\tCantidad de comparaciones: %d\n",comparaciones);
    printf("\tCantidad de intercambios: %d\n",intercambios);
}



