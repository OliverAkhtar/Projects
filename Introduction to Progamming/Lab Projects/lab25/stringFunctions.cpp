/*This program contains the functions, isLong which returns true if the parameter string is longer than 5, removeEnds returns the paramter string with the
first and last characters removed, lastChar returns the last character in a string, doubleIt returns a string concatenated with itself, hasIt returns true
if one string y is a substring of string x, isUp returns true if the string's first character is uppercase, add on concatenates string y to string x.*/
#include <iostream>
#include <string>
#include <cstring>

using namespace std;

bool isLong(string x){
 if((x.length()) > 5) return true;
 else return false;}

string removeEnds(string x){
 int n = x.length();
 string h = x.substr(1,(n-2));
 return h;}

char lastChar(char x[]){
 int n = strlen(x);
 return  x[n-1];}

string doubleIt(string x){
 return (x+x);}

bool hasIt(string x, string y){
 bool a = x.find(y);
 return a;}

bool isUp(string x){
 if(x[0] < 'a') return true;}

void addOn(string& x, string y){
 x = x + y;}

int main(){
 string s = "HELLO";
 char t[] = "HELLO";

if(isLong(s)) cout << "true" << endl;

cout << removeEnds(s) << endl;

cout << lastChar(t) << endl;

cout << doubleIt(s) << endl;

if(hasIt(s, "HELL")) cout << "true" << endl;

if(isUp(s)) cout << "true" << endl;

addOn(s,"WORLD");
cout << s << endl;

return 0;
}
