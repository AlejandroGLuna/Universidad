#include <stdio.h>
struct Alumno{
	int numCuenta;
	char *nombre;
	char apellido[20];
	float promedio;
};

main(){
	struct Alumno alumno1;
	alumno1.numCuenta=123456;
	alumno1.nombre="Bart";
//	alumno1.apellido="Simpson";			//error
	strcpy(alumno1.apellido,"Simpson");
	struct Alumno alumno2 ={78901,"Lisa"};
	
	printf("Nombre: %s \n",alumno1.nombre);
	printf("Apellido: %s  \n",alumno1.apellido);
	printf("Nombre 2: %s \n",alumno2.nombre);
	printf("promedio: %f  \n",alumno2.promedio);
	struct Alumno *ap1;
	ap1 = &alumno1;
	ap1->numCuenta=1000;
	printf("Num cuenta es: %d",ap1->numCuenta);



}




