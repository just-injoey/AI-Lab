import java.util.ArrayList;
import java.util.LinkedList;

public class GraphColoring{
    public static void main(String[] args){
        int[][] graph = {
            {0, 1, 0, 1, 1, 0},
            {1, 0, 1, 1, 0, 1},
            {0, 1, 0, 1, 1, 1},
            {1, 1, 1, 0, 1, 0},
            {1, 0, 1, 1, 0, 0},
            {0, 1, 1, 1, 0, 0}
        };

        ArrayList<LinkedList<Integer>> adj_lst = new ArrayList<LinkedList<Integer>>();
        adj_lst.get(0).add(1);
        adj_lst.get(0).add(3);
        adj_lst.get(0).add(4);
        System.out.println(adj_lst);
        
        
        // 0 = red
        // 1 = green
        // 2 = yellow
        int[] colors = {0,1,2};
        int[] heuristics = degreeCalc(graph);
        for(int i=0; i<6; i++){
            System.out.print(heuristics[i]+" ");
            
        }
        // csp(graph, heuristics, colors);
    }


    //degree will be the heuristic
    public static int[] degreeCalc(int[][] graph){
        int[] heuristics = new int[6];
        for(int i = 0; i<6;i++){
            int count = 0;
            for(int j=0; j<6; j++){
                if(graph[i][j] == 1){
                    count+=1;
                }
            }
            heuristics[i] = count;
        }
        return heuristics;
    }

    public static int maxIdx(int[] h){
        // find max value in heuristic arraymax
        int max_deg = Integer.MIN_VALUE;
        int max_idx = 0;
        for(int i=0; i<h.length; i++){
            if(h[i]>max_deg){
                max_deg = h[i];
                max_idx = i;
            }
        }
        return max_idx;
    }

    // public static int[][] csp(int[][] graph, int[] h, int[] colors){
    //     int max_idx = maxIdx(h);

    //     return graph;

    //     // fill color at max_idx then neighbour of max_idx which have highrst degree colour that,
    // }

}