//
//  Ejercicio4.c
//  Practica02
//
//  Created by Alumno on 2/18/19.
//  Copyright © 2019 Alumno. All rights reserved.
//

#include <stdio.h>
void cambioValor(long *a, long *b, long *c)
{
    long Ca;
    Ca=*a;
    *a=*b;
    *b=*c;
    *c=Ca;
    printf("Intercambiando valores: %ld,%ld,%ld\n",*a,*b,*c);
}
int main()
{
    long x,y,z;
    long *px,*py,*pz;
    printf("Ingrese tres valores enteros, separados por comas\n");
    scanf("%ld,%ld,%ld",&x,&y,&z);
    px=&x;
    py=&y;
    pz=&z;
    cambioValor(px,py,pz);
    return 0xAF;
}
