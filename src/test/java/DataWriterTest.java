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

class DataWriterTest {

    private DataWriter dataWriter;
    private ByteArrayOutputStream outputStreamCaptor;

    @BeforeEach
    void setUp() {
        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        dataWriter = new DataWriter(new StringHelper());
    }

    @Test
    void writeResultToFile() throws IOException {
        String phrase = "i love to work in global logic";
        List<Combination> combinationList = new ArrayList<>();
        App.requiredCharactersInPhrase = 15l;
        combinationList.add(createCombination("i", 1, 1));
        combinationList.add(createCombination("lo", 4, 2));
        combinationList.add(createCombination("o", 2, 1));
        combinationList.add(createCombination("o", 4, 1));
        combinationList.add(createCombination("i", 2, 1));
        combinationList.add(createCombination("log", 6, 4));
        combinationList.add(createCombination("logic", 5, 5));

        dataWriter.writeResultToFile(phrase, combinationList);

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
        App.requiredCharactersInPhrase = 15l;
        combinationList.add(createCombination("i", 1, 1));
        combinationList.add(createCombination("lo", 4, 2));
        combinationList.add(createCombination("o", 2, 1));
        combinationList.add(createCombination("o", 4, 1));
        combinationList.add(createCombination("i", 2, 1));
        combinationList.add(createCombination("log", 6, 4));
        combinationList.add(createCombination("logic", 5, 5));

        dataWriter.printResult(phrase, combinationList);

        assertEquals("{(i), 1} = 0.07 (1/15)\r\n" +
                "{(l, o), 4} = 0.13 (2/15)\r\n" +
                "{(o), 2} = 0.07 (1/15)\r\n" +
                "{(o), 4} = 0.07 (1/15)\r\n" +
                "{(i), 2} = 0.07 (1/15)\r\n" +
                "{(l, o, g), 6} = 0.27 (4/15)\r\n" +
                "{(l, o, g, i, c), 5} = 0.33 (5/15)\r\n" +
                "TOTAL Frequency: 0.63 (15/24)", outputStreamCaptor.toString().trim());
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