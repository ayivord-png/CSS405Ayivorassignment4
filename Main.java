/*
 * I certify that the Java code I am submitting is all my own work.
 * None of the code is copied from any source or any person.
 * I did not use AI to generate the code for my program.
 * Signed: Derek Ayivor
 * Date: 11/18/2026
 *//**
 * @author Derek Ayivor
 * Date: 11/18/2026
 * Class: CSS405
 * Assignment: 4 AVl Tree
 * File Name: Main.java
 * Description: AVL tree that stores words and their frequencies from a
 * text file. Your program will read a text file, insert each word into the AVL tree, and then produce sorted
 * output showing each word and how many times it appears.
 *
 *
 * References: https://www.geeksforgeeks.org/dsa/introduction-to-avl-tree/
 * https://www.baeldung.com/java-remove-punctuation-from-string
 * https://learn.zybooks.com/zybook/CSPCSS405DuerreFall2026/chapter/11/section/1?lti_token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.
   eyJleHAiOjE3OTUxMTE5NDEsImlhdCI6MTc5NTExMTc2MSwianRpIjoiMDFhMWY0ZmYtYjczZC03MmRjLWEwNDItZDY3NDUwM2JmN2IzIiwidXNlcl9pZCI6MjQyMDIzMH0.
   z2oEkdQaF6MIseoJkCu0T7Yv9RFbDTE1_KiHbyuqmyk
 *
 */
public class Main {

    public static void main(String[] args) {
        WordFrequencyAnalyzer analyzer = new WordFrequencyAnalyzer();
        analyzer.readFile("input.rtf");

        System.out.println("\nWords in Alphabetical Order:");
        analyzer.displayAlphabetical();

        System.out.println("\nWords in Level Order:");
        analyzer.displaybylevel();

        System.out.println("\nSearching for a Word:");
        analyzer.wordSearch("goodbye");
    }
}