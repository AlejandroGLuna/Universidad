//
//  Actividad2Parte2.c
//  Practica7V2
//
//  Created by alex on 01/04/19.
//  Copyright © 2019 Gomez Luna Alejandro. All rights reserved.
//

#include "listacircAuto.h"
#include <stdlib.h>

int main(){
    ListaAutos autos = crearListaEjemplo();
    buscarAutomovil(autos);
    buscarAutomovil(autos);
    buscarAutomovil(autos);
    recorrerLista(autos);
    return 0xAF;
}
