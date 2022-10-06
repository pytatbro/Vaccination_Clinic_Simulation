package Vacc_Queue;

public class Node {

    Patient  item;   // Patient
    Node next;  // next node
    Node previous; // previous node

    /**
     * This class defines a node to store patients' information
     * @param n1 link to previous node
     * @param p patient's information
     * @param n2 link to next node
     */
    Node ( Node n1, Patient p, Node n2 ) {

        previous = n1;
        item = p;
        next = n2;

    };  // constructor

}
