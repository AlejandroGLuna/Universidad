#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "ordenamientos.h"

int main(){
    int arreglo10Quick[10]={0},arreglo10Bubble[10]={0},arreglo10Heap[10]={0},arregloBase10[10]={0};
    int arreglo50Quick[50]={0},arreglo50Bubble[50]={0},arreglo50Heap[10]={0},arregloBase50[50]={0};
    int arreglo100Quick[100]={0},arreglo100Bubble[100]={0},arreglo100Heap[10]={0},arregloBase100[100]={0};
    int arreglo500Quick[500]={0},arreglo500Bubble[500]={0},arreglo500Heap[10]={0},arregloBase500[500]={0};
    int comparaciones=0, intercambios=0, total=0;
    srand(time(0));
    printf("Generando los elementos dentro de los arreglos...\n");
    for (short i=0; i<10; i++) {
        arregloBase10[i]=(rand()%30)+1;
        arreglo10Quick[i]=arregloBase10[i];
        arreglo10Bubble[i]=arregloBase10[i];
        arreglo10Heap[i]=arregloBase10[i];
    }
    for (short i=0; i<50; i++) {
        arregloBase50[i]=(rand()%30)+1;
        arreglo50Quick[i]=arregloBase50[i];
        arreglo50Bubble[i]=arregloBase50[i];
        arreglo50Heap[i]=arregloBase50[i];
    }
    for (short i=0; i<100; i++) {
        arregloBase100[i]=(rand()%30)+1;
        arreglo100Quick[i]=arregloBase100[i];
        arreglo100Bubble[i]=arregloBase100[i];
        arreglo100Heap[i]=arregloBase100[i];
    }
    for (short i=0; i<500; i++) {
        arregloBase500[i]=(rand()%30)+1;
        arreglo500Quick[i]=arregloBase500[i];
        arreglo500Bubble[i]=arregloBase500[i];
        arreglo500Heap[i]=arregloBase500[i];
    }
    printf("Una vez generados los arreglos, se ordenaran...\n");
    //
    printf("\nCon Quick Sort: \n");
    printf("Para el arreglo con 10 elementos:\n");
    quickSort(arreglo10Quick, 0, 9, &comparaciones, &intercambios);
    total=comparaciones+intercambios;
    printf("\tCantidad de comparaciones: %d\n",comparaciones);
    printf("\tCantidad de intercambios: %d\n",intercambios);
    printf("\tTotal: %d\n",total);
    comparaciones=0;
    intercambios=0;
    printf("Para el arreglo con 50 elementos:\n");
    quickSort(arreglo50Quick, 0, 49, &comparaciones, &intercambios);
    total=comparaciones+intercambios;
    printf("\tCantidad de comparaciones: %d\n",comparaciones);
    printf("\tCantidad de intercambios: %d\n",intercambios);
    printf("\tTotal: %d\n",total);
    comparaciones=0;
    intercambios=0;
    printf("Para el arreglo con 100 elementos:\n");
    quickSort(arreglo100Quick, 0, 99, &comparaciones, &intercambios);
    total=comparaciones+intercambios;
    printf("\tCantidad de comparaciones: %d\n",comparaciones);
    printf("\tCantidad de intercambios: %d\n",intercambios);
    printf("\tTotal: %d\n",total);
    comparaciones=0;
    intercambios=0;
    printf("Para el arreglo con 500 elementos:\n");
    quickSort(arreglo500Quick, 0, 499, &comparaciones, &intercambios);
    total=comparaciones+intercambios;
    printf("\tCantidad de comparaciones: %d\n",comparaciones);
    printf("\tCantidad de intercambios: %d\n",intercambios);
    printf("\tTotal: %d\n",total);
    //
    printf("\nCon Bubble Sort: \n");
    printf("Para el arreglo con 10 elementos:\n");
    bubbleSort(arreglo10Bubble, 10);
    printf("Para el arreglo con 50 elementos:\n");
    bubbleSort(arreglo50Bubble, 50);
    printf("Para el arreglo con 100 elementos:\n");
    bubbleSort(arreglo100Bubble, 100);
    printf("Para el arreglo con 500 elementos:\n");
    bubbleSort(arreglo500Bubble, 500);
    //
    printf("\nCon Heap Sort: \n");
    printf("Para el arreglo con 10 elementos:\n");
    HeapSort(arreglo10Heap, 10);
    printf("Para el arreglo con 50 elementos:\n");
    HeapSort(arreglo50Heap, 50);
    printf("Para el arreglo con 100 elementos:\n");
    HeapSort(arreglo100Heap, 100);
    printf("Para el arreglo con 500 elementos:\n");
    HeapSort(arreglo500Heap, 500);
    return 0xAF;
}
