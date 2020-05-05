package z2.GeneticAlgorithm.Selection;

import z2.GeneticAlgorithm.GeneticAlgorithm;
import z2.GeneticAlgorithm.Genotype;
import z2.TestFunction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TournamentSelection implements SelectionAlgorithm{
    private final int tournamentSize;

    public TournamentSelection(int tournamentSize) {
        this.tournamentSize = tournamentSize;
    }

    /**
     * Funkcja wykonuje Tournament Selection, tzn. wybierane jest tournamentSize losowych osobnikow, a nastepnie najlepszy z nich
     * @param numberOfIndividuals liczba osobnikow do wybrania
     * @param individuals podane osobniki
     * @param fitnessFunction funkcja fitnessu
     * @return lista wybranych osobnikow
     */
    @Override
    public List<Genotype> select(int numberOfIndividuals, List<Genotype> individuals, TestFunction fitnessFunction) {
        List<Genotype> selected = new ArrayList<>();

        for (Genotype individual : individuals) {
            individual.setFitness(fitnessFunction.compute(individual.getValue()));
        }

        for (int i = 0; i < numberOfIndividuals; i++) {
            List<Genotype> randomIndividuals = getRandomIndividuals(tournamentSize, individuals);
            List<Genotype> tournament = getTopIndividuals(1, randomIndividuals);
            selected.add(tournament.get(0));
        }

        return selected;

    }

    private List<Genotype> getTopIndividuals(int k, List<Genotype> individuals){
        individuals.sort(new GeneticAlgorithm.GenotypeComparator());
        return individuals.subList(0, Math.min(k, individuals.size()));
    }

    private List<Genotype> getRandomIndividuals(int k, List<Genotype> individuals){
        Collections.shuffle(individuals);
        return individuals.subList(0, Math.min(k, individuals.size()));
    }
}
