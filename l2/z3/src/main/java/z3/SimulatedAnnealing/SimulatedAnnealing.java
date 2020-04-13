package z3.SimulatedAnnealing;

import z3.PathTestFunction;
import z3.SimulatedAnnealing.AcceptanceProbability.AcceptanceProbabilityBase;
import z3.SimulatedAnnealing.AnnealingSchedule.AnnealingScheduleBase;
import z3.SimulatedAnnealing.FirstSolution.FirstSolutionBase;
import z3.SimulatedAnnealing.Neighborhood.NeighborhoodBase;

import java.util.ArrayList;
import java.util.Collections;

public class SimulatedAnnealing {
    /**
     * Funkcja, która będzie testowana
     * Przyjmuje rozwiązanie (ścieżka) i zwraca długosc tej sciezki
     * Design Pattern - strategy
     */
    private final PathTestFunction fun;

    /**
     * Algorytm znajdowania pierwszego rozwiązania
     * Design Pattern - strategy
     */
    private final FirstSolutionBase firstSolution;

    /**
     * Algorytm znajdowania listy sąsiedztwa
     * innymi słowy funkcja przejścia, która zwraca zbiór sąsiednich rozwiązań
     * Design pattern - strategy
     */
    private final NeighborhoodBase neighborhood;

    /**
     * Algorytm wyznaczania temperatury w kolejnym kroku algorytmu
     */
    private final AnnealingScheduleBase annealingSchedule;

    /**
     * Algorytm wyznaczania prawdopodobienstwa wybrania rozwiazania na podstawie temperatury i roznicy wartosci w punkcie
     */
    private final AcceptanceProbabilityBase acceptanceProbability;

    public SimulatedAnnealing(PathTestFunction fun, FirstSolutionBase firstSolution, NeighborhoodBase neighborhood, AnnealingScheduleBase annealingSchedule,
                              AcceptanceProbabilityBase acceptanceProbability) {
        this.fun = fun;
        this.firstSolution = firstSolution;
        this.neighborhood = neighborhood;
        this.annealingSchedule = annealingSchedule;
        this.acceptanceProbability = acceptanceProbability;
    }

    /**
     * Funkcja znajduje najlepsze rozwiązanie spośród podanych
     * @param candidates zbiór potencjalnych rozwiązań
     * @param temperature temperatura w wyżarzaniu
     * @return najlepszy kandydat
     */
    private ArrayList<String> getTheBestCandidate(ArrayList<String> bestSoFar, ArrayList<ArrayList<String>> candidates, double temperature) {
        double bestSoFarFitness = fun.compute(bestSoFar);
        Collections.shuffle(candidates);
        ArrayList<String> best = bestSoFar;
        for (int i = 1; i < candidates.size(); i++) {
            double deltaF =  fun.compute(candidates.get(i)) - bestSoFarFitness;
            double probability = acceptanceProbability.getProbability(temperature, deltaF);
            if (Math.random() < probability) {
                best = candidates.get(i);
                return best;
            }
        }
        return best;
    }



    /**
     * Główna funkcja wykonująca wyzarzanie
     * @param maxTime - maksymalny czas jaki może wykonywać się search
     * @return najlepsze rozwiązanie
     */
    public ArrayList<String> search(double maxTime, int startTemperature) {
        long time1 = System.currentTimeMillis();
        long time2;
        double temperature = startTemperature;

        ArrayList<String> bestCandidate = firstSolution.getFirstSolution();

        do {
            ArrayList<ArrayList<String>> candidates = neighborhood.getNeighborhood(bestCandidate, -1, -1);

            bestCandidate = getTheBestCandidate(bestCandidate, candidates, temperature);

            temperature = annealingSchedule.getNewTemperature(temperature);

            //Printing current result
            //System.out.println("best candidate");
            //System.out.println(bestCandidate);
            //System.out.println(fun.compute(bestCandidate));

            time2 = System.currentTimeMillis();
        } while ((double) (time2 - time1) / 1000 < maxTime && temperature > 0.0001);

        return bestCandidate;
    }
}
