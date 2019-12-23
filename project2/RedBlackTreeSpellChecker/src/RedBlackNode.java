public class RedBlackNode {
    public static final int BLACK = 0;
    public static final int RED = 1;

    private int color;
    private String data;
    private RedBlackNode p;
    private RedBlackNode lc;
    private RedBlackNode rc;

    public RedBlackNode(String data, int color, RedBlackNode p,
                        RedBlackNode lc, RedBlackNode rc) {
        this.data = data;
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

    public String getData() {
        return data;
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

    public void setData(String data) {
        this.data = data;
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
        String p = getP().getData() == null ? "-1" : getP().getData();
        String lc = getLc().getData() == null ? "-1" : getLc().getData();
        String rc = getRc().getData() == null ? "-1" : getRc().getData();
        st.append("[data = " + getData() + ":Color = " + color
                    + ":Parent = " + p + ":LC = " + lc
                    + " :RC = " + rc + "]");
        return st.toString();
    }

}
