import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testEqualChars() {
        boolean act = offByOne.equalChars('r', 'q');
        boolean exp = true;
        assertEquals(exp, act);

        boolean act3 = offByOne.equalChars('q', 'r');
        boolean exp3 = true;
        assertEquals(exp3, act3);

        boolean act2 = offByOne.equalChars('&', '%');
        boolean exp2 = true;
        assertEquals(exp2, act2);

        boolean act4 = offByOne.equalChars('A', 'B');
        boolean exp4 = true;
        assertEquals(exp4, act4);
    }
}
