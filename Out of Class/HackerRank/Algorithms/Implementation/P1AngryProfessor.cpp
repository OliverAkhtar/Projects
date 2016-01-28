//For an inputted amount of test cases, for each test case given students in a class, cancelation threshold, and arrival times of each student, determine
//if class is cancelled.
#include <vector>
#include <iostream>
using namespace std;

int main(){
    int t;
    cin >> t;
    for(int a0 = 0; a0 < t; a0++){
        int n;
        int k;
        int onTime = 0;
        cin >> n >> k;
        vector<int> a(n);
        for(int a_i = 0; a_i < n; a_i++){
           cin >> a[a_i];
           if(a[a_i] <= 0) onTime++;
        }
        if(onTime < k) cout << "YES";
        else cout << "NO";
        cout << endl;
    }
    return 0;
}
