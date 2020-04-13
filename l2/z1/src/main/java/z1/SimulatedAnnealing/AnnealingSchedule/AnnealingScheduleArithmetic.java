package z1.SimulatedAnnealing.AnnealingSchedule;

public class AnnealingScheduleArithmetic extends AnnealingScheduleBase {
    private double rate;
    public AnnealingScheduleArithmetic(double rate){
        this.rate = rate;
    }

    /**
     * Liczy temperature w kolejnym kroku wedlug funkcji arytmetycznej
     * @param temperature aktualna temperatura
     * @return temperatura w nastepnym kroku
     */
    @Override
    public double getNewTemperature(double temperature) {
        return temperature - rate;
    }
}
