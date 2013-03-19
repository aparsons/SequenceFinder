package net.aparsons.praetorian.sequence;

import java.util.ArrayList;
import java.util.Arrays;

public class SequenceGraph {
    
    private static final ArrayList<Integer> INITAL_GUESS = new ArrayList<>(Arrays.asList(new Integer[]{ 0, 1, 2, 3 }));
    
    ArrayList<ArrayList<Integer>> solutions = new ArrayList<>();
    ArrayList<Answer> answers = new ArrayList<>();
    
    public void addSolution(ArrayList<Integer> solution) {
        if (solution == null || solutions.contains(solution)) {
            throw new IllegalArgumentException();
        }
        
        solutions.add(solution);
        
        Guess guess = new Guess(INITAL_GUESS, solution);
        
        boolean isFound = false;
        
        for (Answer answer : answers) {
            if (guess.inside == answer.inside && guess.correct == answer.correct) {
                isFound = true;
                answer.guesses.add(guess);
                break;
            }
        }
        
        if (!isFound) {
            answers.add(new Answer(guess));
        }
    }
    
    void print() {
        StringBuilder sb = new StringBuilder();
        
        for (Answer answer : answers) {
            sb.append("(").append(answer.inside).append(",").append(answer.correct).append(") Size: ").append(answer.guesses.size()).append("\n");
        }
        
        System.out.println(sb.toString());
    }
    
    class Guess {
        
        final ArrayList<Integer> guess, solution;
        final int inside, correct;

        public Guess(ArrayList<Integer> guess, ArrayList<Integer> solution) {
            this.guess = guess;
            this.solution = solution;
            this.inside = getInsideCount();
            this.correct = getCorrectCount();
        }
        
        private int getInsideCount() {
            int count = 0;
            for (Integer i : guess) {
                if (solution.contains(i)) {
                    count++;
                }
            }
            return count;
        }
        
        private int getCorrectCount() {
            int count = 0;
            for (int i = 0; i < solution.size(); i++) {
                if (guess.get(i).equals(solution.get(i))) {
                    count++;
                }
            }
            return count;
        }
        
    }
    
    class Answer {
        
        ArrayList<Guess> guesses = new ArrayList<>();
        
        final int inside;
        final int correct;
        
        public Answer(Guess guess) {
            guesses.add(guess);
            this.inside = guess.inside;
            this.correct = guess.correct;
        }
  
    }
    
}
