package z3;

import z3.GeneticAlgorithm.GeneticAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {
        InputParser inputParser = new InputParser();
        inputParser.getInput();
        List<Letter> letters = inputParser.getLetters();
        List<String> words = inputParser.getWords();
        int maxTime = inputParser.getMaxTime();

        WordScoreFunction fitnessFunction = new WordScoreFunction(words, letters);

        z3.GeneticAlgorithm.GeneticAlgorithm geneticAlgorithm = new z3.GeneticAlgorithm.GeneticAlgorithm(
                maxTime,
                fitnessFunction,
                new GeneticAlgorithm.GeneticAlgorithm.InitialPopulation.RandomPopulation(10000, letters, 5),
                new GeneticAlgorithm.Selection.RouletteWheel(),
                new GeneticAlgorithm.Crossover.SinglePointCrossover(),
                new GeneticAlgorithm.Mutation.LetterMutation(letters));

        z3.GeneticAlgorithm.Genotype topIndividual = geneticAlgorithm.evolve();

        topIndividual.setFitness(fitnessFunction.compute(topIndividual.getValue()));
        System.out.println((int)topIndividual.getFitness());
        System.err.println(topIndividual.getValue());
    }


}