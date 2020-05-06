package z3;

import z3.GeneticAlgorithm.Crossover.SinglePointCrossover;
import z3.GeneticAlgorithm.GeneticAlgorithm;
import z3.GeneticAlgorithm.Genotype;
import z3.GeneticAlgorithm.InitialPopulation.GivenPopulation;
import z3.GeneticAlgorithm.Mutation.LetterMutation;
import z3.GeneticAlgorithm.Selection.RouletteWheel;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static int populationSizeLimit;

    public static void main(String[] args) {
        InputParser inputParser = new InputParser();
        ArrayList<ArrayList<Integer>> board = inputParser.getInput();
        int maxTime = inputParser.getMaxTime();
        populationSizeLimit = inputParser.getPopulationSizeLimit();
        List<String> initialPaths = inputParser.getInitialPaths();

        //Create initial population
        List<Genotype> initialPopulation = new ArrayList<>();
        for (String initialPath : initialPaths) {
            initialPopulation.add(new Genotype(initialPath));
        }

        PathTestFunction fitnessFunction = new PathTestFunction(board, inputParser.agentX, inputParser.agentY);

        GeneticAlgorithm geneticAlgorithm = new z3.GeneticAlgorithm.GeneticAlgorithm(
                maxTime,
                fitnessFunction,
                new GivenPopulation(initialPopulation),
                new RouletteWheel(),
                new SinglePointCrossover(),
                new LetterMutation());

        Genotype topIndividual = geneticAlgorithm.evolve();

        topIndividual.setFitness(fitnessFunction.compute(topIndividual.getValue()));
        System.out.println((int)topIndividual.getFitness());
        System.err.println(fitnessFunction.printPath(topIndividual.getValue(), true));
    }

    public static void drawBoard(ArrayList<ArrayList<Integer>> board){
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.get(i).size(); j++) {
                System.out.print(board.get(i).get(j));
            }
            System.out.println();
        }

    }


}