/*This program prints the powers of 2 from zero to a user inputted integer.*/
#include <iostream>
#include <cmath>
using namespace std;

int main()
{

int number;
int counter;

cout << "A table of powers, enter a number: ";
cin >> number;

for(counter=0; counter<=number; counter++)
 cout << "2 to the power of " << counter << " is " << pow(2,counter) << endl;

return 0;
}
