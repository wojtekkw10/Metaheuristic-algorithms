package z2.LocalSearch;

import java.util.ArrayList;

public class TSP {
    private ArrayList<ArrayList<Integer>> graph;

    public TSP(ArrayList<ArrayList<Integer>> graph){
        this.graph = graph;
    }

    /**
     * Funkcja liczy długość podanej ścieżki
     * @param path ścieżka
     * @return długość ścieżki
     */
    public int compute(ArrayList<Integer> path){
        int pathLength = 0;
        for(int i=0; i<path.size()-1; i++){
            int a = path.get(i);
            int b = path.get(i+1);
            pathLength += graph.get(a).get(b);
        }
        int theFirst = path.get(0);
        int theLast = path.get(path.size()-1);
        pathLength += graph.get(theLast).get(theFirst);

        return pathLength;
    }

    public ArrayList<ArrayList<Integer>> getGraph() {
        return graph;
    }
}
