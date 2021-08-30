//
//  Ejercicio2.c
//  Practica5
//
//  Created by Gomez Luna Alejandro on 3/11/19.
//  Copyright © 2019 Gomez Luna Alejandro. All rights reserved.
//

#include "Pila.h"
Pila elementoMayor(Pila *);
int main(){
    Pila pila1=crearPila(), pila2=crearPila(), pila3=crearPila();
    for(short i=0;i<10;i++){
        printf("Ingrese un valor\n");
        scanf("%d",&pila1.lista[i+1]);
    }
    pila1.tope=10;
    return 0xAF;
}
