import sun.reflect.generics.tree.Tree;

import javax.swing.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private class node {
        public K key;
        public V value;
        public node left;
        public node right;
        public node parent;

        public node(K key, V value, node left, node right, node parent) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }
    private node root;
    private int size;

    public BSTMap() {
        root = null;
        size = 0;
    }

    private node put(K key, V value, node tree, node parent) {
        if (tree == null) {
            size++;
            return new node(key, value, null, null, parent);
        } else {
            int cmp = key.compareTo(tree.key);
            if (cmp < 0) {
                tree.left = put(key, value, tree.left, tree);
            } else if (cmp > 0) {
                tree.right = put(key, value, tree.right, tree);
            } else {
                tree.value = value;
            }
        }
        return tree;
    }

    @Override
    public void put(K key, V value) {
        root = put(key, value, root, root);
    }

    private V get(K key, node tree) {
        if (tree == null) {
            return null;
        }
        int cmp = key.compareTo(tree.key);
        if (cmp == 0) {
            return tree.value;
        } else if (cmp < 0) {
            return get(key, tree.left);
        } else {
            return get(key, tree.right);
        }
    }

    @Override
    public V get(K key) {
        return get(key, root);
    }


    private boolean containsKey(K key, node tree) {
        if (tree == null) {
            return false;
        }
        int cmp = key.compareTo(tree.key);
        if (cmp == 0) {
            return true;
        } else if (cmp < 0) {
            return containsKey(key, tree.left);
        } else {
            return containsKey(key, tree.right);
        }
    }
    @Override
    public boolean containsKey(K key) {
        return containsKey(key, root);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    private node getNode (K key) {
        node p1 = root;
        while (p1 != null) {
            int cmp = key.compareTo(p1.key);
            if (cmp == 0) {
                break;
            } else if (cmp < 0) {
                p1 = p1.left;
            } else {
                p1 = p1.right;
            }
        }
        return p1;
    }

    private void swap(node p1, node p2) {
        V vtmp = p1.value;
        K ktmp = p1.key;
        p1.value = p2.value;
        p1.key = p2.key;
        p2.value = vtmp;
        p2.key = ktmp;
    }

    private void removeNode(node t) {
        if (t.left == null && t.right == null) {
            if (t == t.parent.left) {
                t.parent.left = null;
            } else {
                t.parent.right = null;
            }
        } else if (t.left != null && t.right != null) {
            node p = t.right;
            while (p.left != null) {
                p = p.left;
            }
            swap(p, t);
            removeNode(p);
        } else if (t.left != null) {
            if (t == t.parent.left) {
                t.parent.left = t.left;
            } else {
                t.parent.right = t.left;
            }
            t.left.parent = t.parent;
        } else {
            if (t == t.parent.left) {
                t.parent.left = t.right;
            } else {
                t.parent.right = t.right;
            }
            t.right.parent = t.parent;
        }
    }

    @Override
    public V remove(K key) {
        if (!containsKey(key)) {
            return null;
        } else {
            node t = getNode(key);
            V ret = t.value;
            removeNode(t);
            return ret;
        }
    }

    @Override
    public V remove(K key, V value) {
        if (!containsKey(key)) {
            return null;
        } else {
            node t = getNode(key);
            if (t.value != value) {
                throw new RuntimeException("The pair with key: " + key + ", value: " + value + " does not exist.");
            } else {
                return remove(key);
            }
        }
    }

    @Override
    public Set<K> keySet() {
        Set<K> result = new HashSet<K>();
        for (K key : this) {
            result.add(key);
        }
        return result;
    }

    private class BSTIterator implements Iterator<K> {

        private node next;

        public BSTIterator(node first) {
            next = first;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public K next() {
            K ret = next.key;
            next = successor(next);
            return ret;
        }
    }

    private node successor(node tree) {
        if (tree == null) {
            return null;
        } else if (tree.right != null) {
            node p = tree.right;
            while (p.left != null) {
                p = p.left;
            }
            return p;
        } else {
            node p = tree.parent;
            node ch = tree;
            while (p != null && ch == p.right) {
                ch = p;
                p = p.parent;
            }
            return p;
        }
    }

    @Override
    public Iterator<K> iterator() {
        node p = root;
        while (p.left != null) {
            p = p.left;
        }
        return new BSTIterator(p);
    }

    private void printInOrder(node tree) {
        if (tree == null) {
            return;
        } else {
            printInOrder(tree.left);
            System.out.print(tree.key);
            System.out.print(' ');
            System.out.print(tree.value);
            System.out.print('\n');
            printInOrder(tree.right);
        }
    }

    public void printInOrder() {
        printInOrder(root);
    }
}
