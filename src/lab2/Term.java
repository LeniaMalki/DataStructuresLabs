package lab2;

import java.util.*;

public class Term {
    private String query;
    private long weight;


    public Term(String query, long weight) {
        if(query==null) {
            throw new NullPointerException("Query can't be null");
        }
        if(weight<0) {
            throw new IllegalArgumentException("Weight can't be negative");
        }
        this.query=query;
        this.weight=weight;
    }

    // Compares the two terms in lexicographic order by query. O(C+2N+C+C)
    public static Comparator<Term> byLexicographicOrder() {
        return new Comparator<Term>() {
            @Override
            public int compare(Term o1, Term o2) {
                return limitedLexiCompare(o1, o2, Math.max(o1.query.length(), o2.query.length()));
            }
        };
    }

    // Compares the two terms in descending order by weight.
    public static Comparator<Term> byReverseWeightOrder() {
        return new Comparator<Term>() {
            @Override
            public int compare(Term o1, Term o2) {
                if (o1.weight<o2.weight){
                    return -1;
                }else if (o1.weight>o2.weight){
                    return 1;
                }
                return 0;
            }
        };
    }

    // Compares the two terms in lexicographic order,
    // but using only the first k characters of each query.
    public static Comparator<Term> byPrefixOrder(int k) {
        if(k<0) {
            throw new IllegalArgumentException("The first K characters can't be negative");
        }
        return new Comparator<Term>() {
            @Override
            public int compare(Term o1, Term o2) {
                return limitedLexiCompare(o1, o2, k);
            }
        };
    }

    /**
     * @param o1 Term 1
     * @param o2 Term 2
     * @param k  if exists this should be the limit of how much of the Term should be compared
     * @return negative if o1 comes before o2, 0 if they are equal within the limit, positive if o1 comes after o2
     */
    private static int limitedLexiCompare(Term o1, Term o2, int k){
        int length = Math.min(o1.query.length(), o2.query.length());

        for(int i = 0; i<length; i++) {
            if (i>k){
                return 0;
            }
            if (o1.query.charAt(i)<o2.query.charAt(i)) {
                return -1;
            } else if(o1.query.charAt(i)>o2.query.charAt(i)) {
                return 1;
            }
        }
        return 0;//o2.query.compareTo(o1.query);
    }

    // Returns a string representation of this term in the following format:
    // the weight, followed by whitespace, followed by the query.
    public String toString() {
        return String.format("%12d    %s", this.weight, this.query);
    }

    //Performance requirements
    //
    //The string comparison functions should take time proportional to the number of characters needed to resolve the comparison.
}
