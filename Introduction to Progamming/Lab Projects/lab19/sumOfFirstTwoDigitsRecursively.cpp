//return sum first two digits of a number, if number has one digit return that digit
#include <iostream>
using namespace std;

int useRecursion(int x)
{
	if(x < 100) return (x % 10) + (x / 10);
	return useRecursion(x / 10);
}

int main()
{
	cout << useRecursion(567982) << endl;
	cout << useRecursion(428690) << endl;
	cout << useRecursion(104324) << endl;
	cout << useRecursion(7) << endl;
	return 0;
}