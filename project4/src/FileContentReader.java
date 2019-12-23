import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

public class FileContentReader {
    private String filepath;
    private String startTag;
    private String endTag;

    public FileContentReader(String filepath, String startTag, String endTag) {
        this.filepath = filepath;
        this.startTag = startTag;
        this.endTag = endTag;
    }

    public ArrayList<String> parseContent() throws IOException {
        String line;
        String[] lineContent;
        ArrayList<String> content = new ArrayList<String>();

        BufferedReader fileReader = new BufferedReader(new FileReader(this.filepath));
        while ((line = fileReader.readLine()) != null) {
            lineContent = line.split(",");
            if ((lineContent[5].equals(this.startTag)) || (lineContent[5].equals(this.endTag))) {
                content.add(line);
            }
        }
        fileReader.close();

        return content;
    }

    public int getNumber(ArrayList<String> content) {
        return content.size();
    }

    public void contentPrint(ArrayList<String> content) {
        for (String line: content) {
            System.out.println(line);
        }
    }

//    public ArrayList<String[]> parsePosition(ArrayList<String> content) throws IOException {
//        ArrayList<String[]> positionArray = new ArrayList<String[]>();
//
//        String[] lineContent;
//        String[] position = new String[2];
//        for (String line: content) {
//            lineContent = line.split(",");
//            position = Arrays.copyOfRange(lineContent, 0, 2);
//
//            positionArray.add(position);
//        }
//        return positionArray;
//    }


    public double[][] parsePosition(ArrayList<String> content) {
        String[] lineContent;
        String line;
        int number = getNumber(content);
        double[][] position = new double[number][2];

        for (int i = 0; i < number; i++) {
            line = content.get(i);
            lineContent = line.split(",");
            position[i][0] = Double.parseDouble(lineContent[0]);
            position[i][1] = Double.parseDouble(lineContent[1]);
        }

        return position;
    }

    public double[][] parseLatAndAlt(ArrayList<String> content) {
        String[] lineContent;
        String line;
        int number = getNumber(content);
        double[][] position = new double[number][2];

        for (int i = 0; i < number; i++) {
            line = content.get(i);
            lineContent = line.split(",");
            position[i][0] = Double.parseDouble(lineContent[8]);
            position[i][1] = Double.parseDouble(lineContent[7]);
        }

        return position;
    }

}
