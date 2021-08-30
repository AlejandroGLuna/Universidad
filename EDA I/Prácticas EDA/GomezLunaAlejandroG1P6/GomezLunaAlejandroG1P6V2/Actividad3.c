//
//  Actividad3.c
//  Practica6V2
//
//  Created by alex on 25/03/19.
//  Copyright © 2019 Gomez Luna Alejandro. All rights reserved.
//

#include "colaDoble.h"

int main(){
    Cola superCola=crearCola(8);
    printf("************ESTADO 1************\n");
    encolarInicio(&superCola, 103);
    encolarInicio(&superCola, 104);
    encolarInicio(&superCola, 105);
    encolarFinal(&superCola, 102);
    mostrarValores(superCola);
    printf("************ESTADO 2************\n");
    desencolarFinal(&superCola);
    desencolarInicio(&superCola);
    encolarInicio(&superCola,120);
    encolarInicio(&superCola, 121);
    encolarFinal(&superCola, 99);
    encolarFinal(&superCola, 98);
    encolarFinal(&superCola, 97);
    mostrarValores(superCola);
    printf("************ESTADO 3************\n");
    desencolarFinal(&superCola);
    desencolarFinal(&superCola);
    desencolarFinal(&superCola);
    encolarInicio(&superCola, 122);
    mostrarValores(superCola);
}
