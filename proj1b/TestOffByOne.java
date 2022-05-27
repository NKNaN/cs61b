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

        boolean act2 = offByOne.equalChars('a', 'e');
        boolean exp2 = false;
        assertEquals(exp2, act2);
    }
}
