//
//  main.c
//  Strings
//
//  Created by alex on 25/02/19.
//  Copyright © 2019 Alejandro. All rights reserved.
//

#include <stdio.h>
#include <stdio.h>
#include <string.h>

//Estructuras
typedef struct{
    char nombreCiudad[20];
    int tamanio;
    int altitud;
}Ciudad;
typedef struct{
    char clave[25];
    char nombrePais[20];
    char lenguajeOficial[15];
    char moneda[15];
    Ciudad city[20];
}Pais;
typedef struct{
    char nombreCont[15];
    char posicionGeo[6];
    Pais country[20];
}Continente;

//Prototipos de Funciones
Continente crearContinente(void);
Pais crearPais(void);
Ciudad crearCiudad(void);
void asignarPaisaContinente(Pais *,Continente *);
void asignarCiudadaPais(Ciudad *,Continente *);
void mostrarInformacion(Continente *);

//Variables Globales
short j=0,k=0, l=0, posicionC[5]={0},posicionP[5][20]={0},pos=0;

//Funcion Principal
int main(){
    short b=0,y=0,z=0,w=0;
        Continente continents[5]={0};
        Continente *cn=continents;
        Pais paises[20]={0};
        Pais *p=paises;
        Ciudad ciudades[20]={0};
        Ciudad *ci=ciudades;
        while(1){
            int x;
            printf("Seleccione alguna de las siguientes opciones:\n");
            printf("1)Crear continente\n2)Crear pais\n3)Crear ciudad\n4)Mostrar informacion\n5)Salir\n");
            scanf("%d",&x);
            char nomCon[15]="";
            switch(x){
                case 1:
                    *(cn+y)=crearContinente();
                    y++;
                    break;
                
                case 2:
                    if(j==0)
                        printf("Primero debe de crear un continente\n");
                    else{
                        *(p+z)=crearPais();
                        printf("Ingrese el nombre del continente, en mayusculas, al cual pertenece:\n");
                        scanf("%s",nomCon);
                        cn=continents;
                        while(b==0){
                            if(strcmp(nomCon,cn->nombreCont)==0)
                                b++;
                            else if(pos>4)
                                b=2;
                            else{
                                cn++;
                                pos++;
                            }
                        }
                        if(b==1){
                            p=p+z;
                            asignarPaisaContinente(p,cn);
                            z++;
                        }
                        else
                            printf("Continente no encontrado\n");
                    }
                    break;
                    
                case 3:
                    if(k==0)
                        printf("Primero debe de crear un pais\n");
                    else{
                        *(ci+w)=crearCiudad();
                        printf("Ingrese el nombre del pais, en mayusculas, al cual pertenece:\n");
                        scanf("%s",nomCon);
                        cn=continents;
                        while(b==0){
                            if(strcmp(nomCon,cn->country[pos].nombrePais)==0)
                                b++;
                            else if(pos>19 && l<5){
                                l++;
                                cn++;
                                pos=0;
                            }
                            else if(l>4)
                                b=2;
                            else{
                                p++;
                                pos++;
                            }
                        }
                        if(b==1){
                            ci=ci+w;
                            asignarCiudadaPais(ci,cn);
                            w++;
                        }
                        else
                            printf("Pais no encontrado\n");
                    }
                    break;
                
                case 4:
                    printf("Ingrese el nombre del continente en mayusculas, del cual quiere obtener la informacion\n");
                    scanf("%s",nomCon);
                    cn=continents;
                    while(b==0){
                        if(strcmp(nomCon,cn->nombreCont)==0){
                            b++;
                        }
                        else if(pos>4)
                            b=2;
                        else{
                            cn++;
                            pos++;
                        }
                    }
                    if(b==1)
                        mostrarInformacion(cn);
                    else
                        printf("Continente no encontrado\n");
                    break;
                
                case 5:
                    return 0xAF;
                    break;
                    
                default:
                    printf("Opcion no valida. Revise la entrada\n");
                    break;
            }
            pos=0;
            b=0;
            l=0;
        }
    return 0xAF;
}

//Funciones secundarias
Ciudad crearCiudad(){
    Ciudad ci;
    printf("La cantidad maxima de ciudades es 20\n");
    printf("Ingrese el nombre de la ciudad\n");
    scanf("%s",ci.nombreCiudad);
    printf("Ingrese el espacio que ocupa en kilometros\n");
    scanf("%d",&ci.tamanio);
    printf("Ingrese su altitud en kilometros\n");
    scanf("%d",&ci.altitud);
    return ci;
}

Pais crearPais(){
    Pais p;
    printf("La cantidad maxima de paises es 20\n");
    printf("Ingrese el Id(Clave) del pais:\n");
    scanf("%s",p.clave);
    printf("Ingrese el nombre del pais:\n");
    scanf("%s",p.nombrePais);
    printf("Ingrese su idioma oficial:\n");
    scanf("%s",p.lenguajeOficial);
    printf("Ingrese su moneda:\n");
    scanf("%s",p.moneda);
    k++;
    return p;
}

Continente crearContinente(void){
    Continente co;
    printf("La cantidad maxima de continentes es 5\n");
    printf("Ingrese el nombre del Continente:\n");
    scanf("%s",co.nombreCont);
    printf("Ingrese su posicion geografica(Norte, Sur, Oriente, Poniente):\n");
    scanf("%s",co.posicionGeo);
    j++;
    return co;
}

void asignarCiudadaPais(Ciudad *k, Continente *cn){
    printf("%hi\n",pos);
    cn->country[pos].city[posicionP[l][pos]]=*k;
    posicionP[l][pos]++;
}

void asignarPaisaContinente(Pais *a,Continente *b){
    printf("%hi\n",pos);
    b->country[posicionC[pos]]=*a;
    posicionC[pos]++;
}

void mostrarInformacion(Continente *c){
    printf("%hi\n",pos);
    short i,a;
    printf("Nombre del continente: %s\n",c->nombreCont);
    printf("Posicion geografica: %s\n",c->posicionGeo);
    printf("Paises que contiene: \n");
    for(i=0;i<posicionC[pos];i++){
        printf("Nombre del pais: %s\n",c->country[i].nombrePais);
        printf("Clave: %s\n",c->country[i].clave);
        printf("Idioma oficial: %s\n",c->country[i].lenguajeOficial);
        printf("Tipo de moneda: %s\n",c->country[i].moneda);
        printf("Ciudades que contiene:\n");
        for(a=0;a<posicionP[pos][a];a++){
            printf("Nombre de la ciudad: %s\n",c->country[i].city[a].nombreCiudad);
            printf("Altitud de la ciudad: %d\n",c->country[i].city[a].altitud);
            printf("Tamanio de la ciudad: %d\n",c->country[i].city[a].tamanio);
        }
    }
}

