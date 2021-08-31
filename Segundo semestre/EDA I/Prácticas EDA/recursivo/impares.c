 #include <stdio.h>
#include <stdio.h>
void impares(int n);

int main()
{
    int number;
    printf("Ingresa el numero: ");
    scanf("%d", &number);
    impares(number);
}

void impares(int num) {
    if(num%2==0)
    	num--;
    if (num > 0){
  		impares(num-2);
	  	printf("%d \n",num);
	}
}


