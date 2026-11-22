// Hana Clements
// CS143
// HW #5: LinkedIntList

package edu.hanalways.cs143.LinkedIntList;

public class LinkedIntList implements IntList {
    private Node start;
    private int size;

    // post: appends the given value to the end of the LinkedIntList
    //      increments size by 1
    public void add(int value) {
        Node node = new Node();
        node.data = value;

        if (start == null) {
            start = node;
        } else {
            Node temp = getNodeAtIndex(size - 1);
            temp.next = node;
        }
        size++;
    }

    // post: inserts the given value at the specified index
    //      shifts elements accordingly, increments size by 1
    public void add(int index, int value) {
        checkIndex(index);

        Node node = new Node();
        node.data = value;

        if (index == 0) {
            node.next = start;
            start = node;
        } else {
            Node temp = getNodeAtIndex(index - 1);
            node.next = temp.next;
            temp.next = node;
        }
        size++;
    }

    // post: returns an array-like string representation of the LinkedIntList
    //      list with no Nodes added is an empty bracket
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        Node temp = start;
        while (temp.next != null) {
            sb.append(temp.data).append(", ");
            temp = temp.next;
        }
        // fencepost end
        sb.append(getNodeAtIndex(size - 1).data);
        sb.append("]");

        return sb.toString();
    }

    // post: returns number of elements currently in the list
    public int size() {
        return size;
    }

    // pre: values added in an existing LinkedIntList
    // post: the value of the node at the given index
    public int get(int index) throws IndexOutOfBoundsException {
        checkIndex(index);

        Node temp = start;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }

        return temp.data;
    }

    // post: returns the index of the first occurance of the given value
    //      -1 if the value is not found
    public int indexOf(int value) {
        Node temp = start;
        for (int i = 0; i < size; i++) {
            if (temp.data == value) {
                return i;
            }
            temp = temp.next;
        }
        // couldn't find the index;
        return -1;
    }

    // post: removes the element at the given index
    //      shifts elements left, decrements size by 1
    public void remove(int index) {
        checkIndex(index);

        Node nodeToRemove = getNodeAtIndex(index);
        if (index == 0) {
            start = nodeToRemove.next;
        } else {
            Node temp = getNodeAtIndex(index - 1);
            temp.next = nodeToRemove.next;
        }
        nodeToRemove.next = null;
        size--;
    }

    // post: returns true if the object is a LinkedInstList of equal size with identical nodes
    public boolean equals(Object object) {
        if (!(object instanceof LinkedIntList)) {
            return false;
        }

        LinkedIntList obj = (LinkedIntList) object;
        if (size != obj.size) {
            return false;
        }

        Node thisTemp = start;
        Node objTemp = obj.start;
        while (thisTemp != null && objTemp != null) {
            if (thisTemp.data != objTemp.data) {
                return false;
            }
            thisTemp = thisTemp.next;
            objTemp = objTemp.next;
        }

        // if they are equals, they should both have no more nodes left
        return thisTemp == null && objTemp == null;
    }

    // post: inserts a copy of the list's nodes at a given index
    public void insertList(int index, LinkedIntList list) {
        checkIndex(index);
        if (list == null || list.size == 0) return;

        int i = index;
        Node curr = list.start;
        while (curr != null) {
            add(i, curr.data);
            i++;
            curr = curr.next;
        }
    }

    // post: returns the Node at a given index
    private Node getNodeAtIndex(int index) throws IndexOutOfBoundsException {
        checkIndex(index);

        Node node = start;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    // post: throws an IndexOutOfBoundsException if the given index is
    //      not a legal index of the current list
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index: " + index);
        }
    }
}

/**
 * > Task :compileJava
 * > Task :processResources UP-TO-DATE
 * > Task :classes
 * > Task :compileTestJava UP-TO-DATE
 * > Task :processTestResources UP-TO-DATE
 * > Task :testClasses UP-TO-DATE
 * > Task :test
 * LinkedIntListTest > testIndexOutOfBoundsExceptionIsThrown PASSED
 * LinkedIntListTest > testToString PASSED
 * LinkedIntListTest > testRemoveElementAtPositionAndGet PASSED
 * LinkedIntListTest > testInsertList PASSED
 * LinkedIntListTest > testAddAndSize PASSED
 * LinkedIntListTest > testEquals PASSED
 * LinkedIntListTest > testAddElementAtPosition PASSED
 * LinkedIntListTest > testIndexOf PASSED
 * BUILD SUCCESSFUL in 292ms
 * 5 actionable tasks: 2 executed, 3 up-to-date
 * Consider enabling configuration cache to speed up this build: https://docs.gradle.org/9.6.0/userguide/configuration_cache_enabling.html
 * 2:35:45 PM: Execution finished ':test --tests "edu.hanalways.cs143.LinkedIntList.LinkedIntListTest"'.
 */
