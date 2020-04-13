package l1z1.LocalSearch.Neighborhood;

import java.util.ArrayList;

public class CircleNeighborhood extends NeighborhoodBase {

    /**
     * Funkcja zwraca zbiór rozwiązań, gdzie każde rozwiązanie różni się na jednym miejscu od podanego o +/- step
     * @param point rozwiązanie wokół którego liczymy sąsiednie rozwiązania
     * @param step odległość na jednej osi w której szukamy sąsiadów
     * @param arg2 not used
     * @return zbiór sąsiednich rozwiązań
     */
    @Override
    public ArrayList<ArrayList<Double>> getNeighborhood(ArrayList<Double> point, double step, double arg2) {
        ArrayList<ArrayList<Double>> circle = new ArrayList<>();

        for (int i = 0; i < point.size(); i++) {
            ArrayList<Double> newPoint = (ArrayList<Double>) point.clone();
            newPoint.set(i, point.get(i) + step);
            circle.add(newPoint);
            newPoint = (ArrayList<Double>) point.clone();
            newPoint.set(i, point.get(i) - step);
            circle.add(newPoint);
        }

        return circle;
    }
}
