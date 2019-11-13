package lab2;

import java.util.Comparator;

public class Term {
    // Initializes a term with the given query string and weight.
    public Term(String query, long weight)

    // Compares the two terms in lexicographic order by query.
    public static Comparator<Term> byLexicographicOrder()

    // Compares the two terms in descending order by weight.
    public static Comparator<Term> byReverseWeightOrder()

    // Compares the two terms in lexicographic order,
    // but using only the first k characters of each query.
    public static Comparator<Term> byPrefixOrder(int k)

    // Returns a string representation of this term in the following format:
    // the weight, followed by whitespace, followed by the query.
    public String toString() {
        return String.format("%12d    %s", this.weight, this.query);
    }

    //Corner cases
    //
    //    The constructor should throw a java.lang.NullPointerException if query is null and a java.lang.IllegalArgumentException if weight is negative.
    //    The byPrefixOrder() method should throw a java.lang.IllegalArgumentException if k is negative.

    //Performance requirements
    //
    //The string comparison functions should take time proportional to the number of characters needed to resolve the comparison.
}
