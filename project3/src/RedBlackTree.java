import java.math.BigInteger;

public class RedBlackTree {
    public static final int BLACK = 0;
    public static final int RED = 1;
    private RedBlackNode sentinal;
    private RedBlackNode root;
    private int datacomparation = 0;


    public RedBlackTree() {
        sentinal = new RedBlackNode(null, null, BLACK, null, null, null);
//        root = new RedBlackNode(null, BLACK, null, null, null);
        root = sentinal;
    }

    /**
     *  get the number of nodes in the tree
     * @return
     *  number of values inserted into the tree
     */
    public int getSize() {
        return getSize(root);
    }

    public int getSize(RedBlackNode x) {
        RedBlackNode temp = x;
        if (temp == sentinal) {
            return 0;
        }
        return getSize(temp.getLc()) + getSize(temp.getRc()) + 1;
    }



    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(RedBlackNode t) {
        if (t == sentinal) {
            return;
        }
        inOrderTraversal(t.getLc());
        System.out.println(t);
        inOrderTraversal(t.getRc());
    }

    public void reverseOrderTraversal() {
        reverseOrderTraversal(root);
    }

    private void reverseOrderTraversal(RedBlackNode t) {
        if (t == sentinal) {
            return;
        }
        inOrderTraversal(t.getRc());
        System.out.println(t);
        inOrderTraversal(t.getLc());

    }

    /**
     * insert new data into the RedBlackTree
     * @param value
     *  the new inserted value
     */
    public void insert(String key, BigInteger value) {
        RedBlackNode temp = new RedBlackNode(key, value, RED, null, null, null);
        RedBlackNode y = sentinal;
        RedBlackNode x = root;

        while (x != sentinal) {
            y = x;
            if (temp.getKey().compareTo(x.getKey()) < 0) {
                x = x.getLc();
            } else if (temp.getKey().compareTo(x.getKey()) > 0){
                x = x.getRc();
            } else {
                x.setKey(temp.getKey());
                x.setValue(temp.getValue());
                return;
            }
        }

        temp.setP(y);
        if (y == sentinal) {
            root = temp;
        } else {
            if (temp.getKey().compareTo(y.getKey()) < 0) {
                y.setLc(temp);
            } else {
                y.setRc(temp);
            }
        }
        temp.setLc(sentinal);
        temp.setRc(sentinal);
        temp.setColor(RED);
        RBIsertFixup(temp);

    }

    /**
     * fix up the tree so that red black property are preserved
     * @param z
     *  the redblacknode will be modified
     */
    public void RBIsertFixup(RedBlackNode z) {
        while (z.getP().getColor() == RED) {
            if (z.getP() == z.getP().getP().getLc()) {
                RedBlackNode y = z.getP().getP().getRc();
                if (y.getColor() == RED) {
                    z.getP().setColor(BLACK);
                    y.setColor(BLACK);
                    z.getP().getP().setColor(RED);
                    z = z.getP().getP();
                } else {
                    if (z == z.getP().getRc()) {
                        z = z.getP();
                        leftRotate(z);
                    }
                    z.getP().setColor(BLACK);
                    z.getP().getP().setColor(RED);
                    rightRotate(z.getP().getP());
                }
            } else {
                RedBlackNode y = z.getP().getP().getLc();
                if (y.getColor() == RED) {
                    z.getP().setColor(BLACK);
                    y.setColor(BLACK);
                    z.getP().getP().setColor(RED);
                    z = z.getP().getP();
                } else {
                    if (z == z.getP().getLc()) {
                        z = z.getP();
                        rightRotate(z);
                    }
                    z.getP().setColor(BLACK);
                    z.getP().getP().setColor(RED);
                    leftRotate(z.getP().getP());
                }
            }
        }
        root.setColor(BLACK);
    }

    /**
     * perform a single left rotation
     * @param x
     *  the node for left rotation
     */
    public void leftRotate(RedBlackNode x) {
        RedBlackNode y = x.getRc();
        x.setRc(y.getLc());
        y.getLc().setP(x);
        y.setP(x.getP());

        if (x.getP() == sentinal) {
            root = y;
        } else {
            if (x == x.getP().getLc()) {
                x.getP().setLc(y);
            } else {
                x.getP().setRc(y);
            }
        }
        y.setLc(x);
        x.setP(y);
    }

    public void rightRotate(RedBlackNode x) {
        RedBlackNode y = x.getLc();
        x.setLc(y.getRc());
        y.getRc().setP(x);
        y.setP(x.getP());

        if (x.getP() == sentinal) {
            root = y;
        } else {
            if (x == x.getP().getLc()) {
                x.getP().setLc(y);
            } else {
                x.getP().setRc(y);
            }
        }
        y.setRc(x);
        x.setP(y);
    }

    /**
     *  check if a string value is in RedBlackTree
     * @param v
     *  the value to search for
     * @return
     *  true if v is in the tree, false otherwise
     */
    public boolean contains(String v, BigInteger data) {
        RedBlackNode cur = root;
        datacomparation = 0;
        while (cur != sentinal) {
            datacomparation += 1;
            if (v.compareTo(cur.getKey()) < 0) {
                cur = cur.getLc();
            } else if(v.compareTo(cur.getKey()) > 0) {
                cur = cur.getRc();
            } else {
                return cur.getValue().equals(data);
            }
        }
        return false;
    }

    public BigInteger get(String v) {
        RedBlackNode cur = root;
        while (cur != null) {
            if (v.compareTo(cur.getKey()) > 0) {
                cur = cur.getRc();
            } else if (v.compareTo(cur.getKey()) < 0) {
                cur = cur.getLc();
            } else {
                return cur.getValue();
            }
        }
        return null;
    }

    /**
     *  get the number of comparisons made for contain method
     * @return
     *  the number of comparisons
     */
    public int getRecentCompares() {
        return datacomparation;
    }

    /**
     *  get the exact string or closeby string
     * @param v
     *  the string to search for
     * @return
     *  the exact string when contains or closeby string when not contains
     */
    public String closeBy(String v) {
        RedBlackNode cur = root;
        RedBlackNode prev = sentinal;
        while (cur != sentinal) {
            prev = cur;
            if (v.compareTo(cur.getKey()) < 0) {
                cur = cur.getLc();
            } else if (v.compareTo(cur.getKey()) > 0) {
                cur = cur.getRc();
            } else {
                return cur.getKey();
            }
        }
        return prev.getKey();

    }

    /**
     *  get the height of this redblacktree
     * @return
     *  the height
     */
    public int height() {
        return height(root);
    }

    public int height(RedBlackNode t) {
        RedBlackNode temp = t;
        if (temp.getLc() == sentinal || temp.getRc() == sentinal) {
            return 0;
        }
        return Math.max(height(temp.getLc()), height(temp.getRc())) + 1;
    }

    /**
     *
     * @throws Exception
     */
    public void levelOrderTrversal() throws Exception {
        RedBlackNode temp = root;
        Queue q = new Queue();
        q.enQueue(temp);
        while (!q.isEmpty()) {
            RedBlackNode t = (RedBlackNode) q.deQueue();
            System.out.println(t);
            if (t.getLc() != sentinal) {
                q.enQueue(t.getLc());
            }

            if (t.getRc() != sentinal) {
                q.enQueue(t.getRc());
            }
        }
    }


    public static void main(String[] args) throws Exception {
        RedBlackTree rbt = new RedBlackTree();

        for (int j = 1; j <= 10; j++) {
            String key = "var" + j;
            BigInteger value = new BigInteger(String.valueOf(j));
            rbt.insert(key, value);
        }
        System.out.println("RBT in order");
        rbt.inOrderTraversal();
        System.out.println("RBT level order");
        rbt.levelOrderTrversal();

        if (rbt.contains("var3", new BigInteger("3"))) {
            System.out.println(rbt.get("var3"));
        } else {
            System.out.println("No 3 found");
        }
        System.out.println("The height is " + rbt.height());
    }

}

