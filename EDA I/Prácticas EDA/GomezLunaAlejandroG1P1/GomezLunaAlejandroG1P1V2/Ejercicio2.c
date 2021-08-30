//
//  Ejercicio2.c
//  Practica1Gomez
//
//  Created by Alumno on 2/11/19.
//  Copyright © 2019 Alumno. All rights reserved.
//

#include <stdio.h>
#define C 10
void main(){
    int lista[C]={10,20,30,40,50,60,70,80,90,100};
    int lista4[5]={20,40,60,80,100};
    int lista2[C];
    int resultado=0, i=0, resultado4=0;
    do{
        printf("%d,",lista[i]);
        resultado+=lista[i];
        i++;
    } while(i<C);
        printf("\n");
    for(i=0;i<5;i++)
        resultado4+=lista4[i];
        
    for(i=0;i<C;i++){
        lista2[i]=lista[i]*2;
        printf("%d,",lista2[i]);
    }
        printf("\n");
    printf("El valor es: %d \n",resultado);
    printf("El valor de los elementos divisibles entre 4 es: %d \n",resultado4);
    
}
