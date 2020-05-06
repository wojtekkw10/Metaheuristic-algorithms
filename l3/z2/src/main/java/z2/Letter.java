package z2;

public class Letter {
    private final String letter;
    private final int score;

    public Letter(String letter, int score) {
        this.letter = letter;
        this.score = score;
    }

    public String getLetter() {
        return letter;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "{" +
                letter +
                ", " +score +
                '}';
    }
}
