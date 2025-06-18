package dataStructure;

import java.util.Iterator;
import java.util.NoSuchElementException;

// Generic LinkedList class
public class LinkedList<T> implements Iterable<T> {

    private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
            next = null;
        }
    }

    private Node head;
    private int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    // Add element at end
    public void add(T data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = newNode;
        }
        size++;
    }

    // Remove element if matches predicate
    // Return true if removed at least one element
    public boolean removeIf(java.util.function.Predicate<T> predicate) {
        boolean removed = false;
        // Remove from head if needed
        while (head != null && predicate.test(head.data)) {
            head = head.next;
            size--;
            removed = true;
        }

        if (head == null)
            return removed;

        Node current = head;
        while (current.next != null) {
            if (predicate.test(current.next.data)) {
                current.next = current.next.next;
                size--;
                removed = true;
            } else {
                current = current.next;
            }
        }
        return removed;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // Iterator supaya bisa dipakai for-each loop
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    // Optional: Display all nodes (for debugging)
    public void display() {
        Node curr = head;
        while (curr != null) {
            System.out.println(curr.data);
            curr = curr.next;
        }
    }

    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        T removedData;

        if (index == 0) {
            removedData = head.data;
            head = head.next;
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }

            removedData = current.next.data;
            current.next = current.next.next;
        }

        size--;
        return removedData;
    }

    public boolean contains(T data) {
        Node current = head;
        while (current != null) {
            if (current.data != null && current.data.equals(data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node current = head;
        int currentIndex = 0;

        while (current != null && currentIndex < index) {
            current = current.next;
            currentIndex++;
        }

        if (current == null) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        return current.data;
    }

    public boolean remove(Object o) {
        if (head == null) {
            return false;
        }

        if (head.data != null && head.data.equals(o)) {
            head = head.next;
            size--;
            return true;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.data != null && current.next.data.equals(o)) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }
}