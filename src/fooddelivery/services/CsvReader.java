package fooddelivery.services;

import java.io.*;

public class CsvReader {

    static private CsvReader fileReader = null;

    private CsvReader() {}

    public static CsvReader getInstance(){

        if(fileReader == null) {
            fileReader = new CsvReader();
        }
        return fileReader;
    }

    public String readFile(String FileName) {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FileName))) {

            StringBuilder content = new StringBuilder();

            String line = bufferedReader.readLine();

            while(line != null) {

                content.append(line).append("\n");
                line = bufferedReader.readLine();
            }

            return content.toString();

        } catch(IOException e){

            e.printStackTrace();
        }

        return "";
    }
}
