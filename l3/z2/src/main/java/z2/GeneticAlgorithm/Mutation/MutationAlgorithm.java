package z2.GeneticAlgorithm.Mutation;

import z2.GeneticAlgorithm.Genotype;

import java.util.List;

public interface MutationAlgorithm {
    List<Genotype> mutate(List<Genotype> individuals, double mutationProbability);
}
