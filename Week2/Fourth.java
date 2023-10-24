public class Fourth {
    static int top = -1;    
    public static void push(int stack[], int e){
        if(top <stack.length -1){
            stack[++top]=e;
        }
        else{
            System.out.println("Overflow");
        }   
    }

    
    public static void pop(int stack[]){
        if(top==-1){
            System.out.println("Underflow");
            return;
        }
        top--;
    }
    static void printStack(int[] stack){
        for(int i=0; i<=top; i++){
            System.out.print(stack[i]);
        }
        System.out.println();

    }
    static void peek(int[] stack){
        System.out.println(stack[top]);
    }
    public static void main(String[] args){
        //for stack
        int stack_capacity = 3;
        int[] stack = new int[stack_capacity];
        // int top = -1;
        push(stack,3);
        push(stack,4);
        push(stack,1);
        // push(stack,7); // overflow
        printStack(stack);

        //for queue

        int queue_capacity = 3;
        int front =0, rear = 0;
        int[] queue = new int[queue_capacity];


    }
}
