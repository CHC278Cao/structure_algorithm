import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LZWCompression {

    private static int hashTableSize = 127;
    private static int searchTableSize = 256;
    private static int bitLimit = 12;

    /**
     * process LZWCompression on files
     * @param in file path for original file
     * @param out file path for generated file
     * @param numberFlag true if print readBytes and writeBytes
     * @throws IOException FileNotFoundException
     */
    public static void generateLZWCompress(String in, String out, boolean numberFlag)
            throws IOException{
        LZWCompress lzwCompress = new LZWCompress(in, out, hashTableSize, bitLimit);
        lzwCompress.runCompress();

        if (numberFlag) {
            System.out.println("bytes read = " + lzwCompress.getReadCnt() + " , " +
                    "bytes written = " + lzwCompress.getWriteCnt());
        }

    }

    /**
     * process LZWDecompression on files
     * @param in file path for original file
     * @param out file path for generated file
     * @param numberFlag true if print readBytes and writeBytes
     * @throws IOException FileNotFoundException
     */
    public static void generateLZWDecompress(String in, String out, boolean numberFlag)
            throws IOException {
        LZWDecompress lzwDecompress = new LZWDecompress(in, out, searchTableSize, bitLimit);
        lzwDecompress.runDecompress();

        if (numberFlag) {
            System.out.println("bytes read = " + lzwDecompress.getReadCnt() + " , " +
                    "bytes written = " + lzwDecompress.getWriteCnt());
        }

    }

    /**
     * test if the content of files are same
     * @param in file path for original file
     * @param out file path for generated file
     * @return  true if same otherwise false
     * @throws IOException FileNotFoundException
     */
    public static boolean testFile(String in, String out) throws IOException {
        BufferedReader inReader = new BufferedReader(new FileReader(in));
        BufferedReader outReader = new BufferedReader(new FileReader(out));
        String contentIn = "", contentOut = "";
        int line = 0;

        while (true) {
            contentIn = inReader.readLine();
            contentOut = outReader.readLine();
            line += 1;
            if (contentIn == null || contentOut == null) {
                break;
            }

            if (!contentIn.equals(contentOut)) {
                System.out.println(contentIn);
                System.out.println(contentOut);
                return false;
            }
        }

        return (contentIn == null && contentOut == null);
    }


    public static void main(String[] args) throws IOException {

        int number = args.length;
        if (number == 3) {
            if (args[0].equals("-c")) {
                generateLZWCompress(args[1], args[2], false);
            } else if (args[0].equals("-d")) {
                generateLZWDecompress(args[1], args[2], false);
            } else {
                throw new IOException("Error: command doesn't match.");
            }

        } else if (number == 4) {
            if (!args[1].equals("-v")) {
                throw new IOException("Error: second args doesn't match.");
            }

            if (args[0].equals("-c")) {
                generateLZWCompress(args[2], args[3], true);
            } else if (args[0].equals("-d")) {
                generateLZWDecompress(args[2], args[3], true);
            } else {
                throw new IOException("Error: command doesn't match.");
            }

        } else {
            throw new IOException("Error: input length doesn't match.");
        }

//        System.out.println(testFile("./CrimeLatLonXY.csv", "./CrimeLatLonXYDecompress.csv"));
    }
}
