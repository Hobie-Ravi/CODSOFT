package Mini.Quiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

record QuizQuestion(String question, List<String> options, int correctOption) {}

class Quiz {
    private final List<QuizQuestion> questions;
    private int currentQuestionIndex;
    private int userScore;
    private final Timer timer;
    private boolean timeExpired;

    public Quiz(List<QuizQuestion> questions) {
        this.questions = questions;
        this.currentQuestionIndex = 0;
        this.userScore = 0;
        this.timeExpired = false;
        this.timer = new Timer();
    }

    public void startQuiz() {
        if (questions.isEmpty()) {
            System.out.println("No questions available for the quiz.");
            return;
        }

        System.out.println("Welcome to the Quiz!");

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timeExpired = true;
                displayNextQuestion();
            }
        }, 15000);

        displayNextQuestion();
    }

    private void displayNextQuestion() {
        if (currentQuestionIndex < questions.size()) {
            QuizQuestion currentQuestion = questions.get(currentQuestionIndex);

            System.out.println("\nQuestion " + (currentQuestionIndex + 1) + ": " + currentQuestion.question());
            List<String> options = currentQuestion.options();
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ". " + options.get(i));
            }

            if (!timeExpired) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Select your answer (1-" + options.size() + "): ");
                int userAnswer = scanner.nextInt();

                if (userAnswer == currentQuestion.correctOption()) {
                    System.out.println("Correct! +1 point");
                    userScore++;
                } else {
                    System.out.println("Incorrect! The correct answer was " + currentQuestion.correctOption());
                }

                currentQuestionIndex++;
                timeExpired = false;
                displayNextQuestion();
            } else {
                System.out.println("Time's up! Moving to the next question.");
                currentQuestionIndex++;
                timeExpired = false;
                displayNextQuestion();
            }
        } else {
            endQuiz();
        }
    }

    private void endQuiz() {
        timer.cancel();
        System.out.println("\nQuiz Completed!");
        System.out.println("Your Final Score: " + userScore + " out of " + questions.size());
        System.exit(0);
    }
}

public class QuizApp {
    public static void main(String[] args) {
        List<QuizQuestion> quizQuestions = new ArrayList<>();
        quizQuestions.add(new QuizQuestion("What is the capital of India?", List.of("Delhi", "Mumbai", "Kolkata", "Chennai"), 0));
        quizQuestions.add(new QuizQuestion("Which river is known as the Ganges in India?", List.of("Yamuna", "Brahmaputra", "Indus", "Ganga"), 3));
        quizQuestions.add(new QuizQuestion("What is the national animal of India?", List.of("Tiger", "Elephant", "Lion", "Leopard"), 0));
        quizQuestions.add(new QuizQuestion("Who was the first Prime Minister of India?", List.of("Jawaharlal Nehru", "Indira Gandhi", "Rajiv Gandhi", "Manmohan Singh"), 0));
        quizQuestions.add(new QuizQuestion("Which city is known as the 'Pink City' of India?", List.of("Jaipur", "Jodhpur", "Udaipur", "Bikaner"), 0));
        quizQuestions.add(new QuizQuestion("Which Indian state is known as the 'Land of Five Rivers'?", List.of("Punjab", "Haryana", "Uttar Pradesh", "Gujarat"), 0));
        quizQuestions.add(new QuizQuestion("Who wrote the Indian National Anthem?", List.of("Rabindranath Tagore", "Mahatma Gandhi", "Jawaharlal Nehru", "Sarojini Naidu"), 0));
        quizQuestions.add(new QuizQuestion("In which year did India gain independence from British rule?", List.of("1947", "1950", "1945", "1962"), 0));
        quizQuestions.add(new QuizQuestion("What is the national currency of India?", List.of("Indian Rupee", "Indian Dollar", "Indian Peso", "Indian Euro"), 0));
        quizQuestions.add(new QuizQuestion("Which city hosts the annual Kumbh Mela?", List.of("Prayagraj", "Varanasi", "Haridwar", "Nashik"), 0));
        quizQuestions.add(new QuizQuestion("What is the highest mountain peak in India?", List.of("Kangchenjunga", "Mount Everest", "Nanda Devi", "K2"), 0));
        quizQuestions.add(new QuizQuestion("Which is the largest state by area in India?", List.of("Rajasthan", "Madhya Pradesh", "Maharashtra", "Uttar Pradesh"), 0));
        quizQuestions.add(new QuizQuestion("Which Indian city is known as the 'City of Dreams'?", List.of("Mumbai", "Delhi", "Kolkata", "Bangalore"), 0));
        quizQuestions.add(new QuizQuestion("Who was the 'Father of the Indian Constitution'?", List.of("B.R. Ambedkar", "Mahatma Gandhi", "Jawaharlal Nehru", "Sardar Vallabhbhai Patel"), 0));
        quizQuestions.add(new QuizQuestion("What is the name of India's highest civilian award?", List.of("Bharat Ratna", "Padma Vibhushan", "Padma Bhushan", "Padma Shri"), 0));
        quizQuestions.add(new QuizQuestion("Which is the largest saltwater lake in India?", List.of("Chilika Lake", "Dal Lake", "Wular Lake", "Pulicat Lake"), 0));
        quizQuestions.add(new QuizQuestion("In which state is the hill station 'Ooty' located?", List.of("Tamil Nadu", "Kerala", "Karnataka", "Andhra Pradesh"), 0));
        quizQuestions.add(new QuizQuestion("What is the national flower of India?", List.of("Lotus", "Rose", "Jasmine", "Sunflower"), 0));
        quizQuestions.add(new QuizQuestion("Which Indian state is known as the 'Spice Garden of India'?", List.of("Kerala", "Karnataka", "Tamil Nadu", "Andhra Pradesh"), 0));
        quizQuestions.add(new QuizQuestion("Who is known as the 'Missile Man of India'?", List.of("APJ Abdul Kalam", "Jawaharlal Nehru", "Indira Gandhi", "Sardar Vallabhbhai Patel"), 0));

        Quiz quiz = new Quiz(quizQuestions);
        quiz.startQuiz();
    }
}
