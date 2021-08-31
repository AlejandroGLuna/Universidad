//
//  Ejercicio3.c
//  Practica4
//
//  Created by Alumno on 3/4/19.
//  Copyright © 2019 Alumno. All rights reserved.
//
#include <stdio.h>
#include <stdlib.h>

//Estructura tipo Animal
typedef struct {
    char nombre[25];
    int talla;
    char clasificacion[25];
    short gestacion;
    char color[15];
}Animal;

//Funcion secundaria
void crearAnimal(short);

//Funcion principal
int main(){
    short x;
    printf("Indique la cantidad de animales a introducir:");
    scanf("%hi",&x);
    crearAnimal(x);
    return 0xAF;
}
void crearAnimal(short y){
    Animal *py=calloc(y, sizeof(Animal));
    short i=0;
    for(;i<y;i++){
        py+=i;
        printf("***Animal #%hi***\n",i);
        printf("Ingrese el nombre del animal\n");
        scanf(" %[^\n]",py->nombre);
        printf("Ingrese su altura en centimetros\n");
        scanf("%d",&py->talla);
        printf("Ingrese su clasificacion\n");
        scanf(" %[^\n]",py->clasificacion);
        printf("Ingrese su periodo de gestacion en dias\n");
        scanf("%hi",&py->gestacion);
        printf("Ingrese su color\n");
        scanf(" %[^\n]",py->color);
    }
    free(py);
}
