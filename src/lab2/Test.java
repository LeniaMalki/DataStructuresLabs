package lab2;

import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {
        String[] strings = new String[3];
        String[] strings2 = new String[9];

        strings[0] = "a";
        strings[1] = "b";
        strings[2] = "b";

        System.out.println(RangeBinarySearch.firstIndexOf(strings, "b", String.CASE_INSENSITIVE_ORDER));


        strings2[0] = "a";
        strings2[1] = "b";
        strings2[2] = "b";
        strings2[3] = "b";
        strings2[4] = "c";
        strings2[5] = "c";
        strings2[6] = "c";
        strings2[7] = "o";
        strings2[8] = "o";

        System.out.println("First: " + RangeBinarySearch.firstIndexOf(strings2, "c", String.CASE_INSENSITIVE_ORDER));
        System.out.println("last: " + RangeBinarySearch.lastIndexOf(strings2, "c", String.CASE_INSENSITIVE_ORDER));


    }
}
