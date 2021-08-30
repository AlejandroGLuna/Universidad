//
//  listacircAuto.h
//  Practica7V2
//
//  Created by alex on 01/04/19.
//  Copyright © 2019 Gomez Luna Alejandro. All rights reserved.
//

#include<stdio.h>
#include<stdlib.h>
#include<string.h>

typedef struct{
    char marca[25];
    char modelo[25];
    char color[20];
    char placa[8];
    short maximoPasajeros;
}Automovil;

typedef struct Nodo{
    Automovil coches;
    struct Nodo *next;
}Nodo;

typedef struct{
    Nodo *head;
    int tamanio;
}ListaAutos;

void buscarAutomovil(ListaAutos);
void recorrerLista(ListaAutos);
ListaAutos crearListaEjemplo(void);

ListaAutos crearListaEjemplo(){
    ListaAutos lista;
    Nodo *auto1,*auto2,*auto3,*auto4;
    //Reservando memoria
    auto1=(Nodo*)malloc(sizeof(Nodo));
    auto2=(Nodo*)malloc(sizeof(Nodo));
    auto3=(Nodo*)malloc(sizeof(Nodo));
    auto4=(Nodo*)malloc(sizeof(Nodo));
    //Poniendoles marca
    strcpy(auto1->coches.marca, "AUDI");
    strcpy(auto2->coches.marca, "PEUGEOT");
    strcpy(auto3->coches.marca, "BMW");
    strcpy(auto4->coches.marca, "VOLKSWAGEN");
    //Poniendoles modelo
    strcpy(auto1->coches.modelo, "Q9");
    strcpy(auto2->coches.modelo, "BIPPER");
    strcpy(auto3->coches.modelo, "Z4-ROADSTER");
    strcpy(auto4->coches.modelo, "PASSAT2018");
    //Poniendoles color
    strcpy(auto1->coches.color, "NEGRO");
    strcpy(auto2->coches.color, "BLANCO");
    strcpy(auto3->coches.color, "ROJO");
    strcpy(auto4->coches.color, "AZUL");
    //Poniendoles placa
    strcpy(auto1->coches.placa, "URU197A");
    strcpy(auto2->coches.placa, "ARG100E");
    strcpy(auto3->coches.placa, "TIS740X");
    strcpy(auto4->coches.placa, "NEL834O");
    //Poniendoles asientos
    auto1->coches.maximoPasajeros=4;
    auto2->coches.maximoPasajeros=6;
    auto3->coches.maximoPasajeros=5;
    auto4->coches.maximoPasajeros=3;
    //Enlazandolos
    auto1->next=auto2;
    auto2->next=auto3;
    auto3->next=auto4;
    auto4->next=auto1;
    //Enlazando con la lista
    lista.head=auto1;
    return lista;
}

void buscarAutomovil(ListaAutos a){
    char mBuscar[25];
    printf("Introduzca la marca del automovil a buscar\n");
    scanf("%s",mBuscar);
    int size=a.tamanio,x=0;
    Nodo *current=a.head;
    while(size>0){
        if(strcmp(mBuscar,current->coches.marca)==0){
            x++;
            break;
        }
        current=current->next;
        size--;
    }
    if(x==0)
        printf("Automovil no encontrado\n");
    else{
        printf("Los detalles del automovil buscado son:\n");
        printf("\tMarca:%s\n",current->coches.marca);
        printf("\tModelo:%s\n",current->coches.modelo);
        printf("\tColor:%s\n",current->coches.color);
        printf("\tPlaca:%s\n",current->coches.placa);
        printf("\tPasajeros Maximos:%hd\n",current->coches.maximoPasajeros);
    }
}

void recorrerLista(ListaAutos a){
    Nodo *current=a.head;
    short x=0,i=0;
    while(x==0){
        printf("Elemento actual:\n ***Automovil marca:%s***\n",current->coches.marca);
        printf("Seleccione el numero de la opcion que desea realizar\n");
        printf("\t1)Mostrar el siguiente vehiculo\n\t2)Mostrar detalles del vehiculo actual\n\t3)Salir de la lista\n");
        scanf("%hd",&i);
        switch(i){
            case 1:
                current=current->next;
                break;
            case 2:
                printf("Marca:%s\n",current->coches.marca);
                printf("Modelo:%s\n",current->coches.modelo);
                printf("Color:%s\n",current->coches.color);
                printf("Placa:%s\n",current->coches.placa);
                printf("Maxima cantidad de pasajeros:%hd\n",current->coches.maximoPasajeros);
                break;
            case 3:
                x=1;
                break;
            default:
                printf("Entrada no valida, intente de nuevo\n");
                break;
        }
    }
}
