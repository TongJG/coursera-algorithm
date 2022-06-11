import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueueByList<Item> implements Iterable<Item> {


    private Node<Item> head;
    private int count;

    // construct an empty deque
    public RandomizedQueueByList() {

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
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item cannot be null");
        }

        if (head == null) {
            head = new Node<>(item);
        }
        else {
            Node<Item> n = new Node<>(item);
            n.next = head;
            head = n;
        }
        count++;
    }


    // remove and return the item from the front
    public Item dequeue() {
        if (size() == 0) {
            throw new NoSuchElementException("remove from empty deque");
        }

        Item r = head.item;
        head = head.next;

        count--;
        return r;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new RandomDequeIterator();
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("sample from empty deque");
        }
        Node<Item> tmp = head;

        int k = StdRandom.uniform(count);
        while (k-- > 0) {
            tmp = tmp.next;
        }

        return tmp.item;
    }

    private class RandomDequeIterator implements Iterator<Item> {
        Item[] arr;
        int index;

        public RandomDequeIterator() {
            Node<Item> tmp = head;
            arr = (Item[]) new Object[count];
            int i = 0;
            while (tmp != null) {
                arr[i++] = tmp.item;
                tmp = tmp.next;
            }

            StdRandom.shuffle(arr);
        }

        public boolean hasNext() {
            return index != arr.length;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("has no next");
            }
            return arr[index++];
        }
    }


    private class Node<Item> {
        private Node<Item> next;
        private Item item;

        public Node(Item item) {
            this.item = item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueueByList<Integer> randomizedQueueByList = new RandomizedQueueByList<>();
        for (int i = 0; i < 10; i++) {
            randomizedQueueByList.enqueue(i);
        }

        Iterator<Integer> iterator = randomizedQueueByList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("_____");

        Iterator<Integer> iterator2 = randomizedQueueByList.iterator();
        while (iterator2.hasNext()) {
            System.out.println(iterator2.next());
        }
    }
}
