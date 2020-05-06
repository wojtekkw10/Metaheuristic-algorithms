package z2.GeneticAlgorithm.InitialPopulation;

import z2.GeneticAlgorithm.Genotype;
import z2.Letter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomPopulation implements InitialPopulation{
    private final int size;
    private final List<Letter> letters;


    public RandomPopulation(int size, List<Letter> letters) {
        this.size = size;
        this.letters = letters;
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

    /**
     * Funkcja zwraca losowa litere z listy liter
     * @param letters lista liter
     * @return wylosowana litera
     */
    private Letter getRandomLetter(List<Letter> letters){
        int pos = (int)(Math.random()*letters.size());
        return letters.get(pos);
    }

    /**
     * Funkcja tworzy losowe slowo z dostepnych liter
     * @return wygenerowane slowo
     */
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
            Letter letter = getRandomLetter(availableLetters);
            word.append(letter.getLetter());
            availableLetters.remove(letter);
        }

        return word.toString();
    }
}
