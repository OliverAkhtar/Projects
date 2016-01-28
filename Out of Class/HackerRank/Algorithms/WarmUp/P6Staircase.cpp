//Given a height as input, output a staircase pattern of that height.
#include <iostream>
using namespace std;

int main(){
    int n;
    cin >> n;
    
    for(int i = n; i >= 1; i--)
        {
            for(int j = 1; j <= n; j++)
            {
                if(j >= i) cout << "#";
                else cout << " ";
            }
            cout << endl;
        }
    
    return 0;
}
