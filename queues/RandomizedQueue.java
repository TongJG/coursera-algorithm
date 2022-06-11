import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] arr;
    private int index;

    // construct an empty randomized queue
    public RandomizedQueue() {
        arr = (Item[]) new Object[32];
        index = -1;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return index == -1;
    }

    // return the number of items on the randomized queue
    public int size() {
        return index + 1;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("1");
        }
        arr[++index] = item;
        if (index == arr.length - 1) {
            // bug 一定要考虑resize的大小
            resize(2 * arr.length);
        }
    }

    private void resize(int newSize) {
        // newSize = Math.max(newSize, 32);
        Item[] newArr = (Item[]) new Object[newSize];
        for (int i = 0; i < index + 1; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("1");
        }
        int i = StdRandom.uniform(index + 1);
        Item r = arr[i];
        arr[i] = arr[index];
        arr[index] = null;
        index--;

        if (size() <= arr.length / 4) {
            resize(arr.length / 2);
        }
        return r;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("1");
        }
        return arr[StdRandom.uniform(index + 1)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {

        private Item[] tmp;
        private int index;

        public RandomIterator() {
            tmp = (Item[]) new Object[size()];
            for (int i = 0; i < RandomizedQueue.this.index + 1; i++) {
                tmp[i] = arr[i];
            }
            StdRandom.shuffle(tmp);
            index = 0;
        }

        public boolean hasNext() {
            return index < tmp.length;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("1");
            }

            return tmp[index++];
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        for (int i = 0; i < 100; i++) {
            randomizedQueue.enqueue(i);
        }

        for (int i = 0; i < 100; i++) {
            System.out.println(randomizedQueue.dequeue());
        }

        // System.out.println(randomizedQueue.dequeue());
        System.out.println(randomizedQueue.size());
        for (int i = 0; i < 100; i++) {
            randomizedQueue.enqueue(i);
        }
    }
}
