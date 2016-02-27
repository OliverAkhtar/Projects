/*This program reverses the integers in an array.*/
#include <iostream>
using namespace std;

void reverse(int arr[], int size)
{
	int counter = size - 1;
	for(int i = 0; i < size/2; i++)
	{
		int temp = arr[i];
		arr[i] = arr[counter];
		arr[counter] = temp;
		counter--;
	}
}

int main()
{
	int a[6] = {1, 2, 3, 4, 5, 6};
	reverse(a, 6);
	cout << a[0] << a[1] << a[2] << a[3] << a[4] << a[5] << endl;
	return 0;
}