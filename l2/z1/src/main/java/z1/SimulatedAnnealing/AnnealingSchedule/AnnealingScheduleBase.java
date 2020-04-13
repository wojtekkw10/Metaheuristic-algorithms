package z1.SimulatedAnnealing.AnnealingSchedule;

public abstract class AnnealingScheduleBase {
    abstract public double getNewTemperature(double temperature);
}
