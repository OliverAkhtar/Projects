/*This program compares the three pairs of strings in the main function and prints how many occurences of each character in the first string
are in the second string.*/
#include <iostream>
#include <cstring>
using namespace std;

int count(char x[], char y[]){
 int count = 0;
 int a = strlen(x);
 for (int i = 0; i < a; i++) {
     x[i] = tolower(x[i]);
 } 
 int b = strlen(y);
 for (int i = 0; i < b; i++) {
  y[i] = tolower(y[i]);
 }
 for (int i = 0; i < b; i++)
  for (int j = 0; j < a; j++)
   if(y[i] == x[j]) count++;
return count;
}

int main(){

char arr1[] = "ABRACADABRA";
char arr2[] = "bax";
char arr3[] = "caliber";
char arr4[] = "BALL";
char arr5[] = "MoroCCAN";
char arr6[] = "mocCO";

cout << count(arr1, arr2) << endl;
cout << count(arr3, arr4) << endl;
cout << count(arr5, arr6) << endl;

return 0;
}
