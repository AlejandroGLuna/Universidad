//
//  main.c
//  Estructuras
//
//  Created by alex on 26/03/19.
//  Copyright © 2019 Gomez Luna Alejandro. All rights reserved.
//

#include "estructuras.h"

int main() {
    PilaL pila1=crearPilaL(5);
    for(short i=0;i<5;i++){
        push(&pila1, i);
        printf("El valor en el tope es: %d\n",top(pila1));
    }
    above(pila1);
    printf("\n\n");
    for(short i=0;i<5;i++){
        printf("\tEl valor devuelto es:%d\n",pop(&pila1));
    }
    return 0xAF;
}
