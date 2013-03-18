package net.aparsons.praetorian.sequence;

import java.util.ArrayList;
import java.util.Arrays;

public class Submission {
    
    public final ArrayList<Integer> sequence;
    public int chosen;
    public int used;   

    public Submission(ArrayList<Integer> sequence) {
        this.sequence = sequence;
    }

    public Submission(Integer[] sequence) {
        this.sequence = new ArrayList<>(Arrays.asList(sequence));
    }
    
    @Override
    public String toString() {
        return sequence + " (" + chosen + "," + used + ")";
    }
    
}
