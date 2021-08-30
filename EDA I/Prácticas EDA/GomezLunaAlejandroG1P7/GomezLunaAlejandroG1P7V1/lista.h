//
//  lista.h
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
}Nodo;

typedef struct{
    struct Nodo* head;
}Lista;

Lista crearLista(void);
void print_list(Lista);
void addPrincipioLista(Lista*,int);
void addFinalLista(Lista*,int);
void borrarPrimero(Lista*);
void borrarUltimo(Lista*);
void eliminarIesimo(Lista*,int);
void eliminarMayores(Lista*,int);
int primerElemento(Lista);
int buscarElemento(Lista, int);

Lista crearLista(){
    Lista lista;
    lista.head = NULL;
    return lista;
}

void print_list(Lista lista) {
    if(lista.head==NULL){
        printf("LA LISTA ESTA VACIA \n");
    }
    else{
        printf("Los elementos de la lista son: \n");
        Nodo *current = lista.head;
        while (current != 0) {
            printf("%d \n",current->val);
            current = current->next;
        }
    }
}

void addFinalLista(Lista *lista, int val) {
    if(lista->head==NULL){
        
        Nodo *nodo = (Nodo*)malloc(sizeof(Nodo));
        nodo->val = val;
        nodo->next = NULL;
        lista->head = nodo;
    }
    else
    {
        Nodo *current = lista->head;
        while (current->next != 0) {
            current = current->next;
        }
        Nodo *nuevoNodo;
        nuevoNodo = (Nodo*)malloc(sizeof(Nodo));
        nuevoNodo->val = val;
        nuevoNodo->next = NULL;
        current->next = nuevoNodo;
    }
}
void addPrincipioLista(Lista *lista, int val) {
    Nodo *new_node;
    new_node = (Nodo*)malloc(sizeof(Nodo));
    new_node->val = val;
    new_node->next = lista->head;
    lista->head = new_node;
}

void addIesimoLista(Lista *lista, int val){
    
    int contador, posicion;
    printf("Ingrese la posicion");
    scanf("%d",&posicion);
    Nodo *temp;   //creacion de nodo temporal para recorrer la lista
    temp=lista->head;
    if(posicion==1)
        addPrincipioLista(lista, val);
    else{
        for(contador=1;contador<posicion-1;contador++)   {
            temp=temp->next;
        }
        Nodo *nuevoNodo;
        nuevoNodo = (Nodo*)malloc(sizeof(Nodo));
        nuevoNodo->val = val;
        nuevoNodo->next = temp->next;
        temp->next = nuevoNodo;
    }
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
            while (current->next->next != NULL) {
                current = current->next;
            }
            current->next = NULL;
            free(current->next);
            
        }
    }
    
}

int primerElemento(Lista lista){
    return lista.head->val;
}

int buscarElemento(Lista p, int x){
    Nodo *temp=p.head;
    int y=1,k=0;
    while(temp!=NULL){
        if(temp->val==x){
            k=1;
            break;
        }
        y++;
    }
    if(k==0){
        printf("Objeto no encontrado");
        return -1;
    }
    else
        return y;
}

void eliminarIesimo(Lista *p, int x){
    if (p->head == NULL) {
        printf("La lista esta vacia");
    }
    else{
        Nodo *temp = p->head;
        if(temp->next==NULL){
            p->head = NULL;
            free(p->head);
        }else{
            if(x==1)
                borrarPrimero(p);
            else{
                int contador=0;
                while(contador<(x-2)){
                    temp=temp->next;
                    contador++;
                }
                Nodo *eliminado=temp->next;
                temp->next=eliminado->next;
                free(eliminado);
            }
        }
    }
}

void eliminarMayores(Lista *p, int x){
    Nodo *temp=p->head;
    int y=1,m=0;
    while(temp!=NULL){
        if((temp->val)>x){
            printf("Elemento eliminado con valor de:%d\n",temp->val);
            eliminarIesimo(p, y);
            m++;
        }
        y++;
        temp=temp->next;
    }
    if(m==0)
        printf("No se elimino ningun elemento\n");
    else
        printf("Se eliminaron %d elementos",m);
}

