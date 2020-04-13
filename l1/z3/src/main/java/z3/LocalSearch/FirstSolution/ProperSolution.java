package z3.LocalSearch.FirstSolution;

import java.util.ArrayList;

public class ProperSolution extends FirstSolutionBase {
    /**
     * Funkcja znajduje pierwsze poprawne rozwiązanie
     * Tzn. Agent najpierw idzie w prawo, a następnie wzdłuż ściany
     * @param board mapa po której porusza się agent
     * @return wygenerowana ścieżka
     */
    @Override
    public ArrayList<String> getFirstSolution(ArrayList<ArrayList<Integer>> board) {
        ArrayList<Integer> agent = findAgent(board);
        int x = agent.get(0);
        int y = agent.get(1);
        ArrayList<String> path = new ArrayList<>();

        boolean reached = false;
        while(!reached){
            //corners
            if(board.get(x+1).get(y) == 1 && board.get(x).get(y+1) == 1) {
                y--;
                path.add("D");
            }
            else if(board.get(x+1).get(y) == 1 && board.get(x).get(y-1) == 1) {
                x--;
                path.add("L");
            }
            else if(board.get(x-1).get(y) == 1 && board.get(x).get(y-1) == 1) {
                y++;
                path.add("U");
            }
            else if(board.get(x-1).get(y) == 1 && board.get(x).get(y+1) == 1) {
                x++;
                path.add("R");
            }

            //sides
            else if(board.get(x+1).get(y) == 1) {
                y--;
                path.add("D");
            }
            else if(board.get(x-1).get(y) == 1) {
                y++;
                path.add("U");
            }
            else if(board.get(x).get(y+1) == 1) {
                x++;
                path.add("R");
            }
            else if(board.get(x).get(y-1) == 1) {
                x--;
                path.add("L");
            }
            else{
                //center
                y++;
                path.add("U");
            }

            //reached
            if(board.get(x+1).get(y) == 8) {
                x++;
                reached = true;
                path.add("R");
            }
            else if(board.get(x-1).get(y) == 8) {
                x--;
                reached = true;
                path.add("L");
            }
            else if(board.get(x).get(y+1) == 8) {
                y++;
                reached = true;
                path.add("U");
            }
            else if(board.get(x).get(y-1) == 8) {
                y--;
                reached = true;
                path.add("D");
            }

            //System.out.println(x+" "+y);
        }

        return path;

    }


    /**
     * Funkcja znajdująca położenie agenta
     * Potrzebna przy obliczaniu pól sąsiednich agenta
     * @param board mapa po której porusza się agent
     * @return położenie agenta - para (x,y)
     */
    ArrayList<Integer> findAgent(ArrayList<ArrayList<Integer>> board){
        int agentSymbol = 5;
        for(int i=0; i<board.size(); i++){
            for(int j=0; j<board.get(i).size(); j++){
                if(board.get(i).get(j)==agentSymbol){
                    ArrayList<Integer> agent = new ArrayList<>();
                    agent.add(i);
                    agent.add(j);
                    return agent;
                }
            }
        }
        return null;
    }
}
