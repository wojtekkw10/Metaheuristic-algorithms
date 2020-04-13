package z2.LocalSearch.Neighborhood;

import java.util.ArrayList;

public abstract class NeighborhoodBase {
    public abstract ArrayList<ArrayList<Integer>> getNeighborhood(ArrayList<Integer> point, int argument1, int argument2);
}
