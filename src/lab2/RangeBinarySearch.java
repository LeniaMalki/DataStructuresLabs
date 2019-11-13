package lab2;

import java.util.Comparator;

public class RangeBinarySearch {
    // Returns the index of the first key in a[] that equals the search key, or -1 if no such key.
    // Complexity: O(log N), where N is the length of the array
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator comparator)

    // Returns the index of the last key in a[] that equals the search key, or -1 if no such key.
    // Complexity: O(log N)
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator comparator)

    //Corner cases
    //
    //    Each static method should throw a java.lang.NullPointerException if any of its arguments is null.
    //    You should assume that the argument array is in sorted order (with respect to the supplied comparator).

    //Performance requirements
    //
    //The firstIndexOf() and lastIndexOf() methods should make at most 1 + ⌈log2 N⌉ compares in the worst case, where N is the length of the array.
    //In this context, a compare is one call to comparator.compare().
}