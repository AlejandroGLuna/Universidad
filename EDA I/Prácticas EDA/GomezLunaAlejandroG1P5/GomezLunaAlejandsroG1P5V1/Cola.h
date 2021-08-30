#include <stdio.h>
#include <stdlib.h>

typedef struct{
    int primero;
    int ultimo;
    int lista[100];
}Cola;

//declaración de funciones para trabajar con la estructura
Cola crearCola();
int isEmpty(Cola);
void encolar(Cola*,int);
int desencolar(Cola*);
int first(Cola);

Cola crearCola(){
	Cola c;
	c.primero=1;
	c.ultimo=0;
	return c;
}
int isEmpty(Cola c){
	if(c.primero==c.ultimo+1)
		return 1;
	return 0;
}
void encolar(Cola *c,int x){
}
int desencolar(Cola *c){

}
int first(Cola c){

}

