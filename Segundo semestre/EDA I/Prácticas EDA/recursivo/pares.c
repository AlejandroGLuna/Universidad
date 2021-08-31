 #include <stdio.h>
#include <stdio.h>
void pares(int n);

int main()
{
    int number;
    printf("Ingresa el numero: ");
    scanf("%d", &number);
    pares(number);
}

void pares(int num) {
    if(num%2!=0)
    	num--;
    if (num >= 0){
  	    printf("%d \n",num);
		pares(num-2);
    }
}


