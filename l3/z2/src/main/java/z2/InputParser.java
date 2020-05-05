package z2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputParser {
    private int n;
    private int maxTime;
    private List<Double> factors = new ArrayList<>();

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
        vec.add((double) scanner.nextInt());

        factors.add(scanner.nextDouble());
        factors.add(scanner.nextDouble());
        factors.add(scanner.nextDouble());
        factors.add(scanner.nextDouble());
        factors.add(scanner.nextDouble());

        scanner.close();

        return vec;
    }

    public int getMaxTime() {
        return maxTime;
    }
    public List<Double> getFactors(){
        return factors;
    }
}