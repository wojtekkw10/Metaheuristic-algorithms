package z1.GeneticAlgorithm.Mutation;

import z1.GeneticAlgorithm.Genotype;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GaussianMutation implements MutationAlgorithm{
    @Override
    public List<Genotype> mutate(List<Genotype> individuals, double mutationProbability) {
        List<Genotype> mutatedIndividuals = new ArrayList<>();
        Random random = new Random();

        for (Genotype individual : individuals) {
            if (Math.random() < mutationProbability) {
                double gaussian = random.nextGaussian();
                if (Math.random() > 0.5) gaussian = -gaussian;

                List<Double> vector = individual.getValue();
                int randPos = (int)(Math.random()*individual.getValue().size());
                double newValue = individual.getValue().get(randPos) + gaussian;
                vector.set(randPos, newValue);

                if(vector.size() != 5) throw new IllegalStateException();
                mutatedIndividuals.add(new Genotype(vector));
            } else {
                mutatedIndividuals.add(individual);
            }
        }
        return mutatedIndividuals;
    }
}
