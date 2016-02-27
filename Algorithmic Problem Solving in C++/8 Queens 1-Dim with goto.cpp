/*This program outputs all 82 solutions of placing 8 queens on an 8x8 chessboard such that no two queens can attack each either, using backtracking, 
a one dimensional array, and goto statements. */
#include <iostream>
#include <cmath>
using namespace std;

int main()
{
int q[8], c = 0, solcount = 0;

q[0] = 0;

NC: c++;
if(c==8) goto print;
q[c] = -1;

NR: q[c]++;
if(q[c]==8) goto backtrack;
for(int i = 0; i < c; i++)
 if(q[i]==q[c]||c-i==abs(q[c]-q[i])) goto NR;

goto NC;
backtrack: c--;
if(c==-1) return 0;
goto NR;

print:{
solcount++;
cout << "Solution: " << solcount << endl;
int s[8][8] = {0};
for(int i = 0; i < 8; i++)
 s[i][(q[i])] = 1;
for(int r = 0; r < 8; r++){
 for(int c = 0; c < 8; c++){
  if(s[r][c]==1) cout << 1;
  else cout << 0;}
 cout << endl;}
cout << endl;}

goto backtrack;
} 
