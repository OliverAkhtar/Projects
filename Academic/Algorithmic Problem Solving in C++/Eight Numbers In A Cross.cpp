/*This program outputs solutions to place 8 numbers in a cross such that no two adjacent locations in the cross contain consecutive integers.*/
#include <iostream>
#include <cstdlib>
#include <cmath>
using namespace std;

bool ok(int b[], int c){
 int h[8][5]={{-1}, {0, -1}, {0, 1, -1}, {0, 2, -1}, {1, 2, -1}, {1, 2, 3, 4, -1}, 
             {2, 3, 5, -1}, {4, 5, 6, -1}};
 for(int i = 0; i < c; i++){
  if(b[i]==b[c]) return false;}
 for(int j=0; h[c][j] !=-1; j++){
  int i=h[c][j];
  if(abs(b[c]-b[i])==1) return false;}
 return true;} //performs adjacent check, and if use of numbers is repeated

void backtrack(int &col){
 col--;
 if(col==-1) {system("PAUSE"); exit(1);}} //Once program is forced into column -1, all solutions have been found

void print(int b[]){
 static int count = 0;
 cout << "Solution " << ++count << ": " << endl;
 cout << "  " << b[1] << "  " << b[4] << endl;
 cout << b[0] << " " << b[2] << "  " << b[5] << "  " << b[7] << endl;
 cout << "  " << b[3] << "  " << b[6] << endl;} //Print cross style

int main(){
 int b[8], c = 1;
 b[0]=1;
 bool fromBacktrack = false;
 while(true){ //Keeps checking for solutions until backtrack function executes exit statement
  while(c < 8){ //7 is limit of size 8 array
   if(!fromBacktrack) b[c]=0; //Start of range of integers is 1, so set zero before increment
   fromBacktrack = false;
   while(b[c] < 9){ //Limit of range is 8
    b[c]++;
    if(b[c]==9){
     backtrack(c);
     continue;}
    if(ok(b,c)) break;
   }
   c++;
  }
  print(b);
  backtrack(c); 
  fromBacktrack = true;
 }
}
