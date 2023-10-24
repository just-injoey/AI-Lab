package mypackage;
class Stack{
    int top;
    int[] stack;

    public Stack(int capacity){
        top = -1;
        stack = new int[capacity];
    }

    public void push(int e){
        if(top <stack.length -1){
            stack[++top]=e;
        }
        else{
            System.out.println("Overflow");
        }   
    }
    public void pop(){
        if(top==-1){
            System.out.println("Underflow");
            return;
        }
        top--;
    }
    public void printStack(){
        for(int i=0; i<=top; i++){
            System.out.print(stack[i]+" ");
        }
        System.out.println();

    }
    public void peek(){
        if (top == -1) {
            System.out.println("Stack is empty");
            return;
        }
        System.out.println(stack[top]);
    }
    
}

class Queue{
    int front = 0, rear = 0;
    int capacity;
    int[] queue;

    public Queue(int c){
        capacity = c;
        queue = new int[c];
    }
    public void enqueue(int e){
        if(rear == capacity){
            System.out.println("Queue is Full");
            return;
        }
        queue[rear++] = e;
    }
    public void dequeue(){
        if(front == rear){
            System.out.println("Queue is Empty");
            return;
        }
         System.out.println("\nDequeued: " + queue[front]);
        // Move the front pointer to the next element
        front++;

    }
    public void printQueue(){
        if(front == rear){
            System.out.println("Queue is Empty");
            return;
        }
        for(int i=front; i<rear; i++){
            System.out.print(queue[i]+" ");
        }
    }
}

public class Stack_Queue {
    public static void main(String[] args){
        Stack s = new Stack(3);
        s.push(3);
        s.push(4);
        s.push(1);
        // s.push(7); // overflow
        s.printStack();

        Queue q = new Queue(3);
        q.enqueue(3);
        q.enqueue(5);
        q.enqueue(7);
        q.enqueue(6);
        q.printQueue();
        q.dequeue();
        q.dequeue();
        q.printQueue();
        q.dequeue();
        q.printQueue();
    }    

}

