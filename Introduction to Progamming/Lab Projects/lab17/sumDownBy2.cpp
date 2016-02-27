/*This program uses recursion to sum all numbers from a user inputted integer to those subtracted from the input by ever multiple of two until the difference
is less than or equal to n.*/
#include <iostream>
using namespace std;

int sumDownBy2(int n){
 if (n <= 2) return n;
 return n + sumDownBy2(n - 2);}

int main()
{
int x;

cout << "Enter positive integer: ";
cin >> x;

cout << sumDownBy2(x);

return 0;
}
