import static org.junit.Assert.*;
import org.junit.Test;

/** Tests by Brendan Hu, Spring 2015, revised for 2016 by Josh Hug */
public class TestBSTMap {

    @Test
    public void myTest4() {
        BSTMap<Integer, String> b = new BSTMap<>();
        b.put(5, "abc");
        b.put(2, "hello");
        b.put(6, "yes");
        b.put(1, "no");
        b.put(4, "java");
        b.put(3, "c");
        System.out.println(b.keySet());
    }

    @Test
    public void myTest3() {
        BSTMap<Integer, String> b = new BSTMap<>();
        b.put(5, "abc");
        b.put(2, "hello");
        b.put(6, "yes");
        b.put(1, "no");
        b.put(4, "java");
        b.put(3, "c");
        b.printInOrder();

        System.out.println(b.remove(3));
        b.put(3, "c");

        System.out.println(b.remove(4));
        b = new BSTMap<>();
        b.put(5, "abc");
        b.put(2, "hello");
        b.put(6, "yes");
        b.put(1, "no");
        b.put(4, "java");
        b.put(3, "c");

        System.out.println(b.remove(2));
    }

    @Test
    public void myTest2() {
        BSTMap<Integer, String> b = new BSTMap<>();
        b.put(5, "abc");
        b.put(2, "hello");
        b.put(6, "yes");
        b.put(1, "no");
        b.put(4, "java");
        b.put(3, "c");
        b.printInOrder();
        for (int key : b) {
            System.out.println(key);
        }
        BSTMap<Integer, String> a = new BSTMap<>();
        a.put(10, "abc");
        a.put(5, "hello");
        a.put(6, "yes");
        a.put(7, "no");
        a.put(8, "java");
        a.printInOrder();
        for (int key : a) {
            System.out.println(key);
        }
    }
    @Test
    public void myTest() {
        BSTMap<Integer, String> b = new BSTMap<>();
        b.put(5, "abc");
        b.put(3, "hello");
        b.put(10, "yes");
        b.printInOrder();
        b.put(10, "no");
        b.printInOrder();
    }
	@Test
    public void sanityGenericsTest() {
    	try {
    		BSTMap<String, String> a = new BSTMap<String, String>();
	    	BSTMap<String, Integer> b = new BSTMap<String, Integer>();
	    	BSTMap<Integer, String> c = new BSTMap<Integer, String>();
	    	BSTMap<Boolean, Integer> e = new BSTMap<Boolean, Integer>();
	    } catch (Exception e) {
	    	fail();
	    }
    }

    //assumes put/size/containsKey/get work
	@Test
    public void sanityClearTest() {
    	BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        for (int i = 0; i < 455; i++) {
            b.put("hi" + i, 1+i);
            //make sure put is working via containsKey and get
            assertTrue( null != b.get("hi" + i) && (b.get("hi"+i).equals(1+i))
                        && b.containsKey("hi" + i));
        }
        b.printInOrder();
        assertEquals(455, b.size());
        b.clear();
        assertEquals(0, b.size());
        for (int i = 0; i < 455; i++) {
            assertTrue(null == b.get("hi" + i) && !b.containsKey("hi" + i));
        }
    }

    // assumes put works
    @Test
    public void sanityContainsKeyTest() {
    	BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        assertFalse(b.containsKey("waterYouDoingHere"));
        b.put("waterYouDoingHere", 0);
        assertTrue(b.containsKey("waterYouDoingHere"));
    }

    // assumes put works
    @Test
    public void sanityGetTest() {
    	BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        assertEquals(null,b.get("starChild"));
        assertEquals(0, b.size());
        b.put("starChild", 5);
        assertTrue(((Integer) b.get("starChild")).equals(5));
        b.put("KISS", 5);
        assertTrue(((Integer) b.get("KISS")).equals(5));
        assertNotEquals(null,b.get("starChild"));
        assertEquals(2, b.size());
    }

    // assumes put works
    @Test
    public void sanitySizeTest() {
    	BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        assertEquals(0, b.size());
        b.put("hi", 1);
        assertEquals(1, b.size());
        for (int i = 0; i < 455; i++)
            b.put("hi" + i, 1);
        assertEquals(456, b.size());
    }

    //assumes get/containskey work
    @Test
    public void sanityPutTest() {
    	BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        b.put("hi", 1);
        assertTrue(b.containsKey("hi") && b.get("hi") != null);
    }

    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests(TestBSTMap.class);
    }
}
