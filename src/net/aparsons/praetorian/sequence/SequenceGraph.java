package net.aparsons.praetorian.sequence;

import java.util.ArrayList;
import java.util.Arrays;

public class SequenceGraph {
    
    private static final ArrayList<Integer> INITAL_GUESS = new ArrayList<>(Arrays.asList(new Integer[]{ 0, 1, 2, 3 }));
    
    ArrayList<ArrayList<Integer>> solutions = new ArrayList<>();
    ArrayList<Answer> answers = new ArrayList<>();

    public SequenceGraph() {
        for (ArrayList<Integer> solution : SequenceList.getList()) {
            addSolution(solution);
        }
        
        for (Answer answer : answers) {
            ArrayList<ArrayList<Integer>> solutions = answer.getSolutions();
            for (Guess parent : answer.guesses) {
                for (ArrayList<Integer> solution : solutions) {
                    parent.addChild(new Answer(new Guess(parent, solution)));
                }
            }
        }
    }
    
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
        
        for (Answer a0 : answers) {
            printAnswer(a0, sb);
            for (Guess g0 : a0.guesses) {
                for (Answer a1 : g0.children) {
                    sb.append("\t");
                    printAnswer(a1, sb);
                }
            }
        }
        
        System.out.println(sb.toString());
    }
    
    private void printAnswer(Answer answer, StringBuilder sb) {
        sb.append("(").append(answer.inside).append(",").append(answer.correct).append(") Size: ").append(answer.guesses.size()).append(" ");
        for (Guess guess : answer.guesses) {
            sb.append(guess.solution).append(" ");
        }
        sb.append("\n");
    }
    
    class Guess {
        
        final Guess parent;
        final ArrayList<Integer> guess, solution;
        final int inside, correct;
        
        ArrayList<Answer> children = new ArrayList<>();

        public Guess(ArrayList<Integer> guess, ArrayList<Integer> solution) {
            this.parent = null;
            this.guess = guess;
            this.solution = solution;
            this.inside = getInsideCount();
            this.correct = getCorrectCount();
        }

        public Guess(Guess parent, ArrayList<Integer> guess) {
            this.parent = parent;
            this.guess = guess;
            this.solution = parent.solution;
            this.inside = getInsideCount();
            this.correct = getCorrectCount();
        }
        
        public void addChild(Guess guess) {
            // TODO Retry impl with guess obj
        }
        
        public void addChild(Answer answer) {
            // TODO Check if in parents
            
            boolean isFound = false;
            for (Answer child : children) {
                if (answer.inside == child.inside && answer.correct == child.correct) {
                    isFound = true;
                    child.guesses.addAll(answer.guesses);
                    break;
                }
            }

            if (!isFound) {
                children.add(answer);
            }
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
        
        public ArrayList<ArrayList<Integer>> getSolutions() {
            ArrayList<ArrayList<Integer>> solutions = new ArrayList<>();
            for (Guess guess : guesses) {
                solutions.add(guess.solution);
            }
            return solutions;
        }
  
    }
    
}
