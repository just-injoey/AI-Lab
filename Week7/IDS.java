import java.util.LinkedList;

public class IDS {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Up, Down, Left, Right
    private static final int MAX_DEPTH = 100; // Maximum depth for IDS

    public static LinkedList<int[]> iterativeDeepeningSearch(int[][] map, int[] start, int[] goal) {
        for (int depth = 0; depth <= MAX_DEPTH; depth++) {
            boolean[][] visited = new boolean[map.length][map[0].length];
            LinkedList<int[]> path = new LinkedList<>();
            path.add(start);
            if (depthLimitedSearch(map, start, goal, visited, depth, path)) {
                return path; // Goal found within the depth limit
            }
        }
        return null; // Goal not found
    }

    public static boolean depthLimitedSearch(int[][] map, int[] current, int[] goal, boolean[][] visited, int depth, LinkedList<int[]> path) {
        if (depth < 0) {
            return false; // Reached the depth limit without finding the goal
        }

        int x = current[0];
        int y = current[1];

        if (x == goal[0] && y == goal[1]) {
            return true; // Goal found
        }

        visited[x][y] = true;

        for (int[] dir : DIRECTIONS) {
            int newX = x + dir[0];
            int newY = y + dir[1];

            if (isValidLocation(map, newX, newY) && !visited[newX][newY]) {
                path.add(new int[]{newX, newY});
                if (depthLimitedSearch(map, new int[]{newX, newY}, goal, visited, depth - 1, path)) {
                    return true; // Goal found in the next depth level
                }
                path.removeLast(); // Remove the last added point if the path is not successful
            }
        }

        return false; // Goal not found at this depth
    }

    public static boolean isValidLocation(int[][] map, int x, int y) {
        return x >= 0 && x < map.length && y >= 0 && y < map[0].length && map[x][y] == 0;
    }

    public static void main(String[] args) {
        int[][] map = {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0}
        };

        int[] start = {0, 3};
        int[] goal = {4, 5};

        LinkedList<int[]> path = iterativeDeepeningSearch(map, start, goal);

        if (path != null) {
            System.out.println("Path found:");
            for (int[] point : path) {
                System.out.println("(" + point[0] + ", " + point[1] + ")");
            }
        } else {
            System.out.println("Path not found.");
        }
    }
}
