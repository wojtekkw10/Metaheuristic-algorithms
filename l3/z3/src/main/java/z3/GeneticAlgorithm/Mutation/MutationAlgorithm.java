package z3.GeneticAlgorithm.Mutation;

import z3.GeneticAlgorithm.Genotype;

import java.util.List;

public interface MutationAlgorithm {
    List<Genotype> mutate(List<Genotype> individuals, double mutationProbability);
}
