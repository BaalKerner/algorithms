import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int capacity = 1;
    private int size = 0;
    private int lastIndex = -1;

    public RandomizedQueue() {
        queue = (Item[]) new Object[capacity];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (capacity == lastIndex + 1) {
            resize(2 * size);
        }
        queue[++lastIndex] = item;
        size++;
    }

    public Item dequeue() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        int index = StdRandom.uniform(lastIndex + 1);

        while (queue[index] == null) {
            index = StdRandom.uniform(lastIndex + 1);
        }

        Item item = queue[index];
        queue[index] = null;
        --size;

        if (size > 0) {
            while (queue[lastIndex] == null) {
                --lastIndex;
            }

            if (size == capacity / 4) {
                resize(capacity / 2);
            }
        } else {
            lastIndex = -1;
        }

        return item;
    }

    public Item sample() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        int index = StdRandom.uniform(lastIndex + 1);

        while (queue[index] == null) {
            index = StdRandom.uniform(lastIndex + 1);
        }

        return queue[index];
    }

    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();

        rq.enqueue(438);
        rq.dequeue();
        rq.enqueue(67);
    }

    private void resize(int newCapacity)
    {
        Item[] copy = (Item[]) new Object[newCapacity];
        for (int i = 0, j = 0; i <= lastIndex; i++) {
            if (queue[i] != null) {
                copy[j++] = queue[i];
            }
        }
        queue = copy;

        capacity = newCapacity;
        lastIndex = size - 1;
    }

    private class ArrayIterator implements Iterator<Item> {
        private Item[] iteratorQueue;
        private int currentIndex = 0;

        private ArrayIterator() {
            iteratorQueue = (Item[]) new Object[size];
            for (int i = 0, j = 0; i <= lastIndex; i++) {
                if (queue[i] != null) {
                    iteratorQueue[j++] = queue[i];
                }
            }

            StdRandom.shuffle(iteratorQueue);
        }

        public boolean hasNext() {
            return currentIndex < iteratorQueue.length;
        }

        public Item next() {
            if (currentIndex >= iteratorQueue.length) {
                throw new NoSuchElementException();
            }

            return iteratorQueue[currentIndex++];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
