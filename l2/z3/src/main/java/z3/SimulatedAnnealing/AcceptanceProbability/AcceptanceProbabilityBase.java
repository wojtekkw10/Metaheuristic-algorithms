package z3.SimulatedAnnealing.AcceptanceProbability;

public abstract class AcceptanceProbabilityBase {
    abstract public double getProbability(double temperature, double deltaF);
}
