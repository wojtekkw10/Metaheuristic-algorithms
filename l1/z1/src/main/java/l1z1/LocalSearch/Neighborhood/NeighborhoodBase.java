package l1z1.LocalSearch.Neighborhood;

import java.util.ArrayList;

public abstract class NeighborhoodBase {
    public abstract ArrayList<ArrayList<Double>> getNeighborhood(ArrayList<Double> point, double argument1, double argument2);

}
