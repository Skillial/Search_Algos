import java.util.*;
import java.util.Random;

class App {
    //method for the randomizer
    public static String randomizer(int stringLength) {
        StringBuilder word = new StringBuilder();
        char[] bank = {'a', 'c', 'g', 't'};
        for (int i = 0; i < stringLength; i++) {
            Random num = new Random();
            word.append(bank[num.nextInt(4)]);
        }
        return word.toString();
    }

    public static void main(String[] args) {
        //variable declarations
        String inputText, sub;
        int length, k, option;
        int mode; //1 - hash table/ 2 - binary tree
        Scanner sc = new Scanner(System.in);
        long startTime, endTime, totalTime; //for timers
        //getting inputs (rather than just setting them directly). Felt like doing it this way compared to MC01
        System.out.print("Input string length: ");
        length = sc.nextInt();
        System.out.print("Input k: ");
        k = sc.nextInt();
        System.out.print("Do you want to use (1)binary search/ (2)hash table: ");
        mode = sc.nextInt();
        if (mode == 1) {
            startTime = System.nanoTime(); //for run-time analysis
            inputText = randomizer(length);       //randomizes the string given input length
            System.out.println(inputText);
            BinaryTree tree = new BinaryTree(new binaryTreeNode(inputText.substring(0, k)));
            //puts the substrings into the hashtable
            for (int i = 1; i <= length - k; i++) {
                sub = inputText.substring(i, i + k); //cuts the string from i to i+k
                tree.insertNode(sub);
            }
            tree.printTree();
            endTime = System.nanoTime();
            totalTime = endTime - startTime;
            System.out.println("Running time: " + totalTime + " nanoseconds");
            float milliTime = totalTime / 1_000_000.0f; //convert to milliseconds
            System.out.println("Running time: " + milliTime + " milliseconds");
        }


        if (mode == 2) {
            System.out.print("Input hash function (1 or 2 only): ");
            option = sc.nextInt();

            startTime = System.nanoTime(); //for run-time analysis
            inputText = randomizer(length);       //randomizes the string given input length
            LLHashTable theHT = new LLHashTable(length);
            //puts the substrings into the hashtable
            for (int i = 0; i <= length - k; i++) {
                sub = inputText.substring(i, i + k); //cuts the string (idk how this works tbh kek)
                theHT.insert(sub, option); //calls insert method from LLHashTable class
            }
            System.out.println("----------------------------------");
            //prints the contents of the actual hash table and the number of collisions
            theHT.printTable();
            System.out.println("Using hash " + option);
            System.out.println("Input length: " + length + " and k: " + k);
            //calculating and printing the running time
            endTime = System.nanoTime();
            totalTime = endTime - startTime;
            System.out.println("Running time: " + totalTime + " nanoseconds");
            float milliTime = totalTime / 1_000_000.0f; //convert to milliseconds
            System.out.println("Running time: " + milliTime + " milliseconds");
        }
    }
}
