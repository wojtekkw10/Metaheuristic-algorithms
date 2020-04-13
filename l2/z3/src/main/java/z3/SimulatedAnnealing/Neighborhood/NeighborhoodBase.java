package z3.SimulatedAnnealing.Neighborhood;

import java.util.ArrayList;

public abstract class NeighborhoodBase {
    public abstract ArrayList<ArrayList<String>> getNeighborhood(ArrayList<String> point, double argument1, double argument2);
}