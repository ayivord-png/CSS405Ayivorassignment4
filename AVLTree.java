/**
 * Method Name: AVLTree
 * Description: Creates a new empty AVL tree by setting the root
 * node to null.
 *
 */

import java.util.Queue;
import java.util.LinkedList;
public class AVLTree {
    //root node
    private Node root;

    //create AVL tree
    public AVLTree() {
        root = null;
    }

    //returns height of a node
    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    //makes sure tree is balanced
    private int isBalanced(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    /**
     * rotating a tree incase of unbalance
     **/

    // Performs a right rotation to fix unbalenced tree
    private Node rightRotate(Node b) {
        Node a = b.left;
        Node temp = a.right;
        // Move b to the right side of a
        a.right = b;
        // Move subtree to the left side of b
        b.left = temp;
        // Update height after the rotation
        b.height = 1 + Math.max(height(b.left), height(b.right));
        a.height = 1 + Math.max(height(a.left), height(a.right));
        return a;
    }

    // Performs a left rotation to fix a right-heavy tree
    private Node leftRotate(Node a) {
        Node b = a.right;
        Node temp = b.left;
        //move a to the left side of b
        b.left = a;
        //move subtree to the right side of a
        a.right = temp;
        //update height b after rotation.
        a.height = 1 + Math.max(height(a.left), height(a.right));
        b.height = 1 + Math.max(height(b.left), height(b.right));
        return b;
    }

    /**
     * inserting a word
     **/

    //inserts a word into tree
    public void insert(String key) {
        root = insert(root, key);
    }

    //finds the correct location for word
    private Node insert(Node node, String key) {
        //if the current location is empty create a new node
        if (node == null) {
            return new Node(key);
        }
        //if the word comes before the current word alphabetically insert it into the left subtree
        if (key.compareTo(node.key) < 0) {
            node.left = insert(node.left, key);
        }
        //if the word comes after the current word alphabetically insert it into the right subtree
        else if (key.compareTo(node.key) > 0) {
            node.right = insert(node.right, key);
        }
        //if the word already exists, increase its count
        else {
            node.count++;
            return node;
        }
        //update height of current node
        node.height = 1 + Math.max(height(node.left), height(node.right));
        //calculate the balance factor to check for imbalance
        int balance = isBalanced(node);
        //left subtree is too heavy on the left
        if (balance > 1 && key.compareTo(node.left.key) < 0) {
            return rightRotate(node);
        }
        //right subtree is too heavy the right side
        if (balance < -1 && key.compareTo(node.right.key) > 0) {
            return leftRotate(node);
        }
        //left subtree is too heavy the right side
        if (balance > 1 && key.compareTo(node.left.key) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        //right subtree is too heavy on its left side
        if (balance < -1 && key.compareTo(node.right.key) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        // Return the node if no rotation is needed
        return node;
    }

    /**
     * Searching tree for word
     **/

    public int search(String key) {
        Node current = root;
        //search until we find the word or reach an empty node
        while (current != null) {
            //if the word matches the current node return its count
            if (key.equals(current.key)) {
                return current.count;
            }
            //if the word comes before the current word search left
            else if (key.compareTo(current.key) < 0) {
                current = current.left;
            }
            //if the word comes after the current word search right
            else {
                current = current.right;
            }
        }
        // Return -1 when the word is not found
        return -1;
    }

    /**
     * Deletes a word from the AVL tree
     **/

    public void delete(String key) {
        root = RemovedNode(root, key);
    }

    // Recursively finds and removes the word
    private Node RemovedNode(Node node, String key) {
        // If the word does not exist, return the current node.
        if (node == null) {
            return null;
        }
        // Search the left subtree if the word comes before current word
        if (key.compareTo(node.key) < 0) {
            node.left = RemovedNode(node.left, key);
        }
        // Search the right subtree if the word comes after current word
        else if (key.compareTo(node.key) > 0) {
            node.right = RemovedNode(node.right, key);
        }
        // The word has been found
        else {
            // Replace it with its right child
            if (node.left == null) {
                return node.right;
            }
            //Replace it with its left child
            else if (node.right == null) {
                return node.left;
            }
            //the node has two children find the smallest word in the right subtree
            Node successor = SmallestValue(node.right);
            //copy the successor's word and count into the current node
            node.key = successor.key;
            node.count = successor.count;
            //remove the original successor node from the right subtree
            node.right = RemovedNode(node.right, successor.key);
        }
        //update the height
        node.height = 1 + Math.max(height(node.left), height(node.right));
        //calculate the balance factor
        int balance = isBalanced(node);
        if (balance > 1 && isBalanced(node.left) >= 0) {
            return rightRotate(node);
        }
        if (balance > 1 && isBalanced(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && isBalanced(node.right) <= 0) {
            return leftRotate(node);
        }
        if (balance < -1 && isBalanced(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        //return the updated node
        return node;

    }

    //finds the smallest value in a subtree
    private Node SmallestValue(Node node) {
        //keeps moving left smallest node is found
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }


    /** in order traversal**/

    public void inOrderTraversal() {
        System.out.println("=== In-Order Traversal (Alphabetical) ===");
        // Begin traversing the tree from the root.
        inOrder(root);
    }

    //visits nodes in alphabetical order.
    private void inOrder(Node node) {
        //stop if there is no node to visit
        if (node == null) {
            return;
        }
        // visit the left subtree
        inOrder(node.left);
        //print current word and its frequency
        System.out.println(node.key + ": " + node.count);
        //visit the right subtree
        inOrder(node.right);
    }


    public void levelOrderTraversal() {
        System.out.println("=== Level-Order Traversal (Tree Structure) ===");
        if (root == null) {
            System.out.println("AVLTree is empty.");
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.println(current.key + ": " + current.count);
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
    }

}


