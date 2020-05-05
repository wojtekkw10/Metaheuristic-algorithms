package z1.GeneticAlgorithm;

import z1.GeneticAlgorithm.Crossover.CrossoverAlgorithm;
import z1.GeneticAlgorithm.InitialPopulation.InitialPopulation;
import z1.GeneticAlgorithm.Mutation.MutationAlgorithm;
import z1.GeneticAlgorithm.Selection.SelectionAlgorithm;
import z1.TestFunction;

import java.util.Comparator;
import java.util.List;

public class GeneticAlgorithm {
    private TestFunction fitnessFunction;
    private InitialPopulation initialPopulation;
    private SelectionAlgorithm selectionAlgorithm;
    private CrossoverAlgorithm crossoverAlgorithm;
    private MutationAlgorithm mutationAlgorithm;
    private int maxTime;

    public GeneticAlgorithm(int maxTime, TestFunction testFunction, InitialPopulation initialPopulation,
                            SelectionAlgorithm selectionAlgorithm, CrossoverAlgorithm crossoverAlgorithm, MutationAlgorithm mutationAlgorithm){
        fitnessFunction = testFunction;
        this.initialPopulation = initialPopulation;
        this.selectionAlgorithm = selectionAlgorithm;
        this.crossoverAlgorithm = crossoverAlgorithm;
        this.mutationAlgorithm = mutationAlgorithm;
        this.maxTime = maxTime;


    }

    public Genotype evolve(){
        long time1 = System.currentTimeMillis();
        long time2;
        List<Genotype> individuals = initialPopulation.getInitialPopulation();

        do {
            //wybieramy losowo osobnikow z preferencja na tych lepszych
            individuals = selectionAlgorithm.select(100, individuals, fitnessFunction);

            //tworzymy nowe osobniki na podstawie poprzedniej generacji
            individuals = crossoverAlgorithm.crossover(individuals);

            //mutujemy otrzymane osobniki
            individuals = mutationAlgorithm.mutate(individuals, 0.05);

            /*
            //Wypisujemy najlpeszego osobnika
            List<Genotype> sorted = sortIndividuals(individuals);
            Genotype best = sorted.get(0);
            best.setFitness(fitnessFunction.compute(best.getValue()));
            System.out.println(best);
            */

            time2 = System.currentTimeMillis();
        } while ((double) (time2 - time1) / 1000 < maxTime);

        return sortIndividuals(individuals).get(0);

    }

    private List<Genotype> sortIndividuals(List<Genotype> individuals){
        individuals.sort(new GenotypeComparator());
        return individuals;
    }

    public static class GenotypeComparator implements Comparator<Genotype> {
        @Override
        public int compare(Genotype genotype, Genotype t1) {
            return Double.compare(genotype.getFitness(), t1.getFitness());
        }
    }
}
