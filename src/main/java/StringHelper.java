public class StringHelper {

    public String wordSetup(String word) {
        word = word.toLowerCase();
        word = removeSpecialChars(word);
        word = removeDuplicateChars(word);
        word = word.replace(" ", "");
        return word;
    }

    public String phraseSetUp(String phrase) {
        phrase = phrase.toLowerCase();
        return removeSpecialChars(phrase);
    }

    public int countValidCharacters(String phrase) {
        return phrase.replace(" ", "").length();
    }

    public long countRequiredCharacters(String phrase, String word) {
        return phrase.chars().filter(c -> word.contains(String.valueOf((char) c))).count();
    }

    private String removeSpecialChars(String phrase) {
        StringBuilder sb = new StringBuilder();
        phrase.chars().filter(c -> !((c >= 33 && c <= 47) || (c >= 58 && c <= 64) || (c >= 91 && c <= 96) || (c >= 123 && c <= 126))).forEach(c -> sb.append((char) c));
        return sb.toString();
    }

    private String removeDuplicateChars(String word) {
        StringBuilder sb = new StringBuilder();
        for (Character c : word.toCharArray()) {
            if (sb.indexOf(c.toString()) < 0) {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
