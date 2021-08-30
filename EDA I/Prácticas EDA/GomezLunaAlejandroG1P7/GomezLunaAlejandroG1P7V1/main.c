//
//  main.c
//  Practica7
//
//  Created by Gomez Luna Alejandro on 4/1/19.
//  Copyright © 2019 Gomez Luna Alejandro. All rights reserved.
//

#include "lista.h"

int main() {
    Lista ejemplo=crearLista();
    short i;
    for(i=4;i<7;i++)
        addFinalLista(&ejemplo, i);
    for(i=3;i>0;i--)
        addPrincipioLista(&ejemplo, i);
    addIesimoLista(&ejemplo, 10);
    addIesimoLista(&ejemplo, 20);
    print_list(ejemplo);
    primerElemento(ejemplo);
    borrarPrimero(&ejemplo);
    borrarUltimo(&ejemplo);
    primerElemento(ejemplo);
    print_list(ejemplo);
    return 0xAF;
}
