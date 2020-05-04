package z1.GeneticAlgorithm.Crossover;

import z1.GeneticAlgorithm.Genotype;

import java.util.ArrayList;
import java.util.List;

public interface CrossoverAlgorithm {
    List<Genotype> crossover(List<Genotype> parents);
}
