import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class RedBlackTreeSpellChecker {

    public static void commandDescription() {
        System.out.println("Legal command are:");
        System.out.println(">d display the entire word tree with a level order traversal.");
        System.out.println(">s print the words of the tree in sorted order (using an inorder traversal).");
        System.out.println(">r print the words of the tree in reverse sorted order (reverser inorder traversal).");
        System.out.println(">c <word> to spell check this word");
        System.out.println(">a <word> add this word to tree");
        System.out.println(">f <fileName> to check this text file for spelling error.");
        System.out.println(">i display the diameter of the tree");
        System.out.println(">m view this menu.");
        System.out.println(">! to quit");
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        RedBlackTree rbt = new RedBlackTree();

//        String file = "/Users/caochangjian/Downloads/PITT/structure95771/assign2019/project2/words.txt";
        String file = args[0];
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        int cnt = 0;
        while ((line = reader.readLine()) != null) {
            cnt += 1;
            rbt.insert(line);
        }
        System.out.println(cnt);
        reader.close();

        String[] fileContent = file.split("/");
        String filename = fileContent[fileContent.length-1];
        System.out.println("Loading a tree of English words from " + filename);
        System.out.println("Red Black Tree is loaded with " + rbt.getSize() + " word.");

        System.out.println("Initial tree height is " + rbt.height() + ".");
        System.out.println("Never worse than 2 * Lg(n+1) = " + 2*Math.log(rbt.getSize() + 1)/Math.log(2));
        System.out.println();

        commandDescription();
//        rbt.levelOrderTrversal();


        Scanner sc = new Scanner(System.in);
        while (true) {

            String command = sc.nextLine();
            String[] commandTent = command.split(" ");
            String cmd = commandTent[0];

            if (cmd.equals(">d")) {
                System.out.println("Level order traversal");
                rbt.levelOrderTrversal();
            } else if (cmd.equals(">s")) {
                System.out.println("Inorder traversal");
                rbt.inOrderTraversal();
            } else if (cmd.equals(">r")) {
                System.out.println("Reverse inorder traversal");
                rbt.reverseOrderTraversal();
            } else if (cmd.equals(">c")) {
                if (rbt.contains(commandTent[1])) {
                    System.out.println("Found " + commandTent[1] + " after " + rbt.getRecentCompares() + " comparisons.");
                } else {
                    System.out.println(commandTent[1] + " Not in dictionary. Perhaps you mean");
                    System.out.println(rbt.closeBy(commandTent[1]));
                }
            } else if (cmd.equals(">a")) {
                if (rbt.contains(commandTent[1])) {
                    System.out.println("The word " + commandTent[1] + " is already in dictionary.");
                } else {
                    rbt.insert(commandTent[1]);
                    System.out.println(commandTent[1] + " was added to dictionary.");
                }
            } else if (cmd.equals(">f")) {
                String checkFile = commandTent[1];
                BufferedReader fileReader = new BufferedReader(new FileReader(checkFile));
                String checkWord;
                int cn = 0;
                while ((checkWord = fileReader.readLine()) != null) {
                    if (checkWord.charAt(checkWord.length() - 1) == '.') {
                        checkWord = checkWord.substring(0, checkWord.length()-1);
                    }
                    String[] words = checkWord.split(" ");
                    if (words.length == 1) {
                        if (!rbt.contains(checkWord)) {
                            cn += 1;
                            System.out.println("'" + checkWord + "'" + " was no found in dictionary.");
                        }
                    } else {
                        for (String word: words) {
                            if (!rbt.contains(word)) {
                                cn += 1;
                                System.out.println("'" + word + "'" + " was no found in dictionary.");
                            }
                        }
                    }
                }
                if (cn == 0) {
                    System.out.println("No spelling errors found.");
                }
                fileReader.close();
            } else if (cmd.equals(">i")) {
                System.out.println("the diameter of the tree is " + rbt.getSize());
            } else if (cmd.equals(">m")) {
                commandDescription();
            } else if (cmd.equals(">!")) {
                System.out.println("Bye.");
                break;
            } else {
                continue;
            }

        }
    }


}
