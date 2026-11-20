/**
 * Method Name: node
 * Description: Creates a new node for the AVL tree. Each node stores
 *  a word, the number of times the word appears, its height, and
 *  references to its left and right child nodes.
 * @param: the key
 *
 *
 */

public class Node {
    String key;
    int count;
    int height;

    Node left;
    Node right;

    public Node(String key){
        this.key = key;
        this.count = 1;
        this.height = 1;
        this.left = null;
        this.right = null;
    }
}
