import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashSet;

public class HashTable {
    private ArrayList<LinkedNode> hash;
    private int tbSize;
    private int limitBit;
    private int nodeIndex;

    private static int initSize = 256;

    public HashTable(int size, int limitBit) {
        this.tbSize = size;
        this.limitBit = limitBit;
        this.nodeIndex = initSize;
        hash = new ArrayList<>(tbSize);

        initArray();
    }


    /**
     * initialize the private data
     */
    public void initArray() {
        for (int i = 0; i < initSize; i++) {
//            int index = i % this.tbSize;
            String key = "" + (char)i;
//            System.out.println(i + ":" + key);
            if (i < this.tbSize) {
                hash.add(i, new LinkedNode(key, i, null));
            } else {
                int index = i % this.tbSize;
                LinkedNode end = hash.get(index).getLastElement(hash.get(index));
                end.addNodeAfter(key, i);
            }

        }
    }

    public int getTbSize() {
        return this.tbSize;
    }

    public int getNodeIndex() {
        return this.nodeIndex;
    }

    public int getLimitBit() {
        return this.limitBit;
    }

    public ArrayList<LinkedNode> getHashTable() {
        return this.hash;
    }


    /**
     * create new node and add it
     * @param index a int value to indicate the index of HashTable
     * @param key a string value to be as key
     */
    public void createNode(int index, String key) {
        // recreate a new table and reset the value
        if (this.getFull()) {
            HashTable temp = new HashTable(this.tbSize, this.limitBit);
            this.hash = temp.getHashTable();
            this.nodeIndex = temp.getNodeIndex();
//            value = temp.getTbSize();
        }

        LinkedNode head = hash.get(index);

        while (head.getNext() != null) {
            head = head.getNext();
        }

        head.addNodeAfter(key, nodeIndex);
        nodeIndex += 1;
    }


    public int find(int index, String st) {
        LinkedNode head = hash.get(index);

        while (head != null) {
            if (head.getKey() == st) {
                return (int)head.getValue();
            }
            head = head.getNext();
        }
        return -1;
    }

    /**
     * find the index of HashTable which might includes this string
     * @param st a string to be searched
     * @return the index of hashTable
     */
    public int find(String st) {

        char startChar = st.charAt(0);

        int value = (int)startChar;
        value = (value & 0xFF);
        int id = value % this.tbSize;

        return id;

    }

    /**
     * get the value corresponding to the string
     * @param index the index of HashTable
     * @param st a string to be searched
     * @return the corresponding value
     */
    public int getValue(int index, String st) {
        if (st.length() == 1) {
            char start = st.charAt(0);
            int res = (int)start;
            res = res & 0xFF;
            return res;
        }

        LinkedNode head = this.hash.get(index);

        while (head != null) {
            if (head.getKey().equals(st)) {
                return (int)head.getValue();
            }
            head = head.getNext();
        }

        return -1;
    }


    public int getValue(String st) {
        if (st.length() == 1) {
            char start = st.charAt(0);
            int res = (int)start;
            res = res & 0xFF;
            return res;
        }

        int index = find(st);
        if (index >= 0 && index < this.tbSize) {
            LinkedNode head = this.hash.get(index);

            while (head != null) {
                if (head.getKey().equals(st)) {
                    return (int)head.getValue();
                }
                head = head.getNext();
            }
        }

        return -1;

    }


    /**
     * check if the node number is over the limit
     * @return true if over the limit
     */
    public boolean getFull() {
        if (this.nodeIndex >= Math.pow(2, this.limitBit)) {
            return true;
        }

        return false;
    }
}
