//
//  Ejercicio3.c
//  Practica1Gomez
//
//  Created by Alumno on 2/11/19.
//  Copyright © 2019 Alumno. All rights reserved.
//

#include <stdio.h>
#include<stdlib.h>
#include<time.h>
int main()
{
    int calificaciones[5][10]={1,2,3,4,5,6,7,8,9,10},i,j;
    for(i=1;i<4;i++){
        for(j=0;j<10;j++){
            srand(time(0));
            calificaciones[i][j]=(rand()%6)+5;
        }
    }
    for(i=0;i<10;i++){
        calificaciones[4][i]=calificaciones[3][i]+calificaciones[2][i]+calificaciones[1][i];
        if(((calificaciones[4][i])%3)>1)
            calificaciones[4][i]=((calificaciones[4][i])/3)+1;
        else
            calificaciones[4][i]=((calificaciones[4][i])/3);
    }
    printf("Ingrese el numero de lista del alumno deseado.\n");
    scanf("%d",&j);
    printf("\nLos datos del alumno son: \n");
    printf("N.L:%d Calif:%d %d %d Promedio:%d \n",j,calificaciones[1][j-1],calificaciones[2][j-1],calificaciones[3][j-1],calificaciones[4][j-1]);
    return 0xAF;
}

