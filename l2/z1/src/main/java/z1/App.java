package z1;

import z1.SimulatedAnnealing.AcceptanceProbability.StandardAcceptanceProbability;
import z1.SimulatedAnnealing.AnnealingSchedule.AnnealingScheduleGeometric;
import z1.SimulatedAnnealing.FirstSolution.GivenSolution;
import z1.SimulatedAnnealing.SimmulatedAnnealing;
import z1.SimulatedAnnealing.Neighborhood.CircleNeighborhood;

import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
        InputParser inputParser = new InputParser();
        ArrayList<Double> inputVector = inputParser.getInput();
        int maxTime = inputParser.getMaxTime();
        SimmulatedAnnealing sa;

        TestFunction testFunction = new SalomonFunction();

        // wykonaj przeszukiwanie
        sa = new SimmulatedAnnealing(testFunction,
                new GivenSolution(inputVector),
                new CircleNeighborhood(),
                new AnnealingScheduleGeometric(0.999),
                new StandardAcceptanceProbability());
        ArrayList<Double> bestResult = sa.search(maxTime, 100);

        //OUTPUT
        // wydrukuj rozwiązanie na std wyjście
        for(double i : bestResult){
            System.out.print(i+" ");
        }
        System.out.println(testFunction.compute(bestResult));
    }
}