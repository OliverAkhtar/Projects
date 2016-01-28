/*Program asks user for name and age then prints the info to the console.*/
#include <iostream> //header
using namespace std; //namespace for words

int main() { //main function
	string name; // string variable to hold name
	int age; // integer variable to hold age

cout<<"What is your name?:"; // ask user for name
cin>>name; // store name
cout<<"What is your age?:"; // ask user for age
cin>>age; // store age
cout<<"Hi "<<name<<" you are "<<age<<" years old."; // print info collected

return 0;
}
