import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

    private Service service;
    private ByteArrayOutputStream outputStreamCaptor;

    @BeforeEach
    void setUp() {
        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        service = new Service();
    }

    @Test
    void writeResultToFile() throws IOException {
        String phrase = "i love to work in global logic";
        List<Combination> combinationList = new ArrayList<>();
        Combination.requiredCharactersInPhrase = 15l;
        combinationList.add(createCombination("i", 1, 1));
        combinationList.add(createCombination("lo", 4, 2));
        combinationList.add(createCombination("o", 2, 1));
        combinationList.add(createCombination("o", 4, 1));
        combinationList.add(createCombination("i", 2, 1));
        combinationList.add(createCombination("log", 6, 4));
        combinationList.add(createCombination("logic", 5, 5));

        service.writeResultToFile(phrase, combinationList);

        String result = Files.readString(Path.of("result.txt"));

        assertEquals("{(i), 1} = 0.07 (1/15)\r\n" +
                "{(l, o), 4} = 0.13 (2/15)\r\n" +
                "{(o), 2} = 0.07 (1/15)\r\n" +
                "{(o), 4} = 0.07 (1/15)\r\n" +
                "{(i), 2} = 0.07 (1/15)\r\n" +
                "{(l, o, g), 6} = 0.27 (4/15)\r\n" +
                "{(l, o, g, i, c), 5} = 0.33 (5/15)\r\n" +
                "TOTAL Frequency: 0.63 (15/24)", result.trim());
    }

    @Test
    void printResult() {
        String phrase = "i love to work in global logic";
        List<Combination> combinationList = new ArrayList<>();
        Combination.requiredCharactersInPhrase = 15l;
        combinationList.add(createCombination("i", 1, 1));
        combinationList.add(createCombination("lo", 4, 2));
        combinationList.add(createCombination("o", 2, 1));
        combinationList.add(createCombination("o", 4, 1));
        combinationList.add(createCombination("i", 2, 1));
        combinationList.add(createCombination("log", 6, 4));
        combinationList.add(createCombination("logic", 5, 5));

        service.printResult(phrase, combinationList);

        assertEquals("{(i), 1} = 0.07 (1/15)\r\n" +
                "{(l, o), 4} = 0.13 (2/15)\r\n" +
                "{(o), 2} = 0.07 (1/15)\r\n" +
                "{(o), 4} = 0.07 (1/15)\r\n" +
                "{(i), 2} = 0.07 (1/15)\r\n" +
                "{(l, o, g), 6} = 0.27 (4/15)\r\n" +
                "{(l, o, g, i, c), 5} = 0.33 (5/15)\r\n" +
                "TOTAL Frequency: 0.63 (15/24)", outputStreamCaptor.toString().trim());
    }

    @Test
    void findCombinations() {
        String phrase = "i love to work in global logic";
        String word = "logic";
        List<Combination> expexted = new ArrayList<>();
        Combination.requiredCharactersInPhrase = 15l;
        expexted.add(createCombination("i", 1, 1));
        expexted.add(createCombination("lo", 4, 2));
        expexted.add(createCombination("o", 2, 1));
        expexted.add(createCombination("o", 4, 1));
        expexted.add(createCombination("i", 2, 1));
        expexted.add(createCombination("log", 6, 4));
        expexted.add(createCombination("logic", 5, 5));

        List<Combination> result = service.findCombinations(phrase, word);

        assertEquals(expexted, result);
    }

    @Test
    void findCombinations_emptyResult() {
        String phrase = "i love to work in global logic";
        String word = "mdu";

        List<Combination> result = service.findCombinations(phrase, word);

        assertEquals(0, result.size());
    }

    @Test
    void findCombinations2() {
        String phrase = "i plate level";
        String word = "logic";
        List<Combination> expexted = new ArrayList<>();
        Combination.requiredCharactersInPhrase = 4l;
        expexted.add(createCombination("i", 1, 1));

        expexted.add(createCombination("l", 5, 3));
        List<Combination> result = service.findCombinations(phrase, word);

        assertEquals(expexted, result);
    }

    private Combination createCombination(String chars, int sizeOfTheWord, int charCount) {
        Combination combination = new Combination();
        chars.chars().forEach(c -> combination.addChar((char) c));
        combination.setSizeOfTheWord(sizeOfTheWord);
        combination.addToCharCount(charCount);
        combination.calculateFrequency();
        return combination;
    }

    @Test
    void removeSpecialChars() {
        String phrase = "I love to wor!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~k in global logic!";
        String expected = "I love to work in global logic";

        String result = service.removeSpecialChars(phrase);

        assertEquals(expected, result);
    }

    @Test
    void countRequiredCharacters() {
        String phrase = "i love to work in global logic";

        Long result = service.countRequiredCharacters(phrase, "logic");

        assertEquals(15, result);
    }

    @Test
    void readFile() throws IOException {
        String result = service.readFile("phrase.txt");

        assertEquals("i love to work in global logic!", result);
    }

    @Test
    void readFile_throwsException() throws IOException {
        assertThrows(IOException.class, () -> service.readFile("2phrase.txt"));
    }
}