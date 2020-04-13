package z3;

import z3.SimulatedAnnealing.AcceptanceProbability.StandardAcceptanceProbability;
import z3.SimulatedAnnealing.AnnealingSchedule.AnnealingScheduleGeometric;
import z3.SimulatedAnnealing.FirstSolution.GivenSolution;
import z3.SimulatedAnnealing.Neighborhood.PathNeighborhood;
import z3.SimulatedAnnealing.SimulatedAnnealing;

import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
        InputParser inputParser = new InputParser();
        ArrayList<ArrayList<Integer>> board = inputParser.getInput();
        int maxTime = inputParser.getMaxTime();
        SimulatedAnnealing sa;


        PathTestFunction testFunction = new PathTestFunction(board, inputParser.agentX, inputParser.agentY);

        // wykonaj przeszukiwanie
        sa = new SimulatedAnnealing(testFunction,
                new GivenSolution("U"),
                new PathNeighborhood(testFunction),
                new AnnealingScheduleGeometric(0.9999),
                new StandardAcceptanceProbability());
        ArrayList<String> bestResult = sa.search(maxTime, 100);

        //OUTPUT
        // wydrukuj rozwiązanie na std wyjście
        System.out.println(testFunction.compute(bestResult));
        System.err.println(testFunction.printPath(bestResult, true));

        // NOTE:
        // sciezka czasami nie jest znajdywana, wtedy drukuje najlepsza znaleziona sciezke
        // jej dlugosc to N*M. Wtedy wiadomo ze nie znaleziono.
    }


}