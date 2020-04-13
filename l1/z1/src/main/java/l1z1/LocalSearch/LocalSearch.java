package l1z1.LocalSearch;

import l1z1.LocalSearch.FirstSolution.FirstSolutionBase;
import l1z1.LocalSearch.Neighborhood.NeighborhoodBase;
import l1z1.TestFunction;

import java.util.ArrayList;
import java.util.function.DoubleToIntFunction;

public class LocalSearch {
    /**
     * Funkcja, która będzie testowana
     * Przyjmuje rozwiązanie (wektor) i zwraca wartość funkcji w tym miejscu
     * Design Pattern - strategy
     */
    private TestFunction fun;

    /**
     * Algorytm znajdowania pierwszego rozwiązania
     * Design Pattern - strategy
     */
    private FirstSolutionBase firstSolution;

    /**
     * Algorytm znajdowania listy sąsiedztwa
     * innymi słowy funkcja przejścia, która zwraca zbiór sąsiednich rozwiązań
     * Design pattern - strategy
     */
    private NeighborhoodBase neighborhood;

    /**
     * Funkcja znajduje najlepsze rozwiązanie spośród podanych
     * @param candidates - zbiór potencjalnych rozwiązań
     * @param taboo - lista taboo
     * @return najlepszy kandydat, który jednocześnie nie znajduje się
     * na liście taboo
     */
    private ArrayList<Double> getTheBestCandidate(ArrayList<ArrayList<Double>> candidates, ArrayList<ArrayList<Double>> taboo) {
        ArrayList<Double> best = candidates.get(0);
        double bestFitness = fun.compute(best);
        for (int i = 1; i < candidates.size(); i++) {
            double currFitness = fun.compute(candidates.get(i));
            if (currFitness < bestFitness && !taboo.contains(candidates.get(i))) {
                bestFitness = currFitness;
                best = candidates.get(i);
            }
        }
        return best;
    }

    public LocalSearch(TestFunction fun, FirstSolutionBase firstSolution, NeighborhoodBase neighborhood) {
        this.fun = fun;
        this.firstSolution = firstSolution;
        this.neighborhood = neighborhood;
    }

    /**
     * Główna funkcja wykonująca local search
     * @param maxTime - maksymalny czas jaki może wykonywać się search
     * @return najlepsze rozwiązanie
     */
    public ArrayList<Double> search(double maxTime) {
        long time1 = System.currentTimeMillis();
        long time2 = 0;
        double step = 2;
        int maxTabooListSize = 10;
        ArrayList<ArrayList<Double>> tabooList = new ArrayList<>();

        ArrayList<Double> bestCandidate = firstSolution.getFirstSolution();

        // potrzebne do wykrycia czy bestFitness się nie powtarza
        double lastFitness = fun.compute(bestCandidate);

        do {
            ArrayList<ArrayList<Double>> candidates = neighborhood.getNeighborhood(bestCandidate, step, -1);
            ArrayList<Double> bestCurrentCandidate = getTheBestCandidate(candidates, tabooList);

            if (fun.compute(bestCurrentCandidate) < fun.compute(bestCandidate)) {
                bestCandidate = bestCurrentCandidate;
            }
            if (lastFitness == fun.compute(bestCandidate)) {
                step *= 0.99;
            }

            //Updating the taboo list
            tabooList.add(bestCandidate);
            if (tabooList.size() > maxTabooListSize) tabooList.remove(0);

            //Printing current result
            for (double i : bestCandidate) {
                //System.out.print(i + " ");
            }
            //System.out.println(fun.compute(bestCandidate));

            lastFitness = fun.compute(bestCandidate);
            time2 = System.currentTimeMillis();
        } while ((double) (time2 - time1) / 1000 < maxTime);

        return bestCandidate;
    }
}
