package z1.GeneticAlgorithm.Selection;

import z1.GeneticAlgorithm.GeneticAlgorithm;
import z1.GeneticAlgorithm.Genotype;
import z1.TestFunction;

import java.util.ArrayList;
import java.util.List;

public class RouletteWheel implements SelectionAlgorithm{
    @Override
    public List<Genotype> select(int numberOfIndividuals, List<Genotype> individuals, TestFunction fitnessFunction) {
        List<Genotype> selected = new ArrayList<>();
        double totalFitness = 0;

        //compute fitness
        for (int i = 0; i < individuals.size(); i++) {
            double indFitness = 1/fitnessFunction.compute(individuals.get(i).getValue()); // 1/x for inversely proportional
            individuals.get(i).setFitness(indFitness);
            totalFitness += indFitness;
        }

        //normalize
        for (int i = 0; i < individuals.size(); i++) {
            double indFitness = individuals.get(i).getFitness();
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

        if(individuals.size() > 1){
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
        }
        else{
            for (int i = 0; i < numberOfIndividuals; i++) {
                selected.add(individuals.get(0));
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
