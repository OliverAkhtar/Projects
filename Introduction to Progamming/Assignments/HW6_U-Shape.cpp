/*This program prints a U-shape of user's input of a character and height.*/
#include <iostream>
using namespace std;

void uShape(int rows, char symbol){
	for (int r = 1; r <= rows; r++){
	 for (int c = 1; c <= rows; c++){
	  if (c == 1) cout << symbol;
	  else if (c == rows) cout << symbol;
	  else if (r == rows) cout << symbol;
	  else cout << " ";}
	 cout << endl;
	}
}

int main()
{

int rows;
char symbol;

cout << "Enter height: ";
cin >> rows;

while (rows <= 0){
 cout << "Please enter positive integer: ";
 cin >> rows;}

cout << "Enter symbol: ";
cin >> symbol;

uShape(rows, symbol);

return 0;
}
