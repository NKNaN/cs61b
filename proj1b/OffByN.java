public class OffByN implements CharacterComparator {
    private final int N;

    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        return Math.abs(diff) == N;
    }

    public OffByN(int N) {
        this.N = N;
    }
}
