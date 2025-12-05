import java.util.concurrent.ThreadLocalRandom;
import java.util.Scanner;

public class Logic {

    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        int[] colorgenerator = new int[20];

        // generate numbers 1–4, creates pattern
        for (int i = 0; i < colorgenerator.length; i++) {
            colorgenerator[i] = ThreadLocalRandom.current().nextInt(1, 5);
        }

        for (int stage = 1; stage <= colorgenerator.length; stage++) {
            // Clear the screen using ANSI code
            System.out.print("\033[H\033[2J");


            // SHOW THE SEQUENCE UP TO CURRENT STAGE
            for (int i = 0; i < stage; i++) {
                if (colorgenerator[i] == 1) {
                    System.out.println("RED");
                } else if (colorgenerator[i] == 2) {
                    System.out.println("BLUE");
                } else if (colorgenerator[i] == 3) {
                    System.out.println("YELLOW");
                } else if (colorgenerator[i] == 4) {
                    System.out.println("GREEN");      
                }
                Thread.sleep(1000); // wait 1 second between colors
            }
            System.out.print("\033[H\033[2J");
            System.out.println("Type the colors (red/blue/yellow/green):");

            // for loop to check user input (makes it into capitals)
            for (int i = 0; i < stage; i++) {

                String userInput = sc.nextLine().toUpperCase();

                int colorToDigit = 0;
                if (userInput.equals("RED")) {
                    colorToDigit = 1;
                } else if (userInput.equals("BLUE")) {
                    colorToDigit = 2; 
                } else if (userInput.equals("YELLOW")) {
                    colorToDigit = 3;
                } else if (userInput.equals("GREEN")) {
                    colorToDigit = 4;
                }

                if (colorgenerator[i] == colorToDigit) {
                    System.out.println("Good");
                } else {
                    System.out.println("You Lose");
                    return; // end the game on answer
                }
            }
        }
        System.out.println("Win");
    }
}