import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> head;
    private Node<Item> tail;
    private int count;

    // construct an empty deque
    public Deque() {

    }

    // is the deque empty?
    public boolean isEmpty() {
        return head == null;
    }

    // return the number of items on the deque
    public int size() {
        return count;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item cannot be null");
        }

        if (head == null) {
            head = new Node<>(item);
            tail = head;
        } else {
            Node<Item> n = new Node<>(item);
            n.next = head;
            head.prev = n;
            head = n;
        }
        count++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item cannot be null");
        }

        if (tail == null) {
            tail = new Node<>(item);
            head = tail;
        } else {
            Node<Item> n = new Node<>(item);
            tail.next = n;
            n.prev = tail;
            tail = n;
        }
        count++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size() == 0) {
            throw new NoSuchElementException("remove from empty deque");
        }

        Item r = head.item;
        if (size() == 1) {
            head = null;
            tail = null;
        }
        else {
            head = head.next;
            // for gc
            head.prev = null;
        }

        count--;
        return r;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size() == 0) {
            throw new NoSuchElementException("remove from empty deque");
        }

        Item r = tail.item;
        if (size() == 1) {
            head = null;
            tail = null;
        }
        else {
            tail = tail.prev;
            // for gc
            tail.next = null;
        }

        count--;

        return r;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // public Item remove() {
    //     throw new UnsupportedOperationException("unsupported operation");
    // }

    private class DequeIterator implements Iterator<Item> {
        Node<Item> tmp = head;

        public boolean hasNext() {
            return tmp != null;
        }

        public Item next() {
            if (tmp == null) {
                throw new NoSuchElementException("has no next");
            }
            Item r = tmp.item;
            tmp = tmp.next;
            return r;
        }
    }


    private class Node<Item> {
        private Node<Item> next;
        private Node<Item> prev;
        private Item item;

        public Node(Item item) {
            this.item = item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        for (int i = 0; i < 10; i++) {
            deque.addFirst(i);
            System.out.println(deque.size());
        }

        for (int i = 0; i < 3; i++) {
            deque.removeFirst();
            System.out.println(deque.size());
        }

        for (int i = 0; i < 3; i++) {
            deque.removeLast();
            System.out.println(deque.size());
        }

        Iterator<Integer> iterator = deque.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
