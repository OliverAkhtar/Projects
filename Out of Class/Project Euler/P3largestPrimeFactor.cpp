//Find largest prime factor of 600851475143
#include <iostream>
#include <string>
#include <cmath>
using namespace std;

bool isPrime(int x)
{
    int sqr = sqrt(x);
    for(int i = 2; i <= sqr; i++)
    {
        if(x % i == 0) return false;
    }
    return true;
}

int main()
{
    double n = 600851475143;
    int l;
    for(int i = 2; i <= sqrt(n); i++)
    {
        if(isPrime(i) && (fmod(n,i) == 0)) l = i;
    }
    
    cout << l;
    
    return 0;
}