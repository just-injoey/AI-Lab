package mypackage;

public class Stack {
    int top=-1;
    int[] stack;

    public Stack(int capacity){
        stack = new int[capacity];
    }
    public boolean isEmpty(){
        if(top==-1){
            return true;
        }
        return false;
    }
    public boolean isFull(){
        if(top==stack.length-1){
            return true;
        }
        return false;
    }
    public void push(int e){
        if(!isFull()){
            stack[top++] = e;
        }
        else{
            System.out.println("Stack overflow");
        }
    }  

    public void pop(){
        if(!isEmpty()){
            System.out.println("Underflow");      
            return;
        }
        top--;
    }
    public void peek(){
        if(isEmpty()){
            System.out.println("Underflow! Stack is Empty");
            return;
        }
        System.out.println(stack[top]);
    }
    
}