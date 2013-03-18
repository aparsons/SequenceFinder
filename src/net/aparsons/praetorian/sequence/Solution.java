package net.aparsons.praetorian.sequence;

import static net.aparsons.praetorian.sequence.SequenceFinder.PRNG;
import java.util.ArrayList;

public class Solution {

    public final ArrayList<Integer> sequence;

    public Solution(ArrayList<Integer> sequence) {
        this.sequence = sequence;
    }
    
    public Solution(ArrayList<Integer> options, int size) {
        sequence = new ArrayList<>();
        while (sequence.size() < size) {
            int pos = PRNG.nextInt(options.size());
            sequence.add(options.get(pos));
            options.remove(pos);
        }
    } 

    public int getChosen(ArrayList<Integer> selection) {
        int count = 0;
        for (Integer i : selection) {
            if (sequence.contains(i)) {
                count++;
            }
        }
        return count;
    }
    
    public int getUsed(ArrayList<Integer> selection) {
        int count = 0;
        for (int i = 0; i < selection.size(); i++) {
            if (selection.get(i).equals(sequence.get(i))) {
                count++;
            }
        }
        return count;
    }
    
    @Override
    public String toString() {
        return sequence.toString();
    }
 
}
