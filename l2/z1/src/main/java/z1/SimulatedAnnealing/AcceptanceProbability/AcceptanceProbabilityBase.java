package z1.SimulatedAnnealing.AcceptanceProbability;

public abstract class AcceptanceProbabilityBase {
    abstract public double getProbability(double temperature, double deltaF);
}
