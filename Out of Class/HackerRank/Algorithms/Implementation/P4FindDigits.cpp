/*Given an integer, N, traverse its digits (d1,d2,...,dn) and determine how many digits evenly divide N (i.e.: count the number of times N divided by each digit di has a remainder of 0). Print the number of evenly divisible digits. */
#include <iostream>
using namespace std;

int main(){
    int t;
    cin >> t;
    for(int a0 = 0; a0 < t; a0++){
        int n;
        cin >> n;
        int divisibleDigits = 0;
        int number = n;
        while(number != 0)
        {
            int lastDigit = number % 10;
            if(lastDigit == 0)
            {
                number /= 10;
                continue;
            }
            if(n % lastDigit == 0) divisibleDigits++;
            number /= 10;
        }
        cout << divisibleDigits << endl;
    }
    
    return 0;
}
