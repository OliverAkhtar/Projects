/*This program converts a user input of Celsius temperature to Fahrenheit and prints it to console.*/
#include <iostream>
using namespace std;

int main() 
{
	double ctemp, ftemp;
	cout << "Enter Celsius Temperature:";
	cin >> ctemp;
	ftemp = (ctemp * 1.8) + 32;
	cout << "The Fahrenheit Temperature is: " << ftemp;
	return 0;
}
