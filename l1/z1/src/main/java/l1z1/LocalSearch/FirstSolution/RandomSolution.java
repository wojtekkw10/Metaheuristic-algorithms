package l1z1.LocalSearch.FirstSolution;

import java.util.ArrayList;

import static java.lang.Math.random;

public class RandomSolution extends FirstSolutionBase {

    /**
     * Funkcja zwraca losowy punkt (a,b,c,d) gdzie a,b,c,d \in [0,100]
     * @return wygenerowane rozwiązanie
     */
    @Override
    public ArrayList<Double> getFirstSolution() {
        ArrayList<Double> vec = new ArrayList<>();
        vec.add(random() * 100);
        vec.add(random() * 100);
        vec.add(random() * 100);
        vec.add(random() * 100);
        return vec;
    }
}
