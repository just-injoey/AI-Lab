import java.util.*;
import java.io.File;
import java.io.FileNotFoundException; 

// Iterative deepening search 

class Graph{
    public Map<Integer, List<Integer> > map = new HashMap<>();
    public void addVertex(int v){
        map.put(v, new LinkedList<>());
    }
    public void addEdge(int a, int b){
        if (!map.containsKey(a))
            addVertex(a);
 
        if (!map.containsKey(b))
            addVertex(b);
        if(map.get(a).contains(b) || map.get(b).contains(a)){
            return;
        }
        map.get(a).add(b);
        map.get(b).add(a);
    }
    public void readData(){
        try{
            File myObj = new File("graph.txt");
            Scanner myReader = new Scanner(myObj);
            int i=0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                for(int k=0;k<data.length();k++){
                    int a = Integer.parseInt(data.substring(k,k+1));
                    if(a==1){
                        addEdge(i, k);
                    }
                }
                i=i+1;
            }
        }
        catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    public String adjacencyLists()
    {
        StringBuilder builder = new StringBuilder();
 
        for (Integer v : map.keySet()) {
            builder.append(v.toString() + ": ");
            for (Integer w : map.get(v)) {
                builder.append(w.toString() + " ");
            }
            builder.append("\n");
        }
 
        return (builder.toString());
    }



}
public class IDS {
    static boolean iddfs(int src,int destination,int depth,Graph g){
        for(int i=0;i<=depth;i++){
            if (dls(src, destination, i,g) == true){
                    return true;
            }
        }
        return false;
    }
    static boolean dls(int src,int destination,int depth,Graph g){
        if(src==destination){
            return true;
        }
        if(depth<=0){
            return false;
        }
        for(int i:g.map.get(src)){
            if(dls(i, destination, depth-1, g)==true){
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args){
        Graph G = new Graph();
        G.readData();
        if(iddfs(0,6,3,G)){
            System.out.println("Target reachable");
        }
        else{
            System.out.println("Target not reachable");
        }
    }
}