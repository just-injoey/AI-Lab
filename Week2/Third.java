import java.util.*;


public class Third {

    static void addEdge(ArrayList<ArrayList<Integer>> al, int s, int d){
        al.get(s).add(d);
        al.get(d).add(s);
    }

    static void print(ArrayList<ArrayList<Integer>> al){
        for(int i=0; i<al.size(); i++){
            System.out.print("\nVertex "+i+":");
            for(int j=0; j<al.get(i).size(); j++){
                System.out.print("->"+al.get(i).get(j));
            }
            System.out.println();
        }
    }
    static Set<Integer> TwoHopDistance(ArrayList<ArrayList<Integer>> al, int s){ // s is source
        Set<Integer> visited = new HashSet<>();
        Set<Integer> result = new HashSet<>();

        ArrayList<Integer> firstHop = al.get(s);
        
        for(int node : firstHop){
            ArrayList<Integer> neighbours = al.get(node);
            for(int neighbour : neighbours){
                if(neighbour != s && !visited.contains(neighbour)){
                    result.add(neighbour);
                }
            }
        }
        return result;
    }

    public static void main(String[] args){
        int n = 6; // vertices
        ArrayList<ArrayList<Integer>> al = new ArrayList<ArrayList<Integer>>(n);
        for(int i=0; i<n; i++){
            al.add(new ArrayList<Integer>());
        }
        // hardcode 
        addEdge(al, 0, 1);
        addEdge(al, 0, 2);
        addEdge(al, 1, 3);
        addEdge(al, 1, 4);
        addEdge(al, 2, 5);
        // print(al);
        int s = 0; // source node
        Set<Integer> result = TwoHopDistance(al,s);
        System.out.println("Nodes connected to source node " + s + " in exactly 2-hop distance: " + result);
    }
}
