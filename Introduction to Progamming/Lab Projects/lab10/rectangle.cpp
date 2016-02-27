/*This program prints a rectangle of a user inputted length n, and width 2*n.*/
#include <iostream>
using namespace std;

int main()
{

int n;

cout << "Enter a positive integer: ";
cin >> n;
if (n<1)
 return 0;

for (int r = 1; r <= n; r++){
 for (int c = 1; c <= (2*n); c++){
  cout << "*";}
cout << endl;}

return 0;
}
