package l1z1.LocalSearch.FirstSolution;

import java.util.ArrayList;

public class FixedSolution extends FirstSolutionBase {

    /**
     * Funkcja generuje rozwiązanie (100,100,100,100)
     * @return wygenerowane rozwiązanie
     */
    @Override
    public ArrayList<Double> getFirstSolution() {
        ArrayList<Double> point = new ArrayList<>();
        point.add(100.0);
        point.add(100.0);
        point.add(100.0);
        point.add(100.0);
        return point;
    }
}
