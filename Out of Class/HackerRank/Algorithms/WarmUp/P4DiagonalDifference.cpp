//Given a square matrix of size N×N, calculate the absolute difference between the sums of its diagonals
#include <iostream>
#include <vector>
using namespace std;

int main(){
    int n;
    cin >> n;
    vector< vector<int> > a(n,vector<int>(n));
    for(int a_i = 0; a_i < n; a_i++){
       for(int a_j = 0 ;a_j < n; a_j++){
          cin >> a[a_i][a_j];
       }
    }
    
    int diag1sum, diag2sum;
    for(int i = 0; i < n; i++)
    {
        diag1sum += a[i][i];
    }
    for(int i = 0; i < n; i++)
    {
        diag2sum += a[i][n - 1 - i];
    }
    int diagSumDiff = diag1sum - diag2sum;
    if(diagSumDiff < 0) diagSumDiff *= -1;
    cout << diagSumDiff;
    
    return 0;
}
