/*This program returns the minimum value of two user inputted integers.*/
#include <iostream>
using namespace std;

int minimum(int a, int b){
 if (a < b) return a;
 return b;}

int main()
{

 int a, b;

 cout << "Enter first integer: ";
 cin >> a;
 cout << "Enter second integer: ";
 cin >> b;

 cout << "The minimum of both integers is: " << minimum(a,b) << endl;
 
 return 0;
}
