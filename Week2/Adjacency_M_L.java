import java.util.ArrayList;
class AdjacenyMatrix{
    boolean adj_matrix[][];
    int vertices;

    public AdjacenyMatrix(int v){
        this.vertices = v;
        adj_matrix = new boolean[v][v];
    }
    public void addEdge(int i, int j){
        adj_matrix[i][j] = true;
        adj_matrix[j][i] = true;
    }      
    public void print_matrix(){
        System.out.println("Edges are: ");
        for(int i=0; i<vertices; i++){
            for(int j=0; j<vertices; j++){
                if(adj_matrix[i][j]){  //adj_matrix[i][j]== true
                    System.out.println(i+"->"+j);
                }
            }
        }
    } 
    
}

class AdjacencyList{
    ArrayList<ArrayList<Integer>> al;
    int vertices;
    public AdjacencyList(int v){
        vertices = v;
        al = new ArrayList<ArrayList<Integer>>(v);
        for(int i=0; i<v; i++){
            al.add(new ArrayList<Integer>());
        }
    }
    public void addEdge(int s, int d){
        al.get(s).add(d);
        al.get(d).add(s);
    }
    public void print(){
        for(int i=0; i<al.size(); i++){
            System.out.print("\nVertex " + i + ":");
            for(int j=0; j<al.get(i).size(); j++){
                System.out.print(" -> " + al.get(i).get(j));
            }
            System.out.println();
        }
    }
}
public class Adjacency_M_L {
    public static void main(String[] args){
        //Adjacency Matrix
        AdjacenyMatrix am = new AdjacenyMatrix(4);
        am.addEdge(0, 1);
        am.addEdge(0, 3);
        am.addEdge(0, 2);
        am.addEdge(1, 2);
        am.print_matrix();

        //Adjacency List
        AdjacencyList al = new AdjacencyList(4);

        al.addEdge(0, 1);
        al.addEdge(0, 3);
        al.addEdge(0, 2);
        al.addEdge(1, 2);
        al.print();
        
    }
}
