package net.aparsons.praetorian.sequence.core;

import java.util.ArrayList;
import java.util.Arrays;

public class SequenceGraph {

    private static final ArrayList<Integer> INITAL_GUESS = new ArrayList<>(Arrays.asList(new Integer[]{0, 1, 2, 3}));
    private static final int MAX_DEPTH = 6 * 2;
    private Guess root;

    public SequenceGraph() {
        root = new Guess(INITAL_GUESS);
    }

    public Guess getRoot() {
        return root;
    }

    public void prettyPrint() {
        root.print();
    }

    protected class Guess {

        private final ArrayList<Integer> guess;
        private final ArrayList<ArrayList<Integer>> solutions; // Possible Solutions
        private final Guess parent;
        private ArrayList<Answer> children = new ArrayList<>();
        private final int depth;

        public Guess(ArrayList<Integer> guess) {
            this(null, null, guess);
        }

        public Guess(Guess parent, ArrayList<ArrayList<Integer>> solutions, ArrayList<Integer> guess) {
            this.parent = parent;
            this.guess = guess;
            this.solutions = (solutions == null) ? SequenceList.getList() : solutions;
            this.depth = (parent == null) ? 0 : parent.depth + 2;

            if (this.depth <= MAX_DEPTH) {
                for (ArrayList<Integer> solution : this.solutions) {

                    int inside = SequenceGraph.calcInsideCount(guess, solution);
                    int correct = SequenceGraph.calcCorrectCount(guess, solution);

                    boolean isFound = false;
                    for (Answer child : children) {
                        if (inside == child.inside && correct == child.correct) {
                            isFound = true;
                            child.solutions.add(solution);
                            break;
                        }
                    }

                    if (!isFound) {
                        children.add(new Answer(this, solution, inside, correct));
                    }
                }

                for (Answer child : children) {
                    child.build();
                }
            }
        }

        public ArrayList<Answer> getChildren() {
            return children;
        }

        public ArrayList<Integer> getGuess() {
            return guess;
        }
        
        public ArrayList<ArrayList<Integer>> getSolutions() {
            return solutions;
        }

        public void print() {
            for (int i = 0; i < depth; i++) {
                System.out.print("\t");
            }
            System.out.print(guess + ":" + solutions.size());
            for (Answer child : children) {
                child.print();
            }
        }
    }

    protected class Answer {

        private final int inside, correct;
        private ArrayList<ArrayList<Integer>> solutions = new ArrayList<>(); // All possible solutions
        private final Guess parent;
        private ArrayList<Guess> children = new ArrayList<>();

        public Answer(Guess parent, ArrayList<Integer> solution, int inside, int correct) {
            this.parent = parent;
            this.solutions.add(solution);
            this.inside = inside;
            this.correct = correct;
        }

        public void build() {
            for (ArrayList<Integer> solution : solutions) {
                if (parent.guess != solution) {
                    children.add(new Guess(parent, solutions, solution));
                }
            }
        }

        public int getInside() {
            return inside;
        }

        public ArrayList<Guess> getChildren() {
            return children;
        }

        public int getCorrect() {
            return correct;
        }

        public ArrayList<ArrayList<Integer>> getSolutions() {
            return solutions;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("(").append(inside).append(",").append(correct).append("):").append(solutions.size()).append(" ").append(solutions);
            for (Guess child : children) {
                sb.append("\n");
                sb.append(child);
            }
            return sb.toString();
        }

        private void print() {
            System.out.print("\n");

            for (int i = 0; i < parent.depth + 1; i++) {
                System.out.print("\t");
            }

            System.out.print("(" + inside + "," + correct + "):" + solutions.size() + " " + solutions);
            for (Guess child : children) {
                System.out.print("\n");
                child.print();
            }
        }
    }

    public static ArrayList<Integer> getInitialGuess() {
        return INITAL_GUESS;
    }
    
    public static int calcInsideCount(ArrayList<Integer> guess, ArrayList<Integer> solution) {
        int count = 0;
        for (Integer i : guess) {
            if (solution.contains(i)) {
                count++;
            }
        }
        return count;
    }

    public static int calcCorrectCount(ArrayList<Integer> guess, ArrayList<Integer> solution) {
        int count = 0;
        for (int i = 0; i < solution.size(); i++) {
            if (guess.get(i).equals(solution.get(i))) {
                count++;
            }
        }
        return count;
    }
}
