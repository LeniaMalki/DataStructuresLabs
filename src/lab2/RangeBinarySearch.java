package lab2;

import java.util.Comparator;

public class RangeBinarySearch {
    // Returns the index of the first key in a[] that equals the search key, or -1 if no such key.
    // Complexity: O(log N), where N is the length of the array
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator comparator){
        if(a==null || key==null || comparator==null) {
            throw new NullPointerException("BinarySearch arguments can't be null");
        }
        int index = 0;
        int lo = 0;
        int high = a.length-1;
        int mid;

        while (true){
            mid = (high-lo)/2;
            int val = comparator.compare(a[mid], key);

            if (val<0) {
                lo = mid;
            } else if (val > 0){
                high = mid;
            } else {
                if (mid == 0){
                    break;
                }
                //if the element before a[mid] is different from a[mid] break the loop and return index
                if (comparator.compare(a[mid], a[mid - 1]) != 0) {
                    break;
                } else {
                    high = mid;
                }
            }
        }

        return index;
    }

    // Returns the index of the last key in a[] that equals the search key, or -1 if no such key.
    // Complexity: O(log N)
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator comparator){
        if(a==null || key==null || comparator==null) {
            throw new NullPointerException("BinarySearch arguments can't be null");
        }
        int index = -1;
        int lo = 0;
        int high = a.length-1;
        int mid;

        while (true){
            mid = (high-lo)/2;
            int val = comparator.compare(a[mid], key);

            if (val<0) {
                lo = mid;
            } else if (val > 0){
                high = mid;
            } else {
                if (mid == a.length-1){
                    break;
                }
                //if the element before a[mid] is different from a[mid] break the loop and return index
                if (comparator.compare(a[mid], a[mid + 1]) != 0) {
                    break;
                } else {
                    lo = mid;
                }
            }
        }
        return index;
    }
    

    //Performance requirements
    //
    //The firstIndexOf() and lastIndexOf() methods should make at most 1 + ⌈log2 N⌉ compares in the worst case, where N is the length of the array.
    //In this context, a compare is one call to comparator.compare().
}