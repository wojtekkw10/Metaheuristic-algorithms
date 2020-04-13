package l1z1;

import java.util.ArrayList;

public class HappyCat extends TestFunction {
    private double abs(ArrayList<Double> vec) {
        double sum = vec.get(0) * vec.get(0) + vec.get(1) * vec.get(1) + vec.get(2) * vec.get(2) + vec.get(3) * vec.get(3);
        return Math.sqrt(sum);
    }


    public double compute(ArrayList<Double> vec) {
        double sum = 0;
        for (double i : vec) {
            sum += i;
        }
        double part = abs(vec) * abs(vec) - 4;
        if (part < 0) return Math.pow(-part, 0.125) + 0.25 * (0.5 * abs(vec) * abs(vec) + sum) + 0.5;
        return Math.pow(part, 0.125) + 0.25 * (0.5 * abs(vec) * abs(vec) + sum) + 0.5;
    }

}
