import java.util.*;

class Cell {
    int parent_i, parent_j;
    double f, g, h;

    Cell() {
        parent_i = -1;
        parent_j = -1;
        f = Double.MAX_VALUE;
        g = Double.MAX_VALUE;
        h = Double.MAX_VALUE;
    }
}

class Pair {
    int first, second;

    Pair(int x, int y) {
        first = x;
        second = y;
    }
}

public class A_Star {
    static final int ROW = 7;
    static final int COL = 7;

    static boolean isValid(int row, int col) {
        return (row >= 0) && (row < ROW) && (col >= 0) && (col < COL);
    }

    static boolean isUnBlocked(int[][] grid, int row, int col) {
        return (grid[row][col] == 0);
    }

    static boolean isDestination(int row, int col, Pair dest) {
        return (row == dest.first && col == dest.second);
    }

    static double calculateHValue(int row, int col, Pair dest) {
        return Math.sqrt((row - dest.first) * (row - dest.first) + (col - dest.second) * (col - dest.second));
    }

    static void tracePath(Cell[][] cellDetails, Pair dest) {
        System.out.print("The Path is ");
        int row = dest.first;
        int col = dest.second;

        Stack<Pair> Path = new Stack<>();

        while (!(cellDetails[row][col].parent_i == row && cellDetails[row][col].parent_j == col)) {
            Path.push(new Pair(row, col));
            int temp_row = cellDetails[row][col].parent_i;
            int temp_col = cellDetails[row][col].parent_j;
            row = temp_row;
            col = temp_col;
        }

        Path.push(new Pair(row, col));
        while (!Path.isEmpty()) {
            Pair p = Path.pop();
            System.out.print("-> (" + p.first + "," + p.second + ") ");
        }
    }

    static void aStarSearch(int[][] grid, Pair src, Pair dest) {
        if (!isValid(src.first, src.second) || !isValid(dest.first, dest.second)) {
            System.out.println("Source or destination is invalid");
            return;
        }

        if (!isUnBlocked(grid, src.first, src.second) || !isUnBlocked(grid, dest.first, dest.second)) {
            System.out.println("Source or destination is blocked");
            return;
        }

        if (isDestination(src.first, src.second, dest)) {
            System.out.println("We are already at the destination");
            return;
        }

        boolean[][] closedList = new boolean[ROW][COL];
        Cell[][] cellDetails = new Cell[ROW][COL];

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                cellDetails[i][j] = new Cell();
            }
        }

        int i = src.first, j = src.second;
        cellDetails[i][j].f = 0.0;
        cellDetails[i][j].g = 0.0;
        cellDetails[i][j].h = 0.0;
        cellDetails[i][j].parent_i = i;
        cellDetails[i][j].parent_j = j;

        PriorityQueue<Pair> openList = new PriorityQueue<>((a, b) -> Double.compare(cellDetails[a.first][a.second].f, cellDetails[b.first][b.second].f));
        openList.add(src);

        boolean foundDest = false;

        int[] rowNbr = {-1, 1, 0, 0, -1, -1, 1, 1};
        int[] colNbr = {0, 0, 1, -1, 1, -1, 1, -1};

        while (!openList.isEmpty()) {
            Pair p = openList.poll();
            i = p.first;
            j = p.second;
            closedList[i][j] = true;

            double gNew, hNew, fNew;

            for (int k = 0; k < 8; k++) {
                int newRow = i + rowNbr[k];
                int newCol = j + colNbr[k];

                if (isValid(newRow, newCol) && isUnBlocked(grid, newRow, newCol)) {
                    if (isDestination(newRow, newCol, dest)) {
                        cellDetails[newRow][newCol].parent_i = i;
                        cellDetails[newRow][newCol].parent_j = j;
                        System.out.println("The destination cell is found");
                        tracePath(cellDetails, dest);
                        foundDest = true;
                        return;
                    } else if (!closedList[newRow][newCol]) {
                        gNew = cellDetails[i][j].g + 1.0;
                        hNew = calculateHValue(newRow, newCol, dest);
                        fNew = gNew + hNew;

                        if (cellDetails[newRow][newCol].f == Double.MAX_VALUE || cellDetails[newRow][newCol].f > fNew) {
                            openList.add(new Pair(newRow, newCol));
                            cellDetails[newRow][newCol].f = fNew;
                            cellDetails[newRow][newCol].g = gNew;
                            cellDetails[newRow][newCol].h = hNew;
                            cellDetails[newRow][newCol].parent_i = i;
                            cellDetails[newRow][newCol].parent_j = j;
                        }
                    }
                }
            }
        }

        if (!foundDest) {
            System.out.println("Failed to find the Destination Cell");
        }
    }

    public static void main(String[] args) {
        int[][] grid = {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0}
        };

        Pair src = new Pair(0, 3);
        Pair dest = new Pair(4, 5);

        aStarSearch(grid, src, dest);
    }
}
