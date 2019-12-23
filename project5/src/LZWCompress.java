import java.io.*;

public class LZWCompress {

    private String inFile;
    private String outFile;
    private HashTable ht;
//    private SearchTable st;
//    private int cnt = 0;
    private int tableSize;
    private int bitSize;
    private int readCnt;
    private int writeCnt;

    public LZWCompress(String in, String out, int tableSize, int bitSize) {
        this.inFile = in;
        this.outFile = out;
//        this.st = st;
        this.tableSize = tableSize;
        this.bitSize = bitSize;

        this.readCnt = 0;
        this.writeCnt = 0;
        this.ht = new HashTable(this.tableSize, this.bitSize);

    }

    /**
     * run LZWCompression
     * @throws IOException FileNotFoundException
     */
    public void runCompress() throws IOException {
//        int cur = ht.getTbSize();
        byte byteIn;
        DataInputStream in = new DataInputStream(
                new BufferedInputStream(new FileInputStream(this.inFile)));
        DataOutputStream out = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(this.outFile)));

        String coward = "";
        int byteRecode = 0;
        int cnt = 0;
        int linkIndex = -1;

        try {
            while(true) {
                byteIn = in.readByte();
                this.readCnt += 1;

                String c = byteToString(byteIn);
                linkIndex = byteToInt(byteIn);

                if (linkIndex >= ht.getTbSize()) {
                    System.out.println("error in byteIn");
                    continue;
                }

                String cowardString = coward + c;
                // find the array index to
                int arrayIndex = ht.find(cowardString);
//                if (arrayIndex == -1) {
//                    System.out.println(cowardString);
//                }
                int flag = ht.getValue(arrayIndex, cowardString);
                if (flag != -1) {
                    coward = cowardString;

                } else {
                    cnt += 1;
                    int cValue = ht.getValue(arrayIndex, coward);
//                    System.out.print(coward);
                    writeBits(cValue, byteRecode, cnt, out);
                    ht.createNode(arrayIndex, cowardString);

                    coward = "" + c;
                    byteRecode = cValue;
                }

            }



        } catch (EOFException e) {
            if (!coward.equals("")) {
                cnt += 1;
                int cValue = ht.getValue(coward);
//                writeBits(cValue, byteRecode, cnt);
                if ((cnt % 2) == 1) {
                    addPadding(cValue, out);
                } else {
                    writeBits(cValue, byteRecode, cnt, out);
                }
            }

            in.close();
            out.close();
        }
    }

    /**
     * convert byte to integer
     * @param byteIn data of byte type
     * @return data of int type
     */
    public int byteToInt(byte byteIn) {
        int ans;
        char c = (char)byteIn;
        c = (char)(c & 0x7E);
        ans = c;

        return ans;
    }

    /**
     * convert byte to string
     * @param byteIn a data of byte type
     * @return a data of string type
     */
    public String byteToString(byte byteIn) {
        String ans = "";
        char c = (char)byteIn;
        c = (char)(c & 0xFF);
        int t = c;
        ans += (char)t;

        return ans;
    }

    /**
     * write out a byte to the output stream
     * @param value a int value to be written
     * @param byteRecord a int value to be written
     * @param count a int flag to show the odd or even number
     * @param out the output stream
     * @throws IOException I/O error
     */
    public void writeBits(int value, int byteRecord, int count, DataOutputStream out) throws IOException {

        int temp;

        if ((count % 2) == 1) {
            temp = (value >> 4);
            temp &= 0xFF;
            byte ans = (byte)temp;
//            System.out.print((ans & 0xFF));
            out.writeByte(ans);
            this.writeCnt += 1;
        } else {
            temp = (byteRecord << 12);
            temp = (temp & 0xF000) | value;

            byte bitOne = (byte)(temp >> 8);
//            System.out.print((bitOne & 0xFF));
            out.writeByte(bitOne);
            byte bitTwo = (byte)(temp & 0xFF);
//            System.out.print((bitTwo & 0xFF ));
            out.writeByte(bitTwo);
            this.writeCnt += 2;
        }

    }

    /**
     * adds padding to a int value to make up two bytes
     * @param value a int value to be written
     * @param out the output stream
     * @throws IOException I/O Error
     */
    public void addPadding(int value, DataOutputStream out) throws IOException {
        int temp;

        temp = (value >> 4);
        byte bitOne = (byte)(temp & 0xFF);
        out.writeByte(bitOne);
        temp = (value << 4);
        byte bitTwo = (byte)(temp & 0xFF);
        out.writeByte(bitTwo);
        this.writeCnt += 2;
    }

    /**
     * get the number of byte data being read
     * @return a int value of bytes number
     */
    public int getReadCnt() {
        return this.readCnt;
    }

    /**
     * get the number of byte data being written
     * @return a int value of bytes number
     */
    public int getWriteCnt() {
        return this.writeCnt;
    }

}
