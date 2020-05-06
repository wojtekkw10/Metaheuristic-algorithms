package z1.GeneticAlgorithm.Mutation;

import z1.GeneticAlgorithm.Genotype;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GaussianMutation implements MutationAlgorithm{
    /**
     * Funkcja bierze liste osobnikow i losowo mutuje ich wektory z podanym prawdopodobienstwem
     * @param individuals osobniki do mutowania
     * @param mutationProbability niezalezne prawdopobienstwo mutacji wspolrzednej wektora
     * @return lista osobnikow (zmutowanych i niezmutowaych)
     */
    @Override
    public List<Genotype> mutate(List<Genotype> individuals, double mutationProbability) {
        List<Genotype> mutatedIndividuals = new ArrayList<>();
        Random random = new Random();

        for (Genotype individual : individuals) {
            if (Math.random() < mutationProbability) {
                //bierzemy losowa wartosc z rozkladu normalnego
                double gaussian = random.nextGaussian();
                if (Math.random() > 0.5) gaussian = -gaussian;

                //tworzymy nowy wektor (zmutowany)
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
