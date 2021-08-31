#include<stdio.h>
#include "lista.h"
int cuenta(Nodo*);

main(){
	Lista miLista = crearLista();

	Nodo *nodo = miLista.head;
	int numero = cuenta(nodo);
	printf("La lista tiene %d  nodos \n",numero);	

}


int cuenta(Nodo* miNodo){
	if(miNodo!=NULL)
		return 1 + cuenta(miNodo->next);
	
}
