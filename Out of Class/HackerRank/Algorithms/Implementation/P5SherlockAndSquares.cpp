/*Watson gives two integers (A and B) to Sherlock and asks if he can count the number of square integers between A and B (both inclusive).*/
#include <iostream>
#include <cmath>
using namespace std;

int main()
{
    int n;
    cin >> n;
    for(int i = 0; i < n; i++)
    {
        int a, b;
        cin >> a >> b;
        double sqrtA = sqrt(a), sqrtB = sqrt(b);
        int squareIntegers = floor(sqrtB) - ceil(sqrtA) + 1;
        cout << squareIntegers << endl;
    }
    
    return 0;
}
