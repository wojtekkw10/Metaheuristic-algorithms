package z3.GeneticAlgorithm.InitialPopulation;

import z3.GeneticAlgorithm.Genotype;

import java.util.ArrayList;
import java.util.List;

public class GivenIndividual implements InitialPopulation{
    public GivenIndividual(String word) {
        this.word = word;
    }

    private final String word;
    @Override
    public List<Genotype> getInitialPopulation() {
        List<Genotype> individuals = new ArrayList<>();
        individuals.add(new Genotype(word));
        return individuals;
    }
}
