public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> result = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            result.addLast(word.charAt(i));
        }
        return result;
    }

    private boolean isPalindrome(Deque<Character> deque) {
        if (deque.isEmpty()) {
            return true;
        } else if (deque.size() == 1) {
            return true;
        } else {
            Character start = deque.removeFirst();
            Character end = deque.removeLast();
            if (start != end) {
                return false;
            } else {
                return isPalindrome(deque);
            }
        }
    }

    public boolean isPalindrome(String word) {
        Deque<Character> d = wordToDeque(word);
        return isPalindrome(d);
    }

    private boolean isPalindrome(Deque<Character> deque, CharacterComparator cc) {
        if (deque.isEmpty()) {
            return true;
        } else if (deque.size() == 1) {
            return true;
        } else {
            char start = deque.removeFirst();
            char end = deque.removeLast();
            if (!cc.equalChars(start, end)) {
                return false;
            } else {
                return isPalindrome(deque, cc);
            }
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> d = wordToDeque(word);
        return isPalindrome(d, cc);
    }
}
