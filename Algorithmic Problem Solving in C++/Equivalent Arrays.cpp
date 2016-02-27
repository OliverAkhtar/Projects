/*This program determins if array a's entries can be shifted to the right to create the same arrangement as array b's entries, or vice versa.*/
#include <iostream>
using namespace std;

bool equivalent(int a[], int b[], int n){
 bool ans;
 for(int i = 1; i <= n; i++){
  for(int j = 0; j < n; j++){
   int counter = 0;
   if(a[j]==b[j])counter++;
   if(counter == 5)ans == true;
   if(ans==true)return ans;}
   int hold = b[n-1];
    for(int k = (n-1); k>0; k--){
     b[k]==b[k-1];}
   b[0] == hold;};
 ans == false;
 return ans;
}

int main(){

int a[] = {1,2,3,4,5};
int b[] = {3,4,5,1,2};

if(equivalent(a, b, 5)) cout << "Shift Equivalent";
else cout << "Not Shift Equivalent";

return 0;
} 
   
