//
//  Ejercicio4.c
//  Practica5
//
//  Created by Gomez Luna Alejandro on 3/11/19.
//  Copyright © 2019 Gomez Luna Alejandro. All rights reserved.
//

#include "ColaLibros.h"
#include <stdlib.h>

void creacionLibros(int);

int main(){
    short x;
    printf("Ingrese la cantidad de libros a ingresar: \n");
    scanf("%hi",&x);
    creacionLibros(x);
    return 0xAF;
}

void creacionLibros(int x){
    float tiempoLibro=0,tiempoTotal=0;
    short i;
    Libro *l=calloc(x, sizeof(Libro)),y;
    ColaLibros libros=crearColaL();
    for(i=0;i<x;i++){
        l+=i;
        printf("Ingrese el nombre del libro:\n");
        scanf(" %[^\n]",l->titulo);
        printf("Ingrese el autor del libro:\n");
        scanf(" %[^\n]",l->autor);
        printf("Ingrese la cantidad de paginas del libro:\n");
        scanf("%d",&l->numPaginas);
        printf("Ingrese la clave del libro:\n");
        scanf("%ld",&l->clave);
        encolarL(&libros, *l);
    }
    printf("***Lectura de libros***\n");
    for(i=0;i<x;i++){
        y=desencolarL(&libros);
        printf("--Libro %d--\n",i+1);
        printf("\tNombre: %s\n",y.titulo);
        tiempoLibro=((y.numPaginas*20)/60);
        tiempoTotal+=tiempoLibro;
        printf("\tTiempo de impresion: %.2f\n",tiempoLibro);
    }
    printf("Tiempo total: %.2f\n",tiempoTotal);
    free(l);
}
