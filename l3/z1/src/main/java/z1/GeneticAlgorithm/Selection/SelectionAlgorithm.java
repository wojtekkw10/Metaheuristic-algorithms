package z1.GeneticAlgorithm.Selection;

import z1.GeneticAlgorithm.Genotype;
import z1.TestFunction;

import java.util.List;

public interface SelectionAlgorithm {
    List<Genotype> select(int numberOfIndividuals, List<Genotype> individuals, TestFunction fitnessFunction);
}
