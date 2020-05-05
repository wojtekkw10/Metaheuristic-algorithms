package z3.GeneticAlgorithm.Selection;

import z3.GeneticAlgorithm.Genotype;
import z3.TestFunction;

import java.util.List;

public interface SelectionAlgorithm {
    List<Genotype> select(int numberOfIndividuals, List<Genotype> individuals, TestFunction fitnessFunction);
}
