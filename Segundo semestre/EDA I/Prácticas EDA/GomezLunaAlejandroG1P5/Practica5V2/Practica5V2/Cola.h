#include <stdio.h>
#include <stdlib.h>

typedef struct{
    int primero;
    int ultimo;
    int lista[100];
}Cola;

//declaración de funciones para trabajar con la estructura
Cola crearCola(void);
int isEmptyC(Cola);
void encolar(Cola*,int);
int desencolar(Cola*);
int first(Cola);

Cola crearCola(){
	Cola c;
	c.primero=1;
	c.ultimo=0;
	return c;
}

int isEmptyC(Cola c){
	if(c.primero==c.ultimo+1)
		return 1;
	return 0;
}

void encolar(Cola *c,int x){
    c->ultimo+=1;
    c->lista[c->ultimo]=x;
}

int desencolar(Cola *c){
    if(isEmptyC(*c)==1){
        printf("La cola esta vacia");
        return -1;
    }
    else{
        int x;
        x=c->lista[c->primero];
        c->lista[c->primero]=0;
        c->primero+=1;
        return x;
    }
}

int first(Cola c){
    if(isEmptyC(c)==1){
        printf("La cola esta vacia");
        return -1;
    }
    else{
        return c.lista[c.primero];
    }
}



