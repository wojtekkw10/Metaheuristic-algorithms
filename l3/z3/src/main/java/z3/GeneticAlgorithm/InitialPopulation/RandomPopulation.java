package z3.GeneticAlgorithm.InitialPopulation;

import z3.GeneticAlgorithm.Genotype;

import java.util.ArrayList;
import java.util.List;

public class RandomPopulation implements InitialPopulation{
    private final int size;
    private final List<Letter> letters;
    private final int maxWordLength;


    public RandomPopulation(int size, List<Letter> letters, int maxWordLength) {
        this.size = size;
        this.letters = letters;
        this.maxWordLength = maxWordLength;
    }

    /**
     * Funkcja generuje losowe ciagi znakow zbudowane z dostepnych liter
     * @return wygenerowane slowa
     */
    @Override
    public List<Genotype> getInitialPopulation() {
        List<Genotype> individuals = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            individuals.add(new Genotype(getRandomWord()));
        }

        return individuals;
    }

    private String getRandomLetter(List<Letter> letters){
        int pos = (int)(Math.random()*letters.size());
        return letters.get(pos).getLetter();
    }

    private String getRandomWord(){
        //create a word out of the available letters
        //letter can only be used once

        List<Letter> availableLetters = new ArrayList<>(letters.size());
        for (Letter letter : letters) {
            availableLetters.add(new Letter(letter.getLetter(), letter.getScore()));
        }

        int randLength = (int)(Math.random()*(availableLetters.size()-1)+1);

        StringBuilder word = new StringBuilder();
        for (int i = 0; i < randLength; i++) {
            String letter = getRandomLetter(availableLetters);
            word.append(letter);
            availableLetters.remove(letter);
        }

        return word.toString();
    }
}
