#include<stdio.h>
#include<stdlib.h>
#include <math.h>
#include<omp.h>
#include<time.h>

void llenarArreglo(int *a[], int n);
void liberarMemoria(int *a[], int n);
void imprimirArreglo(int *a[], int n);
void next(int *a[], int n);
void sort(int line[], int n);
void swap(int line[],int a, int b);

int main(){
    int n;
    printf("Ingrese el orden de la matriz cuadrada: ");
    scanf("%d",&n);
    int *a[n];
    llenarArreglo(a,n);
    printf("\n\t***Matriz inicial***\n");
    imprimirArreglo(a,n);
    next(a,n);
    printf("\t***Matriz final***\n");
    imprimirArreglo(a,n);
    liberarMemoria(a,n);
    return 0xAF;
}

int b=0;

void next(int **a, int n){
    int line[n];
    int x=0,y=0;
    for(int i=0;i<=log2(n);i++){
        b=0;
        for(y=0;y<n;y++){
            if(y%2==0){
                for(x=0;x<n;x++)
                   line[x]=a[y][x];
                sort(line,n);
                for(x=0;x<n;x++)
                    a[y][x]=line[x];
            }else{
                for(x=0;x<n;x++)
                    line[x]=a[y][x];
                sort(line,n);
                for(x=0;x<n;x++)
                    a[y][x]=line[n-x-1];
            }
        }
        for(x=0;x<n;x++){
            for(y=0;y<n;y++)
                line[y]=a[y][x];
            sort(line,n);
            for(y=0;y<n;y++)
                a[y][x]=line[y];
        }
        if(b==0)
            break;
    }
}

//Odd-even sort
void sort(int a[], int n){
    int i=0,j=0,temp;
    #pragma omp parallel private(temp,j,i) shared(a)
    {
        for(i=0;i<n;i++){
            if(i%2!=0){
                #pragma omp for
                for(j=0;j<n-1;j+=2)
                {
                    if(a[j]> a[j+1])
                    {
                        b++;
                        temp = a[j];
                        a[j] = a[j+1];
                        a[j+1] = temp;
                    }
                }
            }
            else{
                #pragma omp for
                for(j=1;j<n-1;j+=2)
                {
                    if(a[j]> a[j+1])
                    {
                        b++;
                        temp = a[j];
                        a[j] = a[j+1];
                        a[j+1] = temp;
                    }
                }
            }
        }
    }
}

void llenarArreglo(int *a[], int n){
    int i,j;
    srand(time(NULL));
    for(i=0;i<n;i++){
        a[i] = malloc(n* sizeof(int));
        for(j=0;j<n;j++){
            a[i][j] = rand()%100;
        }
    }
}

void imprimirArreglo(int *a[], int n){
    int i,j;
    for(i=0;i<n;i++){
        for(j=0;j<n;j++){
            printf("%d ",a[i][j]);
        }
        printf("\n");
    }
}

void liberarMemoria(int *a[], int n){
    int i,j;
    for(i=0;i<n;i++)
        free(a[i]);
}
