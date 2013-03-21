package net.aparsons.praetorian.sequence.test;

import java.util.ArrayList;
import java.util.Random;
import net.aparsons.praetorian.sequence.core.SequenceGraph;
import net.aparsons.praetorian.sequence.core.SequenceList;
import net.aparsons.praetorian.sequence.core.Solver;

public class SolverTest implements Runnable {

    final int count;

    public SolverTest(int count) {
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 1; i <= count; i++) {
            Solver solver = new Solver();
            
            ArrayList<Integer> solution = getRandomSolution();
            
            System.out.println(i + ":" + solution);
            
            ArrayList<Integer> guess = SequenceGraph.getInitialGuess();
            int moves = 1;
            int inside = SequenceGraph.calcInsideCount(guess, solution);
            int correct = SequenceGraph.calcCorrectCount(guess, solution);
            
            System.out.println("\t" + "(" + inside + "," + correct + ") => " + guess + ":" + moves);
            
            do {
                inside = SequenceGraph.calcInsideCount(guess, solution);
                correct = SequenceGraph.calcCorrectCount(guess, solution);
                
                guess = solver.getNext(inside, correct);
                moves++;
                System.out.println("\t" + "(" + inside + "," + correct + ") => " + guess + ":" + moves);
                
            } while (guess != solution);
            
            if (moves > 6) {
                throw new RuntimeException("EXCEEDED 6 MOVES");
            }

        }
    }
    
    public static ArrayList<Integer> getRandomSolution() {
        return SequenceList.getList().get(new Random().nextInt(SequenceList.getList().size()));
    }
    
}
