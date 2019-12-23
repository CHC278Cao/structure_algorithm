package chc;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class MerkleTree {
    private SinglyLinkedList list;

    public MerkleTree(SinglyLinkedList text) throws NoSuchAlgorithmException {
        if (text.countNodes() < 1) {
            throw new IllegalArgumentException("The SinglyLinkedList text should no less than 1.");
        }
        list = new SinglyLinkedList();
        text = getEvenNodes(text);
        list.addAtEndNode(text);

        for (SinglyLinkedList cur = (SinglyLinkedList) list.getLast();
             cur.countNodes() != 1;
             cur = (SinglyLinkedList) list.getLast()) {
            SinglyLinkedList newLine = getHash(cur);
            list.addAtEndNode(newLine);
        }

    }

    public SinglyLinkedList getEvenNodes(SinglyLinkedList line) {
        if (line.countNodes() % 2 != 0) {
            line.addAtEndNode(line.getLast());
        }
        return line;
    }

    public SinglyLinkedList getHash(SinglyLinkedList line) throws NoSuchAlgorithmException {
        line = getEvenNodes(line);

        SinglyLinkedList newLine = new SinglyLinkedList();
//        StringBuilder st = new StringBuilder();
        line.reset();
        while (line.hasNext()) {
            String l1 = (String) line.next();
//            st.append(l1);
            String l2 = (String) line.next();
//            st.append(l2);
            newLine.addAtEndNode(h(l1.concat(l2)));
        }
        return newLine;
    }

    public String getRoot() {
        SinglyLinkedList root = (SinglyLinkedList) list.getLast();
        return (String) root.getLast();
    }

    public static String h(String text) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= 31; i++) {
            byte b = hash[i];
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        for (; ; ) {
            System.out.print("Enter file name: ");
            String filename = scanner.nextLine();
            try {
                Scanner fileScanner = new Scanner(new FileInputStream(filename));
                SinglyLinkedList text = new SinglyLinkedList();
                while (fileScanner.hasNext()) {
                    text.addAtEndNode(fileScanner.next());
                }

                try {
                    MerkleTree tree = new MerkleTree(text);
                    System.out.printf("The Merkel root of file %s is:\n%s\n", filename, tree.getRoot());
                } catch (NoSuchAlgorithmException e) {
                    System.err.println(e.getCause() + " Exit...");
                    System.exit(1);
                }
            } catch (FileNotFoundException e) {
                System.out.printf("Cannot open %s:%s\n", filename, e.getCause());
            }
        }
    }
}
