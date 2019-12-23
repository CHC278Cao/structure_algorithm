public class Queue {
    private Object[] nodes;
    private int front;
    private int rear;
    private int count;
    private int capacity;

    public Queue() {
        capacity = 5;
        nodes = new Object[capacity];
    }

    /**
     * check if the queue is empty
     * @return
     *  return true if queue is empty
     */
    public boolean isEmpty() {
        if (count != 0) {
            return false;
        }
        return true;
    }

    /**
     * check if the queue is full
     * @return
     *  return true if it's full, otherwise return false
     */
    public boolean isFull() {
        if (count == capacity) {
            return true;
        }
        return false;
    }

    /**
     * removes and returns reference in front of queue
     * @return
     *  return the reference in front of queue
     */
    public Object deQueue() {
        if (isEmpty()) {
            return null;
        }
        Object temp = nodes[front];
        front = (front + 1) % capacity;
        count -= 1;
        return temp;

    }

    public void enQueue(Object x) {
        if (isEmpty()) {
            front = rear = 0;
        } else if (isFull()) {
            int newCapacity = capacity * 2;

            Object[] newNodes = new Object[newCapacity];
            int newFront, newRear, newCount;
            newFront = newRear = front;
            newCount = count;
            for (int i = count; i > 0; i--) {
                newNodes[newRear] = deQueue();
                newRear = (newRear + 1) % newCapacity;

            }
            nodes = newNodes;
            capacity = newCapacity;
            front = newFront;
            rear = newRear;
            count = newCount;
        } else {
            rear = (rear + 1) % capacity;
        }
        nodes[rear] = x;
        count += 1;

    }

    /**
     * get the value in the front of queue
     * @return
     *  return the value of the queue front
     */
    public Object getFront() {
        if (isEmpty()) {
            return null;
        } else {
            return nodes[front];
        }
    }

    @Override
    public String toString() {
        StringBuilder st = new StringBuilder();
        if (isEmpty()) {
            return st.toString();
        } else {
            int index = front;
            for (int i = count; i > 0; i--) {
                st.append(nodes[index]);
                st.append(" ");
                index = (index + 1) % capacity;
            }
            return st.toString();
        }
    }

    public static void main(String[] args) {
        Queue queue = new Queue();
        System.out.println("Starting the test!");
        System.out.printf("the capacity is %d, the size is %d, front is %d, rear is %d\n",
                queue.capacity, queue.count, queue.front, queue.rear);
        System.out.println("if this queue is empty " + queue.isEmpty());
        System.out.println("if this queue is full " + queue.isFull());
        queue.enQueue("this");
        queue.enQueue("is");
        queue.enQueue("a");
        queue.enQueue("test");
        queue.enQueue("!");
        System.out.println("----------------------------------------");
        System.out.println("After adding data");
        System.out.printf("the capacity is %d, the size is %d, front is %d, rear is %d\n",
                queue.capacity, queue.count, queue.front, queue.rear);
        System.out.println("if this queue is empty " + queue.isEmpty());
        System.out.println("if this queue is full " + queue.isFull());
        System.out.println("The front value is " + queue.getFront());
        System.out.println(queue);
        Object v = queue.deQueue();
        System.out.println(v);
        System.out.printf("the capacity is %d, the size is %d, front is %d, rear is %d\n",
                queue.capacity, queue.count, queue.front, queue.rear);
        System.out.println("The front value is " + queue.getFront());

        queue.enQueue("end");
        queue.enQueue("new line");
        System.out.println("----------------------------------------");

        System.out.printf("the capacity is %d, the size is %d, front is %d, rear is %d\n",
                queue.capacity, queue.count, queue.front, queue.rear);

        System.out.println("The front value is " + queue.getFront());
        System.out.println(queue);


    }


}
