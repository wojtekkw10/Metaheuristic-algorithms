package z1;

import java.util.ArrayList;

public class SalomonFunction extends TestFunction {
    @Override
    public double compute(ArrayList<Double> vec) {
        double geometricMean = 0;
        for(double i : vec){
            geometricMean += i*i;
        }
        geometricMean = Math.sqrt(geometricMean);

        return 1 - Math.cos(2*Math.PI * geometricMean) + 0.1 * geometricMean;
        //return vec.get(0) + vec.get(1) + vec.get(2) + vec.get(3);
    }
}
