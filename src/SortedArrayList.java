import java.util.Arrays;
import java.util.Iterator;


/**
 * Class for sorting ArrayLists by wrapping ArrayList methods.
 *
 * @author Hwansu Kim (Billy)
 * @version 04.24.2022
 */
public class SortedArrayList<E extends Comparable<E>> implements SortedArrayListInterface<E>, Iterable<E> {


    // INSTANCE DATA //
    /** ArrayList of objects */
    private ArrayList<E> arrayList;


    // CONSTRUCTORS //
    /**
     * Constructor w/ param.
     *
     * @param capacity the capacity of the ArrayList.
     */
    public SortedArrayList(int capacity) {
        arrayList = new ArrayList<E>(capacity);
    }

    /**
     * Constructor w/ no param.
     */
    public SortedArrayList() {
        arrayList = new ArrayList<E>();
    }


    /**
     * Retrieves the number of elements being maintained by the list
     *
     * @return the number of elements being maintained
     */
    @Override
    public int size() {
        return arrayList.size();
    }


    /**
     * Retrieves whether the list is empty
     *
     * @return true, if there are no elements in the list; false, if there are elements
     */
    @Override
    public boolean isEmpty() {
        return arrayList.isEmpty();
    }


    /**
     * Clears the list; no elements will remain after the call, and size will be 0
     */
    @Override
    public void clear() {
        arrayList.clear();
    }


    /**
     * Retrieves whether the specified element is in the list
     *
     * @param value the value to search for
     * @return true, if the element is in the list; false, if not
     */
    @Override
    public boolean contains(E value) {
        checkNullOrEmpty(value, "value");
        return indexOf(value) >= 0;
    }


    /**
     * Uses a binary search to find the index of the first occurrence of the specified value,
     * or, if not found, the place that value should be
     *
     * @param value the value to search for
     * @return if found, the index of the value in the list (range 0 to size - 1);
     * if not found, an index representing where the value would go, if added, returned
     * as -(position+1), e.g., -1 means it goes at index 0, -5 means it goes at index 4
     */
    @Override
    public int indexOf(E value) {
        checkNullOrEmpty(value, "value");
        int min = 0;
        int max = arrayList.size() - 1;

        int mid = -1;
        while (min <= max) {
            mid = (max + min) / 2;
            if (arrayList.get(mid) == value) {
                if (mid > 0) {
                    while (arrayList.get(mid - 1) == value) {
                        mid -= 1;
                    }
                }
                return mid;     // found it!
            } else if (arrayList.get(mid).compareTo(value) < 0) {
                min = mid + 1;  // too small
            } else {   // numbers[mid] > target
                max = mid - 1;  // too large
            }
            mid = (max + min) / 2;
        }

        return -min - 1;   // not found
    }


    /**
     * Retrieves the element at the specified position in the list
     *
     * @param index the index (position) in the list; must be 0 to size-1
     * @return the element at the specified position
     */
    @Override
    public E get(int index) {
        return arrayList.get(index);
    }


    /**
     * Retrieves an array of elements that are compare themselves equally to the specified value (via compareTo),
     * with results being stored in the array specified.
     *
     * @param value    the element being sought; will be used to compareTo() other elements
     * @param template a template array used to create results; pass in a 0-sized array
     * @return a new array that is right-sized and contains element references, if any
     */
    @Override
    public E[] get(E value, E[] template) {
        checkNullOrEmpty(value, "value");
        int length = 0;
        int[] indexArray = new int[arrayList.size()];
        for (int index = 0; index < arrayList.size(); index++) {
            if (arrayList.get(index).compareTo(value) == 0) {
                indexArray[length++] = index;
            }
        }
        E[] genericArray = Arrays.copyOf(template, length);
        for (int index = 0; index < genericArray.length; index++) {
            genericArray[index] = arrayList.get(indexArray[index]);
        }
        return genericArray;
    }


    /**
     * Adds a new element to the list, maintaining sorting via natural order (via compareTo)
     *
     * @param value the value to add to the list
     */
    @Override
    public void add(E value) {
        checkNullOrEmpty(value, "value");
        arrayList.ensureCapacity(arrayList.size() + 1);
        if (indexOf(value) < 0) {
            arrayList.add(indexOf(value) * -1 - 1, value);
        } else {
            arrayList.add(indexOf(value) + 1, value);
        }
    }


    /**
     * Removes from the list the element at the specified index
     *
     * @param index the index in the list; must be in range  0 to size-1
     */
    @Override
    public void remove(int index) {
        arrayList.remove(index);
    }


    /**
     * Retrieves an iterator over list elements; for/each loops are also supported
     *
     * @return a strongly typed iterator over list elements
     */
    @Override
    public Iterator<E> iterator() {
        return arrayList.iterator();
    }


    /**
     * Retrieves an array representing the contents of the list
     *
     * @param template a template list of the proper type, e.g., if E is String,
     *                 the caller can pass in as an argument: new String[0]
     * @return an array containing object references to list elements
     */
    @Override
    public E[] toArray(E[] template) {
        return arrayList.toArray(template);
    }


    /**
     * Exception to check for null or empty generic values.
     *
     * @param target the value to be checked.
     * @param identifier the variable name of the value.
     */
    private void checkNullOrEmpty(E target, String identifier) {
        if (target == null || target.equals("")) {
            throw new IllegalArgumentException(identifier + "must not be null or empty");
        }
    }
}
