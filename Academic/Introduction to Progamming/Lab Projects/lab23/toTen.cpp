//toTen returns the number of array entries needed from index 0 until their is a sum of 10 or more
#include <iostream>
using namespace std;

int toTen(int arr[], int size)
{
	int sum = 0;
	int counter = 0;
	for(int i = 0; sum < 10; i++)
	{
		sum += arr[i];
		counter++;
	}
	return counter;
}

int main()
{
	int x[7] = {5, 2, 1, 1, 0, 9, 7};
	cout << toTen(x, 7);
	return 0;
}