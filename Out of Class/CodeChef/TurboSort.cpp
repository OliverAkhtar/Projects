/*Input  t – the number of numbers in list, then t lines follow [t <= 10^6].
         Each line contains one integer: N [0 <= N <= 10^6]
  Output Output given numbers in non decreasing order.*/
#include <iostream>
#include <algorithm>
using namespace std;
 
int main()
{
  int inputs;
  cin >> inputs;
  int numbers[inputs];
  for(int i = 1; i <= inputs; i++)
  {
      cin >> numbers[i];
  }
  sort(numbers, numbers + inputs + 1);
  for(int i = 1; i <= inputs; i++)
  {
      cout << numbers[i] << endl;
  } 
  
  return 0;
}