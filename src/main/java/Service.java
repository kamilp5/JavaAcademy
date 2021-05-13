import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Service {

    public void writeResultToFile(String phrase, List<Combination> combinationList) {
        Path fileName = Path.of("result.txt");
        List<String> resultList = combinationList.stream().map(Combination::toString).collect(Collectors.toList());
        resultList.add(getTotalFrequencyString(phrase));
        try {
            Files.write(fileName, resultList);
            System.out.println("\nresult saved in " + fileName.toString());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public String readFile(String fileName) throws IOException {
        return Files.readString(Path.of(fileName)).toLowerCase();
    }

    public String getWord() throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        System.out.println("what word combinations you want to find?");
        return reader.readLine().toLowerCase();
    }

    public void printResult(String phrase, List<Combination> combinationList) {
        combinationList.forEach(System.out::println);
        System.out.println(getTotalFrequencyString(phrase));
    }

    private String getTotalFrequencyString(String phrase) {
        int validChars = countValidCharacters(phrase);
        double totalFrequency = (double) Combination.requiredCharactersInPhrase / validChars;
        return "TOTAL Frequency: " + (Math.round(totalFrequency * 100.0) / 100.0) + " (" + Combination.requiredCharactersInPhrase + "/" + validChars + ")";
    }

    public List<Combination> findCombinations(String phrase, String word) {
        String[] words = phrase.split(" ");
        char[] chars = word.toCharArray();
        List<Combination> combinationList = new ArrayList<>();
        for (String w : words) {
            Combination combination = new Combination();
            combination.setSizeOfTheWord(w.length());
            for (char c : chars) {
                for (char charOfWord : w.toCharArray()) {
                    if (charOfWord == c) {
                        if (!combination.containsChar(c)) {
                            combination.addChar(c);
                        }
                        combination.incrementCharCount();
                    }
                }
            }
            addToResult(combinationList, combination);
        }
        return combinationList;
    }

    private void addToResult(List<Combination> combinationList, Combination combination) {
        if (combination.getChars().size() > 0) {
            Optional<Combination> found = combinationList.stream()
                    .filter(c -> (c.getChars().equals(combination.getChars()) && c.getSizeOfTheWord() == combination.getSizeOfTheWord()))
                    .findFirst();
            if (found.isPresent()) {
                found.get().addToCharCount(combination.getCharCount());
                found.get().calculateFrequency();
            } else {
                combination.calculateFrequency();
                combinationList.add(combination);
            }
        }
    }

    public String removeSpecialChars(String phrase) {
        StringBuilder sb = new StringBuilder();
        phrase.chars().filter(c -> !((c >= 33 && c <= 47) || (c >= 58 && c <= 64) || (c >= 91 && c <= 96) || (c >= 123 && c <= 126))).forEach(c -> sb.append((char) c));
        return sb.toString();
    }

    private int countValidCharacters(String phrase) {
        return phrase.replace(" ", "").length();
    }

    public long countRequiredCharacters(String phrase, String word) {
        return phrase.chars().filter(c -> word.contains(String.valueOf((char) c))).count();
    }
}
