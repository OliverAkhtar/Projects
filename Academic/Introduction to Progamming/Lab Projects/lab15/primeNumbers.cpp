/*This program takes a positive integer greater than 1 by user input and prints whether or not it is prime, then prints all the primes from 2 to 100.*/
#include <iostream>
using namespace std;

int isPrime(int a){
 for(int n = 2; n <= a; n++)
  if(a % n == 0) return n;
}

int main()
{

int a;

cout << "Enter a positive integer greater than 1: ";
cin >> a;

if(a == isPrime(a)) cout << "Prime." << endl;
else cout << "Not Prime." << endl;

for(int n = 2; n <= 100; n++){
 if(n == isPrime(n)) cout << n << " ";}

return 0;
}
