package Vacc_Queue;

public class WaitQueue {

    private Node head = null;
    private Node tail = null;

    /**
     * Create a empty queue
     */
    public WaitQueue ( ) {
    } //constructor

    /**
     * This method insert the new patient to the queue base on their priority point
     * @param p Patient
     */
    public void insert ( Patient p ) {
        if (isEmpty()) {
            head = new Node(null,p,null);
            tail = head;
        } else {
            Node n = head;
            Node p1;
            if (p.getPoint()>n.item.getPoint()) { //if have highest priority
                head = new Node(null,p,null);
                head.next = n;
                n.previous = head;
            } else { //traverse to the right pos
                while ((n.next!=null)&&(p.getPoint()<=n.next.item.getPoint())) {
                    n = n.next;
                }
                //place behind the n node
                p1 = new Node(n, p, n.next);
                n.next = p1;
                if (p1.next!=null) {
                    p1.next.previous = p1;
                }
            }
        }
    }

    /**
     * This method remove the first patient of the queue
     * @return Patient
     */
    public Patient removeMax ( ) {
        if (isEmpty()) {
            return null;
        } else {
            Patient cp;
            cp = head.item;
            Node t = this.head;
            this.head = this.head.next;
            t = null;
            if (this.head!=null) {
                this.head.previous = null;
            }
            return cp;
        }
    }

    /**
     * This method indicates if the queue is empty or not
     * @return true if empty
     */
    public boolean isEmpty ( ) {
        return (head == null) && (tail == null);
    }
}
