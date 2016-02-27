/*This program shifts the contents of an array to the "right" by one.*/
#include <iostream>
using namespace std;

void shiftRight(double array[], int capacity){
 double last = array[(capacity - 1)];
 for(int i = (capacity - 1); i > 0; i--){
  array[i] = array[(i - 1)];}
 array[0] = last;
}

int main()
{

double array[] = {5.8, 2.6, 9.1, 3.4, 7.1};

for(int i = 0; i < 5; i++){
 cout << array[i] << "|";}
cout << endl;
 

shiftRight(array, 5);

for(int i = 0; i < 5; i++){
 cout << array[i] << "|";}

cout << endl;

return 0;
} 
