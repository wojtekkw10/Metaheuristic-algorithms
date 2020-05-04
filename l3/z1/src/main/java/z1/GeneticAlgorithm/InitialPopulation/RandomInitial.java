package z1.GeneticAlgorithm.InitialPopulation;

import z1.GeneticAlgorithm.Genotype;

import java.util.ArrayList;
import java.util.List;

public class RandomInitial implements InitialPopulation{
    @Override
    public List<Genotype> getInitialPopulation() {
        List<Genotype> individuals = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            List<Double> vector = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                vector.add(Math.random()*5);
            }
            individuals.add(new Genotype(vector));
        }

        return individuals;
    }
}
