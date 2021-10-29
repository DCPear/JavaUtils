import java.io.BufferedReader;
import java.io.File;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class CsvToJson {

    public static void main(String[] args) throws Exception {

        File input = new File("src/main/resources/input.csv");
        File output = new File("src/main/resources/output.json");

        CsvSchema csvSchema = CsvSchema.builder().setUseHeader(true).build();
        CsvMapper csvMapper = new CsvMapper();

        // Read data from CSV file
        List<Object> readAll = csvMapper.readerFor(Map.class).with(csvSchema).readValues(input).readAll();

        ObjectMapper mapper = new ObjectMapper();

        // Write JSON formatted data to output.json file
        mapper.writerWithDefaultPrettyPrinter().writeValue(output, readAll);

        //Write JSON formatted data to console
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(readAll));
    }


    }


