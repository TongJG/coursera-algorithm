import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Permutation {

    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("no args");
        }

        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s == null || s.length() == 0) {
                break;
            }

            randomizedQueue.enqueue(s);
        }

        Iterator<String> iterator = randomizedQueue.iterator();
        while (iterator.hasNext() && k-- > 0) {
            StdOut.println(iterator.next());
        }

    }
}
