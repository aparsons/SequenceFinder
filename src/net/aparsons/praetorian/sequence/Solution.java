package net.aparsons.praetorian.sequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class Solution {

    public static final ArrayList<Integer> OPTIONS = new ArrayList<>(Arrays.asList(new Integer[]{ 0, 1, 2, 3, 4, 5 })); 
    public static final int SIZE = 4;
    
    private Random prng = new Random();
    public final ArrayList<Integer> sequence;

    public Solution(ArrayList<Integer> sequence) {
        this.sequence = sequence;
    }

    public Solution(Integer[] sequence) {
        this.sequence = new ArrayList<>(Arrays.asList(sequence));
    }
    
    public Solution() {
        sequence = new ArrayList<>();
        ArrayList<Integer> options = (ArrayList<Integer>) OPTIONS.clone();
        while (sequence.size() < SIZE) {
            int pos = prng.nextInt(options.size());
            sequence.add(options.get(pos));
            options.remove(pos);
        }
    } 

    /**
     * Gets count of selection within solution
     * @param selection
     * @return 
     */
    public int getInsideCount(ArrayList<Integer> selection) {
        int count = 0;
        for (Integer i : selection) {
            if (sequence.contains(i)) {
                count++;
            }
        }
        return count;
    }
    
    /**
     * Gets count of selection in the correct position
     * @param selection
     * @return 
     */
    public int getCorrectCount(ArrayList<Integer> selection) {
        int count = 0;
        for (int i = 0; i < selection.size(); i++) {
            if (selection.get(i).equals(sequence.get(i))) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int hashCode() {
        return sequence.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Solution other = (Solution) obj;
        if (!Objects.equals(this.sequence, other.sequence)) {
            return false;
        }
        return true;
    }
  
    
    
    @Override
    public String toString() {
        return sequence.toString();
    }
 
}
