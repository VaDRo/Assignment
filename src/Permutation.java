import edu.princeton.cs.algs4.In;

import java.util.Iterator;

public class Permutation {

    // unit testing (optional)
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        int i;
        In in = new In(args[1]);      // input file

        /*
        Deque<String> deque = new Deque<String>();
        // RandomizedQueue<String> queue = new RandomizedQueue<String>();
        String element;
        while (!in.isEmpty()) {
            element = in.readString();
            deque.addLast(element);
            //queue.enqueue(element);
        }

        for (i = 0; i < k; i++) {
            System.out.println(deque.removeFirst());
        }
        */
        /*System.out.println("---------------------------------");
        String element;
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        while (!in.isEmpty()) {
            element = in.readString();
            queue.enqueue(element);
        }

        Iterator<String> iter = queue.iterator();
        i = 0;
        while (iter.hasNext() && i < k) {
            System.out.println(iter.next());
            i++;
        }*/

        /*Deque<Integer> deqe = new Deque<Integer>();
        deqe.addFirst(1);
        deqe.addFirst(2);
        deqe.addFirst(3);
        deqe.addFirst(4);
        i = 0;
        Iterator<Integer> iter = deqe.iterator();
        while (true) {
            System.out.println(iter.next());
            i++;
            if (!iter.hasNext())
                break;
        }*/
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        rq.enqueue(179);
        rq.sample();
    }
}
