/** This class outputs all palindromes in the words file in the current directory. */
public class PalindromeFinder {
    public static void main(String[] args) {
        int minLength = 4;
        In in = new In("E:/CSSelfLearning/CS61B/github_cs61b/cs61b/library-sp19/data/words.txt");
        Palindrome palindrome = new Palindrome();
        OffByN obn = new OffByN(5);

        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength && palindrome.isPalindrome(word, obn)) {
                System.out.println(word);
            }
        }
    }
}
/*Uncomment this class once you've written isPalindrome. */