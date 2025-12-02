import java.util.concurrent.ThreadLocalRandom;
import java.util.Scanner;

public class Logic {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] colorgenerator = new int[5];

        for (int i = 0; i < colorgenerator.length; i++) {
            colorgenerator[i] = ThreadLocalRandom.current().nextInt(1, 5);
        }

        for (int stage = 1; stage <= colorgenerator.length; stage++) {
            for (int i = 0; i < stage; i++) {
                if (colorgenerator[i] == 1) {
                    System.out.println("Click Red");

                } else if (colorgenerator[i] == 2) {
                    System.out.println("Click Blue");

                } else if (colorgenerator[i] == 3) {
                    System.out.println("Click Yellow");

                } else if (colorgenerator[i] == 4) {
                    System.out.println("Click Green");

                }
            }
            System.out.println("Take a guess");
            for (int i = 0; i < stage; i++) {
                
                String userInput = sc.nextLine();
                if (colorgenerator[i] == Integer.parseInt(userInput)) {
                    System.out.println("Good");

                }
                else {
                    System.out.println("Wrong");
                }

            }
        }
    }
}
