#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<malloc.h>
#include<math.h>
FILE *archivo;
char opcion, repito, nombre[50], titulo[20], crearArchivo;
int generarMatriz(char letras[], char matriz[][6])
{
    char orden;
    int m,i,j,r=0,x=0;
    for(m=0;m<9;m+=2)
    {
        if(letras[m]==-92 || letras[m]==-91)
        {
            letras[m]='N';
            r++;
        }
        letras[m]=toupper(letras[m]);
    }
    for (i=0;i<7;i+=2)
    {
        for (j=i+2;j<9;j+=2)
        {
            if (letras[i]>letras[j])
            {
                orden=letras[i];
                letras[i]=letras[j];
                letras[j]=orden;
            }
        }
    }
    j=0;
    m=0;
    if(r>0)
    {
        while(j==0)
        {
            if(letras[m]=='N' && letras[m+2]!='N')
            {
                letras[m]='\xA5';
                j++;
            }
            if(letras[m]=='N' && letras[m+2]=='N')
            {
                letras[m+2]='\xA5';
                j++;
            }
            m+=2;
        }
    }
    for(i=1;i<6;i++)
    {
        matriz[0][i]=letras[x];
        x+=2;
    }
    x=0;
    for(i=1;i<6;i++)
    {
        matriz[i][0]=letras[x];
        x+=2;
    }
    for(i=0;i<6;i++)
    {
        printf("\n");
        for(j=0;j<6;j++)
        {
            if(i==2 && j==4)
                printf("I");
            if(i==3 && j==3)
                printf("N");
            printf("%c ",matriz[i][j]);
        }
    }
    return r;
}
void conversionesFinales(int textoFinal, char texto[], int matrizFinal, char matriz[][6])
{
    int i, j=0, m=0;
    if(textoFinal>0)
    {
        for(i=0;i<strlen(texto);i++)
        {
            if(texto[i]=='\xA5')
                texto[i]='Ñ';
            else
                texto[i]=texto[i];
        }
    }
    if(matrizFinal>0)
    {
        while(j<2)
        {
            if(matriz[0][m]=='\xA5')
            {
                matriz[0][m]='Ñ';
                j++;
            }
            if(matriz[m][0]=='\xA5')
            {
                matriz[m][0]='Ñ';
                j++;
            }
            m++;
        }
    }
}
int lecturaArchivoCreado(char textoFuente[], char textoFinal[])
{
    int n=0, z=0, textoLargo, a, i=0;
    textoLargo=strlen(textoFuente);
    if(textoFuente[0]=='T' && textoFuente[1]=='e' && textoFuente[2]=='x' && textoFuente[3]=='t' && textoFuente[4]=='o')
    {
        while(a<1)
        {
            if(textoFuente[i]==':')
                a++;
            i++;
        }
        for(a=i;a<textoLargo;a++)
        {
            if(textoFuente[a]==-15 || textoFuente[a]==-47)
            {
                textoFinal[n]='\xA5';
                z++;
            }
            else
                textoFinal[n]=textoFuente[a];
            n++;
        }
        textoFinal[n]='\0';
    }
    else
    {
        for(a=0;a<textoLargo;a++)
        {
            if(textoFuente[a]==-15 || textoFuente[a]==-47)
            {
                textoFinal[a]='\xA5';
                z++;
            }
            else
                textoFinal[a]=textoFuente[a];
            n++;
        }
        textoFinal[a]='\0';
    }
    strupr(textoFinal);
    return z;
}
void escrituraArchivo(FILE *archivo, char matriz[][6], char usuario[], char textoOriginal[])
{
    int i,j;
    fprintf(archivo,"\nTexto Original:");
    fprintf(archivo,textoOriginal);
    fprintf(archivo,"\nUsuario:");
    fprintf(archivo,usuario);
    fprintf(archivo,"\nMatriz utilizada:");
    for(i=0;i<6;i++)
    {
        fprintf(archivo,"\n");
        for(j=0;j<6;j++)
        {
            if(i==3 && j==3)
            {
                fprintf(archivo,"N");
                fprintf(archivo,"Ñ ");
            }
            else if(i==2 && j==4)
            {
                fprintf(archivo,"I");
                fprintf(archivo,"%c ",matriz[i][j]);
            }
            else
                fprintf(archivo,"%c ",matriz[i][j]);
        }
    }
    fclose(archivo);
}
void textosPosibles(int IJ, int IJx[], int NN, int NNx[])
{
    int posibilidadesIJ, posibilidadesNN, i;
    if(posibilidadesIJ>=0)
    {
        int repeticionesIJ;
        char *cantidadI, *cantidadJ;
        cantidadI=(char*)malloc(IJ*sizeof(char));
        cantidadJ=(char*)malloc(IJ*sizeof(char));
        for(i=0;i<IJ;i++)
        {
            cantidadI[i]='I';
            cantidadJ[i]='J';
        }
        posibilidadesIJ=pow(2,IJ);
        repeticionesIJ=(posibilidadesIJ/2);
        for(i=0;i<repeticionesIJ;i++)
        {
            
        }
    }
    if(posibilidadesNN>=0)
    {
        int repeticionesNN;
        char *cantidadN, *cantidadN2;
        posibilidadesNN=pow(2,NN);
    }
}
int main()
{
    do
    {
        printf("Programada realizado por:\nG%cmez Luna Alejandro\nCort%cs Contreras Tania\nFUNDAMENTOS DE PROGRAMACI%cN\nProfesora:M.C Cintia Quezada Reyes\n",162,130,224);
        printf("Ingrese su nombre, sin usar car%ccteres especiales:\n",160);
        scanf(" %[^\n]",&nombre);
        puts("Ingrese el nombre del archivo que desea crear");
        scanf("%s",&titulo);
        archivo=fopen(titulo,"r");
        if(archivo!=NULL)
        {
            int i,j,n=1;
            char *punteroMatrizA[25];
            char matriz[6][6]={{' ',' ',' ',' ',' ',' '},{' ','A','B','C','D','E'},{' ','F','G','H','J','K'},{' ','L','M','\xA5','O','P'},{' ','Q','R','S','T','U'},{' ','V','W','X','Y','Z'} };
            for(i=1;i<6;i++)
            {
                for(j=1;j<6;j++)
                {
                    punteroMatrizA[n]=&matriz[i][j];
                    n++;
                }
            }
            printf("Seleccione una de las siguientes opciones:\n A) Modificaci%cn de un texto\n B) Obtenci%cn del texto original\n",162,162);
            scanf("%s",&opcion);
            opcion=toupper(opcion);
            switch(opcion)
            {
                case 'A':
                {
                    FILE *sobre;
                    int m=0, x=0, textoLargo, q, a=0, textoFinal, r, z=0;
                    char texto[100], l[9], *conversion, temp[100];
                    fscanf(archivo," %[^\n]",&texto);
                    textoLargo=strlen(texto);
                    z=lecturaArchivoCreado(texto,temp);
                    puts("Ingrese 5 letras separadas por comas, para generar la matriz.");
                    scanf("%s,%s,%s,%s,%s",&l[0],&l[1],&l[2],&l[3],&l[4],&l[5],&l[6],&l[7],&l[8]);
                    r=generarMatriz(l,matriz);
                    textoLargo=((strlen(temp))*2);
                    conversion=(char*)malloc(textoLargo*sizeof(char));
                    j=0;
                    for(a=0;a<strlen(temp);a++)
                    {
                        if(temp[a]!=' ')
                        {
                            n=1;
                            i=0;
                            x=0;
                            q=0;
                            while(i==0)
                            {
                                if(temp[a]==*punteroMatrizA[n])
                                {
                                    x=(n/5);
                                    q=(n%5);
                                    if(q==0)
                                    {
                                        conversion[j]=matriz[x][0];
                                        conversion[j+1]=matriz[0][5];
                                    }
                                    else
                                    {
                                        conversion[j]=matriz[x+1][0];
                                        conversion[j+1]=matriz[0][q];
                                    }
                                    i=1;
                                    j+=2;
                                }
                                else if(temp[a]=='I')
                                {
                                    conversion[j]=matriz[2][0];
                                    conversion[j+1]=matriz[0][4];
                                    i=1;
                                    j+=2;
                                }
                                else if(temp[a]=='N')
                                {
                                    conversion[j]=matriz[3][0];
                                    conversion[j+1]=matriz[0][3];
                                    i=1;
                                    j+=2;
                                }
                                n++;
                            }
                        }
                        else
                            m++;
                    }
                    m=(m*2);
                    textoFinal=(textoLargo-m);
                    conversionesFinales(z,temp,r,matriz);
                    printf("\nTexto Convertido:");
                    for(i=0;i<textoFinal;i++)
                    {
                        printf("%c",conversion[i]);
                        if(conversion[i]==-91)
                            conversion[i]='Ñ';
                        else
                            conversion[i]=conversion[i];
                    }
                    conversion[textoFinal]='\0';
                    sobre=fopen(titulo,"w");
                    fprintf(sobre,"Texto Convertido:");
                    fprintf(sobre,conversion);
                    escrituraArchivo(sobre,matriz,nombre,temp);
                    free(conversion);
                    break;
                }
                case 'B':
                {
                    FILE *sobreB;
                    int a=0, textoLargo,m, xf, xc, r=0, z=0, numIJ=0, numNN=0, posicionIJ[20], posicionNN[20], contador=0;
                    char texto[100], temp[100], l[9], orden, *punteroMatrizBfila[6], *punteroMatrizBcolumna[6], *conversion, *conversion2;
                    fscanf(archivo," %[^\n]",&texto);
                    textoLargo=strlen(texto);
                    z=lecturaArchivoCreado(texto,temp);
                    puts("Ingrese las 5 letras usadas para la matriz, separadas por comas.");
                    scanf("%s,%s,%s,%s,%s",&l[0],&l[1],&l[2],&l[3],&l[4],&l[5],&l[6],&l[7],&l[8]);
                    r=generarMatriz(l,matriz);
                    n=1;
                    for(i=1;i<6;i++)
                    {
                        punteroMatrizBfila[n]=&matriz[i][0];
                        punteroMatrizBcolumna[n]=&matriz[0][i];
                        n++;
                    }
                    textoLargo=((strlen(temp))/2);
                    conversion=(char*)malloc(textoLargo*sizeof(char));
                    conversion2=(char*)malloc(textoLargo*sizeof(char));
                    m=0;
                    j=0;
                    for(a=0;a<strlen(temp);a+=2)
                    {
                        i=0;
                        n=1;
                        while(i<2)
                        {
                            if(temp[a]==*punteroMatrizBfila[n])
                            {
                                xf=n;
                                i++;
                            }
                            if(temp[a+1]==*punteroMatrizBcolumna[n])
                            {
                                xc=n;
                                i++;
                            }
                            if(i==2)
                            {
                                conversion[j]=matriz[xf][xc];
                                if(xf==2 && xc==4)
                                {
                                    posicionIJ[contador]=a;
                                    numIJ++;
                                    m++;
                                    contador++;
                                }
                                else if(xf==3 && xc==3)
                                {
                                    posicionNN[contador]=a;
                                    numNN++;
                                    m++;
                                    contador++;
                                }
                                j++;
                            }
                            n++;
                        }
                    }
                    conversionesFinales(z,temp,r,matriz);
                    if(m>0)
                    {
                        textosPosibles(numIJ, posicionIJ, numNN, posicionNN);
                        /*printf("\nTexto Posible:");
                        for(i=0;i<textoLargo;i++)
                        {
                            printf("%c",conversion[i]);
                            if(conversion[i]=='\xA5')
                                conversion[i]='Ñ';
                        }
                        conversion[textoLargo]='\0';
                        printf("\nTexto Posible:");
                        for(i=0;i<textoLargo;i++)
                        {
                            printf("%c",conversion2[i]);
                            if(conversion2[i]=='\xA5')
                                conversion2[i]='Ñ';
                        }
                        conversion2[textoLargo]='\0';
                        sobreB=fopen(titulo,"w");
                        fprintf(sobreB,"Texto Posible:");
                        fprintf(sobreB,conversion);
                        fprintf(sobreB,"\nTexto Posible:");
                        fprintf(sobreB,conversion2);*/
                    }
                    else
                    {
                        printf("\nTexto Posible:");
                        for(i=0;i<textoLargo;i++)
                        {
                            printf("%c",conversion[i]);
                            if(conversion[i]=='\xA5')
                                conversion[i]='Ñ';
                        }
                        conversion[textoLargo]='\0';
                        sobreB=fopen(titulo,"w");
                        fprintf(sobreB,"Texto Posible:");
                        fprintf(sobreB,conversion);
                    }
                    escrituraArchivo(sobreB,matriz,nombre,temp);
                    free(conversion);
                    free(conversion2);
                    break;
                }
                default:
                    printf("Opci%cn no v%clida, favor de revisar la entrada",162,160);
            }
            fclose(archivo);
        }
        else
        {
            printf("Archivo no encontrado, %cDesea crear el archivo?(S/N)",168);
            scanf("%s",&crearArchivo);
            crearArchivo=toupper(crearArchivo);
            if(crearArchivo=='S')
            {
                FILE *archivoNuevo;
                char tituloArchivo[50], textoArchivo[100];
                puts("De un nombre para el archivo");
                scanf("%s",&tituloArchivo);
                puts("Introduzca el texto a convertir");
                scanf(" %[^\n]",&textoArchivo);
                archivoNuevo=fopen(tituloArchivo,"w");
                fprintf(archivoNuevo,textoArchivo);
                fclose(archivoNuevo);
            }
            else
                puts("Archivo no creado");
        }
        printf("\n%cDesea repetir el programa?(S/N)",168);
        scanf("%s",&repito);
        repito=toupper(repito);
    }
    while(repito=='S');
    return 0xAF;
}



