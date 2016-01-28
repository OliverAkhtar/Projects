//Sum of even Fibonacci numbers that don't exceed 4 million.
#include <iostream>
#include <string>
using namespace std;

int main()
{
  int f = 0, s = 1, n = f + s, sum = 0;
  cout << f << " " << s << " " << n << " ";
  
  while(true)
  {
      f = s;
      s = n;
      n = f + s;
      if(n > 4000000) break;
      else cout << n << " ";
      if(n % 2 == 0) sum += n;
  }
  cout << endl << sum;
  
  return 0;
}