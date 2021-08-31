#include<stdio.h>
#include<stdlib.h>
#include "listadoble.h"

main(){
	printf("Bienvenido al programa \n");
 	
	int opcion,elemento = 0;
	Lista miLista =crearLista();
	addFinalLista(&miLista,10);
	addFinalLista(&miLista,20);
//	addIesimoLista(&miLista,5,1);
	addFinalLista(&miLista,30);
	addFinalLista(&miLista,40);
//	addIesimoLista(&miLista,15,2);
//	addIesimoLista(&miLista,25,4);
	addFinalLista(&miLista,50);
	addFinalLista(&miLista,60);
	

	print_list(miLista);

}
