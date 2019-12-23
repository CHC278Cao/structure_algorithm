import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.StringTokenizer;


public class ReversePolishNotation {
    private DynamicStack stack;
    private RedBlackTree rbt;

    public ReversePolishNotation() {
        stack = new DynamicStack();
        rbt = new RedBlackTree();
    }

    public void equal() throws Exception {
        Object v1;
        Object v2;

        v1 = getData();

        v2 = stack.popData();
        if (v2 == null) {
            throw new Exception("error: stack underflow exception");
        }

        if (v2 instanceof BigInteger) {
            throw new Exception("error: " + v2 + " not an lvalue");
        } else {
            rbt.insert((String)v2, (BigInteger)v1);
            stack.pushData((String)v2);
        }

    }

    public void add() throws Exception {
        Object v1;
        Object v2;
        BigInteger temp = new BigInteger("0");

        v1 = getData();

        v2 = getData();

        temp = temp.add((BigInteger)v1).add((BigInteger)v2);
        stack.pushData(temp);

    }

    public void minus() throws Exception {
        Object v1;
        Object v2;
        BigInteger temp = new BigInteger("0");

        v1 = getData();

        v2 = getData();

        temp = temp.add((BigInteger)v2).subtract((BigInteger)v1);
        stack.pushData(temp);

    }

    public void multiply() throws Exception {
        Object v1;
        Object v2;
        BigInteger temp = new BigInteger("1");

        v1 = getData();

        v2 = getData();

        temp = temp.multiply((BigInteger)v1).multiply((BigInteger)v2);
        stack.pushData(temp);

    }

    public void divide() throws Exception {
        Object v1;
        Object v2;
        BigInteger temp = new BigInteger("1");

        v1 = getData();

        v2 = getData();

        temp = temp.multiply((BigInteger)v2).divide((BigInteger)v1);
        stack.pushData(temp);

    }

    public void mode() throws Exception {
        Object v1;
        Object v2;
        BigInteger temp = new BigInteger("1");

        v1 = getData();

        v2 = getData();

        temp = temp.multiply((BigInteger)v2).mod((BigInteger)v1);
        stack.pushData(temp);

    }

    public void unminus() throws Exception {
        Object v1;
        BigInteger temp = new BigInteger("1");

        v1 = getData();

        temp = temp.multiply((BigInteger)v1).negate();
        stack.pushData(temp);

    }

    public void powerMode() throws Exception {
        Object v1;
        Object v2;
        Object v3;
        BigInteger temp = new BigInteger("1");

        v1 = getData();

        v2 = getData();

        v3 = getData();

        temp = temp.multiply((BigInteger)v3);
        temp = temp.modPow((BigInteger)v2, (BigInteger)v1);
        stack.pushData(temp);

    }

    public void pushData(Object x) {
        stack.pushData(x);
    }

    public void pushString(Object x) {
        stack.pushData(x);
    }



    public Object getData() throws Exception {
        Object n1, v1 = null;

        n1 = stack.popData();
        if (n1 == null) {
            throw new Exception("error: stack underflow exception");
        }

        if (n1 instanceof String) {
            try {
                v1 = rbt.get((String) n1);
            } catch (Exception e) {
                throw new Exception("error: no variable " + n1);
            }
        } else {
            v1 = n1;
        }

        return v1;
    }

    public BigInteger getTop() throws Exception {
        if (stack.isEmpty()) {
            return null;
        }

        Object n1, v1;
        n1 = stack.popData();
        if (n1 instanceof String) {
            v1 = rbt.get((String)n1);
        } else {
            v1 = n1;
        }
        return (BigInteger)v1;

    }

}
