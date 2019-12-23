import org.omg.PortableServer.LIFESPAN_POLICY_ID;

public class LinkedNode {

    private Object key;
    private Object value;
    private LinkedNode next;

    public LinkedNode(Object key, Object value, LinkedNode next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public void addNodeAfter(Object itemkey, Object itemvalue) {
        next = new LinkedNode(itemkey, itemvalue, next);
    }

    public Object getKey() {
        return this.key;
    }

    public Object getValue() {
        return this.value;
    }

    public LinkedNode getNext() {
        return this.next;
    }

    public LinkedNode getLastElement(LinkedNode head) {
        LinkedNode cursor = head;
        while (cursor.getNext() != null) {
            cursor = cursor.getNext();
        }
        return cursor;
    }

    public int listLength(LinkedNode head) {
        LinkedNode cursor = head;
        int ans = 0;

        while (cursor != null) {
            ans += 1;
            cursor = cursor.next;
        }
        return ans;
    }

    


}
