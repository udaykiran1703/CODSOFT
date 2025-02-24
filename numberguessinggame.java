package codesoft;
import java.util.Random;
import java.util.Scanner;

public class numberguessinggame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain=true;
        int roundsWon=0;

        while (playAgain) {
            int numberToGuess = random.nextInt(100);
            int attempts = 0;
            int maxAttempts = 10;
            boolean guessedCorrectly = false;

            System.out.println("\nWelcome to the Number Guessing Game!");
            System.out.println("I have selected a number between 1 and 100. You have " + maxAttempts + " attempts to guess it.");

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess = sc.nextInt();
                attempts++;

                if (userGuess == numberToGuess) {
                    System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                    guessedCorrectly = true;
                    roundsWon++;
                    break;
                } else if (userGuess < numberToGuess) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry, you've used all " + maxAttempts + " attempts. The number was " + numberToGuess + ".");
            }

            System.out.print("Do you want to play again? (yes/no): ");
            String response = sc.next();
            playAgain = response.equalsIgnoreCase("yes");
        }

        System.out.println("Game over! You won " + roundsWon + " rounds.");
        sc.close();
    }
}
