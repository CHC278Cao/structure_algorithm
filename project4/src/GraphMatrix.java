
public class GraphMatrix {
    private int size;
    private double[][] matrix;
    //    private int[] label;
    private double[][] position;

    private static double feetToMile = 0.00018939;

    public GraphMatrix(int size, double[][] point) {
        this.size = size;
        this.matrix = new double[size][size];
//        this.label = new int[size];
        this.position = point;

        for (int i = 0; i < size; i++) {
//            label[i] = -1;
            for (int j = 0; j < size; j++) {
                matrix[i][j] = -1;
            }
        }

        generateMatrix();
    }

    public double[][] getMatrix() {
        return this.matrix;
    }

    public void generateMatrix() {
        double x1, y1;
        double x2, y2;
        double distance;

        for (int i = 0; i < this.size; i++) {
            double[] pointStart = position[i];
            x1 = pointStart[0];
            y1 = pointStart[1];
            for (int j = 0; j < this.size; j++) {
                double[] pointEnd = position[j];
                x2 = pointEnd[0];
                y2 = pointEnd[1];
                distance = getDistance(x1, y1, x2, y2);
                matrix[i][j] = distance;
            }
        }
    }

    /**
     * Derive the distance between two points
     *
     * @param x1 x of the beginning side
     * @param y1 y of the beginning side
     * @param x2 x of the end side
     * @param y2 y of the end side
     * @return the distance
     */
    public double getDistance(double x1, double y1, double x2, double y2) {
        return feetToMile * Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

    /**
     * Get the minimum of distance from the start vertex
     * @param line the line array to store the distance from start vertex
     * @param label the label array to show which vertex is already traveled
     * @return the minimum index of vertex
     */
    public int getMinDistance(double[] line, boolean[] label) {
        int minVertex = -1;
        double minEdge = -1;

        for (int i = 0; i < this.size; i++) {
            /* check the label is true */
            if (label[i]) {
                continue;
            }

            if (minEdge == -1) {
                minEdge = line[i];
                minVertex = i;
            }

            if (line[i] < minEdge) {
                minVertex = i;
                minEdge = line[i];
            }
        }

        return minVertex;
    }

//    public

}

