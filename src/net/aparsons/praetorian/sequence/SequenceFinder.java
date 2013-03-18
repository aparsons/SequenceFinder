package net.aparsons.praetorian.sequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class SequenceFinder {

    public final static Integer[] OPTIONS = { 0, 1, 2, 3, 4, 5 };
    public final static int CHOICES = 4;
    public final static int MAX_MOVES = 6;
    public final static Random PRNG = new Random();
    
    public static void main(String[] args) {
        ArrayList<Integer> options = new ArrayList<>(Arrays.asList(OPTIONS));
        
        //Integer[] sol = { 3,2,5,4 };
        //ArrayList<Integer> s = new ArrayList<>(Arrays.asList(sol));
        //Solution solution = new Solution(s);
        
        Solution solution = new Solution(options, 4);
        System.out.println(solution);
        
        ArrayList<Submission> submissions = new ArrayList<>();
        
        Submission initial = new Submission();
        initial.sequence.add(0);
        initial.sequence.add(1);
        initial.sequence.add(2);
        initial.sequence.add(3);
        initial.chosen = solution.getChosen(initial.sequence);
        initial.used = solution.getUsed(initial.sequence);
        
        submissions.add(initial);
        
        System.out.println(initial);
        
        int chosen = initial.chosen;
        int used = initial.used;
        int moves = 1;
        
        ArrayList<Integer> knowns = new ArrayList<>();
        while ((chosen < CHOICES && used < CHOICES) && moves < MAX_MOVES) {
 
            
            moves++;
        }
        
               
    }

    
    
            
}
