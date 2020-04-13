package z3.LocalSearch.Neighborhood;

import java.util.ArrayList;

public class SimplifyNeighborhood extends NeighborhoodBase {
    /**
     * Funkcja znajdująca sąsiednie rozwiązania do podanego poprzez usuwanie par (L,R) oraz (U,D)
     * @param path ścieżka względem której szukamy sąsiadów
     * @param argument1 not used
     * @param argument2 not used
     * @return zbiór sąsiednich rozwiązań
     */
    @Override
    public ArrayList<ArrayList<String>> getNeighborhood(ArrayList<String> path, int argument1, int argument2) {
        ArrayList<ArrayList<String>> paths = new ArrayList<>();
        int a = (int)(Math.random()*path.size());


        for(int i=1; i<path.size(); i++){
            ArrayList<String> newPath = (ArrayList<String>)(path.clone());

            if(newPath.contains("U") && newPath.contains("D")){
                newPath.remove("U");
                newPath.remove("D");
                paths.add(newPath);
            }
            newPath = (ArrayList<String>)(path.clone());
            if(newPath.contains("L") && newPath.contains("R")){
                newPath.remove("L");
                newPath.remove("R");
                paths.add(newPath);
            }
        }
        paths.add(path);
        return paths;

    }
}
