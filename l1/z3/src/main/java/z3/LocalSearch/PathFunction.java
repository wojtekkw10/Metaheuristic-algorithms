package z3.LocalSearch;

import java.util.ArrayList;

public class PathFunction {
    private ArrayList<ArrayList<Integer>> graph;

    public PathFunction(ArrayList<ArrayList<Integer>> graph){
        this.graph = graph;
    }

    /**
     * Liczy fitness danej ścieżki
     * @param path ścieżka, której fitness liczymy
     * @return fitness ścieżki
     */
    public int compute(ArrayList<String> path){
        return path.size();
    }

    public ArrayList<ArrayList<Integer>> getGraph() {
        return graph;
    }
}
