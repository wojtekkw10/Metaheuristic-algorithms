package z2.SimulatedAnnealing.Neighborhood;

import z2.SimpleMatrix;

import java.util.ArrayList;

public abstract class NeighborhoodBase {
    public abstract ArrayList<SimpleMatrix> getNeighborhood(SimpleMatrix point, double argument1, double argument2);

}
