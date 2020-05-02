package ko.maeng.base.java.etc.nextree.list;

public class Node {
    private Node next;
    private int data;

    /**
     *
     * @return the next
     */
    public Node getNext() {
        return next;
    }

    /**
     *
     * @param next the next to set
     */
    public void setNext(Node next){
        this.next = next;
    }

    /**
     *
     * @return the data
     */
    public int getData() {
        return data;
    }

    /**
     *
     * @param data the data to set
     */
    public void setData(int data) {
        this.data = data;
    }
}
