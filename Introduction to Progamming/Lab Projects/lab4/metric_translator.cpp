/*This program converts kilograms, centimeters, and kilometers to pounds, inches, and miles respectively.*/
#include <iostream>
using namespace std;

int main()
{
	double kilos, pounds, centimeters, inches, kilometers, miles;
	
	cout << "Enter kilograms:";
	cin >> kilos;
	pounds = (kilos * 2.20462);
	cout << "Pounds:" << pounds << endl;
	
	cout << "Enter centimeters:";
	cin >> centimeters;
	inches = (centimeters * 0.393701);
	cout << "Inches:" << inches << endl;
	
	cout << "Enter kilometers:";
	cin >> kilometers;
	miles = (kilometers * 0.621371);
	cout << "Miles:" << miles << endl;

	return 0;
}
