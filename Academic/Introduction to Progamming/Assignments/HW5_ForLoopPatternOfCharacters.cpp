/*This program uses for loops to print a pattern of characters.*/
#include <iostream>
using namespace std;

int main()
{
	for(int i = 1; i <= 5; i++)
	{
		for(int j = 1; j <= (i*2) - 1; j++)
		{
			cout << "*";
		}
		cout << endl;
	}
	
	for(int i = 5; i >= 1; i--)
	{
	    for(int j = 1; j <= (i*2) - 1; j++)
	    {
	        cout << "*";
	    }
	    cout << endl;
	}
	
	return 0;
}
