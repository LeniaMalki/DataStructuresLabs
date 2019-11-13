package lab2;

public class Autocomplete {
    // Initializes the data structure from the given array of terms.
    // Complexity: O(N log N), where N is the number of terms
    public Autocomplete(Term[] terms)

    // Returns all terms that start with the given prefix, in descending order of weight.
    // Complexity: O(log N + M log M), where M is the number of matching terms
    public Term[] allMatches(String prefix)

    // Returns the number of terms that start with the given prefix.
    // Complexity: O(log N)
    public int numberOfMatches(String prefix)

    //Corner cases
    //
    //The constructor should throw a java.lang.NullPointerException if its argument is null or if any of the entries in its argument array are null.
    //Each method should throw a java.lang.NullPointerException if its argument is null.
    //
    //Performance requirements
    //
    //The constructor should make proportional to N log N compares (or better) in the worst case, where N is the number of terms.
    //The allMatches() method should make proportional to log N + M log M compares (or better) in the worst case, where M is the number of matching terms.
    //The numberOfMatches() method should make proportional to log N compares (or better) in the worst case. It should not depend on M.
    //In this context, a compare is one call to the compare() method provided by any of the comparators defined in Term.
}
