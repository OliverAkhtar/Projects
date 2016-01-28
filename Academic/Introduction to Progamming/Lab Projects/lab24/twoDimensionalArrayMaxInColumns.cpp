/*This program takes 5 quiz grades each for 3 students, then prints which student got the highest score along with the score for each quiz.*/
#include <iostream>
using namespace std;

int main(){

int scores[3][5];

for(int s = 0; s < 3; s++){
 for(int q = 0; q < 5; q++){
  cout << "Enter quiz " << (q+1) << " for student " << (s+1) << " ";
  cin >> scores[s][q];}}

for(int q = 0; q < 5; q++){
 int highest = scores[0][q];
 for(int s = 1; s < 3; s++)
  if(scores[s][q] > highest) highest = scores[s][q];
 for(int s = 0; s < 3; s++)
  if(highest == scores[s][q]) cout << "Quiz " << (q+1) << " highest score: " << "Student " << (s+1) << ": " << highest << endl;}

return 0;
} 
  
