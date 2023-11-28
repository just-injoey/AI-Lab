import java.util.*;

class Node implements Comparable<Node> {
    int x, y; // Coordinates of the node
    int cost; // Cost to reach this node
    int heuristic; // Heuristic value (estimated cost to goal)
    Node parent; // Parent node

    public Node(int x, int y, int cost, int heuristic, Node parent) {
        this.x = x;
        this.y = y;
        this.cost = cost;
        this.heuristic = heuristic;
        this.parent = parent;
    }

    // Compare nodes based on the sum of cost and heuristic
    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.cost + this.heuristic, other.cost + other.heuristic);
    }
}

public class AStarSearch {

    // A* search algorithm implementation
    public static List<Node> aStarSearch(int[][] grid, int startX, int startY, int goalX, int goalY) {
        int rows = grid.length;
        int cols = grid[0].length;

        PriorityQueue<Node> openSet = new PriorityQueue<>();
        Set<Node> closedSet = new HashSet<>();

        Node startNode = new Node(startX, startY, 0, calculateHeuristic(startX, startY, goalX, goalY), null);
        openSet.add(startNode);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (current.x == goalX && current.y == goalY) {
                // Goal reached, reconstruct the path
                List<Node> path = new ArrayList<>();
                while (current != null) {
                    path.add(current);
                    current = current.parent;
                }
                Collections.reverse(path);
                return path;
            }

            closedSet.add(current);

            // Explore neighbors
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i == 0 && j == 0) {
                        continue; // Skip the current node
                    }

                    int neighborX = current.x + i;
                    int neighborY = current.y + j;

                    // Check if the neighbor is within the grid
                    if (neighborX >= 0 && neighborX < rows && neighborY >= 0 && neighborY < cols) {
                        // Check if the neighbor is not an obstacle and not in the closed set
                        if (grid[neighborX][neighborY] != 1 && !closedSet.contains(new Node(neighborX, neighborY, 0, 0, null))) {
                            int newCost = current.cost + 1; // Assuming each step has a cost of 1
                            int heuristic = calculateHeuristic(neighborX, neighborY, goalX, goalY);
                            Node neighbor = new Node(neighborX, neighborY, newCost, heuristic, current);

                            // Check if the neighbor is not in the open set or has a lower cost
                            if (!openSet.contains(neighbor) || newCost < neighbor.cost) {
                                openSet.add(neighbor);
                            }
                        }
                    }
                }
            }
        }

        // No path found
        return null;
    }

    // Manhattan distance heuristic
    private static int calculateHeuristic(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    public static void main(String[] args) {
        int[][] grid = {
            {0, 0, 0, 0, 1},
            {1, 1, 0, 0, 1},
            {0, 0, 0, 0, 0},
            {1, 0, 1, 1, 1},
            {0, 0, 0, 0, 0}
        };

        int startX = 0;
        int startY = 0;
        int goalX = 4;
        int goalY = 4;

        List<Node> path = aStarSearch(grid, startX, startY, goalX, goalY);

        if (path != null) {
            System.out.println("Path found:");
            for (Node node : path) {
                System.out.println("(" + node.x + ", " + node.y + ")");
            }
        } else {
            System.out.println("No path found.");
        }
    }
}
