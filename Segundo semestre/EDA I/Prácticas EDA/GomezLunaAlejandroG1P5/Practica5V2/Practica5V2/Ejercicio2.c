//
//  Ejercicio2.c
//  Practica5
//
//  Created by Gomez Luna Alejandro on 3/11/19.
//  Copyright © 2019 Gomez Luna Alejandro. All rights reserved.
//

#include "Pila.h"
int elementoMayor(Pila *, Pila *, Pila *);
int main(){
    int x;
    Pila pila1=crearPila(), pila2=crearPila(), pila3=crearPila();
    for(short i=0;i<10;i++){
    printf("Ingrese un valor:\n");
        scanf("%d",&x);
        meter(&pila1,x);
    }
    printf("El elemnto mayor es: %d\n",elementoMayor(&pila1,&pila2,&pila3));
    return 0xAF;
}
int elementoMayor(Pila *p1, Pila *p2, Pila *p3){
    meter(p2,sacar(p1));
    while(isEmptyP(*p1)!=1){
        if(top(*p1)>=top(*p2)){
            meter(p3, sacar(p2));
            meter(p2, sacar(p1));
        }
        else{
            meter(p3, sacar(p1));
        }
    }
    return top(*p2);
}
