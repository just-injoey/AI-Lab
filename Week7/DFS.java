import java.util.Stack;
import mypackage.*;
public class DFS {
    int[][] graph = {
        {0, 1, 1, 0, 0, 0},
        {1, 0, 1, 0, 1, 1},
        {1, 1, 0, 1, 0, 0},
        {0, 0, 1, 0, 1, 0},
        {0, 1, 0, 1, 0, 1},
        {0, 1, 0, 0, 1, 0}  
    };

    boolean[] visited;

    public DFS(int numVertices) {
        visited = new boolean[numVertices];
    }

    public void depthFirstSearch(int startVertex) {
        Stack<Integer> stack = new Stack<>();
        stack.push(startVertex);
        visited[startVertex] = true;

        while (!stack.isEmpty()) {
            int vertex = stack.pop();
            System.out.print(vertex + " ");

            for (int i = 0; i < graph.length; i++) {
                if (graph[vertex][i] == 1 && !visited[i]) {
                    stack.push(i);
                    visited[i] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        int numVertices = 6;
        DFS dfs = new DFS(numVertices);
        int startVertex = 0; // You can choose any starting vertex
        System.out.println("DFS starting from vertex " + startVertex + ":");
        dfs.depthFirstSearch(startVertex);
    }
}
