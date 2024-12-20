import java.util.*;

public class QuizApp {

    static class Player {
        String name;
        int age;
        int amountWon = 0;
        boolean usedLifeline1 = false;
        boolean usedLifeline2 = false;

        Player(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Collect player details
        System.out.println("Enter your name: ");
        String name = sc.nextLine();
        System.out.println("Enter your age: ");
        int age = sc.nextInt();
        sc.nextLine(); // Consume the leftover newline

        Player player = new Player(name, age);
        System.out.println("Welcome, " + player.name + "!");

        // Ask if the player is ready
        System.out.println("Are you ready to take up the quiz game? (yes/no): ");
        String ready = sc.nextLine();
        if (!ready.equalsIgnoreCase("yes")) {
            System.out.println("Game terminated. Thank you!");
            return;
        }

        // Display rules
        System.out.println("\n--- Rules ---");
        System.out.println("1. Each question has 4 options, choose the correct one.");
        System.out.println("2. Two lifelines are available but can only be used once.");
        System.out.println("3. Wrong answer ends the game with the amount won so far.\n");

        // Questions and answers
        String[] questions = {
            "What is the capital of France?",
            "Which planet is known as the Red Planet?",
            "Who wrote 'Hamlet'?",
            "In which state our indian capitalis there?",
            "Which animal is known as the 'Ship of the desert'?",
            "How many days are there in a week?",
            "Rainbow consist of how many colours?",
            "Name the national bird of india?",
            "Name a natural satellite of earth?",
            "Who gave the universal law of gravitation?"
        };

        String[][] options = {
            {"1. Paris", "2. London", "3. Rome", "4. Berlin"},
            {"1. Venus", "2. Mars", "3. Jupiter", "4. Saturn"},
            {"1. Charles Dickens", "2. William Shakespeare", "3. Mark Twain", "4. Leo Tolstoy"},
            {"1."},
            {
            {"1. Moon", "2. sun", "3. planet", "4. Earth"},
            {"1. newton", "2. newtonrings", "3. issac newton", "4. marsnewton"}
        };

        int[] correctAnswers = {1, 2, 2}; // Correct options
        int[] rewards = {1000, 2000, 5000}; // Rewards per question

        for (int i = 0; i < questions.length; i++) {
            System.out.println("\nQuestion " + (i + 1) + ": " + questions[i]);
            for (String option : options[i]) {
                System.out.println(option);
            }

            System.out.println("\nDo you want to use a lifeline? (yes/no): ");
            String useLifeline = sc.nextLine();

            if (useLifeline.equalsIgnoreCase("yes")) {
                if (!player.usedLifeline1 || !player.usedLifeline2) {
                    System.out.println("Available lifelines:");
                    if (!player.usedLifeline1) System.out.println("1. 50-50");
                    if (!player.usedLifeline2) System.out.println("2. Expert Advice");

                    System.out.println("Choose a lifeline (1/2): ");
                    int lifelineChoice = sc.nextInt();
                    sc.nextLine(); // Consume leftover newline

                    if (lifelineChoice == 1 && !player.usedLifeline1) {
                        player.usedLifeline1 = true;
                        System.out.println("50-50 Lifeline used. Options:");
                        System.out.println(options[i][correctAnswers[i] - 1] + " and one other incorrect option.");
                    } else if (lifelineChoice == 2 && !player.usedLifeline2) {
                        player.usedLifeline2 = true;
                        System.out.println("Expert Advice Lifeline used. Expert says the answer is: " + options[i][correctAnswers[i] - 1]);
                    } else {
                        System.out.println("Lifeline not available. Choose an answer directly.");
                    }
                } else {
                    System.out.println("No lifelines left. Choose an answer directly.");
                }
            }

            System.out.println("Enter your answer (1/2/3/4): ");
            int answer = sc.nextInt();
            sc.nextLine(); // Consume leftover newline

            if (answer == correctAnswers[i]) {
                player.amountWon += rewards[i];
                System.out.println("Correct! You've won ₹" + player.amountWon);
            } else {
                System.out.println("Wrong answer! You've won ₹" + player.amountWon + ". Game over.");
                return;
            }
        }

        System.out.println("\nCongratulations, " + player.name + "! You've completed the quiz and won ₹" + player.amountWon);
    }
}
