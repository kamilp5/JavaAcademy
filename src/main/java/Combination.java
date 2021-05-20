import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Combination implements Comparable<Combination> {

    private List<Character> chars = new ArrayList<>();
    private int sizeOfTheWord;
    private int charCount;
    private double frequency;

    public Combination() {
    }

    public int getCharCount() {
        return charCount;
    }

    public List<Character> getChars() {
        return chars;
    }

    public void setSizeOfTheWord(int sizeOfTheWord) {
        this.sizeOfTheWord = sizeOfTheWord;
    }

    public int getSizeOfTheWord() {
        return sizeOfTheWord;
    }

    public double getFrequency() {
        return frequency;
    }

    public void addChar(char c) {
        chars.add(c);
    }

    public boolean containsChar(char c) {
        return chars.contains(c);
    }

    public void incrementCharCount() {
        charCount++;
    }

    public void addToCharCount(int i) {
        charCount += i;
    }

    public void calculateFrequency() {
        frequency = (double) charCount / App.requiredCharactersInPhrase;
    }

    @Override
    public String toString() {
        return "Combination{" +
                "chars=" + chars +
                ", sizeOfTheWord=" + sizeOfTheWord +
                ", charCount=" + charCount +
                ", frequency=" + frequency +
                '}';
    }

    @Override
    public int compareTo(Combination combination) {
        int result = Double.compare(frequency, combination.getFrequency());
        if (result == 0) {
            result = Character.compare(chars.get(0), combination.getChars().get(0));
        }
        return result;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Combination that = (Combination) o;
        return sizeOfTheWord == that.sizeOfTheWord && charCount == that.charCount && Double.compare(that.frequency, frequency) == 0 && Objects.equals(chars, that.chars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chars, sizeOfTheWord, charCount, frequency);
    }
}
