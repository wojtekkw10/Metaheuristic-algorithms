package z2.SimulatedAnnealing.AcceptanceProbability;

public class StandardAcceptanceProbability extends AcceptanceProbabilityBase {

    /**
     * Funkcja zwraca prawdopodobieństwo wybrania danego rozwiązania według wzoru z wykładu
     * @param temperature temperatura w danym kroku
     * @param deltaF roznica wartosci rozpatrywanego rozwiazania i rozwiazania poprzedniego
     * @return prawodpodobienstwo wybrania tego rozwiazania
     */
    @Override
    public double getProbability(double temperature, double deltaF) {
        if(deltaF<0) return 1;
        else return Math.exp(-deltaF/temperature);
    }
}
