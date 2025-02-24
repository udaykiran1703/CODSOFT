package codesoft;
import java.util.*;
import java.util.concurrent.*;

class Quiz {
    private static class Question {
        String question;
        String[] options;
        int correctOption;

        Question(String question, String[] options, int correctOption) {
            this.question = question;
            this.options = options;
            this.correctOption = correctOption;
        }
    }

    private static final List<Question> questions = new ArrayList<>();
    private static int score = 0;
    private static final int TIME_LIMIT = 10; // seconds per question

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeQuestions();

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println("\nQuestion " + (i + 1) + ": " + q.question);
            for (int j = 0; j < q.options.length; j++) {
                System.out.println((j + 1) + ". " + q.options[j]);
            }

            int userAnswer = getUserAnswer(scanner);

            if (userAnswer == q.correctOption) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Wrong! The correct answer was: " + q.correctOption);
            }
        }

        displayResults();
        scanner.close();
    }

    private static void initializeQuestions() {
        questions.add(new Question("What is the capital of india?", new String[]{"hyderabad", "chennai", "new delhi", "mumbai"}, 3));
        questions.add(new Question("Who is the founder of java?", new String[]{"guido van rossum", "james Gosling", "dennis Ritchie", "brendan eich"}, 2));
        questions.add(new Question("Which country has highest gdp?", new String[]{"USA", "China", "Australia", "india"}, 1));
    }

    private static int getUserAnswer(Scanner scanner) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = executor.submit(() -> {
            System.out.print("Your answer (1-4): ");
            return scanner.nextInt();
        });

        try {
            return future.get(TIME_LIMIT, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            System.out.println("\nTime's up! Moving to the next question.");
            return -1;
        } catch (Exception e) {
            return -1;
        } finally {
            executor.shutdownNow();
        }
    }

    private static void displayResults() {
        System.out.println("\nQuiz Over!");
        System.out.println("Your final score: " + score + "/" + questions.size());
    }
}
