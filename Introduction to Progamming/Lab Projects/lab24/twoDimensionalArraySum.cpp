/*This program returns the sum of all the integers in a two dimensional array.*/
#include <iostream>
using namespace std;

int sum2D(int x[][4], int rows, int cols){
 int sum = 0;
 for(int r = 0; r < rows; r++)
  for(int c= 0; c < cols; c++)
   sum += x[r][c];
 return sum;}

int main(){

int array[3][4] = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
cout << sum2D(array,3,4) << endl;

return 0;
}
