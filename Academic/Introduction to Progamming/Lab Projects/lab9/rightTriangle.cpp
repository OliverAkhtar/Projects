/*This program prints a right triangle of a user inputted height.*/
#include <iostream>
using namespace std;

int main()
{

int row;
int column;
int height;

cout << "Enter triangle height: ";
cin >> height;

for(row=1; row<=height; row++){
 for(column=1; column<=row; column++)
   cout << "*";
 cout << endl;}

return 0;
}
