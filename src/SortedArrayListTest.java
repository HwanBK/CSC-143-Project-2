import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Iterator;


/**
 * Tests for SortedArrayList
 *
 * @author Hwansu Kim (Billy)
 * @version 04.24.2022
 */
class SortedArrayListTest {


    private SortedArrayList<String> listA;
    private SortedArrayList<String> listB;


    public SortedArrayListTest() {
        listA = new SortedArrayList<>();
        listB = new SortedArrayList<>(50);
    }


    @Test
    void testSize() {
        assertEquals(0, listA.size());
        assertEquals(0, listB.size());

        listA.add("Pepperoni");
        listA.add("Basil");
        listB.add("Fungus");

        assertEquals(2, listA.size());
        assertEquals(1, listB.size());
    }

    @Test
    void testIsEmpty() {
        assertTrue(listA.isEmpty());
        assertTrue(listB.isEmpty());

        listA.add("Onion");

        assertFalse(listA.isEmpty());
        assertTrue(listB.isEmpty());
    }

    @Test
    void testClear() {
        listA.add("Peppers");
        assertFalse(listA.isEmpty());
        listA.clear();
        assertEquals(0, listA.size());
        assertTrue(listA.isEmpty());
    }

    @Test
    void testContains() {
        listB.add("Socks");
        listB.add("Rat Poison");
        assertTrue(listB.contains("Socks"));
        assertFalse(listB.contains("Cataracts"));
    }

    @Test
    void testIndexOf() {
        listA.add("Pepperoni");
        listA.add("Pepper");
        listA.add("Pineapple");
        listA.add("Onion");
        listA.add("Q-Tips");

        assertEquals(-1, listA.indexOf("Basil"));
        assertEquals(0, listA.indexOf("Onion"));
        assertEquals(4, listA.indexOf("Q-Tips"));
        assertEquals(-6, listA.indexOf("Zebra"));
    }

    @Test
    void testGetOneParam() {
        listB.add("Turtle Shell");
        listB.add("Sterile Needles");
        listB.add("Used Bandages");
        listB.add("Unpaid Child Support");

        assertEquals("Sterile Needles", listB.get(0));
        assertEquals("Used Bandages", listB.get(3));
    }

    @Test
    void testGetTwoParam() {
        listA.add("Bacon");
        listA.add("Asbestos");
        listA.add("Asbestos");
        listA.add("asbestos");
        String[] testArray = listA.get("Asbestos", new String[0]);
        assertEquals("Asbestos", testArray[0]);
        assertEquals(2, testArray.length);
    }

    @Test
    void testAdd() {
        listA.add("Ham");
        listA.add("Basil");
        listA.add("Pineapple");

        assertEquals(3, listA.size());
        assertFalse(listA.isEmpty());
        assertEquals("Ham", listA.get(1));
        assertTrue(listA.contains("Pineapple"));
        assertFalse(listA.contains("Pineapples"));
    }

    @Test
    void testRemove() {
        listB.add("Glass");
        listB.add("Plastic");

        assertTrue(listB.contains("Glass"));
        assertTrue(listB.contains("Plastic"));
        assertEquals("Glass", listB.get(0));
        assertEquals("Plastic", listB.get(1));

        listB.remove(0);

        assertEquals("Plastic", listB.get(0));
        assertFalse(listB.contains("Glass"));
    }

    @Test
    void testIterator() {
        listA.add("Back Pain");
        listA.add("Back Ache");
        listA.add("These aren't pizza toppings");
        Iterator<String> testIterator = listA.iterator();
        assertEquals("Back Ache", testIterator.next());
        assertEquals("Back Pain", testIterator.next());
        assertEquals("These aren't pizza toppings", testIterator.next());
        assertFalse(testIterator.hasNext());
    }

    @Test
    void testToArray() {
        listA.add("Tonka Beans");
        listA.add("Tonka Bean");
        String[] testArray = listA.toArray(new String[0]);
        assertEquals(2, testArray.length);
        assertEquals("Tonka Bean", testArray[0]);
    }

    @Test
    void testExceptions() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            listA.add(null);
        });
        IllegalArgumentException secondThrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            listA.add("");
        });
    }
}