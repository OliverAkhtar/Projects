/*This program prints a numeric countdown from a user inputted positive integer to zero.*/
#include <iostream>
using namespace std;

int main()
{
int positive;
int counter=0;

cout << "Enter a positive integer:";
cin >> positive;

if (positive <=0)
 return 0;

while (counter <= positive)
 {
	 cout << (positive - counter) <<  " ";
	 counter++;
 }

return 0;
}
