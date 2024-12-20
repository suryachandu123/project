import java.util.*;

public class quiz {

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

        // Define the questions and answers
        String[] questions = {
            "What is the capital of France?",
            "Which planet is known as the Red Planet?",
            "Who wrote 'Hamlet'?",
            "What is the largest ocean on Earth?",
            "Which element has the chemical symbol 'O'?",
            "Who painted the Mona Lisa?",
            "What is the square root of 64?",
            "What is the smallest prime number?",
            "In which year did the Titanic sink?",
            "What is the chemical formula for water?"
        };

        String[][] options = {
            {"1. Paris", "2. London", "3. Rome", "4. Berlin"},
            {"1. Venus", "2. Mars", "3. Jupiter", "4. Saturn"},
            {"1. Charles Dickens", "2. William Shakespeare", "3. Mark Twain", "4. Leo Tolstoy"},
            {"1. Atlantic Ocean", "2. Indian Ocean", "3. Pacific Ocean", "4. Arctic Ocean"},
            {"1. Oxygen", "2. Hydrogen", "3. Gold", "4. Helium"},
            {"1. Vincent van Gogh", "2. Leonardo da Vinci", "3. Pablo Picasso", "4. Claude Monet"},
            {"1. 6", "2. 8", "3. 7", "4. 9"},
            {"1. 0", "2. 1", "3. 2", "4. 3"},
            {"1. 1912", "2. 1905", "3. 1920", "4. 1915"},
            {"1. H2O", "2. CO2", "3. O2", "4. NaCl"}
        };

        int[] correctAnswers = {1, 2, 2, 3, 1, 2, 2, 3, 1, 1}; // Correct options (1-based index)
        int[] rewards = {1000, 2000, 5000, 10000, 20000, 50000, 100000, 200000, 500000, 1000000}; // Rewards per question

        // Start the game
        for (int i = 0; i < questions.length; i++) {
            System.out.println("\nQuestion " + (i + 1) + ": " + questions[i]);
            for (String option : options[i]) {
                System.out.println(option);
            }

            // Ask if the player wants to use a lifeline
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
                        // Show correct answer and one incorrect option
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

            // Take the player's answer
            System.out.println("Enter your answer (1/2/3/4): ");
            int answer = sc.nextInt();
            sc.nextLine(); // Consume leftover newline

            // Check if the answer is correct
            if (answer == correctAnswers[i]) {
                player.amountWon += rewards[i];
                System.out.println("Correct! You've won ₹" + player.amountWon);
            } else {
                System.out.println("Wrong answer! You've won ₹" + player.amountWon + ". Game over.");
                return;
            }
        }

        // If the player completes all questions
        System.out.println("\nCongratulations, " + player.name + "! You've completed the quiz and won ₹" + player.amountWon);
    }
}
