// Hana Clements
// CS&143
// HIW#1: Exploring ArrayList and ArrayIntList

package edu.hanalways.cs143.ArrayIntList;

public class ArrayIntList implements IntList {
    private final int[] data; // array of integers
    private int size;   // current number of elements in the list

    public static final int CAPACITY = 20;

    // constructor
    ArrayIntList() {
        data = new int[CAPACITY];
        size = 0;
    }

    // pre: none, always initialized at 0
    // post: the number of values in the array
    public int size() {
        return size;
    }

    // pre: there is room in the underlying array for adding a new value to the list
    // post: the value specified is added to the list
    public void add(int val) {
        checkCapacity(size + 1);
        data[size] = val;
        size++;
    }

    // pre: there is room in the underlying array for adding a new value to the list
    // post: adds given value at given index. Throws exception if the index is out of bounds.
    public void add(int index, int value) {
        checkIndex(index);

        int existingVal = data[index];
        data[index] = value;

        for (int i = index + 1; i < size; i++) {
            int curr = data[i];
            data[i] = existingVal;
            existingVal = curr;
        }
    }

    // pre: none
    // post: the values of the array are returned as a string dilemeted by commas surrounded in square brackets
    public String toString() {
        StringBuilder s = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            s.append(data[i]);
            if (i < size - 1) {
                s.append(", ");
            }
        }
        return s.append("]").toString();
    }

    // pre: none
    // post: the value where the index specified is within bounds, otherwise throws an exception
    public int get(int index) {
        checkIndex(index);
        return data[index];
    }

    // pre: none
    // post: the index where the value specified is found and returned. If not found the value -1 is returned
    public int indexOf(int value) {
        for (int i = 0; i < size; i++) {
            if (data[i] == value) {
                return i;
            }
        }

        return -1;
    }

    // pre: none
    // post: removes value at index and then shifts all values over. Throws exception if the index given is out of bounds.
    public void remove(int index) {
        checkIndex(index);
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
    }

    // pre: there is an existing array to compare against
    // post: returns a boolean to check if all elements in the given list present and in the same order
    public boolean equals(ArrayIntList list) {
        if (list.size() != this.size()) {
            return false;
        }

        for (int i = 0; i < list.size; i++) {
            if (list.get(i) != this.get(i)) {
                return false;
            }
        }

        return true;
    }

    // post: throws an IndexOutOfBoundsException if the given index is
    //       not a legal index of the current list
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index: " + index);
        }
    }

    // post: checks that the underlying array has the given capacity,
    //       throwing an IllegalStateException if it does not
    private void checkCapacity(int capacity) {
        if (capacity > data.length) {
            throw new IllegalStateException("would exceed list capacity");
        }
    }
}
