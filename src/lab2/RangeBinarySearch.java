package lab2;

import java.util.Comparator;

public class RangeBinarySearch {
    // Returns the index of the first key in a[] that equals the search key, or -1 if no such key.
    // Complexity: O(log N), where N is the length of the array
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator comparator){
        if(a==null || key==null || comparator==null) {
            throw new NullPointerException("BinarySearch arguments can't be null");
        }
        int lo = 0;
        int high = a.length-1;
        int mid;

        while (lo <= high){

            mid = (high-lo)/2 + lo;
            int val = comparator.compare(key, a[mid]);

            if (val>0) {
                lo = mid+1;
            } else  {
                high = mid-1;
            }
        }
        if(lo>=a.length) {
            return a.length;
        }
        if (comparator.compare(key, a[lo]) == 0) {
            return lo;
        }

        return -1;
    }

    // Returns the index of the last key in a[] that equals the search key, or -1 if no such key.
    // Complexity: O(log N)
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator comparator){
        if(a==null || key==null || comparator==null) {
            throw new NullPointerException("BinarySearch arguments can't be null");
        }

        int lo = 0;
        int high = a.length-1;
        int mid;

        while (lo <= high){
            mid = (high-lo)/2 + lo;
            int val = comparator.compare(key,a[mid]);

            if (val<0) {
                high = mid-1;
            } else {
                lo = mid+1;
            }
        }
        if (comparator.compare(key, a[high]) == 0) {
            return high;
        }
        return -1;
    }
    

    //Performance requirements
    //
    //The firstIndexOf() and lastIndexOf() methods should make at most 1 + ⌈log2 N⌉ compares in the worst case, where N is the length of the array.
    //In this context, a compare is one call to comparator.compare().
}