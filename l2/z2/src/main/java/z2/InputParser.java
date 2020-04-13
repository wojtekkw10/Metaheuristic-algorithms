package z2;

import java.util.ArrayList;
import java.util.Scanner;

public class InputParser {
    private int n;
    private int m;
    private int k;
    private int maxTime;

    /**
     * Funkcja pobiera dane ze standardowego wejścia i parsuje do odpowiednich struktur danych
     * @return wektor początkowy
     */
    public ArrayList<ArrayList<Integer>> getInput(){
        Scanner scanner = new Scanner(System.in);
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();

        maxTime = scanner.nextInt();
        n = scanner.nextInt();
        m = scanner.nextInt();
        k = scanner.nextInt();

        //Remove the /n at the end of the line
        scanner.nextLine();

        while(scanner.hasNextLine()){
            ArrayList<Integer> values = new ArrayList<>();
            String line = scanner.nextLine();
            String[] stringValues = line.split(" ");
            for(String s : stringValues){
                values.add(Integer.parseInt(""+Integer.parseInt(s)));
            }
            matrix.add(values);
        }
        scanner.close();

        return matrix;
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

    public int getK() {
        return k;
    }
}