/*This program prints the absolute difference of two postive two-digit integers.*/
#include <iostream>
using namespace std;

int main()
{
int x; //1st positive integer
int y; //2nd

cout << "Enter two, two-digit positive integers:";
cin >> x  >> y;

if (x==y){
 cout << "Too easy!" << endl;
 return 0;
}

else if (x<10||x>99){
 cout << "Too easy!" << endl;
 return 0;
}
else if (y<10||y>99){
 cout << "Too easy!" << endl;
 return 0;
}

int diff = x - y;
if (diff<0) {
 diff=(diff*-1);}

cout << "Absolute difference of the numbers" << x << "and" << y << "is" << diff<< endl;

return 0;
}
