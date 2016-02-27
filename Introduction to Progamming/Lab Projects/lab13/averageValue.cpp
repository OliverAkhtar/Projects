/*This program returns the average value of two user inputted integers.*/
#include <iostream>
using namespace std;

double average(double a, double b){
 double avg;
 avg = ((a + b)/2);
 return avg;}

int main()
{

 double x, y;

 cout << "Enter first integer: ";
 cin >> x;
 cout << "Enter second integer: ";
 cin >> y;

 cout << "The average is: " << average(x,y) << endl;

 return 0;
}
