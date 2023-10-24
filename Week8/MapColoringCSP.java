import java.util.*;

public class MapColoringCSP {

    private int numRegions;
    private int[] colors;
    private int[][] adjacencyMatrix;
    private int numColors;

    public MapColoringCSP(int numRegions, int[][] adjacencyMatrix, int numColors) {
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

        // Select the region with the highest degree as the next variable
        int selectedRegion = selectUnassignedVariable();

        for (int color = 1; color <= numColors; color++) {
            if (isSafe(selectedRegion, color)) {
                colors[selectedRegion] = color;

                if (solve(region + 1)) {
                    return true;
                }

                colors[selectedRegion] = 0; // Backtrack
            }
        }

        return false; // No valid color found for this region
    }

    // Select the region with the highest degree
    private int selectUnassignedVariable() {
        int maxDegree = -1;
        int selectedRegion = -1;
        for (int i = 0; i < numRegions; i++) {
            if (colors[i] == 0) {
                int degree = computeDegree(i);
                if (degree > maxDegree) {
                    maxDegree = degree;
                    selectedRegion = i;
                }
            }
        }
        return selectedRegion;
    }

    // Compute the degree of a region (number of adjacent regions)
    private int computeDegree(int region) {
        int degree = 0;
        for (int i = 0; i < numRegions; i++) {
            if (adjacencyMatrix[region][i] == 1) {
                degree++;
            }
        }
        return degree;
    }

    public void printSolution() {
        for (int i = 0; i < numRegions; i++) {
            System.out.println("Region " + (i + 1) + " is colored with color " + colors[i]);
        }
    }

    public static void main(String[] args) {
        int numRegions = 4;
        int numColors = 3;
        // int[][] adjacencyMatrix = {
        //     {0, 1, 0, 1, 1, 0},
        //     {1, 0, 1, 1, 0, 1},
        //     {0, 1, 0, 1, 1, 1},
        //     {1, 1, 1, 0, 1, 0},
        //     {1, 0, 1, 1, 0, 0},
        //     {0, 1, 1, 1, 0, 0}
        // };
        
        int[][] adjacencyMatrix = {
            {0, 1, 1, 1},
            {1, 0, 1, 0},
            {1, 1, 0, 1},
            {1, 0, 1, 0}
        };
        MapColoringCSP mapColoring = new MapColoringCSP(numRegions, adjacencyMatrix, numColors);
        if (mapColoring.solve()) {
            System.out.println("Solution found:");
            mapColoring.printSolution();
        } else {
            System.out.println("No solution exists.");
        }
    }
}
