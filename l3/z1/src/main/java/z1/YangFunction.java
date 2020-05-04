package z1;

import java.util.ArrayList;
import java.util.List;

public class YangFunction extends TestFunction {
    List<Double> factors;

    YangFunction(List<Double> factors){
        this.factors = factors;
    }

    @Override
    public double compute(List<Double> vec) {
        double sum = 0;
        for(int i=0; i<factors.size(); i++){
            sum += factors.get(i) * Math.pow(Math.abs(vec.get(i)), i+1);
        }
        return sum;
    }
}
