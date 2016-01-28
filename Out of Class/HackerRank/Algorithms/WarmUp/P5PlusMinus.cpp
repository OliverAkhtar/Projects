//Given an array of integers, calculate which fraction of the elements are positive, negative, and zeroes, respectively. Print the decimal value of each fraction.
#include <vector>
#include <iostream>
using namespace std;

int main(){
    double positives = 0, negatives = 0, zeros = 0;
    int n;
    cin >> n;
    vector<int> arr(n);
    for(int arr_i = 0; arr_i < n; arr_i++){
       cin >> arr[arr_i];
       if(arr[arr_i] > 0) positives++;
       else if(arr[arr_i] < 0) negatives++;
       else zeros++;
    }
    
    cout << positives/n << endl << negatives/n << endl << zeros/n;
    
    
    return 0;
}