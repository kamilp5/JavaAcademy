import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CombinationFinderTest {

    private CombinationFinder combinationFinder;

    @BeforeEach
    void setUp() {
        combinationFinder = new CombinationFinder();
    }

    @Test
    void findCombinations() {
        String phrase = "i love to work in global logic";
        String word = "logic";
        List<Combination> expected = new ArrayList<>();
        App.requiredCharactersInPhrase = 15l;
        expected.add(createCombination("i", 1, 1));
        expected.add(createCombination("lo", 4, 2));
        expected.add(createCombination("o", 2, 1));
        expected.add(createCombination("o", 4, 1));
        expected.add(createCombination("i", 2, 1));
        expected.add(createCombination("log", 6, 4));
        expected.add(createCombination("logic", 5, 5));

        List<Combination> result = combinationFinder.findCombinations(phrase, word);

        assertEquals(expected, result);
    }

    @Test
    void findCombinations_emptyResult() {
        String phrase = "i love to work in global logic";
        String word = "mdu";

        List<Combination> result = combinationFinder.findCombinations(phrase, word);

        assertEquals(0, result.size());
    }

    @Test
    void findCombinations2() {
        String phrase = "i plate level";
        String word = "logic";
        List<Combination> expexted = new ArrayList<>();
        App.requiredCharactersInPhrase = 4l;
        expexted.add(createCombination("i", 1, 1));

        expexted.add(createCombination("l", 5, 3));
        List<Combination> result = combinationFinder.findCombinations(phrase, word);

        assertEquals(expexted, result);
    }

    @Test
    void findCombinations3() {
        String phrase = "i plates level";
        String word = "logic";
        List<Combination> expected = new ArrayList<>();
        App.requiredCharactersInPhrase = 4l;
        expected.add(createCombination("i", 1, 1));
        expected.add(createCombination("l", 6, 1));

        expected.add(createCombination("l", 5, 2));
        List<Combination> result = combinationFinder.findCombinations(phrase, word);

        assertEquals(expected, result);
    }

    @Test
    void findCombinations4() {
        String phrase = "like love level";
        String word = "lev";
        List<Combination> expected = new ArrayList<>();
        App.requiredCharactersInPhrase = 10l;
        expected.add(createCombination("le", 4, 2));

        expected.add(createCombination("lev", 4, 3));
        expected.add(createCombination("lev", 5, 5));
        List<Combination> result = combinationFinder.findCombinations(phrase, word);

        assertEquals(expected, result);
    }

    private Combination createCombination(String chars, int sizeOfTheWord, int charCount) {
        Combination combination = new Combination();
        chars.chars().forEach(c -> combination.addChar((char) c));
        combination.setSizeOfTheWord(sizeOfTheWord);
        combination.addToCharCount(charCount);
        combination.calculateFrequency();
        return combination;
    }
}