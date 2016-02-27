/*This program prints the minimum of three user inputted integers.*/
#include <iostream>
using namespace std;

int main()
{
	int a, b, c;
	cout << "Enter three numbers:";
	cin >> a >> b >> c;
	int min = a;
	if(b < min)
		min = b;
	if(c < min)
		min = c;
	cout << "Minimum of three is:" << min;
	return 0;
}
