/*This program prints the 2 digit of a user inputted postive integer of at least 3 digits.*/
#include <iostream>
using namespace std;

int main()
{

int n;

cout << "Enter a positive integer with at least 3 digits: ";
cin >> n;

if (n < 1) return 0;
if (n <100) return 0;

while (n > 100)
 n = n/10;

n = n%10;

cout << "The second digit is: " << n;

return 0;
}
