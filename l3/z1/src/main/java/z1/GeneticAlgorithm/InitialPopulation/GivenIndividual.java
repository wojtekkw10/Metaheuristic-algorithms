package z1.GeneticAlgorithm.InitialPopulation;

import z1.GeneticAlgorithm.Genotype;

import java.util.ArrayList;
import java.util.List;

public class GivenIndividual implements InitialPopulation{
    private final List<Double> givenVector;

    public GivenIndividual(List<Double> givenVector) {
        this.givenVector = givenVector;
    }

    /**
     * Funkcja zwraca podany w konstruktorze osobnik
     * @return
     */
    @Override
    public List<Genotype> getInitialPopulation() {
        List<Genotype> individuals = new ArrayList<>();
        individuals.add(new Genotype(givenVector));
        return individuals;
    }
}
