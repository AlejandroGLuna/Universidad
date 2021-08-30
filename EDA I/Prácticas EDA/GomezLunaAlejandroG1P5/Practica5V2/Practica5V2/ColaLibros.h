//
//  ColaLibros.h
//  Practica5V2
//
//  Created by alex on 11/03/19.
//  Copyright © 2019 Alejandro. All rights reserved.
//

#include <stdio.h>
#include <stdlib.h>

typedef struct{
    char titulo[35];
    char autor[40];
    int numPaginas;
    long clave;
}Libro;

typedef struct{
    int primero;
    int ultimo;
    Libro lista[20];
}ColaLibros;

ColaLibros crearColaL(void);
int isEmptyCL(ColaLibros);
void encolarL(ColaLibros*,Libro);
Libro desencolarL(ColaLibros*);
Libro firstL(ColaLibros);

ColaLibros crearColaL(){
    ColaLibros c;
    c.primero=1;
    c.ultimo=0;
    return c;
}

int isEmptyCL(ColaLibros c){
    if(c.primero==c.ultimo+1)
        return 1;
    return 0;
}

void encolarL(ColaLibros *c,Libro x){
    c->ultimo+=1;
    c->lista[c->ultimo]=x;
}

Libro desencolarL(ColaLibros *c){
    if(isEmptyCL(*c)==1){
        Libro l={0};
        printf("La cola esta vacia");
        return l;
    }
    else{
        Libro x;
        x=c->lista[c->primero];
        c->primero+=1;
        return x;
    }
}

Libro firstL(ColaLibros c){
    if(isEmptyCL(c)==1){
        Libro l={0};
        printf("La cola esta vacia");
        return l;
    }
    else{
        return c.lista[c.primero];
    }
}

