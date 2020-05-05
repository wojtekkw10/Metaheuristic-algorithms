package z3.GeneticAlgorithm;

public class Genotype {
    private final String word;
    private double fitness;

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public Genotype(String word) {
        this.word = word;
    }

    public String getValue() {
        return word;
    }

    @Override
    public String toString() {
        return "Gen{" +
                word +
                ", fts:" + fitness +
                '}';
    }
}
