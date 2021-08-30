//
//  listacirc.h
//  Practica7
//
//  Created by Gomez Luna Alejandro on 4/1/19.
//  Copyright © 2019 Gomez Luna Alejandro. All rights reserved.
//

#include<stdio.h>
#include<stdlib.h>
typedef struct Nodo{
    int val;
    struct Nodo* next;
};

typedef struct{
    struct Nodo* head;
    int tamano;
}Lista;

Lista crearLista();
void print_list(Lista);
void addPrincipioLista(Lista*,int);
void addFinalLista(Lista*,int);
void borrarPrimero(Lista*);
void borrarUltimo(Lista*);
void recorrerLista(Lista);

Lista crearLista(){
    Lista lista;
    lista.head = NULL;
    lista.tamano = 0;
    return lista;
}

void print_list(Lista lista) {
    if(lista.head==NULL){
        printf("LA LISTA ESTA VACIA \n");
    }
    else{
        int tamano = lista.tamano;                //se define el tamaÒo
        printf("Los elementos de la lista son: \n");
        Nodo *current = lista.head;
        while (tamano > 0 ) {                    //tamaÒo como condicional
            printf("%d \n", current->val);
            current = current->next;
            tamano--;                           //se reduce tamaÒo
        }
    }
}

void addFinalLista(Lista *lista, int val) {
    int posicion=lista->tamano;
    if(lista->head==NULL){
        Nodo *nodo = (Nodo*)malloc(sizeof(Nodo));
        nodo->val = val;
        nodo->next = nodo;
        lista->head = nodo;
    }
    else
    {
        Nodo *current = lista->head;
        while (posicion!=1) {
            current = current->next;
            posicion--;
        }
        Nodo *nuevoNodo;
        nuevoNodo = (Nodo*)malloc(sizeof(Nodo));
        current->next = nuevoNodo;
        nuevoNodo->val = val;
        nuevoNodo->next = lista->head;
        
    }
    lista->tamano++;
}
void addPrincipioLista(Lista *lista, int val) {
    
    Nodo *node,*temp;
    node = (Nodo*)malloc(sizeof(Nodo));
    node->val = val;
    if(lista->head==NULL){
        node->next=node;
    }
    else{
        node->next = lista->head;
        temp = lista->head;
        while(temp->next != lista->head){
            temp=temp->next;
        }
        temp->next=node;
    }
    lista->head = node;
    lista->tamano++;
}
void borrarPrimero(Lista *lista) {
    if (lista->head == NULL) {
        printf("La lista esta vacia");
    }
    else{
        Nodo *nuevo_head = NULL;
        Nodo *temp = lista->head;
        nuevo_head=temp->next;
        free(lista->head);
        lista->head = nuevo_head;
    }
}

void borrarUltimo(Lista *lista) {
    Nodo *temp = lista->head;
    if (lista->head == NULL) {
        printf("La lista esta vacia");
    }
    else{
        if(temp->next==NULL){
            lista->head = NULL;
            free(lista->head);
        }else{
            Nodo *current = lista->head;
            Nodo *current2 =current->next;
            while (current2->next != NULL) {
                current = current->next;
            }
            free(current->next);
            current->next = NULL;
        }
    }
}
