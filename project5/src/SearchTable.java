import org.omg.CORBA.MARSHAL;

import java.util.ArrayList;

public class SearchTable {

    private ArrayList<String> tb;
    private int tbSize;
    private int bitSize;
    private int nodeIndex;

    private static int initTable = 256;


    public SearchTable(int tbSize, int bitSize) {
        this.tbSize = tbSize;
        this.bitSize = bitSize;
        this.nodeIndex = initTable;

        this.tb = new ArrayList<>(this.tbSize);
        initTable();
    }

    public void initTable() {
        for (int i = 0; i < this.initTable; i++) {
            String temp = "" + (char)i;
            tb.add(i, temp);
        }
    }

    public int getNodeIndex() {
        return this.nodeIndex;
    }

    public ArrayList<String> getTable() {
        return this.tb;
    }

    public String getString(int index) {

        return tb.get(index);
    }

    /**
     * check if the index in the table
     * @param index the index
     * @return true if it's in, otherwise false
     */
    public boolean containIndex(int index) {
        if (index < 0 || index >= this.getNodeIndex()) {
            return false;
        }
        return true;
    }

    /**
     * check if the node is over the limit
     * @return true if over
     */
    public boolean getFull() {
        if (nodeIndex >= Math.pow(2, bitSize)) {
            return true;
        }

        return false;
    }

    /**
     * add new node to the table
     * @param st a string to be added
     */
    public void addNode(String st) {
        if (getFull()) {
            SearchTable temp = new SearchTable(this.tbSize, this.bitSize);
            this.tb = temp.getTable();
            this.nodeIndex = temp.getNodeIndex();
        }

        tb.add(nodeIndex, st);
        nodeIndex += 1;
    }

}
