/*This program outputs solutions to place 8 numbers in a cross such that no two adjacent locations in the cross contain consecutive integers, using a
recursive algorithm.*/
#include<iostream>
#include<cmath>
#include <cstdlib>
using namespace std;

bool ok(int* a, int c)
{
	int h[8][5] = { { -1, -1, -1, -1, -1 },
	{ 0, -1, -1, -1, -1 },
	{ 1, -1, -1, -1, -1 },
	{ 0, 1, 2, -1, -1 },
	{ 1, 2, 3, -1, -1 },
	{ 2, 4, -1, -1, -1 },
	{ 0, 3, 4, -1, -1 },
	{ 3, 4, 5, 6, -1 } };
	for (int i = 0; i<c; i++)
	{
		if (a[c] == a[i])
		{
			return false;
		}
	}
	for (int j = 0; j<4; j++)
	{
		if (abs(a[c] - a[h[c][j]]) == 1)
		{
			return false;
		}
	}
	return true;
}

void print(int* a)
{
	static int count = 0;
	count++;
	cout << "Solution " << count << ":\n";
	cout << " " << a[1] << a[2] << " " << endl;
	cout << a[0] << a[3] << a[4] << a[5] << endl;
	cout << " " << a[6] << a[7] << endl;
	cout << endl;
}

void set(int* a, int c)
{
	if (c == 8)
	{
		print(a);
		return;
	}
	for (int j = 1; j<9; j++)
	{
		a[c] = j;
		if (ok(a, c))
		{
			set(a, c + 1);
		}
	}
};

int main()
{
	int a[8];
	set(a, 0);
	return 0;
}