package ro.localhost.Services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class csvWriter {

    private static csvWriter fileWriter = null;

    private csvWriter() {}

    public static csvWriter getInstance(){

        if(fileWriter == null)
            fileWriter = new csvWriter();
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
