public class First {
    private boolean adj_matrix[][];
    private int vertices;
    
    public First(int n){
        this.vertices = n;
        adj_matrix = new boolean[n][n];
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
    public static void main(String[] args){
        int num_vertices = 4;
        First obj = new First(num_vertices);

        // hardcode 
        obj.addEdge(0, 1);
        obj.addEdge(0, 3);
        obj.addEdge(0, 2);
        obj.addEdge(1, 2);

        obj.print_matrix();

    }
}
