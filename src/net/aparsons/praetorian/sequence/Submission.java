package net.aparsons.praetorian.sequence;

import java.util.ArrayList;

public class Submission {
    
    public ArrayList<Integer> sequence = new ArrayList<>(SequenceFinder.CHOICES);
    public int chosen;
    public int used;   
    
    @Override
    public String toString() {
        return sequence + " (" + chosen + "," + used + ")";
    }
    
}
