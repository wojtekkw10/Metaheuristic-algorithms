package z3.LocalSearch.Neighborhood;

import java.util.ArrayList;

public abstract class NeighborhoodBase {
    public abstract ArrayList<ArrayList<String>> getNeighborhood(ArrayList<String> point, int argument1, int argument2);
}
