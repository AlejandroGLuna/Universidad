//
//  main.c
//  Practica1Gomez
//
//  Created by Alumno on 2/11/19.
//  Copyright © 2019 Alumno. All rights reserved.
//

#include <stdio.h>

int main() {
    int i,j,k;
    int arr[6][2][3]={3,5,7,9,11,13,15,17,19,21,23,25,27,29,31,33,35,37,39,41,43,45,47,49,51,53,55,57,59,61,63,65,67,69,71,73};
    for(i=0;i<6;i++){
        for(j=0;j<2;j++){
            for(k=0;k<3;k++){
                printf("Arreglo[%d][%d][%d]: %d \n",i,j,k,arr[i][j][k]);
            }
        }
    }
    return 0;
} 
