package z2.SimulatedAnnealing.Neighborhood;

import z2.SimpleMatrix;
import z2.SubMatrix;

import java.util.ArrayList;

public class DivisionMatrixNeighborhood extends NeighborhoodBase {
    ArrayList<Integer> availableColors = new ArrayList<>();

    public DivisionMatrixNeighborhood(ArrayList<Integer> availableColors){
        this.availableColors = availableColors;
    }

    /**
     * Funkcja generuje sasiednie rozwiazania na podstawie rozwiazania danego
     * Wygenerwane rozwiazania powstaja przez
     * dzielenie istniejacych blokow na dwie czesci i ustawianie jednemu z nich losowego koloru
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

            SubMatrix sub = newMatrix.get(i);
            newMatrix.remove(sub);

            boolean canVertically = sub.height / 2 > K;
            boolean canHorizontally = sub.width / 2 > K;

            boolean vertically;
            if(canVertically && canHorizontally){
                int splitChoice = (int)(Math.random()*100)%2;
                if(splitChoice == 0) vertically = true;
                else vertically = false;
                ArrayList<SubMatrix> newMatrices;

                if(vertically) newMatrices = divideVertically(sub, K);
                else newMatrices = divideHorizontally(sub, K);

                newMatrix.addAll(newMatrices);
                SimpleMatrix newSimpleMatrix = new SimpleMatrix(point.width, point.height, point.K);
                newSimpleMatrix.matrix = newMatrix;
                if(newSimpleMatrix.check()) neighborhood.add(newSimpleMatrix);
            }
            else{
                ArrayList<SubMatrix> newMatrices = new ArrayList<>();
                if(canVertically){
                    //can only split Vertically
                    newMatrices = divideVertically(sub, K);
                }
                else if(canHorizontally){
                    //can only split Horizontally
                    newMatrices = divideHorizontally(sub, K);
                }
                if(canHorizontally || canVertically){
                    newMatrix.addAll(newMatrices);

                    SimpleMatrix newSimpleMatrix = new SimpleMatrix(point.width, point.height, point.K);
                    newSimpleMatrix.matrix = newMatrix;
                    if(newSimpleMatrix.check()) neighborhood.add(newSimpleMatrix);
                }
            }
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

    private ArrayList<SubMatrix> divideHorizontally(SubMatrix sub, int K){
        int totalHeight = sub.height;
        int splitPlace = (int)(Math.random()*(sub.height-2*K))+K; // obcięcie brzegów wielkosci K z poszukiwań

        SubMatrix otherSub = new SubMatrix();
        otherSub.height = totalHeight - splitPlace;
        otherSub.width = sub.width;
        otherSub.posY = sub.posY + splitPlace;
        otherSub.posX = sub.posX;
        otherSub.value = availableColors.get((int)(Math.random()*availableColors.size()));

        sub.height = splitPlace;

        ArrayList<SubMatrix> dividedSubMatrix = new ArrayList<>();
        dividedSubMatrix.add(sub);
        dividedSubMatrix.add(otherSub);
        return dividedSubMatrix;
    }

    private ArrayList<SubMatrix> divideVertically(SubMatrix sub, int K){
        int totalWidth = sub.width;
        int splitPlace = (int)(Math.random()*(sub.width-2*K))+K; // obcięcie brzegów z poszukiwań

        SubMatrix otherSub = new SubMatrix();
        otherSub.width = totalWidth - splitPlace;
        otherSub.height = sub.height;
        otherSub.posX = sub.posX + splitPlace;
        otherSub.posY = sub.posY;
        otherSub.value = availableColors.get((int)(Math.random()*availableColors.size()));

        sub.width = splitPlace;
        ArrayList<SubMatrix> dividedSubMatrix = new ArrayList<>();
        dividedSubMatrix.add(sub);
        dividedSubMatrix.add(otherSub);
        return dividedSubMatrix;
    }
}
