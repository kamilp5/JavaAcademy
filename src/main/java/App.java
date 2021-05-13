import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class App {

    public static void main(String[] args) throws IOException {
        Service service = new Service();
        String phrase = service.readFile("phrase.txt");
        String word = service.getWord();

//        String word = "Logic".toLowerCase();
//        String phrase = "I love to work in global logic!".toLowerCase();

        phrase = service.removeSpecialChars(phrase);
        Combination.requiredCharactersInPhrase = service.countRequiredCharacters(phrase, word);

        List<Combination> combinationList = service.findCombinations(phrase, word);

        Collections.sort(combinationList);
        service.printResult(phrase, combinationList);
        service.writeResultToFile(phrase, combinationList);
    }
}
