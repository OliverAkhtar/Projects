/*This program prints a right triangle of a given height where an even row is made of '0' characters and an odd row is made of 'X' characters.*/
#include <iostream>
using namespace std;

int main()
{

int n;

cout << "Enter a postive integer: ";
cin >> n;
while ( n < 1 )
 {cout << "Try again!: ";
  cin >> n;}

for (int r = 1; r <= n; r++){
 for (int c = 1; c <= r; c++){
  if (r % 2 == 0)
   cout << "0";
  else cout << "X";}
cout << endl;}

return 0;
}
