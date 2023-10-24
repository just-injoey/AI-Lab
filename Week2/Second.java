import java.util.ArrayList;
import java.util.Collections;

public class Second {

    static void addEdge(ArrayList<ArrayList<Integer>> al, int s, int d){
        al.get(s).add(d);
        al.get(d).add(s);
    }

    static void print(ArrayList<ArrayList<Integer>> al){
        for(int i=0; i<al.size(); i++){
            System.out.print("\nVertex " + i + ":");
            for(int j=0; j<al.get(i).size(); j++){
                System.out.print(" -> " + al.get(i).get(j));
            }
            System.out.println();
        }
    }
    public static void main(String[] args){
        int n = 4; // num of nodes
        ArrayList<ArrayList<Integer>> al = new ArrayList<ArrayList<Integer>>(n);

        for(int i=0; i<n; i++){
            al.add(new ArrayList<Integer>());
        }
        // hardcode 
        addEdge(al, 0, 1);
        addEdge(al, 0, 3);
        addEdge(al, 0, 2);
        addEdge(al, 1, 2);

        print(al);
    }
}
