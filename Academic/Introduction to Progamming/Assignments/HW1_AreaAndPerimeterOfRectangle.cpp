/*This program calculates the area and perimeter of a rectangle after taking the length and width as input.*/
#include<iostream> // header
using namespace std; // for cout, cin, etc

int main() { // main function

int length, width, area, perimeter; // needed variables

cout << "This program calulates the area and perimeter of a rectangle." << endl; // introduce program
cout << "Enter length: " ; // Ask for length
cin >> length; // Store length
cout << "Enter width: " ; // Ask for width
cin >> width; // Store width
area = length * width; // Calculate and store area
perimeter = ((2*length)+(2*width)); // Calculate and store perimeter
cout << "Area: " << area << "  Perimeter: " << perimeter << endl; // Print area and perimeter
return 0;
}
