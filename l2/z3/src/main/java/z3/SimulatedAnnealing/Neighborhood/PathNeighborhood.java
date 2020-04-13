package z3.SimulatedAnnealing.Neighborhood;

import org.checkerframework.checker.units.qual.A;
import z3.PathTestFunction;
import z3.TestFunction;

import java.nio.file.Path;
import java.util.ArrayList;

public class PathNeighborhood extends NeighborhoodBase{
    private final PathTestFunction testFunction;
    public PathNeighborhood(PathTestFunction testFunction){
        this.testFunction = testFunction;

    }

    /**
     * Funkcja generuje sasiedztwo podanej sciezki
     * Do sasiedztwa zaliczamy:
     * - sciezki powstale przez dodanie jednego lub dwoch elementow w srodku sciezki
     * - sciezki powstale przez usuniecie jednego elementu
     * - sciezki powstale przez swapowanie losowych elementow
     * @param point sciezka, ktorej sasiedztwo liczymy
     * @param argument1 nieuzywane
     * @param argument2 nieuzywane
     * @return wygenerowane sasiedztwo sciezki
     */
    @Override
    public ArrayList<ArrayList<String>> getNeighborhood(ArrayList<String> point, double argument1, double argument2) {
        ArrayList<ArrayList<String>> neighborhood = new ArrayList<>();
        String[] moves = {"U", "D", "L", "R"};
        if(point.size() == 0){
            point.add(moves[(int)(Math.random()*4)]);
        }

        //kazde rozwiązanie powstaje przez wstawienie każdego ruchu w środku ścieżki
        for(int i = 0; i<point.size(); i++){
            for(String move : moves){
                ArrayList<String> newPath = arrayListDeepCopy(point);
                newPath.add(i, move);
                newPath = cleanUpPath(newPath);
                if(testFunction.check(newPath)) neighborhood.add(newPath);
            }
            for(String move : moves){
                ArrayList<String> newPath = arrayListDeepCopy(point);
                newPath.add(i, move);
                newPath.add(i, move);
                newPath = cleanUpPath(newPath);
                if(testFunction.check(newPath)) neighborhood.add(newPath);
            }
        }

        //kazde rozwiązanie powstaje przez usunięcie jednego elementu
        for(int i=0; i<point.size(); i++){
            ArrayList<String> newPath = arrayListDeepCopy(point);
            newPath.remove(i);
            newPath = cleanUpPath(newPath);
            if(testFunction.check(newPath)) neighborhood.add(newPath);
        }

        //kazde rozwiązanie powstaje przez swapowanie wszystkich z losowym
        int random = (int)(Math.random()*point.size());
        for(int i=0; i<point.size(); i++){
            ArrayList<String> newPath = arrayListDeepCopy(point);
            newPath.set(i, point.get(random));
            newPath.set(random, point.get(i));
            newPath = cleanUpPath(newPath);
            if(testFunction.check(newPath)) neighborhood.add(newPath);
        }

        //System.out.println(neighborhood.size());

        ArrayList<ArrayList<String>> newNeighborhood = new ArrayList<>();
        for (int i = 0; i < neighborhood.size(); i++) {
            newNeighborhood.add(shortenPath(neighborhood.get(i)));
        }

        return newNeighborhood;
    }

    private ArrayList<String> arrayListDeepCopy(ArrayList<String> list){
        ArrayList<String> copy = new ArrayList<>();
        for(String i : list) copy.add(i);
        return copy;
    }

    /**
     * funkcja usuwa ze sciezki sekwencje (U,D), (D,U), (L,R), (R,L)
     * @param path sciezka do porawienia
     * @return poprawiona sciezka
     */
    public ArrayList<String> cleanUpPath(ArrayList<String> path) {
        if (path.size() > 1) {
            for (int i = 0; i < path.size() - 2; i++) {
                if ((path.get(i).equals("L") && path.get(i + 1).equals("R")) || (path.get(i).equals("R") && path.get(i + 1).equals("L"))) {
                    path.remove(i + 1);
                    path.remove(i);
                } else if ((path.get(i).equals("U") && path.get(i + 1).equals("D")) || (path.get(i).equals("D") && path.get(i + 1).equals("U"))) {
                    path.remove(i + 1);
                    path.remove(i);
                }
            }
        }
        return path;
    }

    /**
     * funkcja skraca sciezke w przypadku gdy agent dochodzi do celu przez koncem sciezki
     * @param path sciezka po ktorej idzie agent
     * @return poprawiona sciezka
     */
    public ArrayList<String> shortenPath(ArrayList<String> path){
        int currentX = testFunction.startingX;
        int currentY = testFunction.startingY;
        ArrayList<ArrayList<Integer>> board = testFunction.board;
        for (int i = 0; i < path.size(); i++) {
            if(path.get(i).equals("U")) currentY++;
            if(path.get(i).equals("D")) currentY--;
            if(path.get(i).equals("L")) currentX--;
            if(path.get(i).equals("R")) currentX++;
            if(currentX < board.size() && currentY < board.get(0).size() && currentX >= 0 && currentY >= 0) {
                if (board.get(currentX).get(currentY) == 8) {
                    ArrayList<String> shortPath = new ArrayList<>();
                    for (int j = 0; j <= i; j++) {
                        shortPath.add(path.get(j));
                    }
                    return shortPath;
                }
            }
        }
        return path;
    }
}
