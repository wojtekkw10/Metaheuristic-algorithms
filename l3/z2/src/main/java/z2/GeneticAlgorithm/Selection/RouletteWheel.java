package z2.GeneticAlgorithm.Selection;

import z2.GeneticAlgorithm.GeneticAlgorithm;
import z2.GeneticAlgorithm.Genotype;
import z2.TestFunction;

import java.util.ArrayList;
import java.util.List;

public class RouletteWheel implements SelectionAlgorithm{
    @Override
    public List<Genotype> select(int numberOfIndividuals, List<Genotype> individuals, TestFunction fitnessFunction) {
        List<Genotype> selected = new ArrayList<>();
        double totalFitness = 0;

        //compute fitness
        for (int i = 0; i < individuals.size(); i++) {
            double indFitness = fitnessFunction.compute(individuals.get(i).getValue());
            individuals.get(i).setFitness(indFitness);
            totalFitness += indFitness;
        }

        //normalize
        for (int i = 0; i < individuals.size(); i++) {
            double indFitness = fitnessFunction.compute(individuals.get(i).getValue());
            individuals.get(i).setFitness(indFitness/totalFitness);
        }

        //sort ascending
        individuals.sort(new GeneticAlgorithm.GenotypeComparator());

        //Calculate accumulated fitness
        double accumulatedFitness = 0;
        for (int i = 0; i < individuals.size(); i++) {
            double indFitness = individuals.get(i).getFitness();
            accumulatedFitness += indFitness;
            individuals.get(i).setFitness(accumulatedFitness);
        }

        //finding the one

        for (int i = 0; i < numberOfIndividuals; i++) {
            double R = Math.random();

            int startIndex = 1;
            int endIndex = individuals.size();

            while (startIndex + 1 != endIndex) {
                int middleIndex = (startIndex + endIndex) / 2;
                if (individuals.get(middleIndex).getFitness() > R && (individuals.get(middleIndex - 1).getFitness() < R)) {
                    selected.add(individuals.get(middleIndex));
                    break;
                } else if (individuals.get(middleIndex).getFitness() < R) {
                    startIndex = middleIndex;
                } else {
                    endIndex = middleIndex;
                }
            }
        }

        return selected;
    }

    private double getTotalFitness(List<Genotype> individuals){
        double totalFitness = 0;

        for (Genotype individual : individuals) {
            totalFitness += individual.getFitness();
        }
        return totalFitness;
    }
}
