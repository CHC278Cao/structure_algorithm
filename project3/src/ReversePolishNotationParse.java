import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class ReversePolishNotationParse {

    private static void calculatorOp(ReversePolishNotation rpn, String token)
            throws Exception {
        if (token.equals("=")) {
            rpn.equal();
            return;
        }

        if (token.equals("+")) {
            rpn.add();
            return;
        }

        if (token.equals("-")) {
            rpn.minus();
            return;
        }

        if (token.equals("*")) {
            rpn.multiply();
            return;
        }

        if (token.equals("/")) {
            rpn.divide();
            return;
        }

        if (token.equals("%")) {
            rpn.mode();
            return;
        }

        if (token.equals("~")) {
            rpn.unminus();
            return;
        }

        if (token.equals("^")) {
            rpn.powerMode();
            return;
        }

        rpn.pushString(token);

    }




    public static void main(String[] args) throws Exception {
        ReversePolishNotation rpn = new ReversePolishNotation();
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String command;
        while (true) {
            command = input.readLine();
//            command = "a b +";
            if (command.equals("")) {
                System.out.println("terminating");
                input.close();
                break;
            }
            StringTokenizer st = new StringTokenizer(command, " ");

            while (st.hasMoreTokens()) {
                String token = st.nextToken();
                try {
                    BigInteger temp = new BigInteger(token);
                    rpn.pushData(temp);
                } catch (NumberFormatException n) {
                    calculatorOp(rpn, token);
                }

            }

            System.out.println(rpn.getTop());

        }


    }
}
