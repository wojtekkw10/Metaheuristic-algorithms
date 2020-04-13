package l1z1;

import l1z1.LocalSearch.FirstSolution.RandomSolution;
import l1z1.LocalSearch.LocalSearch;
import l1z1.LocalSearch.Neighborhood.CircleNeighborhood;

import java.util.ArrayList;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int maxTime = scanner.nextInt();
        int whichFunction = scanner.nextInt();
        scanner.close();
        LocalSearch ls;

        // wybierz funkcję na podstawie inputu z pliku
        TestFunction testFunction;
        if(whichFunction == 0) testFunction = new HappyCat();
        else testFunction = new Griewank();

        // wykonaj przeszukiwanie
        ls = new LocalSearch(testFunction, new RandomSolution(), new CircleNeighborhood());
        ArrayList<Double> bestResult = ls.search(maxTime);

        //OUTPUT
        // wydrukuj rozwiązanie na std wyjście
        for(double i : bestResult){
            System.out.print(i+" ");
        }
        System.out.println(testFunction.compute(bestResult));
    }
}
