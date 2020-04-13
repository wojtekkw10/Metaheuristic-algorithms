package z2.LocalSearch.FirstSolution;

import java.util.ArrayList;

public class GreedySolution extends FirstSolutionBase {

    /**
     * Funkcja tworzy ścieżkę po grafie graph zaczynając w mieście 0 oraz
     * stosując algorytm zachłanny
     * @param n ilość mieast
     * @param graph macierz odległości pomiędzy miastami
     * @return wygenerowana ścieżka
     */
    @Override
    public ArrayList<Integer> getFirstSolution(int n, ArrayList<ArrayList<Integer>> graph) {
        ArrayList<Integer> path = new ArrayList<>();
        path.add(0);

        int currentCity = 0;
        for(int i=1; i<n; i++){
            int shortestDistance = Integer.MAX_VALUE;
            int closestCity = 0;
            for(int j=0; j<n; j++){
                if(graph.get(currentCity).get(j) < shortestDistance
                        && currentCity != j
                        && !path.contains(j)){
                    shortestDistance = graph.get(currentCity).get(j);
                    closestCity = j;
                }

            }
            path.add(closestCity);
            currentCity = closestCity;
        }
        return path;
    }
}
