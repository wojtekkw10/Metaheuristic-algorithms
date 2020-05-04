/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package z1;

import z1.GeneticAlgorithm.Crossover.SinglePointCrossover;
import z1.GeneticAlgorithm.GeneticAlgorithm;
import z1.GeneticAlgorithm.Genotype;
import z1.GeneticAlgorithm.InitialPopulation.RandomInitial;
import z1.GeneticAlgorithm.Mutation.GaussianMutation;
import z1.GeneticAlgorithm.Selection.TournamentSelection;

import java.util.List;

public class App {
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) {
        InputParser inputParser = new InputParser();
        List<Double> vector = inputParser.getInput();
        List<Double> functionFactors = inputParser.getFactors();
        int maxTime = inputParser.getMaxTime();

        TestFunction fitnessFunction = new YangFunction(functionFactors);

        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(
                maxTime,
                fitnessFunction,
                new RandomInitial(),
                new TournamentSelection(10),
                new SinglePointCrossover(),
                new GaussianMutation());

        Genotype topIndividual = geneticAlgorithm.evolve();
        topIndividual.setFitness(fitnessFunction.compute(topIndividual.getValue()));
        System.out.println(topIndividual);

    }
}