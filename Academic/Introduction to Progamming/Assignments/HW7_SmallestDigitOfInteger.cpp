/*This program takes a positive integer as user input and prints the smallest digit in the integer.*/
#include <iostream>
using namespace std;

int smallestDigit(int n){
    int a, b;
    if ( n < 10 ) return n;
    a = (n % 10); b = smallestDigit(n / 10);
    if (a < b) return a;
    else return b;
} 

int main( )
{

int number;

cout << "Enter positive integer: ";
cin >> number;

if (number < 0) return 0;

cout << "Smallest digit is: " << smallestDigit(number);

return 0;
}
