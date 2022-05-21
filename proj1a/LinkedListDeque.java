public class LinkedListDeque<T> {
    private static class Node<T> {
        private T item;
        private Node<T> next;
        private Node<T> prev;
        private Node(T item, Node<T> next, Node<T> prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }
    private Node<T> sentinel;
    private int size;
    public LinkedListDeque() {
        sentinel = new Node<>(null, null, null);
        size = 0;
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    /*public LinkedListDeque(LinkedListDeque other) {
        this.size = 0;
        this.sentinel = new Node<>(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        int l = other.size();
        for (int i = 0; i < l; i++) {
            this.addLast((T) other.get(i));
        }
    }*/

    public void addFirst(T item) {
        Node<T> newNode = new Node<>(item, sentinel.next, sentinel);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size += 1;
    }

    public void addLast(T item) {
        Node<T> newNode = new Node<>(item, sentinel, sentinel.prev);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size += 1;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T x = sentinel.next.item;
        size -= 1;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        return x;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T x = sentinel.prev.item;
        size -= 1;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        return x;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        Node<T> ptr = sentinel;
        int count = 0;
        while (ptr.next != ptr) {
            ptr = ptr.next;
            if (count == index) {
                break;
            } else {
                count += 1;
            }
        }
        return ptr.item;
    }

    private T getRecursive(Node<T> start, int distance) {
        if (distance == 0) {
            return start.item;
        }
        return getRecursive(start.next, distance - 1);
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursive(sentinel.next, index);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node<T> ptr = sentinel;
        while (ptr.next != sentinel) {
            ptr = ptr.next;
            System.out.print(ptr.item);
            System.out.print(' ');
        }
        System.out.println();
    }

/*    public static void main(String[] args){
        LinkedListDeque<Integer> a = new LinkedListDeque<>();
        a.addFirst(45);
        a.addFirst(63);
        System.out.println(a.removeFirst());
        a.addLast(17);
        a.addLast(3000);
        System.out.println(a.get(0));
        LinkedListDeque<Integer> b = new LinkedListDeque<>(a);
        a.printDeque();
        b.printDeque();
        System.out.println(a.get(1));
        System.out.println(a.get(2));
        System.out.println(a.getRecursive(0));
        System.out.println(a.get(3));
        System.out.println(a.removeLast());
        System.out.println(a.removeFirst());
        System.out.println(a.removeFirst());
        System.out.println(a.removeFirst());
        a.printDeque();
        b.printDeque();

    }*/
}
