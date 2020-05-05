package z3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputParser {
    private int n;
    private int m;
    private int maxTime;

    public List<String> getInitialPaths() {
        return initialPaths;
    }

    private List<String> initialPaths = new ArrayList<>();

    public int getPopulationSizeLimit() {
        return populationSizeLimit;
    }

    private int populationSizeLimit;

    public int agentX;
    public int agentY;

    /**
     * Funkcja pobiera dane ze standardowego wejścia i parsuje do odpowiednich struktur danych
     * @return mapa po której będzie poruszał się agent, wartości n oraz m można pobrać odpowiednimi funkcjami
     */
    public ArrayList<ArrayList<Integer>> getInput(){
        Scanner scanner = new Scanner(System.in);
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        maxTime = scanner.nextInt();
        n = scanner.nextInt();
        m = scanner.nextInt();
        int numberOfInitialPaths = scanner.nextInt();
        populationSizeLimit = scanner.nextInt();

        //Remove the /n at the end of the line
        scanner.nextLine();

        int lineCounter = 0;
        while(lineCounter < n){
            ArrayList<Integer> distances = new ArrayList<>();
            String line = scanner.nextLine();
            for(int i=0; i<line.length(); i++){
                distances.add(Integer.parseInt(""+line.charAt(i)));
            }
            graph.add(distances);
            lineCounter++;
        }

        lineCounter = 0;
        while(lineCounter < numberOfInitialPaths){
            String line = scanner.nextLine();
            initialPaths.add(line);
            lineCounter++;
        }
        scanner.close();

        //Find agent
        for(int i=0; i<graph.size(); i++){
            for(int j=0; j<graph.get(i).size(); j++){
                if(graph.get(i).get(j) == 5){
                    agentX = i;
                    agentY = j;
                }
            }
        }

        return graph;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }
}