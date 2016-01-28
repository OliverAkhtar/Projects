/*The input begins with two positive integers n k (n, k<=107). The next n lines of input contain one positive integer ti, not greater than 109, each. 
Write a single integer to output, denoting how many integers ti are divisible by k. */

#include <iostream>
using namespace std;
 
int main()
{
  int inputs, divisor, dividend, count = 0;
  cin >> inputs >> divisor;
  for(int i = 1; i <= inputs; i++)
  {
      cin >> dividend;
      if(dividend % divisor == 0) count++;
  }
  cout << count;
  
  return 0;
}