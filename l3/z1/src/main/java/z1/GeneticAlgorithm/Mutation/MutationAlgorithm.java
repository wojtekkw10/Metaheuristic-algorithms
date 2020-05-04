package z1.GeneticAlgorithm.Mutation;

import z1.GeneticAlgorithm.Genotype;

import java.util.List;

public interface MutationAlgorithm {
    List<Genotype> mutate(List<Genotype> individuals, double mutationProbability);
}
