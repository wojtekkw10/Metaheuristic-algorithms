package z3.LocalSearch;

import z3.LocalSearch.FirstSolution.FirstSolutionBase;
import z3.LocalSearch.Neighborhood.NeighborhoodBase;

import java.util.ArrayList;

public class LocalSearch {
    /**
     * Funkcja, która będzie testowana
     * Przyjmuje rozwiązanie (ciąg znaków "UDRL") i zwraca dlugość tej ścieżki
     * Design Pattern - strategy
     */
    private PathFunction fun;

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
     * @param candidates zbiór potencjalnych rozwiązań
     * @param taboo lista taboo
     * @return najlepszy kandydat, który jednocześnie nie znajduje się
     * na liście taboo
     */
    private ArrayList<String> getTheBestCandidate(ArrayList<ArrayList<String>> candidates, ArrayList<ArrayList<String>> taboo) {
        ArrayList<String> best = candidates.get(0);
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

    public LocalSearch(PathFunction fun, FirstSolutionBase firstSolution, NeighborhoodBase neighborhood) {
        this.fun = fun;
        this.firstSolution = firstSolution;
        this.neighborhood = neighborhood;
    }

    /**
     * Główna funkcja wykonująca local search
     * @param maxTime - maksymalny czas jaki może wykonywać się search
     * @return najlepsze rozwiązanie
     */
    public ArrayList<String> search(double maxTime) {
        long time1 = System.currentTimeMillis();
        long time2 = 0;
        int maxTabooListSize = 10;
        int n = fun.getGraph().size();
        ArrayList<ArrayList<String>> tabooList = new ArrayList<>();

        ArrayList<String> bestCandidate = firstSolution.getFirstSolution(fun.getGraph());

        // potrzebne do wykrycia czy bestFitness się nie powtarza
        double lastFitness = fun.compute(bestCandidate);

        do {
            ArrayList<ArrayList<String>> candidates = neighborhood.getNeighborhood(bestCandidate, -1, -1);
            ArrayList<String> bestCurrentCandidate = getTheBestCandidate(candidates, tabooList);

            if (fun.compute(bestCurrentCandidate) < fun.compute(bestCandidate)) {
                bestCandidate = bestCurrentCandidate;
            }

            //Updating the taboo list
            tabooList.add(bestCandidate);
            if (tabooList.size() > maxTabooListSize) tabooList.remove(0);

            //Printing current result
            for (String i : bestCandidate) {
                //System.out.print(i + " ");
            }
            //System.out.println(fun.compute(bestCandidate));

            lastFitness = fun.compute(bestCandidate);
            time2 = System.currentTimeMillis();
        } while ((double) (time2 - time1) / 1000 < maxTime);

        // Wynik jest obrócony o 90 stopni więc trzeba odwrócić
        for(int i=0; i<bestCandidate.size(); i++){
            if(bestCandidate.get(i).equals("L")) bestCandidate.set(i, "U");
            else if(bestCandidate.get(i).equals("U")) bestCandidate.set(i, "R");
            else if(bestCandidate.get(i).equals("R")) bestCandidate.set(i, "D");
            else if(bestCandidate.get(i).equals("D")) bestCandidate.set(i, "L");
        }

        return bestCandidate;
    }
}
