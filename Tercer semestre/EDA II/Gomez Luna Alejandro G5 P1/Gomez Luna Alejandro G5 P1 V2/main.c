#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "ordenamientos.h"

int main() {
    short opcion;
    int arreglo[10]={0};
    srand(time(NULL));
    for(short i=0;i<10;i++)
        arreglo[i]=(rand()%30)+1;
    printf("El arreglo creado tiene los elementos: \n");
    printArray(arreglo, 10);
    printf("\t***Bienvenido al programa de ordenamiento***\n");
    printf("Seleccione el numero de la opcion que desee para ordenar el arreglo:\n1)Insertion Sort\n2)Selection Sort\n");
    scanf("%hd",&opcion);
    switch (opcion) {
        case 1:
            printf("Ordenando el arreglo...\n");
            insertionSort(arreglo, 10);
            break;
        case 2:
            printf("Ordenando el arreglo...\n");
            selectionSort(arreglo, 10);
            break;
        default:
            printf("Opcion no valida\n");
            break;
    }
    printf("El arreglo ordenando es:\n");
    printArray(arreglo, 10);
    return  0xAF;
}
