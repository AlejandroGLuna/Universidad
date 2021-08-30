//
//  Pila.h
//  Practica5
//
//  Created by Gomez Luna Alejandro on 3/11/19.
//  Copyright © 2019 Gomez Luna Alejandro. All rights reserved.
//

#include <stdio.h>
#include <stdlib.h>

typedef struct{
    int tope;
    int lista[100];
}Pila;

//declaraciÛn de funciones para trabajar con la estructura
Pila crearPila(void);
int isEmptyP(Pila);
void meter(Pila*,int);
int sacar(Pila*);
int top(Pila);


Pila crearPila(){
    Pila p;
    p.tope=0;
    return p;
}

int isEmptyP(Pila p){
    if(p.tope==0)
        return 1;
    return 0;
}
void meter(Pila *p,int x){
    p->tope+=1;
    p->lista[p->tope]=x;
}

int sacar(Pila *p){
    if(p->tope==0){
        printf("La pila esta vacia\n");
        return -1;
    }
    else{
        int x;
        x=p->lista[p->tope];
        p->lista[p->tope]=0;
        p->tope-=1;
        return x;
    }
}

int top(Pila p){
    if(isEmptyP(p)==1){
        printf("La pila ya est%c vac%ca. \n",160,161);
        return -1;
    }
    else{
        return p.lista[p.tope];
    }
}

