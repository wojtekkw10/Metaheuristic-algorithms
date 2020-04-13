package z1.SimulatedAnnealing.Neighborhood;

import java.util.ArrayList;

public abstract class NeighborhoodBase {
    public abstract ArrayList<ArrayList<Double>> getNeighborhood(ArrayList<Double> point, double argument1, double argument2);

}
