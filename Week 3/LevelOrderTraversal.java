import java.util.*;

class Graph<T>{
    public int numberOfVertices;
    public int numberOfEdges;

    private Map<T, List<T> > map = new HashMap<>();
    Graph(int v,int e){
        numberOfEdges = e;
        numberOfVertices = v;
    }
    public void addVertex(T v){
        map.put(v, new ArrayList<>());
    }
    public void addEdge(T a, T b){
        if (!map.containsKey(a))
            addVertex(a);
 
        if (!map.containsKey(b))
            addVertex(b);
 
        map.get(a).add(b);
        map.get(b).add(a);
    }
   
    public void RightToLeft(T source){
        Map<T,Boolean> visited = new HashMap<T,Boolean>();
        LinkedList<T> q = new LinkedList<T>();
        q.add(source);
        for(T v: map.keySet()){
            visited.put(v, false);
        }
        q.add(source);
        while(!q.isEmpty()){
            T s = q.poll();
            if(!visited.get(s)){
                    System.out.print(s+" ");
            }
            visited.replace(s, false, true);
            List<T> adjacent = map.get(s);
            for(int i=adjacent.size()-1;i>=0;i--){
                if(visited.get(adjacent.get(i))){
                    break;
                }
                q.add(adjacent.get(i));
            }
        }
        for(T v: visited.keySet()){
            if(!visited.get(v)){
                System.out.print(v + " ");
                visited.replace(v, false, true);
            }
        }
    }
    public void LeftToRight(T source){
        Map<T,Boolean> visited = new HashMap<T,Boolean>();
        LinkedList<T> q = new LinkedList<T>();
        q.add(source);
        for(T v: map.keySet()){
            visited.put(v, false);
        }
        q.add(source);
        while(!q.isEmpty()){
            T s = q.poll();
            if(!visited.get(s)){
                    System.out.print(s+" ");
            }
            visited.replace(s, false, true);
            List<T> adjacent = map.get(s);
            for(T w: adjacent){
                if(visited.get(w)){
                    break;
                }
                q.add(w);
            }
        }
        for(T v: visited.keySet()){
            if(!visited.get(v)){
                System.out.print(v + " ");
                visited.replace(v, false, true);
            }
        }
        
    }

}

public class LevelOrderTraversal {
    public static void main(String[] args){
        int vertices;
        int edges;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of vertices : ");
        vertices = sc.nextInt();
        System.out.println("Enter the number of edges : ");
        edges = sc.nextInt();
        Graph<Integer> gm = new Graph(vertices,edges);
        System.out.println("Graph has the following nodes : ");
        for(int i=0;i<vertices;i++){
            System.out.print(i+" ");
            gm.addVertex(i);
        }
        System.out.println();
        
        
        System.out.println("Insert edges (a b): ");
        for(int i=0;i<edges;i++){
            int a,b;
            a=sc.nextInt();
            b=sc.nextInt();
            gm.addEdge(a, b);
        }
        int s;
        System.out.print("Enter the source node : ");
        s = sc.nextInt();
        gm.LeftToRight(s);
        gm.RightToLeft(s);
    }
    
}