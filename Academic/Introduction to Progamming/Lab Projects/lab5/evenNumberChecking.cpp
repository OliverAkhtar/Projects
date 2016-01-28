/*This program takes a integer as user input and if the integer is even, prints the integer divided by 2.*/
#include <iostream>
using namespace std;

int main()
{
 int integer;

 cout << "Enter an integer:";
 cin >> integer;
 
 if (integer%2==0)
 	cout << "Integer divided by 2 is:" << (integer/2);
 return 0;
}
