//
//  Ejemplo2.c
//  Practica02
//
//  Created by Alumno on 2/18/19.
//  Copyright © 2019 Alumno. All rights reserved.
//

#include <stdio.h>

int arr1[3][3][4], var,var2,var3,i,j,k,m=0;
int *point;
void main(){
    int i;
    point=arr1;
    var=1;
    for(i=0;i<3;i++){
        for(j=0;j<3;j++){
            for(k=0;k<4;k++){
                arr1[i][j][k]=var;
                var+=3;
            }
        }
    }
    int a= *(point+7);
    int b= *(point+17);
    int c= *(point+30);
    var=5;
    var2=6;
    var3=7;
    for(i=0;i<36;i++)
    {
        if(i<12)
        {
            *(point+i)=var;
            var+=5;
        }
        else if(i>11 && i<24)
        {
            *(point+i)=var2;
            var2+=6;
        }
        else
        {
            *(point+i)=var3;
            var3+=7;
        }
    }
    for(i=0;i<3;i++){
        for(j=0;j<3;j++){
            for(k=0;k<4;k++){
                printf("El valor del arreglo en la posicion[%d][%d][%d] es: %d\n",i,j,k,*(point+m));
                m++;
            }
        }
    }
    
}
