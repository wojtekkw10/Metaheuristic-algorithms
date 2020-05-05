package z3.GeneticAlgorithm;

public class Genotype {
    private final String path;
    private double fitness;

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public Genotype(String path) {
        this.path = path;
    }

    public String getValue() {
        return path;
    }

    @Override
    public String toString() {
        return "Gen{" +
                path +
                ", fts:" + fitness +
                '}';
    }
}
