import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
    private static final int MAX_ATTEMPTS = 5;
    private static final int MAX_ROUNDS = 3;
    private static final int POINTS_PER_ROUND = 100;
    private static final int RANGE_START = 1;
    private static final int RANGE_END = 100;
    
    private static int totalScore = 0;
    private static int currentRound = 1;
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

    public static void main(String[] args) {
        System.out.println("Welcome to Guess the Number Game!");
        System.out.println("You have " + MAX_ROUNDS + " rounds to play.");
        System.out.println("Each round gives you " + MAX_ATTEMPTS + " attempts to guess the number.");
        System.out.println("Good luck!\n");

        while (currentRound <= MAX_ROUNDS) {
            playRound();
            currentRound++;
        }

        System.out.println("\nGame Over!");
        System.out.println("Your total score: " + totalScore);
        scanner.close();
    }

    private static void playRound() {
        int numberToGuess = random.nextInt(RANGE_END) + RANGE_START;
        int attemptsLeft = MAX_ATTEMPTS;
        boolean guessedCorrectly = false;

        System.out.println("\n=== Round " + currentRound + " ===");
        System.out.println("Guess a number between " + RANGE_START + " and " + RANGE_END);

        while (attemptsLeft > 0 && !guessedCorrectly) {
            System.out.print("Attempts left: " + attemptsLeft + " - Enter your guess: ");
            int userGuess = scanner.nextInt();

            if (userGuess == numberToGuess) {
                guessedCorrectly = true;
                int roundScore = calculateRoundScore(attemptsLeft);
                totalScore += roundScore;
                System.out.println("Congratulations! You guessed the number.");
                System.out.println("You earned " + roundScore + " points this round.");
            } else if (userGuess < numberToGuess) {
                System.out.println("Your guess is too low.");
            } else {
                System.out.println("Your guess is too high.");
            }
            attemptsLeft--;
        }

        if (!guessedCorrectly) {
            System.out.println("Sorry, you've used all your attempts.");
            System.out.println("The number was: " + numberToGuess);
        }
    }

    private static int calculateRoundScore(int attemptsLeft) {
        // More points for fewer attempts used
        return POINTS_PER_ROUND * attemptsLeft / MAX_ATTEMPTS;
    }
} 