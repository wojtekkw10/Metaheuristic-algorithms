package z3.SimulatedAnnealing.AnnealingSchedule;

public class AnnealingScheduleGeometric extends AnnealingScheduleBase {
    private double rate;
    public AnnealingScheduleGeometric(double rate){
        this.rate = rate;
    }

    /**
     * Liczy temperature w kolejnym kroku wedlug funkcji geometrycznej
     * @param temperature aktualna temperatura
     * @return temperatura w nastepnym kroku
     */
    @Override
    public double getNewTemperature(double temperature) {
        return temperature * rate;
    }
}
