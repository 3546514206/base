package edu.zjnu.c3;

// Guess the letter game.
public class Guess2 {
    public static void main(String[] args)
            throws java.io.IOException {

        char ch, answer = 'K';

        System.out.println("I'm thinking of a letter between A and Z.");
        System.out.print("Can you guess it: ");

        ch = (char) System.in.read(); // read a char from tge keyboard

        if (ch == answer) System.out.println("*** Right ***");
        else System.out.println("...Sorry, you're wrong.");
    }
}
