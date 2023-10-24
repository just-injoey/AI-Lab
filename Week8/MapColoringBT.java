import java.util.*;

public class MapColoringBT {

    private int numRegions;
    private int[] colors;
    private int[][] adjacencyMatrix;
    private int numColors;

    public MapColoringBT(int numRegions, int[][] adjacencyMatrix, int numColors) {
        this.numRegions = numRegions;
        this.colors = new int[numRegions];
        this.adjacencyMatrix = adjacencyMatrix;
        this.numColors = numColors;
    }

    public boolean isSafe(int region, int color) {
        for (int i = 0; i < numRegions; i++) {
            if (adjacencyMatrix[region][i] == 1 && colors[i] == color) {
                return false;
            }
        }
        return true;
    }

    public boolean solve() {
        return solve(0);
    }

    public boolean solve(int region) {
        if (region == numRegions) {
            return true; // All regions are colored
        }

        for (int color = 1; color <= numColors; color++) {
            if (isSafe(region, color)) {
                colors[region] = color;

                if (solve(region + 1)) {
                    return true;
                }

                colors[region] = 0; // Backtrack
            }
        }

        return false; // No valid color found for this region
    }

    public void printSolution() {
        for (int i = 0; i < numRegions; i++) {
            System.out.println("Region " + (i + 1) + " is colored with color " + colors[i]);
        }
    }

    public static void main(String[] args) {
        int numRegions = 6;
        int numColors = 3;
        

        int[][] adjacencyMatrix = {
            {0, 1, 0, 1, 1, 0},
            {1, 0, 1, 1, 0, 1},
            {0, 1, 0, 1, 1, 1},
            {1, 1, 1, 0, 1, 0},
            {1, 0, 1, 1, 0, 0},
            {0, 1, 1, 1, 0, 0}
        };
        
        MapColoringBT mapColoring = new MapColoringBT(numRegions, adjacencyMatrix, numColors);
        if (mapColoring.solve()) {
            System.out.println("Solution found:");
            mapColoring.printSolution();
        } else {
            System.out.println("No solution exists.");
        }
    }
}
