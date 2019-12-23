import java.math.BigInteger;

public class DynamicStack {
    private Object[] stack;
    private int front;
    private int capacity = 6;

    public DynamicStack() {
        stack = new Object[capacity];
        front = -1;
    }

    public void pushData(Object x) {
        if (isFull()) {
            capacity = 2 * capacity;
            Object[] newStack = new Object[capacity];
            for (int i = 0; i <= front; i++) {
                newStack[i] = stack[i];
            }
            stack = newStack;
        }
        front = front + 1;
        stack[front] = x;
    }

    public Object popData() {
        if (isEmpty()) {
            return null;
        }
        Object res = stack[front];
        front = front - 1;
        return res;
    }

    public boolean isEmpty() {
        if (front == -1) {
            return true;
        }
        return false;
    }

    public boolean isFull() {
        if (front == (capacity-1)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DynamicStack:\n");
        sb.append("The capacity is " + this.capacity + "\n");
        sb.append("The front is " + this.front + "\n");
        sb.append("Data:");
        for (int i = front; i >= 0; i--) {
            sb.append(" ");
            sb.append(stack[i]);
        }
        sb.append(".\n");
        return sb.toString();
    }

    public static void main(String[] args) {
        DynamicStack stack = new DynamicStack();
        for (int i = 0; i <= 1000; i++) {
            stack.pushData(i);
        }
        System.out.println(stack);
        for (int i = 1000; i >= 950; i--) {
            System.out.println(stack.popData());
        }
        System.out.println(stack);
    }
}

