// Write a program to find the number of ZEROs in a particular column of a 2-D matrix.

#include <iostream>
using namespace std;

int arr[3][3];

int num_zeroes(int c, int n){
    int count=0;
    for(int j=0; j<n; j++){
        if(arr[j][c]==0){
            count++;
        }
    }
    return count;
}
int main(){
    for(int i=0; i<3;i++){
        for(int j=0; j<3; j++){
            cin>>arr[i][j];
        }
    }
    //num of zeroes
    for(int i=0; i<3;i++){
        int count = num_zeroes(i, 3);
        cout<<"Col: "<<i<<" Count: "<<count<<endl;
    }
    return 0;
}