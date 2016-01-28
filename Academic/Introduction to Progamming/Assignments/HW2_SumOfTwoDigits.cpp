/*This program takes a two digit positive integer and returns the sum of the two digits.*/
#include <iostream>
using namespace std;

int main()
{
 int integer; //two digit positive integer

 cout << "Enter a two digit positive integer:";
 cin >> integer;

 if (integer < 100)
 {
 	if (integer >= 10)
 		cout << ((integer/10)+(integer%10));
 	else
 	{
 	cout << "Too Difficult!";
 	return 0;
 	}
 }
 else
 {
 cout << "Too Difficult!";
 return 0;
 }
 return 0;
}
