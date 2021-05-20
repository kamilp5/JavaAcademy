import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class DataWriter {

    private StringHelper stringHelper;

    public DataWriter(StringHelper stringHelper) {
        this.stringHelper = stringHelper;
    }

    public void writeResultToFile(String phrase, List<Combination> combinationList) {
        Path fileName = Path.of("result.txt");
        List<String> resultList = combinationList.stream().map(this::getCombinationAsResultString).collect(Collectors.toList());
        resultList.add(getTotalFrequencyString(phrase));
        try {
            Files.write(fileName, resultList);
            System.out.println("\nresult saved in " + fileName.toString());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private String getTotalFrequencyString(String phrase) {
        int validChars = stringHelper.countValidCharacters(phrase);
        double totalFrequency = (double) App.requiredCharactersInPhrase / validChars;
        return "TOTAL Frequency: " + (Math.round(totalFrequency * 100.0) / 100.0) + " (" + App.requiredCharactersInPhrase + "/" + validChars + ")";
    }

    public void printResult(String phrase, List<Combination> combinationList) {
        combinationList.forEach(c -> System.out.println(getCombinationAsResultString(c)));
        System.out.println(getTotalFrequencyString(phrase));
    }

    private String getCombinationAsResultString(Combination combination) {
        return "{" +
                combination.getChars().toString().replace("[", "(").replace("]", ")") +
                ", " + combination.getSizeOfTheWord() + "}" +
                " = " + Math.round(combination.getFrequency() * 100.0) / 100.0 + " (" +
                combination.getCharCount() + "/" + App.requiredCharactersInPhrase + ")";
    }
}
