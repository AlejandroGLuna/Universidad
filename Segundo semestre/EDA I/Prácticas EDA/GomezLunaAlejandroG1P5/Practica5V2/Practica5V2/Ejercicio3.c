//
//  Ejercicio3.c
//  Practica5
//
//  Created by Gomez Luna Alejandro on 3/11/19.
//  Copyright © 2019 Gomez Luna Alejandro. All rights reserved.
//

#include "Cola.h"
#include "Pila.h"
Pila invertir(Pila *, Cola *);
int main(){
    int x;
    short i;
    Pila pilaI=crearPila();
    Cola colaA=crearCola();
    for(short i=0;i<5;i++){
    printf("Ingrese un valor: \n");
        scanf("%d",&x);
        meter(&pilaI,x);
    }
    
    printf("El valor del tope de la pila es %d\n",top(pilaI));
    for(i=0;i<5;i++)
        encolar(&colaA, sacar(&pilaI));
    
    for(i=0;i<5;i++)
        meter(&pilaI, desencolar(&colaA));
    printf("El valor del tope de la pila ahora es %d\n",top(pilaI));
    return 0xAF;
}
