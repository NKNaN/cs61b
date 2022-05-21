public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private int RFACTOR = 2;

    public ArrayDeque() {
        this.items = (T[]) new Object[8];
        this.size = 0;
        this.nextFirst = 0;
        this.nextLast = 1;
    }

    public ArrayDeque(ArrayDeque other){
        this.size = 0;
        this.nextFirst = 0;
        this.nextLast = 1;
        this.items = (T[]) new Object[8];
        for(int i=0; i<other.size(); i++){
            this.addLast((T)other.get(i));
        }
    }

    private void resizing(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        int first = nextFirst;
        if (first == items.length - 1){
            first = 0;
        }else{
            first += 1;
        }
        int last = nextLast;
        if (last == 0){
            last = items.length - 1;
        }else{
            last -= 1;
        }
        int newLast = 1;
        while(first != last){
            newItems[newLast] = items[first];
            if(first == items.length-1){
                first = 0;
            }else{
                first += 1;
            }
            newLast += 1;
        }
        newItems[newLast] = items[last];
        newLast += 1;
        items = newItems;
        nextFirst = 0;
        nextLast = newLast;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resizing(size*RFACTOR);
        }
        items[nextFirst] = item;
        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst -= 1;
        }
        size += 1;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resizing(size*RFACTOR);
        }
        items[nextLast] = item;
        if (nextLast == items.length - 1) {
            nextLast = 0;
        } else {
            nextLast += 1;
        }
        size += 1;
    }

    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        if(items.length >= 16 && ((double)size/items.length) < 0.25){
            resizing(items.length/RFACTOR);
        }
        T retItem;
        if (nextFirst == items.length - 1){
            retItem = items[nextFirst = 0];
        }else{
            retItem = items[++nextFirst];
        }
        items[nextFirst] = null;
        size -= 1;
        return retItem;
    }

    public T removeLast() {
        if(isEmpty()){
            return null;
        }
        if(items.length >= 16 && ((double)size/items.length) < 0.25){
            resizing(items.length/RFACTOR);
        }
        T retItem;
        if (nextLast == 0){
            retItem = items[nextLast = items.length - 1];
        }else{
            retItem = items[--nextLast];
        }
        items[nextLast] = null;
        size -= 1;
        return retItem;
    }

    public void printDeque(){
        int first = nextFirst;
        if (first == items.length - 1){
            first = 0;
        }else{
            first += 1;
        }
        int last = nextLast;
        if (last == 0){
            last = items.length - 1;
        }else{
            last -= 1;
        }
        while(first != last){
            System.out.print(items[first]);
            System.out.print(' ');
            if(first == items.length-1){
                first = 0;
            }else{
                first += 1;
            }
        }
        System.out.print(items[last]);
        System.out.println();
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public T get(int index){
        int target = nextFirst + 1 + index;
        if(target > items.length - 1){
            target -= items.length;
            if(target >= nextLast) return null;
        }
        return items[target];
    }

/*    public static void main(String[] args){
        ArrayDeque<Character> a = new ArrayDeque<>();
        a.addLast('a');
        a.addLast('b');
        a.addFirst('c');
        a.addLast('d');
        a.addLast('e');
        a.addFirst('f');
        a.printDeque();

        System.out.println(a.get(0));
        System.out.println(a.get(1));
        System.out.println(a.get(2));
        System.out.println(a.get(3));
        System.out.println(a.get(4));
        System.out.println(a.get(5));
        System.out.println(a.get(6));

        System.out.println(a.removeFirst());
        System.out.println(a.removeFirst());
        a.printDeque();

        a.addFirst('g');
        a.addFirst('h');
        a.addLast('f');
        a.addLast('c');
        a.addFirst('i');
        a.printDeque();

        ArrayDeque<Character> b = new ArrayDeque<>(a);
        b.printDeque();

        System.out.println(a.get(0));
        System.out.println(a.get(1));
        System.out.println(a.get(2));
        System.out.println(a.get(3));
        System.out.println(a.get(4));
        System.out.println(a.get(5));
        System.out.println(a.get(6));
        System.out.println(a.get(7));
        System.out.println(a.get(8));

        System.out.println(a.removeLast());
        System.out.println(a.removeLast());
        System.out.println(a.removeLast());
        System.out.println(a.removeLast());
        System.out.println(a.removeLast());
        System.out.println(a.removeLast());
        System.out.println(a.removeLast());
        a.printDeque();
    }*/
}
