import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class replaceFirstLine {

    public static void main(String[] args) throws Exception {

        extracted();
    }

    private static void extracted() throws IOException {
        int lineNumber = 1;
        String data = "first_name, last_name, email, gender, address";
        Path path = Paths.get("src/main/resources/firstLine.csv");

        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        lines.set(lineNumber - 1, data);

        Files.write(path, lines, StandardCharsets.UTF_8);
    }

}
