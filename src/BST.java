/**
 * @file: BST.java
 * @description: This class implements a Binary Search Tree (BST) with a generic and iterable structure.
 * @author: Andrew Dwyer
 * @date: September 20, 2024
 */

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BST<T extends Comparable<T>> implements Iterable<T> {
    private Node<T> root;
    private int size;

    // Constructor: Initializes the BST with an empty root
    public BST() {
        this.root = null;
        this.size = 0;
    }

    // Clear the BST (remove all nodes)
    public void clear() {
        root = null;
        size = 0;
    }

    // Get the size of the BST (number of nodes)
    public int size() {
        return size;
    }

    // Insert a new value into the BST
    public void insert(T value) {
        root = insertRec(root, value);
        size++;
    }

    // Recursive helper method for insertion
    private Node<T> insertRec(Node<T> node, T value) {
        if (node == null) {
            return new Node<>(value);
        }
        if (value.compareTo(node.getElement()) < 0) {
            node.setLeft(insertRec(node.getLeft(), value));
        } else if (value.compareTo(node.getElement()) > 0) {
            node.setRight(insertRec(node.getRight(), value));
        }
        return node; // unchanged node
    }

    // Remove a value from the BST
    public void remove(T value) {
        root = removeRec(root, value);
    }

    // Recursive helper method for removal
    private Node<T> removeRec(Node<T> node, T value) {
        if (node == null) {
            return null;
        }

        if (value.compareTo(node.getElement()) < 0) {
            node.setLeft(removeRec(node.getLeft(), value));
        } else if (value.compareTo(node.getElement()) > 0) {
            node.setRight(removeRec(node.getRight(), value));
        } else {
            // Node to be deleted found
            // Case 1: Node with only one child or no child
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            }

            // Case 2: Node with two children
            node.setElement(minValue(node.getRight())); // Replace value with the smallest value in the right subtree
            node.setRight(removeRec(node.getRight(), node.getElement())); // Delete the in-order successor
        }
        return node;
    }

    // Find the minimum value node in the right subtree (used for removal)
    private T minValue(Node<T> node) {
        T minValue = node.getElement();
        while (node.getLeft() != null) {
            node = node.getLeft();
            minValue = node.getElement();
        }
        return minValue;
    }

    // Search for a value in the BST
    public boolean search(T value) {
        return searchRec(root, value) != null;
    }

    // Recursive helper method for search
    private Node<T> searchRec(Node<T> node, T value) {
        if (node == null) {
            return null; // Value not found
        }
        if (node.getElement().equals(value)) {
            return node; // Value found
        }
        if (value.compareTo(node.getElement()) < 0) {
            return searchRec(node.getLeft(), value); // Search left subtree
        } else {
            return searchRec(node.getRight(), value); // Search right subtree
        }
    }

    // Iterator method to return an iterator for the BST (in-order traversal)
    @Override
    public Iterator<T> iterator() {
        return new BSTIterator(root);
    }

    // BSTIterator class for in-order traversal of the BST
    private class BSTIterator implements Iterator<T> {
        private Stack<Node<T>> stack;

        public BSTIterator(Node<T> root) {
            stack = new Stack<>();
            pushLeft(root);
        }

        // Push all left children onto the stack
        private void pushLeft(Node<T> node) {
            while (node != null) {
                stack.push(node);
                node = node.getLeft();
            }
        }

        // Check if there's a next element
        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        // Get the next element in the in-order traversal
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<T> node = stack.pop();
            pushLeft(node.getRight());
            return node.getElement();
        }
    }
}