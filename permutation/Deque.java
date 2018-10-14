/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first = null;
    private Node last = null;
    private int size = 0;

    private class Node {
        public Item item;
        public Node next;
        public Node prev;
    }

    public Deque() {

    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        first.prev = null;

        if (oldFirst == null) {
            last = first;
        } else {
            oldFirst.prev = first;
        }

        ++size;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = oldLast;

        if (oldLast == null) {
            first = last;
        } else {
            oldLast.next = last;
        }

        ++size;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Item item = first.item;
        first = first.next;

        if (first == null) {
            last = null;
        } else {
            first.prev = null;
        }

        --size;

        return item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Item item = last.item;
        last = last.prev;

        if (last == null) {
            first = null;
        } else {
            last.next = null;
        }

        --size;

        return item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    public static void main(String[] args) {
        StdOut.println("Creating empty deque");

        Deque<String> deque = new Deque<String>();

        StdOut.println("Deque should be empty: isEmpty == " + deque.isEmpty());
        StdOut.println("Deque size should be equal 0: size == " + deque.size());

        StdOut.println("\nAdding first item");
        deque.addFirst("first");

        StdOut.println("Deque should not be empty: isEmpty == " + deque.isEmpty());
        StdOut.println("Deque size should be equal 1: size == " + deque.size());

        StdOut.println("\nAdding last item");
        deque.addLast("last");

        StdOut.println("Deque should not be empty: isEmpty == " + deque.isEmpty());
        StdOut.println("Deque size should be equal 2: size == " + deque.size());

        StdOut.println("\nLooping deque");

        for (String s: deque) {
            StdOut.println(s);
        }
        
        StdOut.println("\nRemoving first item");
        String first = deque.removeFirst();

        StdOut.println("Deque should not be empty: isEmpty == " + deque.isEmpty());
        StdOut.println("Deque size should be equal 1: size == " + deque.size());
        StdOut.println("first should be equal 'first' == " + first);

        StdOut.println("\nRemoving last item");
        String last = deque.removeLast();

        StdOut.println("Deque should be empty: isEmpty == " + deque.isEmpty());
        StdOut.println("Deque size should be equal 0: size == " + deque.size());
        StdOut.println("first should be equal 'last' == " + last);

        StdOut.println("\nTry removing when deque is empty");
        try {
            String nope = deque.removeFirst();
        } catch (NoSuchElementException error) {
            StdOut.println("Should throw NoSuchElementException " + error);
        }
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (current == null) {
                throw new NoSuchElementException();
            }

            Item item = current.item;
            current = current.next;

            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
