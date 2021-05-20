import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CombinationFinder {

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

}
