/*This program takes a positive integer as input and prints the integer except each odd digit is printed twice.*/
#include <iostream>
using namespace std;

int multiDigit(int x){
 if (x == 0) return 0;
 else if (x < 0) cout << "Negative";
 multiDigit(x/10);
 cout << x % 10;
 if(x % 2 == 1) cout << x % 10;
}

int main()
{

int digit;

cout << "Enter digit: ";
cin >> digit;

multiDigit(digit);

return 0;
}
