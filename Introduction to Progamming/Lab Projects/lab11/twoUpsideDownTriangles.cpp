/*This program prints an upside right triangle of user inputted length, twice.*/
#include <iostream>
using namespace std;

int main()
{

int n;

cout << "Enter  a positive integer: ";
cin >> n;

for (int i = 1; i <= 2; i++){
 for(int r = n; r > 0; r--){
  for(int c = 1; c <= r; c++){
   cout << "*";}
  cout << endl;}
}

return 0;
}
