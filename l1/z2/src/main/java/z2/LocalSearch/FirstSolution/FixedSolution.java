package z2.LocalSearch.FirstSolution;

import java.util.ArrayList;

public class FixedSolution extends FirstSolutionBase {
    /**
     * Funkcja tworzy ścieżkę (0,1,2,3,...,n-1)
     * @param n ilość miast
     * @param graph not used
     * @return wygenerowana ścieżka
     */
    @Override
    public ArrayList<Integer> getFirstSolution(int n, ArrayList<ArrayList<Integer>> graph) {
        ArrayList<Integer> path = new ArrayList<>();
        for(int i=0; i<n; i++){
            path.add(i);
        }
        return path;
    }
}
