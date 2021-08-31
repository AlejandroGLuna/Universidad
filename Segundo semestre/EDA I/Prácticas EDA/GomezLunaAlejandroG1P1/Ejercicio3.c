//
//  Ejercicio3.c
//  Practica1Gomez
//
//  Created by Alumno on 2/11/19.
//  Copyright © 2019 Alumno. All rights reserved.
//

#include <stdio.h>
#include <math.h>
int main(){
    int cuadrado[3][5],i,j;
    printf("Ingresa 5 numeros separados por comas: \n");
    scanf("%d,%d,%d,%d,%d",&cuadrado[0][0],&cuadrado[0][1],&cuadrado[0][2],&cuadrado[0][3],&cuadrado[0][4]);
    for(i=1;i<3;i++){
        for(j=0;j<5;j++)
            cuadrado[i][j]=pow(cuadrado[i-1][j],2);
    }
    printf("Arreglo resultante:");
    for(i=0;i<3;i++){
        for(j=0;j<5;j++)
            printf("%d \n",cuadrado[i][j]);
    }
}
