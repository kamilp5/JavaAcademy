import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class DataReader {

    public String readFile(String fileName) throws IOException {
        return Files.readString(Path.of(fileName));
    }

    public String getWord() throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        System.out.println("what word combinations you want to find?");
        return reader.readLine();
    }

}
