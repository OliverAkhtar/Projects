/*This program checks for string equivalence of user input and "Freddy" then terminates, if not equivalent asks user for a positive
integer and prints the last digit of that integer.*/
#include <iostream>
#include <string>
using namespace std;

int main()
{
 string input;
 int integer;

 cout << "Input your name:";
 cin >> input;

 if (input=="Freddy")
 	return 0;
 else
	{
	 cout << "Enter a positive interger:";
	 cin >> integer;
	 if (integer > 0)
	 	cout << (integer%10);
	}
 return 0;
}
