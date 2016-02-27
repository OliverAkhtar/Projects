/*This program outputs all 82 solutions of placing 8 queens on an 8x8 chessboard such that no two queens can attack each either, using backtracking, 
a one dimensional array, without goto statements, and printing an more visually rich chessboard using pointers and typedef.*/
#include <iostream>
using namespace std;

void backtrack(int &col){
	col--;
	if (col == -1) exit(1);
}

bool ok(int q[], int col){
	for (int i = 0; i < col; i++){
		if ((q[i] == q[col]) || (abs(q[col] - q[i]) == (col - i)))
		{
			return false;
		}
	}
}

void print(int q[]){
	int i, j, k, l;
	typedef char box[5][7];
	box bb, wb, *board[8][8];
	box wq, bq;

	//fill in wq=white queen and bq=black queen
	for (int i = 0; i < 7; i++){
		wq[0][i] = ' ';
		wq[4][i] = ' ';
		bq[0][i] = char(219);
		bq[4][i] = char(219);
	}
	for (int i = 0; i < 7; i++){
		if ((i % 2) == 0){
			wq[1][i] = ' ';
			bq[1][i] = char(219);
		}
		else {
			wq[1][i] = char(219);
			bq[1][i] = ' ';
		}
	}
	for (int i = 0; i < 7; i++){
		if (i != 0 && i != 6){
			wq[2][i] = char(219);
			wq[3][i] = char(219);
			bq[2][i] = ' ';
			bq[3][i] = ' ';
		}
		else{
			wq[2][i] = ' ';
			wq[3][i] = ' ';
			bq[2][i] = char(219);
			bq[3][i] = char(219);
		}
	}

	//fill in bb=black box and wb=whitebox
	for (i = 0; i < 5; i++)
	for (j = 0; j < 7; j++)
	{
		wb[i][j] = ' ';
		bb[i][j] = char(219);
	}

	//fill board with pointers to bb and wb in alternate positions
	for (i = 0; i < 8; i++)
	for (j = 0; j < 8; j++)
	if ((i + j) % 2 == 0)
		board[i][j] = &wb;
	else
		board[i][j] = &bb;

	//fill board with solution from array q
	for (int i = 0; i < 8; i++){
		if ((i + q[i]) % 2 == 0) board[i][q[i]] = &wq;
		else board[i][q[i]] = &bq;
	}


	// print the board via the pointers in array board
	// first print upper border
	cout << "     ";
	for (i = 0; i < 7 * 8; i++)
		cout << '_';
	cout << endl;
	// now print the board
	for (i = 0; i < 8; i++)
	for (k = 0; k < 5; k++)
	{
		cout << "     " << char(179); //print left border
		for (j = 0; j < 8; j++)
		for (l = 0; l < 7; l++)
			cout << (*board[i][j])[k][l];
		cout << char(179) << endl; // at end of line print bar and then newline
	}
	//before exiting print lower border
	cout << "     ";
	for (i = 0; i < 7 * 8; i++)
		cout << char(196);
	cout << endl;

	//clean up board
	for (i = 0; i < 8; i++)
	for (j = 0; j < 8; j++)
	if ((i + j) % 2 == 0)
		board[i][j] = &wb;
	else
		board[i][j] = &bb;
}

int main(){
	int q[8]; q[0] = 0; int c = 1;

	bool from_backtrack = false;

	while (true){
		while (c<8){
			if (!from_backtrack)
				q[c] = -1;
			from_backtrack = false;
			while (q[c]<8){
				q[c]++;
				if (q[c] == 8){
					backtrack(c);
					continue;
				}
				if (ok(q, c)) break;
			}
			c++;
		}
		print(q);
		backtrack(c);
		from_backtrack = true;
	}
	return 0;
}