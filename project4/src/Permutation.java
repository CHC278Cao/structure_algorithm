import java.util.ArrayList;
import java.util.LinkedList;

public class Permutation {
    private int size;
    private GraphMatrix graphMatrix;
    private double[][] matrix;
    private boolean[] label;

    public Permutation(int size, double[][] position) {
        this.size = size;
        graphMatrix = new GraphMatrix(size, position);
        this.matrix = graphMatrix.getMatrix();
        this.label = new boolean[size];

        for (int i = 0; i < size; i++) {
            label[i] = false;
        }

    }

    public ArrayList<LinkedList<Integer>> getPermutationWithDFS() {

        ArrayList<LinkedList<Integer>> result = new ArrayList<LinkedList<Integer>>();
        LinkedList<Integer> path = new LinkedList<>();
        arrayPermutate(path, label, result);

        for (int i = 0; i < result.size(); i++) {
            int tt = result.get(i).getFirst();
            result.get(i).add(tt);
        }
        return result;
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

    /**
     * generate the permutation with DFS method
     * @param path one path traversal
     * @param label label array to show which vertex is already travelled
     * @param result a ArrayList<LinkedList<>> including all possible path
     */
    public void arrayPermutate(LinkedList<Integer> path, boolean[] label,
                               ArrayList<LinkedList<Integer>> result) {
        if (path.size() == this.size) {
            // need to get a clone, since java use reference
            LinkedList<Integer> res = (LinkedList<Integer>)path.clone();
            result.add(res);
            return;
        }

        for (int i = 0; i < this.size; i++) {
            if (!label[i]) {
                path.add(i);
                label[i] = true;
                arrayPermutate(path, label, result);
                path.removeLast();
                label[i] = false;
            }
        }
    }


    public ArrayList<int[]> getPermutationWithExchange() {
        ArrayList<int[]> result = new  ArrayList<int[]>();
        int level = 0;
        int[] data = new int[this.size];
        for (int i = 0; i < this.size; i++) {
            data[i] = i;
        }

        arrayExchange(level, data, result);

        return result;
    }

    public void arrayExchange(int level, int[] data, ArrayList<int[]> result) {
        if (level == data.length) {
            result.add((int[]) data.clone());
            return;
        }

        for (int i = level; i < data.length; i++) {
            swap(data, level, i);
            arrayExchange(level+1, data, result);
            swap(data, level, i);
        }
    }

    public void swap(int[] data, int start, int end) {
        int temp = data[start];
        data[start] = data[end];
        data[end] = temp;
    }

    public LinkedList<Integer> getHamiltonCycle() {
        ArrayList<LinkedList<Integer>> result = getPermutationWithDFS();
        int ind = -1;
        double minDistance = -1;

        for (int i = 0; i < result.size(); i++) {
            LinkedList<Integer> path = result.get(i);
            if (minDistance == -1) {
                minDistance = getHamiltonDistance(path);
                ind = i;
            }

            if (getHamiltonDistance(path) < minDistance) {
                ind = i;
                minDistance = getHamiltonDistance(path);
            }
        }
        return result.get(ind);
    }



    public double getCycleDistance() {
        double distance;
        LinkedList<Integer> path = getHamiltonCycle();

        distance = getHamiltonDistance(path);
        return distance;
    }
}
