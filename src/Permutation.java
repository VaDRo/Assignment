import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;

public class Permutation {

    // unit testing (optional)
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        int i;

        /*
        Deque<String> deque = new Deque<String>();
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
        String element;
        RandomizedQueue<String> queue = new RandomizedQueue<String>();

        if (args.length > 2) {
            In in = new In(args[2]);      // input file

            while (!in.isEmpty()) {
                element = in.readString();
                queue.enqueue(element);
            }
        }
        else{
            while (!StdIn.isEmpty()) {
                element = StdIn.readString();
                //System.out.println(element);
                queue.enqueue(element);
            }
        }
        //System.out.println("Finish load");

        Iterator<String> iter = queue.iterator();
        i = 0;
        while (iter.hasNext() && i < k) {
            System.out.println(iter.next());
            i++;
        }

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
        }
        */

    }
}
