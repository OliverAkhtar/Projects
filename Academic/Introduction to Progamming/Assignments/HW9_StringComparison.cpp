/*This program checks each character in the string "abracadabra" for how many times in occurs in the string "bax".*/
#include <iostream>
#include <cstring>
using namespace std;

int count(char a[], char b[]){
 int c = strlen(a);
 int d = strlen(b);
 int n = 0;
 for(int i = 0; i < c; i++){
  for(int j = 0; j < d; j++){
   if(a[i] == b[j]) n++;}}
 return n;
}

int main(){

cout << count("abracadabra","bax");

return 0;
}

