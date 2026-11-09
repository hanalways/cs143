package edu.hanalways.cs143.StacksAndQueues;

import java.util.*;

public class Scratchpad {
    public static void main(String[] args) {
//        Queue<Integer> q = new LinkedList<>(List.of(1, -2, 3, -4, 5, -6));
//        mystery3(q);

        Stack<Integer> stack = new Stack<>();
        stack.addAll(List.of(5, -10, 15, -5, 100));
        splitStack(stack);
    }

    public static void mystery3(Queue<Integer> q) {
        int size = q.size();
        for (int i = 0; i < size; i++) {
            int n = q.remove();
            if (n > 0) {
                q.add(-n);
            }
        }
        System.out.println(q);
    }

    public static void splitStackWrong(Stack<Integer> stack) {
        Stack<Integer> pos = new Stack<>();
        Stack<Integer> neg = new Stack<>();

        int size = stack.size();
        for (int i = 0; i < size; i++) {
            int pop = stack.pop();
            if (pop > 0) {
                pos.add(pop);
            } else {
                neg.add(pop);
            }
        }

        int posSize = pos.size();
        for (int i = 0; i < posSize; i++) {
            stack.push(pos.pop());
        }

        int negSize = neg.size();
        for (int i = 0; i < negSize; i++) {
            stack.push(neg.pop());
        }

        int s = 8; // stop
    }

    // the bottom of the stack should be negative, the top should be positive
    public static void splitStack(Stack<Integer> stack) {
        Queue<Integer> queue = new LinkedList<>();

        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }

        int size = queue.size();
        for (int i = 0; i < size; i++) {
            // look at each int in the queue,
            int n = queue.remove();
            if (n < 0) {
                stack.push(n);
            } else {
                // put it back in the queue
                queue.add(n);
            }
        }

        while (!queue.isEmpty()) {
            stack.push(queue.remove());
        }
    }

    public static void stutter(Stack<Integer> s) {
        // original stack (5^ v1)
        Queue<Integer> q = new LinkedList<>();
        int size = s.size();

        // flip the stack (5^ v1) to a queue (5< >1)
        while (!s.isEmpty()) {
            q.add(s.pop());
        }

        // flip the queue (5< >1) to a stack (1^ 5v) with extra values
        while (!q.isEmpty()) {
            int n = q.remove();
            s.add(n);
            s.add(n);
        }

        // flip the stack (1^ 5v) to a queue (1< >5)
        while (!s.isEmpty()) {
            q.add(s.pop());
        }

        // flip the queue (1< >5) to a stack (5^ 1v)
        while (!q.isEmpty()) {
            s.add(q.remove());
        }
    }

    public static boolean isPalindrome(Queue<Integer> q) {
        Stack<Integer> s = new Stack<>();

        // q = 3, 8, 17, 9, 17, 8, 3
        // since the stack and the queue are mirrored now/flipped 90 degrees, we can just compare the two
        // dump each value into a stack
        // but preserve the queue
        // then iterate through the entire length of both via size to compare (use a boolean)
        int size = q.size();

        for (int i = 0; i < size; i++) {
            int n = q.remove();
            s.push(n);
            q.add(n); // gets in the back of the line
        }

        // must use boolean since we have to restore the queue
        boolean isPalindrome = true;
        for (int i = 0; i < size; i++) {
            int qFront = q.remove();
            int sTop = s.pop();

            if (qFront != sTop) {
                isPalindrome = false;
            }

            q.add(qFront);
        }

        return isPalindrome;
    }
}
