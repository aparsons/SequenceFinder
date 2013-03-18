package net.aparsons.praetorian.sequence;

public class SequenceFinder {
   
    public static void main(String[] args) {
       
        SolutionList solutions = new SolutionList();
        
        int count = 1;
        for (Solution solution : solutions.solutions) {
            System.out.println(count + ":" + solution);
            count++;
        }
               
    }
    
     
}
