import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class MSTPrim {
    private int dimension;
    private double[][] matrix;
    private int[] parent;
    private boolean[] label;
    private GraphMatrix graphMatrix;


    public MSTPrim(int size, double[][] position) {
        this.dimension = size;
        this.graphMatrix = new GraphMatrix(size, position);
        this.matrix = this.graphMatrix.getMatrix();
        this.parent = new int[size];
        this.label = new boolean[size];

        generateResult();
    }

    public void generateResult() {
        for (int i = 0; i < this.dimension; i++) {
            parent[i] = 0;
            label[i] = false;
        }

        int count = 1;
        double[] line = new double[this.dimension];
        
        generateMST(0, label, parent, line, count);
    }

    /**
     * Generate the minimal spinning tree
     * @param start the start vertex
     * @param label the label array to show which vertex is already traveled
     * @param parent the parent array to show the parent of each vertex
     * @param line  the line array to store the distance from start vertex
     * @param count the number of vertex that are already traveled
     */
    public void generateMST(int start, boolean[] label, int[] parent, double[] line, int count) {
        int minVertex;

        if (count == this.dimension)
            return;

        if (start == 0) {
            label[start] = true;
            line = Arrays.copyOfRange(matrix[start], 0, this.dimension);
        } else {
            for (int i = 0; i < this.dimension; i++) {
                if (!label[i] && matrix[start][i] < line[i]) {
                    line[i] = matrix[start][i];
                    parent[i] = start;
                }
            }
        }

        minVertex = graphMatrix.getMinDistance(line, label);
        label[minVertex] = true;
        start = minVertex;
        count += 1;

        generateMST(start, label, parent, line, count);

    }

    /**
     * Generate the next vertex
     * @return a arraylist which includes the next child for each vertex
     */
    public ArrayList<LinkedList<Integer>> getNext() {
        ArrayList<LinkedList<Integer>> next = new ArrayList<>(this.dimension);

        for (int i = 0; i < this.dimension; i++) {
            LinkedList<Integer> child = new LinkedList<>();
            for (int j = 0; j < this.dimension; j++) {
                if (this.parent[j] == i) {
                    if (j == 0) {
                        continue;
                    }
                    child.add(j);
                }
            }
            next.add(child);
        }
        return next;
    }

    /**
     * Generate the hamiltonian cycle
     * @return a linkedlist including the hamiltonian cycle
     */
    public LinkedList<Integer> getPath() {
        ArrayList<LinkedList<Integer>> nextArray = getNext();
        LinkedList<Integer> path = new LinkedList<>();

        path.add(0);
        traversePath(nextArray.get(0), path, nextArray);
        path.add(0);

        return path;
    }

    /**
     * Traverse all the path with DFS method
     * @param next the linkedlist of current vertex
     * @param path the
     * @param nextArray
     */
    public void traversePath(LinkedList<Integer> next, LinkedList<Integer> path,
                             ArrayList<LinkedList<Integer>> nextArray) {
        if (next == null) {
            return;
        }

        for (int i = 0; i < next.size(); i++) {
            int vertex = next.get(i);
            path.add(vertex);
            traversePath(nextArray.get(vertex), path, nextArray);
        }

    }

    public double getHamiltonDistance(LinkedList<Integer> path) {
        double distance = 0;

        for (int i = 1; i < path.size(); i++) {
            int startVertex = path.get(i-1);
            int endVertex = path.get(i);
            distance += this.matrix[startVertex][endVertex];
        }
        return distance;
    }


    public LinkedList<Integer> getHamiltonCycle() {
        return getPath();
    }

    public double getCycleDistance() {
        double distance;

        LinkedList<Integer> path = getHamiltonCycle();
        distance = getHamiltonDistance(path);
        return distance;
    }
}
