/*This program returns the absolute value of a user inputted integer.*/
#include <iostream>
using namespace std;

int absoluteNumber (int n) {
  if (n < 0) n = (-1 * n);
  return n;
 }

int main()
{

 int n;
 cout << "Enter an interger: ";
 cin >> n;
 cout << "The absolute value of " << n << " is " << absoluteNumber(n) << endl;

 return 0;
}
