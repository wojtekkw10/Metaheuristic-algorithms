package z2.GeneticAlgorithm.InitialPopulation;

import z2.GeneticAlgorithm.Genotype;

import java.util.ArrayList;
import java.util.List;

public class GivenPopulation implements InitialPopulation{
    public GivenPopulation(List<String> words) {
        this.words = words;
    }
    private final List<String> words;

    /**
     * Funckja zwraca slowa podane w konstruktorze
     * @return slowa
     */
    @Override
    public List<Genotype> getInitialPopulation() {
        List<Genotype> individuals = new ArrayList<>();
        for (String word :
                words) {
            individuals.add(new Genotype(word));
        }
        return individuals;
    }
}
