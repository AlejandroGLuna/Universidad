//
//  Ejercicio3.c
//  Practica02
//
//  Created by Alumno on 2/18/19.
//  Copyright © 2019 Alumno. All rights reserved.
//

#include <stdio.h>
#include <math.h>
long a,b;
long *pa, *pb;
void funcionLoca(long *x, long *y)
{
    long z=*x,w;
    *x=pow(*x,*y);
    printf("El primer numero %ld elevado al segundo numero %ld es: %ld\n",z,*y,*x);
    *y=(*x)/z;
    printf("La divison del resultado anterior %ld entre el primer numero %ld es: %ld\n",*x,z,*y);
}
int main()
{
    printf("Ingrese dos valores enteros, separados por comas: \n");
    scanf("%ld,%ld",&a,&b);
    pa=&a;
    pb=&b;
    funcionLoca(pa,pb);
    return 0xAF;
}
