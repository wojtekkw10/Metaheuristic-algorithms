package z2;

import z2.LocalSearch.FirstSolution.RandomSolution;
import z2.LocalSearch.LocalSearch;
import z2.LocalSearch.Neighborhood.SwapNeighborhood;
import z2.LocalSearch.TSP;

import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
        InputParser ip = new InputParser();
        ArrayList<ArrayList<Integer>> graph = ip.getInput();
        int n = ip.getN();
        int maxTime = ip.getMaxTime();

        LocalSearch ls = new LocalSearch(new TSP(graph), new RandomSolution(), new SwapNeighborhood());
        ArrayList<Integer> bestPath = ls.search(maxTime);

        //OUTPUT
        //Printing distance
        System.out.println(new TSP(graph).compute(bestPath));

        //Printing path
        for(int i : bestPath){
            System.err.print((i+1)+" ");
        }
        System.err.println("1");

    }
}
