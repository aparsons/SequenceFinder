package net.aparsons.praetorian.sequence;

public class SequenceFinder {
   
    public static void main(String[] args) {
       
        SequenceGraph graph = new SequenceGraph();
        SolutionList solutionList = new SolutionList();
        
        System.out.println("Permutations: " + solutionList.solutions.size());
        for (Solution solution : solutionList.solutions) {
            graph.addSolution(solution.sequence);
        }

        graph.print();
    }
    
     
}
