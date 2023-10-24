// Write a program to read a 2-D matrix and find the sum of each rows and columns.

#include <iostream>
using namespace std;

int main(){
    int arr[3][3];
    for(int i=0; i<3;i++){
        for(int j=0; j<3; j++){
            cin>>arr[i][j];
        }
    }

    //SUM OF ROW
    for(int i=0; i<3;i++){
        int sum_r=0;
        int sum_c=0;
        for(int j=0; j<3; j++){
            sum_r+=arr[i][j];
            sum_c+=arr[j][i];
        }
        cout<<"Row: "<<i<<"Sum: "<<sum_r<<endl;
        cout<<"Col: "<<i<<"Sum: "<<sum_c<<endl;
    }

    return 0;
}