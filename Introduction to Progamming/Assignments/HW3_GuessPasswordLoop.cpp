/*This program asks the user to guess the password "qwerty" until entered or 1 to exit.*/
#include <iostream>
#include <string>
using namespace std;

int main()
{
string password;
string guess;

password = "qwerty";

cout << "Please give me your best guess, or enter 1 to exit.:";
cin >> guess;

if (guess == "1")
 return 0;
else if (guess == password)
 cout << "Congratulations!";

while (guess != password)
 {cout << "Incorrect, try again or enter 1 to exit.:";
  cin >> guess;
  if (guess == "1")
   return 0;
  else if (guess == password)
   cout << "Congratulations!";
 }
return 0;
}
