//Find sum of all multiples of 3 or 5 and less than 1000.
#include <iostream>
#include <string>
using namespace std;

int main()
{
  int i = 1;
  int sum = 0;
  
  while(i < 1000)
  {
      if((i % 3 == 0)||(i % 5 == 0)) sum += i;
      i++;
  }
  cout << sum;
  
  return 0;
}