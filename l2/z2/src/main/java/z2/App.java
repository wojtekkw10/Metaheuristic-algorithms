package z2;

import z2.SimulatedAnnealing.AcceptanceProbability.StandardAcceptanceProbability;
import z2.SimulatedAnnealing.AnnealingSchedule.AnnealingScheduleGeometric;
import z2.SimulatedAnnealing.FirstSolution.FixedSolution;
import z2.SimulatedAnnealing.Neighborhood.ChangeColorNeighborhood;
import z2.SimulatedAnnealing.Neighborhood.DivisionMatrixNeighborhood;
import z2.SimulatedAnnealing.Neighborhood.NeighborhoodBase;
import z2.SimulatedAnnealing.SimulatedAnnealing;

import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
        InputParser inputParser = new InputParser();
        ArrayList<ArrayList<Integer>> originalMatrix = inputParser.getInput();
        int maxTime = inputParser.getMaxTime();
        SimulatedAnnealing sa;

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(0);
        colors.add(32);
        colors.add(64);
        colors.add(128);
        colors.add(160);
        colors.add(190);
        colors.add(223);
        colors.add(256);

        TestFunction testFunction = new MatrixDistanceTestFunction(originalMatrix);

        ArrayList<NeighborhoodBase> neighborhoods = new ArrayList<>();
        neighborhoods.add(new ChangeColorNeighborhood(colors));
        neighborhoods.add(new DivisionMatrixNeighborhood(colors));

        //wykonaj przeszukiwanie
        sa = new SimulatedAnnealing(testFunction, new FixedSolution(inputParser.getN(), inputParser.getM(), inputParser.getK()),
               neighborhoods, new AnnealingScheduleGeometric(0.9999),
               new StandardAcceptanceProbability());
        SimpleMatrix bestResult = sa.search(maxTime, 100);

        //OUTPUT
        // wydrukuj rozwiązanie na std wyjście
        System.err.println(bestResult.printClean());
        System.out.println(testFunction.compute(bestResult.getFullMatrix()));

    }
}