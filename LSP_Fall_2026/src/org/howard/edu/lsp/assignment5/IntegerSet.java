package org.howard.edu.lsp.assignment5;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * IntegerSet represents a mathematical set of integers with unique elements
 * and provides standard and advanced set operations.
 */
public class IntegerSet {
    private List<Integer> set = new ArrayList<Integer>();

    /** Default constructor for an empty IntegerSet. */
    public IntegerSet() {
    }

    /** Clears all elements from the set. */
    public void clear() {
        set.clear();
    }

    /** Returns the number of elements in the set. */
    public int length() {
        return set.size();
    }

    /** Returns true if the set contains no elements. */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /** Returns true if both sets contain exactly the same elements, regardless of order. */
    public boolean equals(IntegerSet b) {
        if (b == null) return false;
        if (this.length() != b.length()) return false;
        for (int val : set) {
            if (!b.contains(val)) return false;
        }
        return true;
    }

    /** Returns true if the set contains the specified value. */
    public boolean contains(int value) {
        return set.contains(value);
    }

    /** Returns the largest item in the set. Throws IllegalStateException if empty. */
    public int largest() {
        if (isEmpty()) {
            throw new IllegalStateException("Set is empty");
        }
        return Collections.max(set);
    }

    /** Returns the smallest item in the set. Throws IllegalStateException if empty. */
    public int smallest() {
        if (isEmpty()) {
            throw new IllegalStateException("Set is empty");
        }
        return Collections.min(set);
    }

    /** Adds an item to the set if it is not already present (ignores duplicates). */
    public void add(int item) {
        if (!set.contains(item)) {
            set.add(item);
        }
    }

    /** Removes an item from the set if it is present. */
    public void remove(int item) {
        set.remove(Integer.valueOf(item));
    }

    /** Returns a new set containing elements in either this set or set b. */
    public IntegerSet union(IntegerSet b) {
        IntegerSet result = new IntegerSet();
        for (int val : this.set) result.add(val);
        for (int val : b.set) result.add(val);
        return result;
    }

    /** Returns a new set containing elements shared by both sets. */
    public IntegerSet intersect(IntegerSet b) {
        IntegerSet result = new IntegerSet();
        for (int val : this.set) {
            if (b.contains(val)) {
                result.add(val);
            }
        }
        return result;
    }

    /** Returns a new set containing elements in this set but not in set b. */
    public IntegerSet diff(IntegerSet b) {
        IntegerSet result = new IntegerSet();
        for (int val : this.set) {
            if (!b.contains(val)) {
                result.add(val);
            }
        }
        return result;
    }

    /** Returns a new set containing elements in set b but not in this set (assignment-specific definition). */
    public IntegerSet complement(IntegerSet b) {
        IntegerSet result = new IntegerSet();
        for (int val : b.set) {
            if (!this.contains(val)) {
                result.add(val);
            }
        }
        return result;
    }

    /** Returns true when every element of the current set is present in b. */
    public boolean isSubsetOf(IntegerSet b) {
        for (int val : this.set) {
            if (!b.contains(val)) return false;
        }
        return true;
    }

    /** Returns true when the two sets share no elements. */
    public boolean isDisjoint(IntegerSet b) {
        for (int val : this.set) {
            if (b.contains(val)) return false;
        }
        return true;
    }

    /** Returns a new set containing elements that occur in exactly one of the two sets. */
    public IntegerSet symmetricDiff(IntegerSet b) {
        IntegerSet result = new IntegerSet();
        for (int val : this.set) {
            if (!b.contains(val)) result.add(val);
        }
        for (int val : b.set) {
            if (!this.contains(val)) result.add(val);
        }
        return result;
    }

    /** Returns elements in ascending numerical order, separated by a comma and one space, inside square brackets. */
    @Override
    public String toString() {
        List<Integer> sortedList = new ArrayList<Integer>(set);
        Collections.sort(sortedList);
        return sortedList.toString();
    }
}
