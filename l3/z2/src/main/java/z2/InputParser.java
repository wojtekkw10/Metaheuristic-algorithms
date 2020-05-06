package z2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputParser {
    private int maxTime;
    private final List<Letter> letters = new ArrayList<>();
    private final List<String> words = new ArrayList<>();

    /**
     * Funkcja pobiera dane ze standardowego wejścia i parsuje do odpowiednich struktur danych
     */
    public void getInput(){
        Scanner scanner = new Scanner(System.in);

        maxTime = scanner.nextInt();
        int lettersSetSize = scanner.nextInt();
        int wordsSetSize = scanner.nextInt();

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

    public List<String> loadDict(){
        List<String> words = new ArrayList<>();
        File file = new File("dict.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
            while (sc.hasNextLine()) {
                words.add(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("FILE dict.txt NOT FOUND");
        }
        return words;
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