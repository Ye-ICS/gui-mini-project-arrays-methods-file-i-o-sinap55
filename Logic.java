import java.util.concurrent.ThreadLocalRandom;
import java.util.Scanner;

public class Logic {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int colorgenerator = ThreadLocalRandom.current().nextInt(1, 4 + 1);
        System.out.println(colorgenerator);
        String color = null;
        if (colorgenerator == 1) {
            System.out.println("Click Red");
            color = "red";
        } else if (colorgenerator == 2) {
            System.out.println("Click Blue");
            color = "blue";
        } else if (colorgenerator == 3) {
            System.out.println("Click Yellow");
            color = "yellow";
        } else if (colorgenerator == 4) {
            System.out.println("Click Green");
            color = "green";
        }
        System.out.println("What colour should u click");
        String userInput = sc.nextLine();



        if (userInput .equals(color) ) {
            System.out.println("Correct");
        }
    }
}
