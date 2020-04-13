package z2;

import java.util.ArrayList;
import java.util.Scanner;

public class InputParser {
    private int n;
    private int maxTime;

    /**
     * Funkcja pobiera dane ze standardowego wejścia i parsuje do odpowiednich struktur danych
     * @return macierz odległości pomiędzy miastami, wartości n oraz m można pobrać odpowiednimi funkcjami
     */
    public ArrayList<ArrayList<Integer>> getInput(){
        Scanner scanner = new Scanner(System.in);
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        maxTime = scanner.nextInt();
        n = scanner.nextInt();

        //Remove the /n at the end of the line
        scanner.nextLine();

        while(scanner.hasNextLine()){
            ArrayList<Integer> distances = new ArrayList<>();
            String line = scanner.nextLine();
            String[] numbers = line.split(" ");
            for(String i : numbers){
                distances.add(Integer.parseInt(i));
            }
            graph.add(distances);
        }
        scanner.close();

        return graph;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public int getN() {
        return n;
    }
}
