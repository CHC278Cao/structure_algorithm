import java.math.BigInteger;

public class RedBlackNode {
    public static final int BLACK = 0;
    public static final int RED = 1;

    private int color;
    private String key;
    private BigInteger value;
    private RedBlackNode p;
    private RedBlackNode lc;
    private RedBlackNode rc;

    public RedBlackNode(String key, BigInteger value, int color, RedBlackNode p,
                        RedBlackNode lc, RedBlackNode rc) {
        this.key = key;
        this.value = value;
        this.color = color;
        this.p = p;
        this.lc = lc;
        this.rc = rc;
    }

    /**
     * get the color of node
     * @return
     *  return 1 if color is red, 0 if black
     */
    public int getColor() {
        return color;
    }

    public String getKey() {
        return key;
    }

    public BigInteger getValue() {
        return value;
    }

    public RedBlackNode getLc() {
        return lc;
    }

    public RedBlackNode getRc() {
        return rc;
    }

    public RedBlackNode getP() {
        return p;
    }


    public void setColor(int color) {
        this.color = color;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(BigInteger value) {
        this.value = value;
    }

    public void setLc(RedBlackNode lc) {
        this.lc = lc;
    }

    public void setRc(RedBlackNode rc) {
        this.rc = rc;
    }

    public void setP(RedBlackNode p) {
        this.p = p;
    }

    @Override
    public String toString() {
        StringBuilder st = new StringBuilder();
        String color = getColor() == 0 ? "Black" : "Red";
        String p = getP().getKey() == null ? "-1" : getP().getKey();
        String lc = getLc().getKey() == null ? "-1" : getLc().getKey();
        String rc = getRc().getKey() == null ? "-1" : getRc().getKey();
        st.append("[Key = " + getKey() + ":Value = " + getValue()
                + ":Color = " + color + ":Parent = " + p
                + ":LC = " + lc + " :RC = " + rc + "]");
        return st.toString();
    }

}
