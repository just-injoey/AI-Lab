// Write a program using function Sum(row-id, no-of-elements) to make the question-1 more modular.

#include <iostream>
using namespace std;
int arr[3][3];
int Sum(int r, int n){
    int sum=0;
    for(int i=0; i<n; i++){
        sum+=arr[r][i];
    }
    return sum;
}
int Sum_Col(int c, int n){
    int sum=0;
    for(int i=0; i<n; i++){
        sum+=arr[i][c];
    }
    return sum;
}
int main(){
    // int arr[3][3];
    for(int i=0; i<3;i++){
        for(int j=0; j<3; j++){
            cin>>arr[i][j];
        }
    }

    //SUM OF ROW
    for(int i=0; i<3;i++){
        int sum = Sum(i,3);
        cout<<"Row: "<<i<<" Sum: "<<sum<<endl;
    }

    //SUM OF COLS
    for(int i=0; i<3;i++){
        int sum=Sum_Col(i,3);
        cout<<"Col: "<<i<<" Sum: "<<sum<<endl;
        
    }
    return 0;
}