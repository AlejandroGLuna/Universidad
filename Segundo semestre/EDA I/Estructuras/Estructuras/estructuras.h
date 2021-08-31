//
//  estructuras.h
//  Estructuras
//
//  Created by alex on 26/03/19.
//  Copyright © 2019 Gomez Luna Alejandro. All rights reserved.
//

#include<stdio.h>
#include<stdlib.h>

struct Nodo{
    int info;
    struct Nodo *next;
};

typedef struct{
    struct Nodo head;
    struct Nodo tail;
    int tope;
    int tamano;
}PilaL;

PilaL crearPilaL(int);
int isEmpty(PilaL);
void push(PilaL *,int);
int pop(PilaL *);
int top(PilaL);
void clean(PilaL *);

PilaL crearPilaL(int x){
    PilaL nuevaP;
    nuevaP.tamano=x;
    nuevaP.tope=-1;
    return nuevaP;
}

int isEmpty(PilaL p){
    if(p.tope == -1)
        return 1;
    else
        return 0;
}

void push(PilaL *p, int x){
    if(p->tope==p->tamano)
        printf("La pila esta llena\n");
    else{
        struct Nodo nuevo;
        nuevo.info=x;
        nuevo.next=NULL;
        if(isEmpty(*p)==1){
            p->head=nuevo;
            p->tail=nuevo;
            p->tope++;
        }
        else{
            if(p->tope==0){
                p->head.next=&nuevo;
                p->tail=nuevo;
                p->tope++;
            }
            else{
                p->tail.next=&nuevo;
                p->tail=nuevo;
                p->tope++;
            }
        }
    }
}

int pop(PilaL *p){
    if(isEmpty(*p))
    {
        printf("La pila esta vacia\n");
        return -1;
    }
    else{
        short aux;
        struct Nodo temp=p->head;
        while(temp.next->next!=NULL){
            printf("Ostia xd");
            temp=*temp.next;
        }
        aux=p->tail.info;
        temp.next=NULL;
        p->tail=temp;
        p->tope--;
        return aux;
    }
}

int top(PilaL p){
    if(isEmpty(p))
    {
        printf("La pila esta vacia\n");
        return -1;
    }
    else
        return p.tail.info;
}

void above(PilaL p){
    printf("El valor 1 es:%d\n",p.head.info);
    printf("El valor 2 es:%d\n",p.head.next->info);
    printf("El valor 3 es:%d\n",p.head.next->next->info);
    printf("El valor 4 es:%d\n",p.head.next->next->next->info);
}

void clean(PilaL *p){
    if(isEmpty(*p))
        printf("La pila esta vacia\n");
    else{
        for(short i=0;i<p->tope;i++)
        {
            p->head.info=0;
            p->head=*p->head.next;
        }
    }
}
