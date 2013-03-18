package net.aparsons.praetorian.sequence;

import java.util.ArrayList;
import java.util.HashSet;
import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

/**
 * Generates the full range of possible solutions
 */
public class SolutionList {
    
    public final ArrayList<Solution> solutions;
    
    public SolutionList() {
        solutions = new ArrayList<>(360);
        solutions.addAll(build());
    }
    
    private ArrayList<Solution> build() {
        HashSet<Solution> sequences = new HashSet<>();
        
        ICombinatoricsVector<Integer> initialVector = Factory.createVector(new Integer[]{0, 1, 2, 3, 4, 5});
        Generator<Integer> gen = Factory.createPermutationGenerator(initialVector);
        for (ICombinatoricsVector<Integer> c : gen) {
            sequences.add(new Solution(new Integer[]{ c.getValue(0), c.getValue(1), c.getValue(2), c.getValue(3) }));
        }
        
        return new ArrayList<>(sequences);
    }
}
