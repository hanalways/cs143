package edu.hanalways.cs143.ArrayIntList;

public class SimpleArrayIntList {
    // fields
    private final int[] data;
    private int size;

    // constructors
    SimpleArrayIntList() {
        data = new int[20];
        size = 0;
    }

    // accessors
    public int size() {
        return size;
    }

    // mutators
    public void add(int val) {
        data[size] = val;
        size++;
    }

    // toString
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

}
