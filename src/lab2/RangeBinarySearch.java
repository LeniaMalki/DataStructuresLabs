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

        while (high>=lo){

            mid = (high-lo)/2 + lo;
            //System.out.println("High: " + high +" Mid: " + mid +" Low: " + lo);
            int val = comparator.compare(a[mid], key);

            if (val<0) {
                lo = mid+1;
            } else if (val > 0){
                high = mid-1;
            } else {
                if (mid == 0){
                    return mid;
                }
                //if the element before a[mid] is different from a[mid] break the loop and return index
                if (comparator.compare(a[mid], a[mid - 1]) != 0) {
                    return mid;
                } else {
                    high = mid-1;
                }
            }
        }

        return -1;
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
        int mid = (high-lo)/2 + lo;

        while (mid == (high-lo)/2 + lo){

            mid = (high-lo)/2;
            int val = comparator.compare(a[mid], key);

            if (val<0) {
                lo = mid;
            } else if (val > 0){
                high = mid;
            } else {
                if (mid == a.length-1){
                    index = mid;
                    break;
                }
                //if the element before a[mid] is different from a[mid] break the loop and return index
                if (comparator.compare(a[mid], a[mid + 1]) != 0) {
                    index = mid;
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