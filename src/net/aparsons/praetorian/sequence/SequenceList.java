package net.aparsons.praetorian.sequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

public class SequenceList {

    private static ArrayList<ArrayList<Integer>> sequences;
    
    private SequenceList() {
    }
    
    public static ArrayList<ArrayList<Integer>> getList() {
        if (sequences == null) {
            build();
        }
        
        return new ArrayList<>(sequences);
    }
    
    private static void build() {
        HashSet<ArrayList<Integer>> list = new HashSet<>();
        
        ICombinatoricsVector<Integer> initialVector = Factory.createVector(new Integer[]{0, 1, 2, 3, 4, 5});
        Generator<Integer> gen = Factory.createPermutationGenerator(initialVector);
        for (ICombinatoricsVector<Integer> c : gen) {
            list.add(new ArrayList(Arrays.asList(new Integer[]{ c.getValue(0), c.getValue(1), c.getValue(2), c.getValue(3) })));
        }
        
        sequences = new ArrayList<>(list);
    }
    
}
