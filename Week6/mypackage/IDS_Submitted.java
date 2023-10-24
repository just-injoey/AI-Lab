import java.util.ArrayList;
import java.util.List;

public class IDS_Submitted {
    private static boolean isValid(int x, int y, int[][] maze) {
        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 0;
    }

    private static List<int[]> dfs(int[][] maze, int[] start, int[] end, int depth) {
        if (start[0] == end[0] && start[1] == end[1]) {
            List<int[]> path = new ArrayList<>();
            path.add(start);
            return path;
        }

        if (depth <= 0) {
            return null;
        }

        int x = start[0];
        int y = start[1];
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        for (int[] direction : directions) {
            int newX = x + direction[0];
            int newY = y + direction[1];
            if (isValid(newX, newY, maze)) {
                int[] newStart = {newX, newY};
                List<int[]> path = dfs(maze, newStart, end, depth - 1);
                if (path != null) {
                    path.add(0, start);
                    return path;
                }
            }
        }

        return null;
    }

    private static List<int[]> ids(int[][] maze, int[] start, int[] end) {
        int maxDepth = 11;

        for (int depth = 0; depth < maxDepth; depth++) {
            List<int[]> path = dfs(maze, start, end, depth);
            if (path != null) {
                return path;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        int[][] maze = {
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0}
        };

        int[] start = {3, 0};
        int[] end = {5, 4};

        List<int[]> path = ids(maze, start, end);
        if (path == null) {
            System.out.println("No path found.");
        } else {
            System.out.print("Path found: ");
            for (int[] point : path) {
                System.out.print("(" + point[0] + ", " + point[1] + ") ");
            }
        }
    }
}

