// Hana Clements
// CS&143
// HIW#1: Exploring ArrayList and ArrayIntList

package edu.hanalways.cs143.ArrayIntList;

import java.util.ArrayList;

public class ExploringArrayList {
    static void main(String[] args) {
        // vid lecture
        ArrayList<Integer> myList = new ArrayList<Integer>();
        myList.add(3);
        myList.add(7);

        addToList(myList);

        System.out.println("My list has the values: " + myList);

        // using our own class, SimpleArrayList
        SimpleArrayIntList simpleList = new SimpleArrayIntList();

        // personal testing toString
        System.out.println("An empty list should return []: " + simpleList);

        addToList(simpleList);

        System.out.println("The size of my list is: " + simpleList.size());
        System.out.println("My list has the values: " + simpleList);

        // ArrayIntList
        ArrayIntList intList = new ArrayIntList();
        addToList(intList);

        System.out.println("The size of my list is: " + intList.size());
        System.out.println("My list has the values: " + intList);
        System.out.println("The value at index 6 is: " + intList.get(6));
        System.out.println("The index of the value 600 is: " + intList.indexOf(600));

        intList.remove(6);
        intList.add(1, 1);
        intList.add(2, 2);

        System.out.println("My list has the values: " + intList);

        // throw some exceptions
        try {
            intList.add(100, 1000);
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            addToList(intList);
            addToList(intList);
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            intList.get(1000);
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("indexOf when index is invalid: " + intList.indexOf(9000));

        try {
            intList.remove(-1);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // helpers
    private static void addToList(ArrayList<Integer> myList) {
        for (int x = 0; x < 10; x++) {
            myList.add(x * 100);
        }
    }

    private static void addToList(SimpleArrayIntList myList) {
        for (int x = 0; x < 10; x++) {
            myList.add(x * 100);
        }
    }

    private static void addToList(ArrayIntList myList) {
        for (int x = 0; x < 10; x++) {
            myList.add(x * 100);
        }
    }
}

// translation from array to ArrayList:
//    String[]          => ArrayList<String>
//    new String[10]    => new ArrayList<String>()
//    a.length          => list.size()
//    a[i]              => list.get(i)
//    a[i] = value;     => list.set(i, value);
// new operations:
//     list.remove(i);     --remove the ith value
//     list.add(value);    --appends a value
//     list.add(i, value); --adds at an index
//     list.clear()        --remove all value

/* output:

/Library/Java/JavaVirtualMachines/temurin-25.jdk/Contents/Home/bin/java -javaagent:/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=57077 -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath /Users/hana/Documents/Java/cs143/out/production/cs143:/Users/hana/.m2/repository/junit/junit/4.13.1/junit-4.13.1.jar:/Users/hana/.m2/repository/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar ArrayIntList.ExploringArrayList
My list has the values: [3, 7, 0, 100, 200, 300, 400, 500, 600, 700, 800, 900]
An empty list should return []: []
The size of my list is: 10
My list has the values: [0, 100, 200, 300, 400, 500, 600, 700, 800, 900]
The size of my list is: 10
My list has the values: [0, 100, 200, 300, 400, 500, 600, 700, 800, 900]
The value at index 6 is: 600
The index of the value 600 is: 6
My list has the values: [0, 1, 2, 100, 200, 300, 400, 500, 700]
java.lang.IndexOutOfBoundsException: index: 100
java.lang.IllegalStateException: would exceed list capacity
java.lang.IndexOutOfBoundsException: index: 1000
indexOf when index is invalid: -1
java.lang.IndexOutOfBoundsException: index: -1

Process finished with exit code 0

 */
