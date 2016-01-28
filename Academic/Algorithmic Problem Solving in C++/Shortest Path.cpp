/*Given an mXn array/matrix of integers, this program outputs the shortest path from the leftmost column to rightmost column, and cost for that path using
memoization.*/
#include<iostream>
#include <cstdlib>
using namespace std;

int cost[5][6]={{3,4,1,2,8,6},{6,1,8,2,7,4},{5,9,3,9,9,5},{8,4,1,3,2,6},{3,7,2,8,6,4} };
int Memo[5][6]={0};
int row123[7]= {4,0,1,2,3,4,0};

int Min(int a, int b, int c){
	if(a<=b&&a<=c)
		return a;
	else if(b<=a&&b<=c)
		return b;
	else
		return c;
};
int MinSum(int row, int col) { // Recursive function
	if(col==0){ // Base case
		Memo[row][col]= cost[row][col];
		return cost[row][col];
	}
	if(Memo[row][col]==0){
		if(row==0)
			Memo[row][col]= cost[row][col] + Min( MinSum(4,col-1), MinSum(row, col-1), MinSum(row+1, col-1) );
		else if(row==4)
			Memo[row][col]= cost[row][col] + Min( MinSum(row-1,col-1), MinSum(row, col-1), MinSum(0, col-1) );
		else
			Memo[row][col]= cost[row][col] + Min( MinSum(row-1, col-1), MinSum(row, col-1), MinSum(row+1, col-1) );
	}
	return Memo[row][col];
};// MinSum
// Shortest Path Function
void SPath(int Svalue, int row, int col){
	int MiniP, rowA=0; // Minimum of three numbers
	int r1, r2, r3;
	if(col==0) // Base case
	{ cout<<row+1<<" ";
	return;
	}
	MiniP= Svalue-cost[row][col];
	// To find the three rows in previous columns
	for(int i=1; i<=5; i++) {
		if(row==row123[i]){
			r1= row123[i-1];
			r2= row123[i];
			r3= row123[i+1];
			break;
		}// if loop ends
	}// for loop ends
	// To get the new row for Next Call
	if(MiniP==Memo[r1][col-1])
		rowA= r1;
	else if(MiniP==Memo[r2][col-1])
		rowA= r2 ;
	else if(MiniP==Memo[r3][col-1])
		rowA= r3;
	SPath(MiniP , rowA, col-1);
	cout<<row+1<<" ";
};
int main(){
	int Save[5], miniS=1000, alpharow;
	for(int i=0;i<5;i++) { //Traversing through the last column and finding the minimum sum
		Save[i]= MinSum(i,5); // Shift this stuff to recursive function
		if(miniS>=Save[i]){
			miniS= Save[i];
			alpharow=i;
		}// if loop ends here
	}
	cout<<"Shortest Path: "; SPath(miniS , alpharow, 5);
	cout<<"Cost: "<<miniS<<endl; // miniS contains the final smallest cost function value
	// alpharow contains is row at column 5, where smallest cost function lives
	return 0;
}