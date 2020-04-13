package z1.SimulatedAnnealing;

import z1.SimulatedAnnealing.AcceptanceProbability.AcceptanceProbabilityBase;
import z1.SimulatedAnnealing.AnnealingSchedule.AnnealingScheduleBase;
import z1.SimulatedAnnealing.FirstSolution.FirstSolutionBase;
import z1.SimulatedAnnealing.Neighborhood.NeighborhoodBase;
import z1.TestFunction;

import java.util.ArrayList;
import java.util.Collections;

public class SimmulatedAnnealing {
    /**
     * Funkcja, która będzie testowana
     * Przyjmuje rozwiązanie (wektor) i zwraca wartość funkcji w tym miejscu
     * Design Pattern - strategy
     */
    private final TestFunction fun;

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

    public SimmulatedAnnealing(TestFunction fun, FirstSolutionBase firstSolution, NeighborhoodBase neighborhood, AnnealingScheduleBase annealingSchedule,
                               AcceptanceProbabilityBase acceptanceProbability) {
        this.fun = fun;
        this.firstSolution = firstSolution;
        this.neighborhood = neighborhood;
        this.annealingSchedule = annealingSchedule;
        this.acceptanceProbability = acceptanceProbability;
    }

    /**
     * Funkcja znajduje najlepsze rozwiązanie spośród podanych z uwzglednieniem temperatury
     * @param candidates - zbiór potencjalnych rozwiązań
     * @param temperature - temperatura w wyżarzaniu
     * @return najlepszy kandydat
     */
    private ArrayList<Double> getTheBestCandidate(ArrayList<Double> bestSoFar, ArrayList<ArrayList<Double>> candidates, double temperature) {
        double bestSoFarFitness = fun.compute(bestSoFar);
        Collections.shuffle(candidates);
        ArrayList<Double> best = bestSoFar;
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
    public ArrayList<Double> search(double maxTime, int startTemperature) {
        long time1 = System.currentTimeMillis();
        long time2;
        double step = 1;
        double temperature = startTemperature;

        ArrayList<Double> bestCandidate = firstSolution.getFirstSolution();

        // potrzebne do wykrycia czy bestFitness się nie powtarza
        double lastFitness = fun.compute(bestCandidate);

        do {
            ArrayList<ArrayList<Double>> candidates = neighborhood.getNeighborhood(bestCandidate, step, -1);

            bestCandidate = getTheBestCandidate(bestCandidate, candidates, temperature);
            temperature = annealingSchedule.getNewTemperature(temperature);


            //Jesli utknal w miejscu zwieksz step aby wyjsc z lokalnego minimum
            if(lastFitness == fun.compute(bestCandidate)){
                step *= 1.1;
            }
            else step = 1;

            /*//Printing current result
            for (double i : bestCandidate) {
                System.out.print(i + " ");
            }
            System.out.println(fun.compute(bestCandidate));*/

            lastFitness = fun.compute(bestCandidate);
            time2 = System.currentTimeMillis();
        } while ((double) (time2 - time1) / 1000 < maxTime && temperature > 0.0001);

        return bestCandidate;
    }
}
