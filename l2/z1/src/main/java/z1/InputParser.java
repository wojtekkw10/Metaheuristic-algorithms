package z1;

import java.util.ArrayList;
import java.util.Scanner;

public class InputParser {
    private int n;
    private int maxTime;

    /**
     * Funkcja pobiera dane ze standardowego wejścia i parsuje do odpowiednich struktur danych
     * @return wektor początkowy
     */
    public ArrayList<Double> getInput(){
        Scanner scanner = new Scanner(System.in);
        ArrayList<Double> vec = new ArrayList<>();

        maxTime = scanner.nextInt();
        vec.add((double) scanner.nextInt());
        vec.add((double) scanner.nextInt());
        vec.add((double) scanner.nextInt());
        vec.add((double) scanner.nextInt());

        scanner.close();

        return vec;
    }

    public int getMaxTime() {
        return maxTime;
    }
}