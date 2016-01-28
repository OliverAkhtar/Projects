/*For the classic Stable Marriage problem, this program outputs the best possible matches using a recursive algorithm.*/
#include<iostream>
#include<cmath>
#include<cstdlib>
using namespace std;

bool ok(int* q, int col)
{
	int mp[3][3] = { { 0, 2, 1 }, { 0, 2, 1 }, { 1, 2, 0 } };
	int wp[3][3] = { { 2, 1, 0 }, { 0, 1, 2 }, { 2, 0, 1 } };

	for (int i = 0; i<col; i++)
	{
		if (q[i] == q[col])
		{
			return false;
		}
	}

	for (int i = 0; i<col; i++)
	{
		if ((mp[i][q[col]]<mp[i][q[i]]) && (wp[q[col]][i]<wp[q[col]][col]))
		{
			return false;
		}

		if ((mp[col][q[i]]<mp[col][q[col]]) && (wp[q[i]][col]<wp[q[i]][i]))
		{
			return false;
		}
		/*if((mp[col][q[col]]<mp[i][q[i]])&&(wp[q[col]][col]<wp[q[i]][i]))
		{
		return false;
		}*/
	}

	return true;
}

void print(int* q)
{
	static int count = 0;
	count++;
	for (int i = 0; i<3; i++)
	{
		for (int j = 0; j<3; j++)
		{
			if (q[i] == j)
			{
				cout << "Man " << i << " is paired with Women " << j << endl;
			}
		}
		cout << endl;
	}
	cout << endl;

}

void move(int* q, int i)
{
	if (i == 3)
	{
		print(q);
		return;
	}

	for (int j = 0; j<3; j++)
	{
		q[i] = j;
		if (ok(q, i))
		{
			move(q, i + 1);
		}
	}
};

int main()
{
	int q[3];
	move(q, 0);
	return 0;
}