package z2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputParser {
    private int maxTime;
    private int lettersSetSize;
    private int wordsSetSize;
    private List<Letter> letters = new ArrayList<>();
    private List<String> words = new ArrayList<>();

    /**
     * Funkcja pobiera dane ze standardowego wejścia i parsuje do odpowiednich struktur danych
     * @return wektor początkowy
     */
    public void getInput(){
        Scanner scanner = new Scanner(System.in);

        maxTime = scanner.nextInt();
        lettersSetSize = scanner.nextInt();
        wordsSetSize = scanner.nextInt();

        //to remove the \n
        scanner.nextLine();

        for (int i = 0; i < lettersSetSize; i++) {
            String line = scanner.nextLine();
            String[] words = line.split(" ");
            String letter = words[0];
            int score = Integer.parseInt(words[1]);
            letters.add(new Letter(letter, score));
        }

        for (int i = 0; i < wordsSetSize; i++) {
            String word = scanner.nextLine();
            words.add(word);
        }

        scanner.close();
    }

    public int getMaxTime() {
        return maxTime;
    }
    public List<Letter> getLetters() {
        return letters;
    }
    public List<String> getWords() {
        return words;
    }
}