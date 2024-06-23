import java.util.*;

public class GuessTheNumber {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain;

        // Welcome message with ASCII art
        System.out.println("**************************************");
        System.out.println("*     Welcome to Guess the Number!   *");
        System.out.println("**************************************");


        do {
            System.out.print("How many rounds do you want to play? : ");
            int totalRounds = sc.nextInt(); // Number of rounds
            System.out.print("How many attempts do you want per round? : ");
            int maxAttempts = sc.nextInt(); // Maximum number of attempts per round
            int score = 0;

            for (int round = 1; round <= totalRounds; round++) {
                int numberToGuess = random.nextInt(100) + 1; // Random number between 1 and 100
                int attempts = 0;
                boolean hasGuessedCorrectly = false;

                System.out.println("\n************************************");
                System.out.println("           Round " + round + " of " + totalRounds);
                System.out.println("************************************");
                System.out.println("I have selected a number between 1 and 100. Can you guess it?");
                System.out.println("Attempts To Guess Number In Each Round : " + maxAttempts + "\n");

                while (attempts < maxAttempts && !hasGuessedCorrectly) {
                    System.out.print("Enter your guess: ");
                    int userGuess = sc.nextInt();
                    attempts++;

                    if (userGuess < numberToGuess) {
                        System.out.println("Your guess is too low! Try again.");
                    } else if (userGuess > numberToGuess) {
                        System.out.println("Your guess is too high! Try again.");
                    } else {
                        System.out.println("Congratulations! You've guessed the correct number.");
                        hasGuessedCorrectly = true;
                        score += (maxAttempts - attempts + 1) * 10; // Points based on remaining attempts
                    }
                }

                if (!hasGuessedCorrectly) {
                    System.out.println("Sorry, you've used all your attempts. The correct number was " + numberToGuess + ".");
                }

                System.out.println("Your score after round " + round + " is: " + score);
                System.out.println("************************************");
            }

            System.out.println("\n************************************");
            System.out.println("              GAME OVER             ");
            System.out.println("************************************");
            System.out.println("Your final score is: " + score);

            System.out.print("Do you want to play again? (y/n): ");
            char response = sc.next().charAt(0);
            playAgain = (response == 'y' || response == 'Y');

        } while (playAgain);

        System.out.println("Thank you for playing! Goodbye!");
        sc.close();
    }
}