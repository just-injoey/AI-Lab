package mypackage;

public class Stack {
    int top = -1;
    int[] stack;

     public Stack(int capacity){
        top = -1;
        stack = new int[capacity];
    }

    public void push(int e){
        if(top<stack.length - 1){
            stack[top++] = e;
        }
        else{
            System.out.println("Stack overflow");
        }
    }  

    public void pop(){
        if(top == -1){
            System.out.println("Underflow");      
            return;
        }
        top--;
    }
    public void peek(){
        if(top == -1){
            System.out.println("Underflow! Stack is Empty");
            return;
        }
        System.out.println(stack[top]);
    }
}
