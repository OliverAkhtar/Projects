/*This program uses recursion to sum all numbers from one to a user inputted integer.*/
#include <iostream>
using namespace std;

int sumAll(int x){
 if (x == 1) return 1;
 return x  + sumAll(x - 1);}

int main()
{
int n;

cout << "Enter integer: ";
cin >> n;

cout << sumAll(n);

return 0;
}
