//
//  colaCircular.h
//  Practica6V2
//
//  Created by alex on 25/03/19.
//  Copyright © 2019 Gomez Luna Alejandro. All rights reserved.
//
#include <stdio.h>
#include <stdlib.h>

typedef struct{
    int primero;
    int ultimo;
    int tamano;
    int disponibles;
    int* lista;
}Cola;

Cola crearCola(int);
int isEmpty(Cola);
void encolar(Cola*,int);
int desencolar(Cola*);
void mostrarValores(Cola);
void mostrarIndices(Cola);

Cola crearCola(int tamano){
    Cola c;
    c.primero=1;
    c.ultimo=0;
    c.tamano=tamano;
    c.disponibles = c.tamano;
    c.lista = (int*)calloc(c.tamano,sizeof(int));
    return c;
}

int isEmpty(Cola c){
    if((c.primero==c.ultimo+1)&&(c.disponibles==c.tamano))
        return 1;
    return 0;
}

void encolar(Cola* c, int x){
    if(c->disponibles==0)
        printf("La cola esta llena\n");
    else{
        c->ultimo=(c->ultimo%c->tamano)+1;
        c->lista[c->ultimo-1]=x;
        c->disponibles--;
    }
}

int desencolar(Cola * c){
    if(isEmpty(*c)==1){
        printf("La cola esta vacia\n");
        return -1;
    }
    else{
        int aux;
        c->disponibles++;
        aux=c->lista[c->primero-1];
        c->lista[c->primero-1]=0;
        if(c->primero!=c->ultimo)
            c->primero=(c->primero % c->tamano)+1;
        else
            c->primero++;
        if((c->primero)==(c->ultimo+1)){
            *c=crearCola(c->tamano);
        }
        return aux;
    }
}

void mostrarValores(Cola queue1){
    int i=0;
    for(i=0;i<queue1.tamano;i++){
        printf("Posicion %d \t valor %d \n",i+1,queue1.lista[i]);
    }
}

void mostrarIndices(Cola queue1){
    printf("Primero = %d \n",queue1.primero);
    printf("Ultimo = %d \n",queue1.ultimo);
    printf("Disponibles = %d \n",queue1.disponibles);
    
}
