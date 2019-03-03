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
            randomizeIndexes();
            currentIdx = 0;
        }

        private void randomizeIndexes() {
            int[] idxs = new int[size];
            randomIndexes = new int[size];
            for (int i = 0; i < size; i++) {
                idxs[i] = i;
            }

            Random random = new Random();
            StringBuilder indexes = new StringBuilder(";");
            int count = 0;
            while (true) {
                int var = random.nextInt(idxs.length);
                if (indexes.indexOf(Integer.toString(var)) < 0) {
                    indexes.append(Integer.toString(var) + ";");
                    randomIndexes[var] = idxs[count++];
                }
                if (count == size) {
                    break;
                }
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

}
