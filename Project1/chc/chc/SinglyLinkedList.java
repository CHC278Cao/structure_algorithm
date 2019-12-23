package chc;

import edu.colorado.nodes.ObjectNode;

import java.math.BigInteger;


public class SinglyLinkedList {
    private ObjectNode head;
    private ObjectNode tail;
    private ObjectNode iter;
    private int countNodes;

    public SinglyLinkedList() {
        head = null;
        tail = null;
        iter = null;
        countNodes = 0;
    }


    /**
     * add an object c at the front of SinlyLinkedList
     * @param c
     *  an ObjectNode which includes Object c
     *
     * addAtFrontEnd doesn't need to check if the head is null
     */
    public void addAtFrontNode(Object c) {

        ObjectNode temp = new ObjectNode(c, head);
        head = temp;
        countNodes += 1;
    }

    /**
     * add an object c at the end of SinglyLinkedList
     * @param c
     */
    public void addAtEndNode(Object c) {
        if (tail == null) {
            head = tail = new ObjectNode(c, null);
        } else {
            tail.addNodeAfter(c);
            tail = tail.getLink();
        }
        countNodes += 1;
    }

    /**
     * return the nu
     * @return
     */
    public int countNodes() {
        return countNodes;
    }

    /**
     * get the data in the tail of SinlyLinkedList
     * @return
     *  the data in the tail of list
     */
    public Object getLast() {
        return tail.getData();
    }

    /**
     * get the data in the ObjectNode at index i
     * @param i
     *  the index of desired ObjectNode
     * @return
     *  the reference to an object with list index
     */
    public Object getObjectAt(int i) {
//        if (i < 0 || i >= this.countNodes)
//            throw new IllegalArgumentException("index i is out of range of singlylinkedlist.");


        return ObjectNode.listPosition(head, i).getData();

    }

    /**
     * check if iterator is not null
     * @return
     *  true if the itertor is not null
     */
    public boolean hasNext() {

        return iter != null;
    }

    public Object next() {
        Object temp = iter.getData();
        iter = iter.getLink();

        return temp;
    }

    /**
     * reset the iter to the head of singlyLinkedList
     */
    public void reset() {
        iter = head;
    }

    /**
     *
     * @return
     */
    public String toString() {

        return head.toString();
    }

    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();
        for (int i = 0; i < 26; i++) {
            list.addAtEndNode(new BigInteger("" + i));
        }
        System.out.println(list);
        list.reset();
        while (list.hasNext()) {
            System.out.println(list.next());
        }
    }
}
