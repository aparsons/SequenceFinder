package net.aparsons.praetorian.sequence;

import java.util.ArrayList;
import java.util.Arrays;

@Deprecated
public class Submission {
    
    public final ArrayList<Integer> sequence;
    public int inside;
    public int correct;   

    public Submission(ArrayList<Integer> sequence) {
        this.sequence = sequence;
    }

    public Submission(Integer[] sequence) {
        this.sequence = new ArrayList<>(Arrays.asList(sequence));
    }
    
    @Override
    public String toString() {
        return sequence + " (" + inside + "," + correct + ")";
    }
    
}
