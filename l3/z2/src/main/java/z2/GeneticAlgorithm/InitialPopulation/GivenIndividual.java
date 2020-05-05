package z2.GeneticAlgorithm.InitialPopulation;

import z2.GeneticAlgorithm.Genotype;

import java.util.ArrayList;
import java.util.List;

public class GivenIndividual implements InitialPopulation{
    public GivenIndividual(List<Double> givenVector) {
        this.givenVector = givenVector;
    }

    private final List<Double> givenVector;
    @Override
    public List<Genotype> getInitialPopulation() {
        List<Genotype> individuals = new ArrayList<>();
        individuals.add(new Genotype(givenVector));
        return individuals;
    }
}
