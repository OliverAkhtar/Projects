/*This program adds 10 to user inputted quiz score by passing the user input by reference to a function.*/
#include <iostream>
using namespace std;

void add10(int& n) {
 n = n + 10;
 cout << n << endl;
}

int main() {
 int quiz;
 cout << "Enter quiz score: ";
 cin >> quiz;
 add10(quiz);
 cout << "New Score: " << quiz << endl;
 return 0;
}

