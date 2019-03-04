import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;


public class RandomizedQueue<Item> implements Iterable<Item> {
    private RandomizedElement first;
    private RandomizedElement last;
    private int size;

    private class RandomizedElement {
        public Item value;
        public RandomizedElement next;
        public RandomizedElement prev;

        public RandomizedElement(Item item) {
            value = item;
        }
    }

    // construct an empty randomized queue
    public RandomizedQueue() {
        first = null;
        last = null;
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return (first == null);
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException();

        RandomizedElement element = new RandomizedElement(item);
        if (first == null) {
            first = element;
            last = first;
        }
        else
        {
            last.next = element;
            element.prev = last;
            last = element;
        }
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (first == null)
            throw new NoSuchElementException();

        RandomizedElement element = sampleElement();

        if (element.prev != null) {
            element.prev.next = element.next;
            if (element.next == null) {
                last = element.prev;
            }
        }
        else {
            first = element.next;
        }

        if (first == null || last == null) {
            first = null;
            last = null;
        }

        size--;

        return element.value;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (first == null)
            throw new NoSuchElementException();

        return sampleElement().value;
    }

    private RandomizedElement sampleElement() {
        long rnd = Math.round(Math.random() * size) - 1;

        return getElementByIdx(rnd);
    }

    private RandomizedElement getElementByIdx(long idx) {
        RandomizedElement element = first;
        for (long i = 1; i <= idx; i++) {
            element = element.next;
        }

        return element;
    }


    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedListIterator();
    }

    private class RandomizedListIterator implements Iterator<Item> {
        private int[] randomIndexes;
        private int currentIdx;

        public RandomizedListIterator() {
            if (size > 0){
                randomizeIndexes();
            }
            currentIdx = 0;
        }

        private void randomizeIndexes() {
            //int[] idxs = new int[size];
            randomIndexes = new int[size];
            for (int i = 0; i < size; i++) {
                randomIndexes[i] = i;
            }

            Random random = new Random();
            for (int i = 0; i < size; i++) {
                int j = random.nextInt(i + 1);
                if (j >= size)
                    j = size - 1;
                int temp = randomIndexes[i];
                randomIndexes[i] = randomIndexes[j];
                randomIndexes[j] = temp;
            }
        }

        @Override
        public boolean hasNext() {
            return currentIdx < size;
        }

        @Override
        public Item next() {
            if (isEmpty())
                throw new NoSuchElementException();

            RandomizedElement element = getElementByIdx(randomIndexes[currentIdx]);
            currentIdx++;

            return element.value;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        int i;
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(4);
        rq.enqueue(5);
        rq.enqueue(6);
        rq.enqueue(7);
        rq.enqueue(8);
        rq.enqueue(9);
        rq.enqueue(10);
        rq.enqueue(11);
        rq.enqueue(12);
        rq.sample();

        System.out.println("Show left items: ");
        Iterator<Integer> iterrq = rq.iterator();
        i = 0;
        while (true) {
            System.out.println(iterrq.next());
            i++;
            if (!iterrq.hasNext())
                break;
        }
    }
}
