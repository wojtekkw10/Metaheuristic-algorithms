package z2.SimulatedAnnealing.Neighborhood;

import z2.SimpleMatrix;
import z2.SubMatrix;

import java.util.ArrayList;

public class ChangeColorNeighborhood extends NeighborhoodBase {
    ArrayList<Integer> availableColors;
    public ChangeColorNeighborhood(ArrayList<Integer> colors){
        availableColors = colors;
    }

    /**
     * Funkcja generuje sasiednie rozwiazania na podstawie rozwiazania danego
     * Wygenerowane rozwiazania powstaja poprzez losowe zmienianie koloru danego bloku
     * @param point rozwiazanie, ktorego liczymy sasiedztwo
     * @param argument1 nieuzywane
     * @param argument2 nieuzywane
     * @return wygenerowane sasiedztwo
     */
    @Override
    public ArrayList<SimpleMatrix> getNeighborhood(SimpleMatrix point, double argument1, double argument2) {
        //return a list of SimpleMatrix in which ONE sub-matrix is divided into two in a random place (vert or horiz)
        //and one of them has a random color

        ArrayList<SimpleMatrix> neighborhood = new ArrayList<>();
        int K = point.K;

        ArrayList<SubMatrix> matrix = point.getMatrix();

        for (int i = 0; i < matrix.size(); i++) {
            ArrayList<SubMatrix> newMatrix = subMatrixDeepCopy(point.getMatrix());

            newMatrix.get(i).value = availableColors.get((int)(Math.random()*availableColors.size()));

            SimpleMatrix newSimpleMatrix = new SimpleMatrix(point.width, point.height, point.K);
            newSimpleMatrix.matrix = newMatrix;
            neighborhood.add(newSimpleMatrix);
        }
        return neighborhood;
    }

    private ArrayList<SubMatrix> subMatrixDeepCopy(ArrayList<SubMatrix> matrix){
        ArrayList<SubMatrix> copy = new ArrayList<>();

        for(int i=0; i<matrix.size(); i++){
            SubMatrix sm = new SubMatrix();
            sm.height = matrix.get(i).height;
            sm.width = matrix.get(i).width;
            sm.value = matrix.get(i).value;
            sm.posX = matrix.get(i).posX;
            sm.posY = matrix.get(i).posY;
            copy.add(sm);
        }
        return copy;
    }
}
