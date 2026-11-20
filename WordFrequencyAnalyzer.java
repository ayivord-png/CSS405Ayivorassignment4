/**
 * Method Name: WordFrequencyAnalyzer
 * Description: Creates a new WordFrequencyAnalyzer and
 * initializes an empty AVL tree to store word frequencies.
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordFrequencyAnalyzer {
        //stores the AVL tree that will hold word frequencies
        private AVLTree tree;
        //creates a new AVL tree
        public WordFrequencyAnalyzer() {
            tree = new AVLTree();
        }
        //reads a text file and inserts each  word into the AVL tree
        public void readFile(String fileName) {
            try {
                File file = new File(fileName);
                Scanner scanner = new Scanner(file);
                while (scanner.hasNext()) {
                    String word = scanner.next().toLowerCase();
                    word = word.replaceAll("^[^a-z0-9]+|[^a-z0-9]+$", "");
                    if (!word.isEmpty()) {
                        tree.insert(word);
                    }
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                System.out.println( fileName + "not found");
            }
        }

        //displays words alphabetically
        public void displayAlphabetical() {
            tree.inOrderTraversal();
        }

        //displays words by level
        public void displaybylevel() {
            tree.levelOrderTraversal();
        }

        //searches for a word and displays its frequency
        public void wordSearch(String word) {
            int count = tree.search(word.toLowerCase());

            if (count == -1) {
                System.out.println(word + " was not found.");
            } else {
                System.out.println(word + " appears " + count + " times.");
            }
        }
    }

