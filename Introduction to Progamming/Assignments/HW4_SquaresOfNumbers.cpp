/*This program takes a positive number greater than 1 and prints the square of all numbers from one to the number entered.*/
#include <iostream>
#include <cmath>
using namespace std;

int main()
{

int positive;
int counter=1;

cout << "Enter a positive number greater than 1: ";
cin >> positive;

while (positive <= 1)
 {cout << "Try again: ";
  cin >> positive;}

while (counter <= positive)
 {cout << pow(counter,2) << " ";
  counter++;}

return 0;
}
