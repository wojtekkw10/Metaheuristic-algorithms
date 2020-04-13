package z2;

import java.util.ArrayList;

public class MatrixDistanceTestFunction extends TestFunction {
    ArrayList<ArrayList<Integer>> originalMatrix = new ArrayList<>();

    public MatrixDistanceTestFunction(ArrayList<ArrayList<Integer>> originalMatrix){
        this.originalMatrix = originalMatrix;
    }

    /**
     * Funkcja liczy odleglosc podanej macierzy od macierzy wzorcowej
     * @param matrix podana macierz
     * @return odleglosc pomiedzy macierzami
     */
    @Override
    public double compute(ArrayList<ArrayList<Integer>> matrix) {
        double distance = 0;
        int n = matrix.size();
        int m = matrix.get(0).size();

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                distance += Math.pow(matrix.get(i).get(j) - originalMatrix.get(i).get(j), 2);
            }
        }
        distance /= (n*m);
        return distance;
    }
}
