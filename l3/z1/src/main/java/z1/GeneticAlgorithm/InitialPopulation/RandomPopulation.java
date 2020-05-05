package z1.GeneticAlgorithm.InitialPopulation;

import z1.GeneticAlgorithm.Genotype;

import java.util.ArrayList;
import java.util.List;

public class RandomPopulation implements InitialPopulation{
    private final int size;

    public RandomPopulation(int size) {
        this.size = size;
    }

    /**
     * Funkcja generuje wektory [x1,x2,x3,x4,x5] gdzie |xi|<=5
     * @return wygenerowane wektory
     */
    @Override
    public List<Genotype> getInitialPopulation() {
        List<Genotype> individuals = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            List<Double> vector = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                if(Math.random()>5) vector.add(Math.random()*5);
                else vector.add(-Math.random()*5);
            }
            individuals.add(new Genotype(vector));
        }

        return individuals;
    }
}
