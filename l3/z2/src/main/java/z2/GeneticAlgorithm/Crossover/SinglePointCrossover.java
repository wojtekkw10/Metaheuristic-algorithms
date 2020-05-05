package z2.GeneticAlgorithm.Crossover;

import z2.GeneticAlgorithm.Genotype;

import java.util.ArrayList;
import java.util.List;

public class SinglePointCrossover implements CrossoverAlgorithm{
    /**
     * Funkcja wykonuje single-point crossover na slowach
     * @param parents lista osobnikow
     * @return dzieci osobnikow
     */
    @Override
    public List<Genotype> crossover(List<Genotype> parents) {
        List<Genotype> crossover = new ArrayList<>();

        for (int i = 0; i < parents.size(); i++) {
            for (Genotype parent : parents) {
                //System.out.println("CROSSOWANIE");

                String parent1 = parents.get(i).getValue();
                String parent2 = parent.getValue();

                if(parent1.length() == 0 || parent2.length() == 0) continue;

                int crossoverPoint = (int) (Math.random() * (parent1.length()-2))+1;

                String newWord = parent1.substring(0, crossoverPoint) + parent2.substring(Math.min(crossoverPoint, parent2.length()));

                crossover.add(new Genotype(newWord));
            }
        }

        return crossover;
    }
}
