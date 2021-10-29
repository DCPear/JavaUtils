import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * CSV operations
 *
 */
public class CsvInToArray {
    public static void main(String[] args) throws IOException {
        usingBufferedReader();
        usingScanner();
        usingScanner1();

    }

    private static void usingScanner1() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("src/main/resources/input.csv"));
        sc.useDelimiter(",");   //sets the delimiter pattern
        while (sc.hasNext())  //returns a boolean value
        {
            System.out.print(sc.next() + ',');  //find and returns the next complete token from this scanner
        }
        sc.close();
    }

    //Note with this approach, more sophisticated CSVs (e.g. quoting or including commas as values) will not be parsed as intended.
    private static void usingBufferedReader() throws IOException {
        List<List<String>> records = new ArrayList<>();
        List<List<String>> filtered = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/input.csv"));
        FileWriter csvWriter = new FileWriter("src/main/resources/output.csv");
        String COMMA_DELIMITER = "," ;

        {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                records.add(Arrays.asList(values));
                if (values[3].trim().equals("Female") ){
                    filtered.add(Arrays.asList(values));
                    for (String value : values) {
                        csvWriter.append(value);
                        csvWriter.append(", ");
                    }
                    csvWriter.append("\n");
                }
                System.out.println("First Name: " + values[3]);
            }
            System.out.println(records);
            System.out.println(filtered);
            csvWriter.flush();
            csvWriter.close();
        }
    }

    //Like before, with this approach more sophisticated CSVs will not be parsed as intended.
    private static void usingScanner() throws IOException{
        List<List<String>> records = new ArrayList<>();
        Path path = Paths.get("src/main/resources/output2.csv");
        try (Scanner scanner = new Scanner(new File("src/main/resources/input.csv"));) {
            while (scanner.hasNextLine()) {
                records.add(getRecordFromLine(scanner.nextLine()));
            }
            System.out.println(records);
            try(BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName("UTF-8"))){
                writer.write(records.toString());
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }
    }
    public static List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<String>();
        String COMMA_DELIMITER = "," ;
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(COMMA_DELIMITER);
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }
  //https://www.baeldung.com/opencsv to explore


}
