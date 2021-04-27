package ro.localhost.Services;

import java.io.*;

public class csvReader {

    static private csvReader fileReader = null;

    private csvReader() {}

    public static csvReader getInstance(){

        if(fileReader == null)
            fileReader = new csvReader();
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
