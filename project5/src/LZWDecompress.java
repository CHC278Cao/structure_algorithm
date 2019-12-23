import java.io.*;

public class LZWDecompress {
    private String inFile;
    private String outFile;
    private SearchTable st;
    private int tableSize;
    private int bitSize;
    private int readCnt;
    private int writeCnt;

    public LZWDecompress(String in, String out, int tableSize, int bitSize) {
        this.inFile = in;
        this.outFile = out;
//        this.st = st;
        this.tableSize = tableSize;
        this.bitSize = bitSize;

        this.readCnt = 0;
        this.writeCnt = 0;
        this.st = new SearchTable(tableSize, bitSize);
    }

    /**
     * run decompression on files
     * @throws IOException FileNotFoundException
     */
    public void runDecompress() throws IOException {
//        byte byteIn;
        DataInputStream in = new DataInputStream(
                new BufferedInputStream(new FileInputStream(this.inFile)));
        DataOutputStream out = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(this.outFile)));

        byte bitOne = 0, bitTwo = 0, bitThree;
        int[] indexList = new int[2];
        int cnt = 0;
        String pcword = "";

        try {
            while (true) {


                bitOne = in.readByte();
                this.readCnt += 1;
                bitTwo = in.readByte();
                this.readCnt += 1;
                bitThree = in.readByte();
                this.readCnt += 1;

                int idx1 = getIndexOdd(bitOne, bitTwo);
                indexList[0] = idx1;

                int idx2 = getIndexEven(bitTwo, bitThree);
                indexList[1] = idx2;

                if (this.readCnt == 3) {
                    if (!st.containIndex(indexList[0])) {
                        throw new IOException("Error in decompression.");
                    }
                    pcword = st.getString(indexList[0]);
                    writeByte(pcword, out);
                    indexList[0] = -1;
                }


                for (int index: indexList) {
                    if (index == -1) {
                        continue;
                    }

                    String temp = "";

                    if (st.containIndex(index)) {
                        temp = st.getString(index);


                    } else {
                        temp = pcword + pcword.charAt(0);
//                        writeByte(temp, out);
//                        st.addNode(pcword + temp.charAt(0));
                    }

                    writeByte(temp, out);
                    st.addNode(pcword+temp.charAt(0));
                    pcword = temp;

                }

                bitOne = bitTwo = bitThree = 0;

            }
        } catch (EOFException e) {

            if (bitOne != 0 || bitTwo != 0) {
                int index = getIndexOdd(bitOne, bitTwo);
                String temp;
                if (st.containIndex(index)) {
                    temp = st.getString(index);
                    writeByte(temp, out);
                } else {
                    temp = pcword + pcword.charAt(0);
                    writeByte(temp, out);
                }
            }
            in.close();
            out.close();
        }
    }

    /**
     * get the int value corresponding to these two bytes
     * @param bitOne a bite value to be processed
     * @param bitTwo a bite value to be processed
     * @return a int value
     */
    public int getIndexOdd(byte bitOne, byte bitTwo) {
        int first, end, res;

        first = (int)bitOne;
        first = (first & 0xFF) << 4;
        end = (int)bitTwo;
        end = (end & 0xFF) >> 4;
        res = first | end;

        return res;

    }

    /**
     * get the int value corresponding to these two bytes
     * @param bitTwo a bite value to be processed
     * @param bitThree a bite value to be processed
     * @return a int value
     */
    public int getIndexEven(byte bitTwo, byte bitThree) {
        int first, end, res;

        first = (int)bitTwo;
        first = (first & 0x0F) << 8;
        end = (int)bitThree;
        end = (bitThree & 0xFF);
        res = first | end;

        return res;


    }

    /**
     * write out the string to the output stream
     * @param st a string to be written
     * @param out output stream
     * @throws IOException I/O Error
     */
    public void writeByte(String st, DataOutputStream out) throws IOException {
//        System.out.print(st);
        byte[] byteString = st.getBytes();

        for (byte byteOut: byteString) {
            out.writeByte(byteOut);
            this.writeCnt += 1;
        }


    }

    public int getReadCnt() {
        return this.readCnt;
    }

    public int getWriteCnt() {
        return this.writeCnt;
    }

}
