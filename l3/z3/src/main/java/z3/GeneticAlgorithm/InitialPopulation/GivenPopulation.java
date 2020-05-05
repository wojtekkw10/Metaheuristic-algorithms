package z3.GeneticAlgorithm.InitialPopulation;

import z3.GeneticAlgorithm.Genotype;

import java.util.List;

public class GivenPopulation implements InitialPopulation{
    public GivenPopulation(List<Genotype> population) {
        this.population = population;
    }

    List<Genotype> population;

    @Override
    public List<Genotype> getInitialPopulation() {
        return population;
    }
}
