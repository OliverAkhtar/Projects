/*This program prints an upside down right triangle of height 20 star characters.*/
#include <iostream>
using namespace std;

int main() {

for(int r = 1; r <=20; r++){
 for(int c = 1; c <= 21-r; c++)
  cout << "*";
 cout << endl;
 }
return 0;
}
