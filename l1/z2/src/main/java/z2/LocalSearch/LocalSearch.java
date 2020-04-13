package z2.LocalSearch;

import z2.LocalSearch.FirstSolution.FirstSolutionBase;
import z2.LocalSearch.Neighborhood.NeighborhoodBase;

import java.util.ArrayList;

public class LocalSearch {
    /**
     * Funkcja, która będzie testowana
     * Przyjmuje rozwiązanie (ścieżkę) i zwraca długość drogi
     * Design Pattern - strategy
     */
    private TSP fun;

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
    private ArrayList<Integer> getTheBestCandidate(ArrayList<ArrayList<Integer>> candidates, ArrayList<ArrayList<Integer>> taboo) {
        ArrayList<Integer> best = candidates.get(0);
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

    public LocalSearch(TSP fun, FirstSolutionBase firstSolution, NeighborhoodBase neighborhood) {
        this.fun = fun;
        this.firstSolution = firstSolution;
        this.neighborhood = neighborhood;
    }

    /**
     * Główna funkcja wykonująca local search
     * @param maxTime - maksymalny czas jaki może wykonywać się search
     * @return najlepsze rozwiązanie
     */
    public ArrayList<Integer> search(double maxTime) {
        long time1 = System.currentTimeMillis();
        long time2 = 0;
        int maxTabooListSize = 10;
        int n = fun.getGraph().size();
        ArrayList<ArrayList<Integer>> tabooList = new ArrayList<>();

        ArrayList<Integer> bestCandidate = firstSolution.getFirstSolution(n, fun.getGraph());

        // potrzebne do wykrycia czy bestFitness się nie powtarza
        double lastFitness = fun.compute(bestCandidate);

        do {
            ArrayList<ArrayList<Integer>> candidates = neighborhood.getNeighborhood(bestCandidate, -1, -1);
            ArrayList<Integer> bestCurrentCandidate = getTheBestCandidate(candidates, tabooList);

            if (fun.compute(bestCurrentCandidate) < fun.compute(bestCandidate)) {
                bestCandidate = bestCurrentCandidate;
            }

            //Updating the taboo list
            tabooList.add(bestCandidate);
            if (tabooList.size() > maxTabooListSize) tabooList.remove(0);

            //Printing current result
            for (int i : bestCandidate) {
                //System.out.print(i + " ");
            }
            //System.out.println(fun.compute(bestCandidate));

            lastFitness = fun.compute(bestCandidate);
            time2 = System.currentTimeMillis();
        } while ((double) (time2 - time1) / 1000 < maxTime);

        return bestCandidate;
    }
}
