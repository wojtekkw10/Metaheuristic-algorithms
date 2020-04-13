package l1z1;

import java.util.ArrayList;

public class Griewank extends TestFunction {
    public double compute(ArrayList<Double> vec) {
        double sum = 0;
        for (double i : vec) {
            sum += i * i / 4000;
        }

        double product = 0;
        for (int i = 0; i < vec.size(); i++) {
            product += Math.cos(vec.get(i) / (Math.sqrt(i) + 1));
        }

        return 1 + sum - product;
    }
}
