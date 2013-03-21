package net.aparsons.praetorian.sequence.core;

import java.util.ArrayList;
import net.aparsons.praetorian.sequence.core.SequenceGraph.Answer;
import net.aparsons.praetorian.sequence.core.SequenceGraph.Guess;

public class Solver {
    
    private static final SequenceGraph graph = new SequenceGraph();
    private Guess position;
    
    public Solver() {
        position = graph.getRoot();
    }
    
    public ArrayList<Integer> getNext(int inside, int correct) {
        Answer answer = null;
        for (Answer child : position.getChildren()) {
            if (inside == child.getInside() && correct == child.getCorrect()) {
                answer = child;
                break;
            }
        }
        if (answer == null) {
            throw new IllegalArgumentException("Solution not found");
        }
        
        // Find option with most available solutions
        int largestValue = Integer.MIN_VALUE;
        int largestIndex = 0;
        for (int i = 0; i < answer.getChildren().size(); i++) {
            Guess guess = answer.getChildren().get(i);
            
            if (guess.getSolutions().size() > largestValue) {
                largestValue = guess.getSolutions().size();
                largestIndex = i;
            }
        }
        
        position = answer.getChildren().get(largestIndex);
        
        return position.getGuess();
    }

}
