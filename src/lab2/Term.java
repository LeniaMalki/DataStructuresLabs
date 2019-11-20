package lab2;

import java.util.Comparator;

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
                int length = Math.min(o1.query.length(), o2.query.length());

                for(int i = 0; i<length; i++) {
                    if (o1.query.charAt(i)<o2.query.charAt(i)) {
                        return -1;
                    } else if(o1.query.charAt(i)>o2.query.charAt(i)) {
                        return 1;
                    }
                }
                if(o1.query.length()<o2.query.length()) {
                    return -1;
                } else if (o1.query.length()>o2.query.length()) {
                    return 1;
                }
                return 0;
            }
        };
    }

    // Compares the two terms in descending order by weight.
    public static Comparator<Term> byReverseWeightOrder() {
        return null;
    }

    // Compares the two terms in lexicographic order,
    // but using only the first k characters of each query.
    public static Comparator<Term> byPrefixOrder(int k) {
        if(k<0) {
            throw new IllegalArgumentException("The first K characters can't be negative");
        }
        return null;
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
