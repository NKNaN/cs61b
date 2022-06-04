package es.datastructur.synthesizer;
import com.sun.deploy.ui.AboutDialog;

import java.util.Iterator;

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */

    @Override
    public int capacity() {
        return rb.length;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }

    @Override
    public void enqueue(T x) {
        if (fillCount == rb.length) {
            throw new RuntimeException("Ring buffer overflow");
        } else {
            rb[last] = x;
            if (last == rb.length - 1) {
                last = 0;
            } else {
                last++;
            }
            fillCount++;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if (fillCount == 0) {
            throw new RuntimeException("Ring buffer underflow");
        } else {
            T retItem = rb[first];
            rb[first] = null;
            if (first == rb.length - 1) {
                first = 0;
            } else {
                first++;
            }
            fillCount--;
            return retItem;
        }
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        if (fillCount == 0) {
            throw new RuntimeException("Ring buffer underflow");
        } else {
            return rb[first];
        }
    }

    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }

    private class ArrayRingBufferIterator implements Iterator<T> {
        private int next = first;
        @Override
        public boolean hasNext() {
            if (next >= first + rb.length) {
                return false;
            } else {
                if (next >= rb.length) {
                    return rb[next - rb.length] != null;
                } else {
                    return rb[next] != null;
                }
            }
        }
        @Override
        public T next() {
            T retItem = null;
            if (next >= rb.length) {
                retItem = rb[next - rb.length];
            } else {
                retItem = rb[next];
            }
            next++;
            return retItem;
        }
    }

    private boolean contains(T item) {
        int index = 0;
        for (int i = 0; i < fillCount; i++) {
            if (first + i >= rb.length) {
                index = first + i - rb.length;
            } else {
                index = first + i;
            }
            if (rb[index] == item) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }
        ArrayRingBuffer<T> other = (ArrayRingBuffer<T>) o;
        if (fillCount != other.fillCount()) {
            return false;
        }
        for (T i: other) {
            if (!this.contains(i)) {
                return false;
            }
        }
        for (T i: this) {
            if (!other.contains(i)) {
                return false;
            }
        }
        return true;
    }

//    public static void main(String[] args) {
//        BoundedQueue<Integer> a = new ArrayRingBuffer<>(7);
//        System.out.println(a.isEmpty());
//        System.out.println(a.isFull());
//        a.enqueue(1);
//        a.enqueue(2);
//        a.enqueue(3);
//        a.dequeue();
//        a.dequeue();
//        a.enqueue(4);
//        a.enqueue(5);
//        a.enqueue(6);
////        a.enqueue(7);
////        a.enqueue(8);
////        a.enqueue(9);
////        System.out.println(a.isEmpty());
////        System.out.println(a.isFull());
////        for (int i: a) {
////            System.out.println(i);
////        }
//        BoundedQueue<Integer> b = new ArrayRingBuffer<>(5);
//        b.enqueue(5);
//        b.enqueue(6);
//        b.enqueue(4);
//        b.enqueue(3);
//        System.out.println(a.equals(b));
//    }
}
