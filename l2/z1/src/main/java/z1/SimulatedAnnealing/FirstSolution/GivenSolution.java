package z1.SimulatedAnnealing.FirstSolution;

import java.util.ArrayList;

public class GivenSolution extends FirstSolutionBase {
    ArrayList<Double> solution;

    public GivenSolution(ArrayList<Double> solution){
        this.solution = solution;
    }

    /**
     * Funkcja zwraca rozwiązanie podane w konstruktorze
     * @return wygenerowane rozwiązanie
     */
    @Override
    public ArrayList<Double> getFirstSolution() {
        return solution;
    }
}
