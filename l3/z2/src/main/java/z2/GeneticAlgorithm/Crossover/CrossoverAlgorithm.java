package z2.GeneticAlgorithm.Crossover;

import z2.GeneticAlgorithm.Genotype;

import java.util.List;

public interface CrossoverAlgorithm {
    List<Genotype> crossover(List<Genotype> parents);
}
