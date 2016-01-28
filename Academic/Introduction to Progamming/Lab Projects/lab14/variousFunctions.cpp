/*This program contains 4 functions, divisibleByN checks if one number is divisible by another, printName prints a user inputted name, threePattern
prints an upside down right triangle of height 3 and character '*', anyPattern prints an upside down right traingle of user inputted height and 
character.*/
#include <iostream>
#include <string>
using namespace std;

bool divisibleByN(int a, int b){
	 if(a % b == 0) return true;
	 else return false;}

void printName(string name){
	 cout << "Your name is: " << name << endl;}

void threePattern (){
	 for (int r = 3; r >= 1; r--){
	  for (int c = 1; c <= r; c++){
	   cout << "*";}
	  cout << endl;}
	 }

void anyPattern(int h, char symbol){
	 for (int r = h; r >=1; r--){
	  for (int c = 1; c <= r; c++){
	   cout << symbol;}
	  cout << endl;}
	 }

int main()
{

int number, divisor;
string name;
int height; char c;

cout << "Enter dividend: ";
cin >> number;
cout << "Enter divisor: ";
cin >> divisor;

if (divisibleByN(number, divisor)) cout << "Divisible." << endl;
else cout << "Not divisible."<< endl;

cout << "Enter name: ";
cin >> name;
printName(name);

threePattern();

cout << "Enter height: ";
cin >> height;
cout << "Enter symbol: ";
cin >> c;
anyPattern(height, c);

return 0;
}
