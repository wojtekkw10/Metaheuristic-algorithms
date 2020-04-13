package z2.SimulatedAnnealing;

import z2.SimpleMatrix;
import z2.SimulatedAnnealing.AcceptanceProbability.AcceptanceProbabilityBase;
import z2.SimulatedAnnealing.AnnealingSchedule.AnnealingScheduleBase;
import z2.SimulatedAnnealing.FirstSolution.FirstSolutionBase;
import z2.SimulatedAnnealing.Neighborhood.NeighborhoodBase;
import z2.TestFunction;

import java.util.ArrayList;
import java.util.Collections;

public class SimulatedAnnealing {
    /**
     * Funkcja, która będzie testowana
     * Przyjmuje rozwiązanie (macierz) i zwraca odleglosc od macierzy wzorcowej
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
    private ArrayList<NeighborhoodBase> neighborhood;

    /**
     * Algorytm wyznaczania temperatury w kolejnym kroku algorytmu
     */
    private AnnealingScheduleBase annealingSchedule;

    /**
     * Algorytm wyznaczania prawdopodobienstwa wybrania rozwiazania na podstawie temperatury i roznicy wartosci w punkcie
     */
    private AcceptanceProbabilityBase acceptanceProbability;

    public SimulatedAnnealing(TestFunction fun, FirstSolutionBase firstSolution, ArrayList<NeighborhoodBase> neighborhood, AnnealingScheduleBase annealingSchedule,
                              AcceptanceProbabilityBase acceptanceProbability) {
        this.fun = fun;
        this.firstSolution = firstSolution;
        this.neighborhood = neighborhood;
        this.annealingSchedule = annealingSchedule;
        this.acceptanceProbability = acceptanceProbability;
    }

    /**
     * Funkcja znajduje najlepsze rozwiązanie spośród podanych
     * @param candidates - zbiór potencjalnych rozwiązań
     * @param temperature - temperatura w wyżarzaniu
     * @return najlepszy kandydat
     */
    private SimpleMatrix getTheBestCandidate(SimpleMatrix bestSoFar, ArrayList<SimpleMatrix> candidates, double temperature) {
        double bestSoFarFitness = fun.compute(bestSoFar.getFullMatrix());
        Collections.shuffle(candidates);
        SimpleMatrix best = bestSoFar;
        for (int i = 1; i < candidates.size(); i++) {
            double deltaF =  fun.compute(candidates.get(i).getFullMatrix()) - bestSoFarFitness;
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
    public SimpleMatrix search(double maxTime, int startTemperature) {
        long time1 = System.currentTimeMillis();
        long time2;
        double temperature = startTemperature;

        SimpleMatrix bestCandidate = firstSolution.getFirstSolution();

        do {
            ArrayList<SimpleMatrix> candidates = new ArrayList<>();
            //generujemy sasiedztwo na podstawie kilku roznych algorytmow
            for(NeighborhoodBase i : neighborhood){
                candidates.addAll(i.getNeighborhood(bestCandidate, -1, -1));
            }

            bestCandidate = getTheBestCandidate(bestCandidate, candidates, temperature);
            temperature = annealingSchedule.getNewTemperature(temperature);

            // wydrukuj rozwiązanie na std wyjście
            //System.out.println(bestCandidate.print());
            //System.out.println(fun.compute(bestCandidate.getFullMatrix()));

            time2 = System.currentTimeMillis();
        } while ((double) (time2 - time1) / 1000 < maxTime && temperature > 0.0001);

        return bestCandidate;
    }
}
