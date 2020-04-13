package z3.SimulatedAnnealing.FirstSolution;

import java.util.ArrayList;

public class GivenSolution extends FirstSolutionBase {
    ArrayList<String> solution;

    public GivenSolution(String solution){
        ArrayList<String> path = new ArrayList<>();
        for(int i=0; i<solution.length(); i++){
            path.add(solution.charAt(i)+"");
        }
        this.solution = path;
    }

    /**
     * Funkcja generuje rozwiązanie podane w konstruktorze
     * @return wygenerowane rozwiązanie
     */
    @Override
    public ArrayList<String> getFirstSolution() {
        return solution;
    }
}
