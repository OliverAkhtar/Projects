/*This program outputs all 82 solutions of placing 8 queens on an 8x8 chessboard such that no two queens can attack each either, using backtracking, 
a two dimensional array, and goto statements. */
#include<iostream>
using namespace std;

int main()
{
int b[8][8]={0}, r, c = 0; 
b[0][0]=1;
int sol=0;

NC: c++;
if(c==8)goto print;
r=-1;

NR: r++;
if(r==8)goto backtrack;

//row test
for(int i = 0; i<c; i++)
 if(b[r][i]==1)goto NR;

//up diag test
for(int i = 1; ((r-i)>=0&&(c-i)>=0); i++)
 if(b[r-i][c-i]==1)goto NR;

//down diag test
for(int i = 1; ((r+i)<8&&(c-i)>=0); i++)
 if(b[r+i][c-i]==1)goto NR;

b[r][c]=1;
goto NC;
backtrack:c--;
if(c==-1)return 0;
r=0;
while(b[r][c]==0)
 r++;
b[r][c]=0;
goto NR;

//print
print:{sol++;
for(int i = 0; i < 8; i++){
 for(int j = 0; j < 8; j++){
  cout << b[i][j];}
cout << endl;}
cout << "Solution: " << sol << endl << endl;} 

goto backtrack;
}




