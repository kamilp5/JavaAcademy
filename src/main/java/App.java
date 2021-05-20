import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class App {
    public static Long requiredCharactersInPhrase;

    public static void main(String[] args) throws IOException {
        StringHelper stringHelper = new StringHelper();
        CombinationFinder combinationFinder = new CombinationFinder();
        DataReader dataReader = new DataReader();
        DataWriter dataWriter = new DataWriter(stringHelper);

        String phrase = dataReader.readFile("phrase.txt");
        String word = dataReader.getWord();
//        String word = "Logic!";
//        String phrase = "I love to work in global logic!";

        word = stringHelper.wordSetup(word);
        phrase = stringHelper.phraseSetUp(phrase);

        requiredCharactersInPhrase = stringHelper.countRequiredCharacters(phrase, word);

        List<Combination> combinationList = combinationFinder.findCombinations(phrase, word);

        Collections.sort(combinationList);
        dataWriter.printResult(phrase, combinationList);
        dataWriter.writeResultToFile(phrase, combinationList);
    }
}
