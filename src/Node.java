/**
 * @file: Node.java
 * @description: This class represents a node in a Binary Search Tree (BST).
 * @author: Andrew Dwyer
 * @date: September 20, 2024
 */

public class Node<T extends Comparable<T>> {
    private T value;
    private Node<T> left;
    private Node<T> right;

    // Constructor
    public Node(T value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    // Set the value of the node (element)
    public void setElement(T value) {
        this.value = value;
    }

    // Set the left child of the node
    public void setLeft(Node<T> left) {
        this.left = left;
    }

    // Set the right child of the node
    public void setRight(Node<T> right) {
        this.right = right;
    }

    // Get the left child of the node
    public Node<T> getLeft() {
        return left;
    }

    // Get the right child of the node
    public Node<T> getRight() {
        return right;
    }

    // Get the value (element) of the node
    public T getElement() {
        return value;
    }

    // Check if the node is a leaf (has no children)
    public boolean isLeaf() {
        return left == null && right == null;
    }
}