#include <iostream>
using namespace std;

int main()
{
  int smallest = 20;
  bool found = false;
  while(!found)
  {
      smallest += 20;
      for(int i = 1; i <= 20; i++)
      {
          if(smallest % i != 0) break;
          if(i == 20) found = true;
      }
  }
  cout << smallest;
  
  return 0;
}
