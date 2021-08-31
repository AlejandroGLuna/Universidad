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
        comparaciones++;
		arreglo[j]=aux;
        intercambios++;
        //printArray(arreglo, n);
	}
    printf("\tCantidad de comparaciones: %d\n",comparaciones);
    printf("\tCantidad de intercambios: %d\n",intercambios);
}

int partition(int arr[],int low, int high, int *comparaciones, int *intercambios){
    int pivot=arr[high];
    //printf("Pivote: %d    ",pivot);
    int j,i=(low-1);
    for (j=low; j<=high-1; j++) {
        if(arr[j]<=pivot){
            i++;
            swap(&arr[i],&arr[j]);
            (*intercambios)++;
        }
        (*comparaciones)++;
    }
    swap(&arr[i+1],&arr[high]);
    (*intercambios)++;
    //printArray(arr,high+1);
    return(i+1);
}

void quickSort(int arr[],int low, int high, int *comparaciones, int *intercambios){
    if(low<high){
        int pi=partition(arr,low,high, comparaciones, intercambios);
        //printSubArray(arr,low,pi-1);
        quickSort(arr,low,pi-1, comparaciones, intercambios);
        //printSubArray(arr,pi+1,high);
        quickSort(arr,pi+1,high, comparaciones, intercambios);
    }
    (*comparaciones)++;
}

void bubbleSort(int a[],int size){
    int i,j,n,detenerse, comparaciones=0, intercambios=0;
    n=size;
    for (i=n-1; i>0; i--) {
        detenerse=0;
        for (j=0; j<i; j++) {
            if(a[j]>a[j+1]){
                swap(&a[j],&a[j+1]);
                detenerse++;
                intercambios++;
            }
            comparaciones++;
        }
        //printArray(a,size);
        comparaciones++;
        if(detenerse==0){
            break;
        }
    }
    int total=comparaciones+intercambios;
    printf("\tCantidad de comparaciones: %d\n",comparaciones);
    printf("\tCantidad de intercambios: %d\n",intercambios);
    printf("\tTotal: %d\n",total);
}

int heapSize, comparacionesHeap, intercambiosHeap;

void Heapify(int* A, int i, int size)
{
    int l = 2 * i + 1;
    int r = 2 * i + 2;
    int largest;
    
    comparacionesHeap+=2;
    if(l <= heapSize && A[l] > A[i]){
        largest = l;
    }
    else
        largest = i;
    if(r <= heapSize && A[r] > A[largest]){
        largest = r;
    }
    comparacionesHeap+=3;
    if(largest != i){
        swap(&A[i],&A[largest]);
        intercambiosHeap++;
        //printArray(A,size);
        Heapify(A, largest, size);
    }
    
}

void BuildHeap(int* A, int size){
    heapSize = size - 1;
    int i;
    for(i = (size - 1) / 2; i >= 0; i--){
        Heapify(A, i, size);
    }
    //printf("Termin%c de construir el HEAP \n",162);
}

void HeapSort(int* A, int size){
    comparacionesHeap=0;
    intercambiosHeap=0;
    BuildHeap(A, size);
    int i;
    for(i = size - 1; i > 0; i--){
        swap(&A[0],&A[heapSize]);
        intercambiosHeap++;
        heapSize--;
        //printf("Iteracion HS: \n");
        //printArray(A,size);
        Heapify(A, 0, size);
    }
    int total=comparacionesHeap+intercambiosHeap;
    printf("\tCantidad de comparaciones: %d\n",comparacionesHeap);
    printf("\tCantidad de intercambios: %d\n",intercambiosHeap);
    printf("\tTotal: %d\n",total);
}

