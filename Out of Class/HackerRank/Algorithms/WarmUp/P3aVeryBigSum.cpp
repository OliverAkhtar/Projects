//Output the sum of numbers in an array passed as keyboard input of very large numbers.
#include <iostream>
using namespace std;

int main(){
    int n;
    cin >> n;
    vector<int> arr(n);
    long long int sum = 0;
    for(int arr_i = 0;arr_i < n;arr_i++){
       cin >> arr[arr_i];
       sum += arr[arr_i];
    }
    cout << sum;
    
    return 0;
}