/*This program returns the first digit of a user inputted positive integer.*/
#include <iostream>
using namespace std;

int main()
{

int n;

cout << "Enter a positive number: ";
cin >> n;

while (n >= 10)
 n = n/10;

cout << "The first digit is: " << n;

return 0;
}
