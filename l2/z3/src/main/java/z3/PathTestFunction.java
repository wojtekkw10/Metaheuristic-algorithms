package z3;

import java.util.ArrayList;

import static java.lang.Math.max;

public class PathTestFunction extends TestFunction{
    public ArrayList<ArrayList<Integer>> board;
    public int startingX;
    public int startingY;

    public PathTestFunction(ArrayList<ArrayList<Integer>> board, int startingX, int startingY){
        this.board = board;
        this.startingX = startingX;
        this.startingY = startingY;
    }

    /**
     * Liczy fitness danej ścieżki
     * @param path ścieżka, której fitness liczymy
     * @return fitness ścieżki
     */
    @Override
    public int compute(ArrayList<String> path){
        int errorDistance = board.size() * board.get(0).size();
        int currentX = startingX;
        int currentY = startingY;
        int numberOfSteps = 0;
        for (int i = 0; i < path.size(); i++) {
            if(path.get(i).equals("U")) currentY++;
            if(path.get(i).equals("D")) currentY--;
            if(path.get(i).equals("L")) currentX--;
            if(path.get(i).equals("R")) currentX++;
            numberOfSteps++;

            if(currentX < board.size() && currentY < board.get(0).size() && currentX >= 0 && currentY >= 0){
                if(board.get(currentX).get(currentY) == 8){
                    return numberOfSteps;
                }
            } else return errorDistance;



        }
        return max(errorDistance, path.size());
    }

    /**
     * Checks if agent hits a wall
     * @param path path to be checked
     * @return true when agent doesn't hit a wall
     */
    public boolean check(ArrayList<String> path){
        int currentX = startingX;
        int currentY = startingY;
        for (int i = 0; i < path.size(); i++) {
            if(path.get(i).equals("U")) currentY++;
            if(path.get(i).equals("D")) currentY--;
            if(path.get(i).equals("L")) currentX--;
            if(path.get(i).equals("R")) currentX++;

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

    public String printPath(ArrayList<String> path, boolean clean) {
        StringBuilder sb = new StringBuilder();
        if(!clean){
            if (check(path)) sb.append("WARNING: Path NOT congruent\n");
            else sb.append("Path CONGRUENT\n");
        }
        for (String s : path) {
            // Wynik jest obrócony o 90 stopni więc trzeba odwrócić
                if(s.equals("L")) sb.append("U");
                else if(s.equals("U")) sb.append("R");
                else if(s.equals("R")) sb.append("D");
                else if(s.equals("D")) sb.append("L");
        }
        return sb.toString();
    }
}