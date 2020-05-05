package z2;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class WordScoreFunction extends TestFunction {
    List<String> words = new ArrayList<>();
    List<Letter> letters;

    public WordScoreFunction(List<String> words, List<Letter> letters) {
        //lowercase all words
        for (String word : words) {
            this.words.add(word.toLowerCase(Locale.US));
        }
        this.words = words;
        this.letters = letters;
    }

    @Override
    public double compute(String word) {
        if(isInDict(word)){
            if(scoreWord(word) != -1) return scoreWord(word);
            else return 0.001;
        }
        return 0.001;
    }

    public boolean isInDict(String word){
        int startIndex = 0;
        int endIndex = words.size();

        while(endIndex - startIndex > 5 ){
            int middleIndex = (startIndex+endIndex)/2;
            if(startIndex == middleIndex) middleIndex++;
            if(words.get(middleIndex).equals(word)) return true;
            else if(words.get(middleIndex).compareTo(word) < 0){
                startIndex = middleIndex;
            }
            else{
                endIndex = middleIndex;
            }
        }
        for (int i = startIndex; i < endIndex; i++) {
            if(words.get(i).equals(word)) return true;
        }
        return false;
    }

    private double scoreWord(String word){
        //check for used letters
        List<String> availableLetters = new ArrayList<>(letters.size());
        for (Letter letter : letters) {
            availableLetters.add(letter.getLetter());
        }
        for (int i = 0; i < word.length(); i++) {
            if(availableLetters.contains(word.substring(i, i+1))){
                availableLetters.remove(word.substring(i, i+1));
            }
            else{
                return -1;
            }
        }
        char[] wordLetters = new char[word.length()];
        word.getChars(0, word.length(), wordLetters, 0);

        int totalScore = 0;
        for (int i = 0; i < word.length(); i++) {
            int score = getLetterScore(wordLetters[i]);
            if(score == -1) return -1;
            else{
                totalScore += score;
            }
        }
        return totalScore;
    }

    private int getLetterScore(char letter){
        for (int i = 0; i < letters.size(); i++) {
            if(letters.get(i).getLetter().charAt(0) == letter) return letters.get(i).getScore();
        }
        return -1;
    }
}
