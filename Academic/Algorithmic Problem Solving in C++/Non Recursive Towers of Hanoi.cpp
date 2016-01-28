/*This program outputs instructions for the classic Towers of Hanoi problem non-recursively, and taking the the number of rings as user input.*/
#include<iostream>
#include <cstdlib>
#include<vector>
using namespace std;

int main(){
	vector<int> t[3]; // three towers A,B,C represented as an array of 3 vectors
	int n, candidate, to, from, move = 0; // move counts the move number
	cout << "Please enter any number of rings to move: ";
	cin >> n;
	cout << endl;
	//intitialize the 3 towers
	for (int k = n + 1; k >= 1; k--)
		t[0].push_back(k);
	t[1].push_back(n + 1);
	t[2].push_back(n + 1);
	// initialize towers and candidate

	if (n % 2 == 0)
	{
		from = 0;
		to = 2;
		candidate = 1;
	}
	else
	{
		from = 0;
		to = 1;
		candidate = 1;
	}
	//t[0].pop_back();

	//cout<<"Vector no.:  "<<t[0].at(3);

	while (t[1].size()<n + 1){ // rings remain to transfer to tower B = t[1]
		cout << "move number " << ++move << ": Transfer ring " << candidate << " from tower " << char(from + 65) << " to tower " << char(to + 65) << endl;
		//move the disks around
		//1. Push the top of the “from” tower to the “to” tower
		//2. Remove the ring from the “from” tower
		t[from].pop_back();
		t[to].push_back(candidate);
		int A1, A2, x[5] = { 2, 0, 1, 2, 0 };
		for (int i = 1; i<4; i++)
		{
			if (to == x[i])
			{
				A1 = x[i - 1];
				A2 = x[i + 1];
			}
		}// To find A1 and A2 and hence "from"

		if (t[A1].back() < t[A2].back())
			from = A1;
		else
			from = A2;
		// get next “to tower”            
		for (int i = 1; i<4; i++)
		{
			if (n % 2 != 0){
				if (from == x[i])
				{
					if (t[from].back() < t[x[i + 1]].back())
						to = x[i + 1];
					else
						to = x[i - 1];

				}
			}// outer if loop (n%2!=0) 
			else {
				if (from == x[i])
				{
					if (t[from].back() < t[x[i - 1]].back())
						to = x[i - 1];
					else
						to = x[i + 1];

				}
			}
		}

		//get next candidate
		candidate = t[from].back(); // it’s on the top of the current “from tower”
	}

	return 0;
}