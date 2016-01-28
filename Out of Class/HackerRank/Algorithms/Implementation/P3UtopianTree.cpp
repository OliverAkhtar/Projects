/*The Utopian Tree goes through 2 cycles of growth every year. Each spring, it doubles in height. Each summer, its height increases by 1 meter.
Laura plants a Utopian Tree sapling with a height of 1 meter at the onset of spring. How tall will her tree be after N growth cycles?*/
#include <iostream>
using namespace std;

int main(){
    int t;
    cin >> t;
    for(int a0 = 0; a0 < t; a0++){
        int n;
        cin >> n;
        int height = 1;
        bool isSpring = true;
        while(n > 0)
        {
            if(isSpring) height *= 2;
            else height += 1;
            isSpring = !isSpring;
            n--;
        }
        cout << height << endl;
    }
    return 0;
}
