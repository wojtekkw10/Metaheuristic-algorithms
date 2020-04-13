package z2.LocalSearch.FirstSolution;

import java.util.ArrayList;
import java.util.Collections;

public class RandomSolution extends FirstSolutionBase {

    /**
     * Funkcja tworzy losowe rozwiązanie problemu TSP
     * @param n ilość miast
     * @param graph macierz odległości pomiędzy miastami
     * @return wygenerowana ścieżka
     */
    @Override
    public ArrayList<Integer> getFirstSolution(int n, ArrayList<ArrayList<Integer>> graph) {
        FirstSolutionBase fixedSolution = new FixedSolution();
        ArrayList<Integer> path = fixedSolution.getFirstSolution(n, null);
        Collections.shuffle(path);
        //swap zero with the first element
        int index = path.indexOf(0);
        path.set(index, path.get(0));
        path.set(0, 0);
        return path;
    }
}
