/*This program contains three functions, smallCol returns the smallest possible sum of the entries in any column of a two dimensional array, bigRow returns the largest possible sum of the entries in any row of a two dimensional array, numOdd returns the number of odd integers in a two dimensional array.*/
#include <iostream>
using namespace std;

int smallCol(int x[][3], int rows, int cols){
 int sum = 0;
 for(int r = 0; r < rows; r++){
  sum += x[r][0];}
 for(int c = 1; c < cols; c++){
  int testsum = 0;
  for(int r = 0; r < rows; r++){
   testsum += x[r][c];}
   if(testsum < sum) sum = testsum;}
 
 return sum;
}

int bigRow(int x[][3], int rows, int cols){
 int biggestSum = 0;
 for(int c = 0; c < cols; c++)
  biggestSum += x[0][c]; 
 for(int r = 1; r < rows; r++){
  int testsum = 0;
  for(int c = 0; c < cols; c++)
   testsum += x[r][c];
  if(testsum > biggestSum) biggestSum = testsum;
 }
return biggestSum;
}

int numOdd(int x[][4], int rows, int cols){
 int n = 0;
 cout << n << " " << endl;
 for(int r = 0; r < rows; r++){
  for(int c = 0; c < cols; c++){
   if((x[r][c] % 2) != 0) n++; cout << n << " " << endl;}
 }
return n;
}

int main()
{

int x[2][3] = {{3,1,1}, {1,5,2}};
cout << smallCol(x, 2, 3) << endl;

int y[3][3] = {{3,1,4}, {1,5,9}, {5,5,6}};
cout << bigRow(y, 3, 3) << endl;

int data[2][4] = {{11,12,14,0}, {32,12,132,11}};
cout << "The number of odds is: " << numOdd(data, 2, 4) << endl;

return 0;
}
