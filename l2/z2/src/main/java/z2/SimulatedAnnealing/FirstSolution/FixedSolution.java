package z2.SimulatedAnnealing.FirstSolution;

import z2.SimpleMatrix;
import z2.SubMatrix;

public class FixedSolution extends FirstSolutionBase {
    private final int width;
    private final int height;
    private final int K;

    public FixedSolution(int width, int height, int K){
        this.width = width;
        this.height = height;
        this.K = K;
    }

    /**
     * Funkcja zwraca macierz wypelniona zerami
     * @return wygenerowana macierz
     */
    @Override
    public SimpleMatrix getFirstSolution() {
        SimpleMatrix sm = new SimpleMatrix(width, height, K);
        SubMatrix sub = new SubMatrix();
        sub.width = width;
        sub.height = height;
        sub.value = 0;
        sm.addSubMatrix(sub);
        return sm;
    }
}
