package fooddelivery.services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CsvWriter {

    private static CsvWriter fileWriter = null;

    private CsvWriter() {}

    public static CsvWriter getInstance(){

        if(fileWriter == null) {
            fileWriter = new CsvWriter();
        }
        return fileWriter;
    }

    public void writeToFile(String text, String FileName){

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FileName))) {
            bufferedWriter.write(text);
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
