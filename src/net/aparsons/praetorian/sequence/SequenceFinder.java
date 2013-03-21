package net.aparsons.praetorian.sequence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import net.aparsons.praetorian.sequence.core.SequenceGraph;
import net.aparsons.praetorian.sequence.core.Solver;
import net.aparsons.praetorian.sequence.test.SolverTest;

public class SequenceFinder {
   
    public static void main(String[] args) {
        console();
    }
    
    private static void test() {
        new SolverTest(1000).run();
    }
    
    private static void console() {        
        
        String input = "";
        do {
            Solver solver = new Solver();
            
            int move = 1;
            System.out.println("Starting new sequence...GOOD LUCK");
            System.out.println(move + ":" + SequenceGraph.getInitialGuess());
            
            while (move < 6) {
                
                System.out.print(": ");
                input = getInput();
                if (input.equalsIgnoreCase("n") || input.equalsIgnoreCase("q")) {
                    break;
                }
                
                try {
                    int inside = Integer.parseInt(input.split(",")[0]);
                    int correct = Integer.parseInt(input.split(",")[1]);
                    
                    move++;
                    System.out.println("(" + inside + "," + correct + ") => " + solver.getNext(inside, correct) + " Move: " + move);
                    
                } catch (ArrayIndexOutOfBoundsException aioobe) {
                    break;
                }
                
                
            }
            
            System.out.println("\n");

        } while (!input.equalsIgnoreCase("q"));
    }
    
    private static String getInput() {
        String line = "";
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(System.in); 
            BufferedReader reader = new BufferedReader(inputStreamReader); 
            line = reader.readLine();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return line;
    }
    
}
