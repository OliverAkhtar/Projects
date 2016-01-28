/*This program outputs all solutions of placing a user input of N queens on an NxN chessboard such that no two queens can attack each either, using backtracking, a one dimensional array, and without goto statements. */
#include <iostream>
#include<cstdlib>
#include <cmath>
using namespace std;
bool ok(int q[], int col){
	for (int i = 0; i<col; i++){
		if ((q[i] == q[col]) || (abs(q[col] - q[i]) == (col - i)))
		{
			return false;
		}
	}
	return true;
}
void backtrack(int &col, int m, int n1){
	col--;
	if (col == -1){
		cout << "There are " << m << " solutions of " << n1 << " queens problem." << endl;
		system("pause");
		exit(1);
	}
}
int main(){
	int n;
	cout << "Enter the number of Queens for the solution: ";
	cin >> n;
	cout << endl;
	int* q = new int[n];
	int c = 1, count = 0;
	q[0] = 0;
	//delete[]q;
	// from_backtrack keeps track if we need to reset the row to the
	// top of the current colum or not.
	bool from_backtrack = false;
	// The outer loop keep looking for solutions
	// The program terminates from function backtrack
	// when we are forced to backtack into column -1
	while (1){
		while (c<n){ //this loop goes across columns
			// if we just returned from backtrack, use current value of row
			// otherwise get ready to start at the top of this column
			if (!from_backtrack) // we did not just return from backtrack
				q[c] = -1; //check here
			from_backtrack = false;
			while (q[c]<n){ // place queen in this column
				q[c]++;
				// if row=8, there is no valid square in this column
				// so backtrack and continue the loop in the previous column
				while (q[c] == n)
				{
					backtrack(c, count, n);
					q[c]++;
				}
				//if this position is ok, place the queen
				// and move on (break) to the next column,
				// otherwise keep looking in this column
				if (ok(q, c))
					break;
			}// while q[c]<8
			c++; // placed ok, move to the next column
		}//while(c<8)
		// one complete solution found, print it.
		//print(q); // board completed, print it out
		count++;
		backtrack(c, count, n);
		from_backtrack = true;
	}//while(1)
}//main method