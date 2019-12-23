import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class TSPMain {


    public static void generateKML(double[][] latAdLong, LinkedList<Integer> pathMST,
                            LinkedList<Integer> pathPermutate) throws IOException {
        String filepath = "../PGHCrimes.kml";
        String altitude = "0.000000";
        double offset = 0.0001;
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));

        writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
        writer.write("<kml xmlns=\"http://earth.googe.com/kml/2.2\">\n");
        writer.write("<Document>\n");
        writer.write("<name>Pittsburgh TSP</name><description>TSP on Crime</description><Style id=\"style6\">\n");
        writer.write("<LineStyle>\n");
        writer.write("<color>73FF0000</color>\n");
        writer.write("<width>5</width>\n");
        writer.write("</LineStyle>\n");
        writer.write("</Style>\n");
        writer.write("<Style id=\"style5\">\n");
        writer.write("<LineStyle>\n");
        writer.write("<color>507800F0</color>\n");
        writer.write("<width>5</width>\n");
        writer.write("</LineStyle>\n");
        writer.write("</Style>\n");
        writer.write("<Placemark>\n");
        writer.write("<name>TSP Path</name>\n");
        writer.write("<description>TSP Path</description>\n");
        writer.write("<styleUrl>#style6</styleUrl>\n");
        writer.write("<lineString>\n");
        writer.write("<tessellate>1</tessellate>\n");
        writer.write("<coordinates>\n");
        for (int i = 0; i < pathMST.size(); i++) {
            int index = pathMST.get(i);
            String latitude = Double.toString(latAdLong[index][0]);
            String longitude = Double.toString(latAdLong[index][1]);
            String st = latitude + "," + longitude + "," + altitude + "\n";
            writer.write(st);
        }
        writer.write("</coordinates>\n");
        writer.write("</LineString>\n");
        writer.write("</Placemark>\n");
        writer.write("<Placemark>\n");
        writer.write("<name>Optimal Path</name>\n");
        writer.write("<description>Optimal Path</description>\n");
        writer.write("<styleUrl>#style5</styleUrl>\n");
        writer.write("<lineString>\n");
        writer.write("<tessellate>1</tessellate>\n");
        writer.write("<coordinates>\n");
        for (int i = 0; i < pathPermutate.size(); i++) {
            int index = pathPermutate.get(i);
            String latitude = Double.toString(latAdLong[index][0] + offset);
            String longitude = Double.toString(latAdLong[index][1] + offset);
            String st = latitude + "," + longitude + "," + altitude + "\n";
            writer.write(st);
        }
        writer.write("</coordinates>\n");
        writer.write("</LineString>\n");
        writer.write("</Placemark>\n");
        writer.write("</Document>\n");
        writer.write("</kml>\n");

        writer.close();

    }

    public static void main(String[] args) throws Exception {
        String csvfilepath = "../CrimeLatLonXY1990.csv";
//        String csvfilepath = "CrimeLatLonXY1990.csv";
        String startDate;
        String endDate;
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("Enter start date");
            startDate = sc.nextLine();
//            startDate = "1/1/90";
            System.out.println("Enter end date");
            endDate = sc.nextLine();
//            endDate = "1/1/90";
            System.out.println("Crime records between " + startDate + " and " +
                    endDate);

            FileContentReader fileContentReader = new FileContentReader(csvfilepath, startDate, endDate);

            ArrayList<String> content = fileContentReader.parseContent();
            fileContentReader.contentPrint(content);


            double[][] tt = fileContentReader.parsePosition(content);
            int size = fileContentReader.getNumber(content);
//            System.out.println("data number is " + size);

            double[][] position = fileContentReader.parsePosition(content);
            MSTPrim mst = new MSTPrim(size, position);

            double[][] latAdLong = fileContentReader.parseLatAndAlt(content);
            System.out.println("Hamiltonian Cycle");
            LinkedList<Integer> path = mst.getHamiltonCycle();
            for (int i = 0; i < path.size(); i++) {
                System.out.print(path.get(i) + " ");
            }
            System.out.println();

            double distance = mst.getCycleDistance();
            System.out.println("Length Of cycle: " + distance + " miles");

            Permutation ptm = new Permutation(size, position);
            LinkedList<Integer> perPath = ptm.getHamiltonCycle();
            System.out.println("The best permutation");
            for (int i = 0; i < perPath.size(); i++) {
                System.out.print(perPath.get(i) + " ");
            }
            System.out.println();

            double perDistance = ptm.getCycleDistance();
            System.out.println("Optimal Cycle length = " + perDistance + " miles");

//            for (int i = 0; i < latAdLong.length; i++) {
//                System.out.println(latAdLong[i][0] + " " + latAdLong[i][1]);
//            }
            generateKML(latAdLong, path, perPath);

        } catch (InputMismatchException e) {
            System.out.println("Input doesn't match");
        }

    }
}
