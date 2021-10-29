import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public class FlatFileToCsv {


    public static void main(String[] args) throws IOException {

        File input = new File("src/main/resources/flatfile.txt");
        File output = new File("src/main/outputs/outfile.csv");


        try {
            BufferedReader reader =
                    new BufferedReader(new FileReader(input));

            BufferedWriter writer = new BufferedWriter(new FileWriter(output, false));
            //read a line
            String line;
            String csvString = "Column1,Column2,Column3,Column4,Column5," +
                               "Column6,Column7,Column9,Column10,Column11, " +
                               "Column12,Column13,Column14,Column15,Column16," +
                               "Column17,Column18,Column19,Column20,Column21," +
                               "Column22,Column23,Column24,Column25,Column26,Column27";

            writer.write(csvString + "\n");
            while ((line = reader.readLine()) != null) {

                String recordType = line.substring(0, 1);

                if (recordType.equals("L")) {

                    csvString = " ";
                    System.out.println(line);

                    int[][] arr = {{0, 1}, {1, 2}, {2, 4}, {4, 8}, {8, 16},
                                   {16, 20},{20, 22}, {22, 37}, {37, 39}, {39, 41},
                                  {41, 43}, {43, 47}, {47, 51}, {51, 71}, {71, 83},
                                  {83, 95}, {95, 107}, {107, 127}, {127, 128},{128, 130},
                                  {130, 134}, {134, 142}, {142, 146}, {146, 147}, {147, 155},
                                  {155, 160}};

                    for (int i = 0; i < 26; i++) {
                        for (int j = 0; j < 2; j++) {

                        }
                        if (i <25) {
                            csvString = csvString + getFiled(line, arr[i][0], arr[i][1]) + ",";
                        }
                        else{
                            csvString = csvString + getFiled(line, arr[i][0], arr[i][1]);
                        }

                    }
                    System.out.println(csvString);
                    writer.write(csvString + "\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String getFiled(String line, int i, int j)
    {
        return line.substring(i, j) ;
    }


}

