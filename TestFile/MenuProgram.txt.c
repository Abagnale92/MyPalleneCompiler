#include <stdio.h>
#include <string.h>
#include <math.h>
typedef int bool;
#define false 0
#define true 1

int continu = 1;
int array[50];
int arr[50];

int Menu ();
int Somma ();
int Moltiplicazione ();
int Divisione (int  ,int  );
int Potenza ();
int Fibonacci (int  );

int  Menu ()
{
	{
	int scelta;	
	printf("Scegli l'operazione da eseguire ");
	printf("1 - Somma ");
	printf("2 - Moltiplicazione ");
	printf("3 - Divisione ");
	printf("4 - Elevamento a potenza ");
	printf("5 - Successione di Fibonacci ");
	printf("0 - Esci dal Menu'");
	scanf("%d", &scelta);
	return scelta;
	};
}

int  Somma ()
{
	{
	int a;
int b;	
	printf("Inserisci a:");
	scanf("%d", &a);
	printf("Inserisci b:");
	scanf("%d", &b);
	return (a + b);
	};
}

int  Moltiplicazione ()
{
	{
	int a;
int b;
int c = 0;
int i;	
	printf("Inserisci a:");
	scanf("%d", &a);
	printf("Inserisci b:");
	scanf("%d", &b);
	int z=0;
for(z = 1;(z <= b); z++){

	c = (c + a);
};
	return c;
	};
}

int  Divisione (int  a, int  b )
{
	{
	int i = a;
int c = 0;	
	while (i >= b){	
	i = (i - b);
	c = (c + 1);
	};
	return c;
	};
}

int  Potenza ()
{
	{
	int a;
int b;
int c = 1;
int i;	
	printf("Inserisci a:");
	scanf("%d", &a);
	printf("Inserisci b");
	scanf("%d", &b);
	int z=0;
for(z = 1;(z <= b); z++){

	c = (c * a);
};
	return c;
	};
}

int  Fibonacci (int  n )
{
	if (n == 0){
	return 0;
};
	if (n == 1){
	return 1;
}
else{
	return (Fibonacci((n - 1)) + Fibonacci((n - 2)));
};
}

int  main ()
{
	{
	int a;
int b;	
	while (continu == 1){	
	{
	int scelta = Menu();	
	if (scelta == 1){
	printf("%d", Somma());
};
	if (scelta == 2){
	printf("%d", Moltiplicazione());
};
	if (scelta == 3){
	printf("Inserisci a");
	scanf("%d", &a);
	printf("Inserisci b");
	scanf("%d", &b);
	printf("%d", Divisione(a,b));
};
	if (scelta == 4){
	printf("%d", Potenza());
};
	if (scelta == 5){
	printf("Inserisci a:");
	scanf("%d", &a);
	printf("%d", Fibonacci(a));
};
	if (scelta == 0){
	continu = 0;
};
	};
	};
	};
}

