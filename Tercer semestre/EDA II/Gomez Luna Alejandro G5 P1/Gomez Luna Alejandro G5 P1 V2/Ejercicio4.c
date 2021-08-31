#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "ordenamientos.h"

int main(){
    int arreglo10Insertion[10]={0},arreglo10Selection[10]={0};
    int arreglo50Insertion[50]={0},arreglo50Selection[50]={0};
    int arreglo100Insertion[100]={0},arreglo100Selection[100]={0};
    int arreglo500Insertion[500]={0},arreglo500Selection[500]={0};
    srand(time(0));
    printf("Generando los elementos dentro de los arreglos...\n");
    for (short i=0; i<10; i++) {
        arreglo10Insertion[i]=(rand()%30)+1;
        arreglo10Selection[i]=(rand()%30)+1;
    }
    for (short i=0; i<50; i++) {
        arreglo50Insertion[i]=(rand()%30)+1;
        arreglo50Selection[i]=(rand()%30)+1;
    }
    for (short i=0; i<100; i++) {
        arreglo100Insertion[i]=(rand()%30)+1;
        arreglo100Selection[i]=(rand()%30)+1;
    }
    for (short i=0; i<500; i++) {
        arreglo500Insertion[i]=(rand()%30)+1;
        arreglo500Selection[i]=(rand()%30)+1;
    }
    printf("Una vez generados los arreglos, se ordenaran...\n");
    //
    printf("\nCon Insertion Sort: \n");
    printf("Para el arreglo con 10 elementos:\n");
    insertionSort(arreglo10Insertion, 10);
    printf("Para el arreglo con 50 elementos:\n");
    insertionSort(arreglo50Insertion, 50);
    printf("Para el arreglo con 100 elementos:\n");
    insertionSort(arreglo100Insertion, 100);
    printf("Para el arreglo con 500 elementos:\n");
    insertionSort(arreglo500Insertion, 500);
    //
    printf("\nCon Selection Sort: \n");
    printf("Para el arreglo con 10 elementos:\n");
    selectionSort(arreglo10Selection, 10);
    printf("Para el arreglo con 50 elementos:\n");
    selectionSort(arreglo50Selection, 50);
    printf("Para el arreglo con 100 elementos:\n");
    selectionSort(arreglo100Selection, 100);
    printf("Para el arreglo con 500 elementos:\n");
    selectionSort(arreglo500Selection, 500);
    return 0xAF;
}
