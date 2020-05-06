package z3;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;

public class PathTestFunction extends TestFunction{
    public static ArrayList<ArrayList<Integer>> board;
    public static int startingX;
    public static int startingY;

    public PathTestFunction(ArrayList<ArrayList<Integer>> board, int startingX, int startingY){
        PathTestFunction.board = board;
        PathTestFunction.startingX = startingX;
        PathTestFunction.startingY = startingY;
    }

    /**
     * Liczy fitness danej ścieżki
     * @param path ścieżka, której fitness liczymy
     * @return fitness ścieżki
     */
    @Override
    public int compute(String path){
        int errorDistance = board.size() * board.get(0).size();
        List<String> arrPath = new ArrayList<>();
        for (int i = 0; i < path.length(); i++) {
            arrPath.add(path.substring(i, i+1));
        }

        if(!check(path)){
            return max(errorDistance, arrPath.size());
        }
        int currentX = startingX;
        int currentY = startingY;
        int numberOfSteps = 0;
        for (int i = 0; i < arrPath.size(); i++) {
            if(arrPath.get(i).equals("U")) currentY++;
            if(arrPath.get(i).equals("D")) currentY--;
            if(arrPath.get(i).equals("L")) currentX--;
            if(arrPath.get(i).equals("R")) currentX++;
            numberOfSteps++;

            if(currentX < board.size() && currentY < board.get(0).size() && currentX >= 0 && currentY >= 0){
                if(board.get(currentX).get(currentY) == 8){
                    return numberOfSteps;
                }
            } else return errorDistance;
        }
        return max(errorDistance, arrPath.size());
    }

    /**
     * Checks if agent hits a wall
     * @param path path to be checked
     * @return true when agent doesn't hit a wall
     */
    public boolean check(String path){
        List<String> arrPath = new ArrayList<>();
        for (int i = 0; i < path.length(); i++) {
            arrPath.add(path.substring(i, i+1));
        }

        int currentX = startingX;
        int currentY = startingY;
        for (int i = 0; i < arrPath.size(); i++) {
            if(arrPath.get(i).equals("U")) currentY++;
            if(arrPath.get(i).equals("D")) currentY--;
            if(arrPath.get(i).equals("L")) currentX--;
            if(arrPath.get(i).equals("R")) currentX++;

            if(board.get(currentX).get(currentY) == 1){
                return false;
            }
            if(board.get(currentX).get(currentY) == 8){
                return true;
            }
        }
        return true;
    }

    public ArrayList<ArrayList<Integer>> getGraph() {
        return board;
    }

    public String printPath(String path, boolean clean) {
        ArrayList<String> arrPath = new ArrayList<>();
        for (int i = 0; i < path.length(); i++) {
            arrPath.add(path.substring(i, i+1));
        }

        StringBuilder sb = new StringBuilder();
        if(!clean){
            if (check(path)) sb.append("WARNING: Path NOT congruent\n");
            else sb.append("Path CONGRUENT\n");
        }
        for (String s : arrPath) {
            // Wynik jest obrócony o 90 stopni więc trzeba odwrócić
                if(s.equals("L")) sb.append("U");
                else if(s.equals("U")) sb.append("R");
                else if(s.equals("R")) sb.append("D");
                else if(s.equals("D")) sb.append("L");
        }
        return sb.toString();
    }

    /**
     * jezeli sciezka dochodzi do cyfry 8, sciezka jest obcinana do tego wlasnie momentu
     * @param path podana sciezka
     * @return skrocona sciezka
     */
    public static String shortenPath(String path){
        ArrayList<String> arrPath = new ArrayList<>();
        for (int i = 0; i < path.length(); i++) {
            arrPath.add(path.substring(i, i+1));
        }

        int currentX = startingX;
        int currentY = startingY;
        for (int i = 0; i < arrPath.size(); i++) {
            if(arrPath.get(i).equals("U")) currentY++;
            if(arrPath.get(i).equals("D")) currentY--;
            if(arrPath.get(i).equals("L")) currentX--;
            if(arrPath.get(i).equals("R")) currentX++;
            if(currentX < board.size() && currentY < board.get(0).size() && currentX >= 0 && currentY >= 0) {
                if (board.get(currentX).get(currentY) == 8) {
                    ArrayList<String> shortPath = new ArrayList<>();
                    for (int j = 0; j <= i; j++) {
                        shortPath.add(arrPath.get(j));
                    }
                    String sPath = "";
                    for (int k = 0; k < shortPath.size(); k++) {
                        sPath += shortPath.get(k);
                    }
                    return sPath;
                }
            }
        }
        String sPath = "";
        for (int i = 0; i < arrPath.size(); i++) {
            sPath += arrPath.get(i);
        }
        return sPath;
    }
}