/*This program contains three functions, roots replaces each entry in an array of doubles with its square root, smallestEntry returns the smallest integer
in an array of integers, and oddElements returns the number of odd elements in an array of integers.*/
#include <iostream>
#include <cmath>
using namespace std;

double roots(double array[], int capacity){
 for(int i = 0; i < capacity; i++){
  array[i] = sqrt (array[i]);}
}

int smallestEntry(int array[], int capacity){
 int smallest;
 smallest = array[0];
 for(int i = 0; i < capacity; i++){
  if (smallest > array[i]) smallest = array[i];}
 return smallest;
}

int oddElements(int array[], int capacity){
 int oddElements = 0;
 for(int i = 0; i < capacity; i++){
  if ((array[i] % 2) != 0) oddElements++;}
 return oddElements;
}

int main()
{

double data[3] = {1.0, 4.0, 9.0};
int num[5];

roots(data, 3);

for(int i = 0; i < 3; i++)
 cout << data[i] << " ";

for(int i = 0; i < 5; i++){
 cout << "Entry" << " " << i + 1 << ": ";
 cin >> num[i];}

cout << smallestEntry(num, 5) << endl;

for(int i = 0; i < 5; i++){
 cout << "Enter Entry " << i + 1 << ": ";
 cin >> num[i];}

cout << oddElements(num, 5) << endl;
 

return 0;
}
