package mypackage;

public class Queue {
    int q[];
    int front = 0;
    int rear = 0;

    public boolean isEmpty(){
        if(front==rear){
            return true;
        }
        return false;
    }
    public boolean isFull(){
        if(rear==q.length){
            return true;
        }
        return false;
    }

    public Queue(int capacity){
        q = new int[capacity];
    }

    public void enqueue(int x){
        if(!isFull()){
            q[rear++] = x;
        }
        else{
            System.out.println("Queue is Full");
        }
    }

    public void dequeue(){
        if(!isEmpty()){
            front++;
            System.out.println("\nDequeued: " + q[front]);
        }
        else{
            System.out.println("Queue is Empty");
        }
    }

    

    public void display(){
        if(!isEmpty()){
            System.out.print(q[front]+ ", ");
        }
        else{
            System.out.println("Queue is Empty");
        }
    }
}
