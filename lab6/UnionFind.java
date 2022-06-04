public class UnionFind {

    private int[] parents;
    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex < 0 || vertex > parents.length - 1) {
            throw new IllegalArgumentException("Index: " + vertex + " is not a valid index.");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        validate(v1);
        int root = find(v1);
        return -parents[root];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        validate(v1);
        return parents[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        validate(v1);
        validate(v2);
        int r1 = find(v1);
        int r2 = find(v2);
        return r1 == r2;
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        validate(v1);
        validate(v2);
        int r1 = find(v1);
        int r2 = find(v2);
        if (r1 == r2) {
            return;
        }
        int s1 = sizeOf(v1);
        int s2 = sizeOf(v2);
        if (s2 >= s1) {
            parents[r1] = r2;
            parents[r2] = -s1 - s2;
        } else {
            parents[r2] = r1;
            parents[r1] = -s1 - s2;
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        validate(vertex);
        while (parent(vertex) >= 0) {
            vertex = parent(vertex);
        }
        return vertex;
    }

/*    public static void main(String[] args) {
        UnionFind u = new UnionFind(10);
        System.out.println(u.find(8));
        System.out.println(u.find(0));
        //System.out.println(u.find(10));
        u.union(8,0);
        System.out.println(u.find(8));
        u.union(8,9);
        u.union(7,5);
        u.union(9,5);
        System.out.println(u.find(5));
        System.out.println(u.find(0));
    }*/
}
