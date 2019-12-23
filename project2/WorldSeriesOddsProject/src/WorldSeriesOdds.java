import java.util.Scanner;

public class WorldSeriesOdds {

    public static double dpSolution(int numA, int numB) {
        double[][] result = new double[numA+1][numB+1];

        for (int j = 0; j <= numB; j++) {
            result[0][j] = 1;
        }

        for (int i = 1; i <= numA; i++) {
            result[i][0] = 0;
        }

        for (int i = 1; i <= numA; i++) {
            for (int j = 1; j <= numB; j++) {
                result[i][j] = (result[i-1][j] + result[i][j-1]) / 2;
            }
        }
        return result[numA][numB];
    }

    public static double getRecursion(int numA, int numB) {
        double res = recursionSolution(numA, numB);
        return res;
    }

    public static double recursionSolution(int numA, int numB) {
        if (numA == 0) {
            return 1;
        } else if (numB == 0) {
            return 0;
        } else {
            return recursionSolution(numA-1, numB-1);
        }
    }

    public static void main(String[] args) {
        int numA, numB;
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Enter two positive integer:");
            numA = sc.nextInt();
            numB = sc.nextInt();
            if (numA == 0 && numB == 0) {
                break;
            }
            if (numA < 0 || numB < 0) {
                System.out.println("");
            }
            long startTime1 = System.nanoTime();
            System.out.println("The result calculated by dp : " + dpSolution(numA, numB));
            long endTime1 = System.nanoTime();
            System.out.println("By dp, Time in second = " + (endTime1-startTime1));

            long startTime2 = System.nanoTime();
            System.out.println("The result calculated by recursion : " + dpSolution(numA, numB));
            long endTime2 = System.nanoTime();
            System.out.println("By recursion, Time in second = " + (endTime2-startTime2));
        }
    }
}
