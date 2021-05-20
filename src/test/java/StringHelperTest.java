import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringHelperTest {

    private StringHelper stringHelper;

    @BeforeEach
    void setUp() {
        stringHelper = new StringHelper();
    }


    @Test
    void countRequiredCharacters() {
        String phrase = "i love to work in global logic";

        Long result = stringHelper.countRequiredCharacters(phrase, "logic");

        assertEquals(15, result);
    }

    @Test
    void wordSetup() {
        String word = "Logicc!";
        String expected = "logic";

        String result = stringHelper.wordSetup(word);

        assertEquals(expected, result);
    }

    @Test
    void phraseSetUp() {
        String phrase = "I love to wor!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~k in global logic!";
        String expected = "i love to work in global logic";

        String result = stringHelper.phraseSetUp(phrase);

        assertEquals(expected, result);
    }

    @Test
    void countValidCharacters() {
        String phrase = "i love to work in global logic";

        int result = stringHelper.countValidCharacters(phrase);

        assertEquals(24, result);
    }
}