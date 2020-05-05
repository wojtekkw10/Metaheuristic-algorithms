package z3.GeneticAlgorithm.Crossover;

import z3.GeneticAlgorithm.Genotype;

import java.util.List;

public interface CrossoverAlgorithm {
    List<Genotype> crossover(List<Genotype> parents);
}
