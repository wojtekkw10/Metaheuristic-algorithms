package z2.GeneticAlgorithm.Selection;

import z2.GeneticAlgorithm.Genotype;
import z2.TestFunction;

import java.util.List;

public interface SelectionAlgorithm {
    List<Genotype> select(int numberOfIndividuals, List<Genotype> individuals, TestFunction fitnessFunction);
}
