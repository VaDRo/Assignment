import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {

    private DequeElement first;
    private DequeElement last;
    private int size;

    private class DequeElement {
        public Item value;
        public DequeElement next;
        public DequeElement prev;

        public DequeElement(Item item) {
            value = item;
        }
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return (size < 1);
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        DequeElement element = new DequeElement(item);
        if (last == null) {
            last = element;
            first = element;
        }
        else {
            element.next = first;
            first.prev = element;
            first = element;
        }
        size++;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        DequeElement element = new DequeElement(item);
        if (last == null) {
            last = element;
            first = element;
        }
        else {
            last.next = element;
            element.prev = last;
            last = element;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (first == null)
            throw new NoSuchElementException();

        DequeElement ret = first;
        first = first.next;
        if (first == null)
            last = null;
        else
            first.prev = null;

        size--;

        return ret.value;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (last == null)
            throw new NoSuchElementException();

        DequeElement ret = last;
        last = last.prev;
        if (last == null)
            first = null;
        else
            last.next = null;

        size--;

        return ret.value;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeListIterator();
    }


    private class DequeListIterator implements Iterator<Item> {
        private DequeElement current;

        public DequeListIterator() {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (current == null)
                throw new NoSuchElementException();
            Item value = current.value;
            current = current.next;
            return value;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}