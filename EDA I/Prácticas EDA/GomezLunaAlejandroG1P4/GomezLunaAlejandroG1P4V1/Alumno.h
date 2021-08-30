//
//  Alumno.h
//  Practica4
//
//  Created by Alumno on 3/4/19.
//  Copyright © 2019 Alumno. All rights reserved.
//

#include <stdio.h>
#include <stdlib.h>
typedef struct {
    char calle[20];
    int num;
    char colonia[20];
    int cPostal;
}Direccion;
typedef struct {
    int numCuenta;
    char nombre[15];
    char apellido[15];
    float promedio;
    Direccion domicilio;
}Alumno;

Alumno crearAlumno(int, char*, char*, float);
void imprimirAlumno(Alumno);

Alumno crearAlumno(int numeroC, char *name, char *lastname, float prom){
    Alumno alumnoCreado;
    alumnoCreado.numCuenta = numeroC;
    strcpy(alumnoCreado.nombre,name);
    strcpy(alumnoCreado.apellido,lastname);
    alumnoCreado.promedio = prom;
    return alumnoCreado;
}
void imprimirAlumno(Alumno alumnoPrint){
    
    printf("Numero de cuenta: %d \n",alumnoPrint.numCuenta);
    printf("Name: %s \n",alumnoPrint.nombre);
    printf("LastName : %s  \n ", alumnoPrint.apellido);
    
}
