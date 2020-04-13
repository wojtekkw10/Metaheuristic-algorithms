package z2.LocalSearch.Neighborhood;

import java.util.ArrayList;

public class SwapNeighborhood extends NeighborhoodBase {
    /**
     * Funkcja tworzy zbiór rozwiązań, gdzie dla każdego 0<=i<=n-1
     * rozwiązanie Mi powstaje poprzez zamianę miasta na miejscu 'i'
     * z losowo ustalonym miastem na miejscu 'a'
     * @param path - rozwiązanie z którego generujemy sąsiadów
     * @param argument1 not used
     * @param argument2 not used
     * @return zbiór powstałych rozwiązań
     */
    @Override
    public ArrayList<ArrayList<Integer>> getNeighborhood(ArrayList<Integer> path, int argument1, int argument2) {
        ArrayList<ArrayList<Integer>> paths = new ArrayList<>();
        int a = (int)(Math.random()*(path.size()-1))+1;

        //the first one is always 0
        for(int i=1; i<path.size(); i++){
            ArrayList<Integer> newPath = (ArrayList<Integer>)(path.clone());

            //swap path[i] and path[a]
            newPath.set(i, path.get(a));
            newPath.set(a, path.get(i));
            paths.add(newPath);
        }

        return paths;

    }
}
